
package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.entity.properties.UserProperties;
import com.m2i.flexiflex.service.HibernateSession;
import com.m2i.flexiflex.service.TokenGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.TransactionRequiredException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.m2i.flexiflex.controller.factories.UserFactory.deleteTestUser;
import static com.m2i.flexiflex.controller.factories.UserFactory.makeTestUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmailValidationController.class)
public class EmailValidationControllerTest {

    @Autowired
    private MockMvc mvc;
    private Session hbsession = HibernateSession.getSession();
    private String testUserMail = "user@mail.com";
    private String testUserPassword = "secret";

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void emailValidationRequestParamAbsent() throws Exception {
        String url = "/email_validation";
        this.mvc.perform(get(url)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void emailValidationBadUuid() throws Exception {
        deleteTestUser();
        UserEntity userEntity = makeTestUser();
        String url = "/email_validation?key1=" + "nimportequoi" + "&key2=" + "nimportequoi";
        this.mvc.perform(get(url)).andExpect(status().isBadRequest()).andDo(print());
        deleteTestUser();
    }

    @Test
    public void emailValidationBadValidationToken() throws Exception {
        deleteTestUser();
        UserEntity userEntity = makeTestUser();
        String url = "/email_validation?key1=" + userEntity.getUuid() + "&key2=" + "nimportequoi";
        this.mvc.perform(get(url)).andExpect(status().isBadRequest()).andDo(print());
        deleteTestUser();
    }

    @Test
    public void emailValidationGoodParameters() throws Exception {
          deleteTestUser();
        UserEntity userEntity = makeTestUser();
        String url = "/email_validation?key1=" + userEntity.getUuid() + "&key2=" + userEntity.getValidationToken();
        this.mvc.perform(get(url)).andExpect(status().isAccepted()).andDo(print());
        deleteTestUser();
    }
}
