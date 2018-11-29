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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    private String testUserMail = "user@mail.com";
    private String testUserPassword = "secret";

    private Session hbsession = HibernateSession.getSession();

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));



    @Test
    public void userCanLoginWithPassword() throws Exception {
        makeTestUser();

        mvc.perform(post("/login")
                .param(UserProperties.EMAIL.get(), testUserMail)
                .param(UserProperties.PASSWORD.get(), testUserPassword)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andDo(print());

        deleteTestUser();
    }

    @Test
    public void userCannotLoginWithBadPassword() throws Exception {

        makeTestUser();

        mvc.perform(post("/login")
                .param(UserProperties.EMAIL.get(), testUserMail)
                .param(UserProperties.PASSWORD.get(), "caca")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        deleteTestUser();
    }

    @Test
    public void nonUserCannotLogin() throws Exception {
        mvc.perform(post("/login")
                .param(UserProperties.EMAIL.get(), "toto@caca.toto")
                .param(UserProperties.PASSWORD.get(), testUserPassword)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void nonUserCanRegister() throws Exception {

        mvc.perform(post("/register")
                .param(UserProperties.EMAIL.get(), testUserMail)
                .param(UserProperties.PASSWORD.get(), testUserPassword)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated()).andDo(print());

        deleteTestUser();
    }

    @Test
    public void registeredUserCannotRegister() throws Exception {
        makeTestUser();

        mvc.perform(post("/register")
                .param(UserProperties.EMAIL.get(), testUserMail)
                .param(UserProperties.PASSWORD.get(), testUserPassword)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest()).andDo(print());

        deleteTestUser();
    }

    private void deleteTestUser() {
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class)
                    .add(Property.forName(UserProperties.EMAIL.get()).eq(testUserMail));
            List userEntity = detachedCriteria.getExecutableCriteria(hbsession).list();
            if (!userEntity.isEmpty()) {
                Transaction tx = hbsession.beginTransaction();
                hbsession.delete(userEntity.get(0));
                tx.commit();
            }
        } catch (TransactionRequiredException e) {
            e.fillInStackTrace();
        }
    }

    private void makeTestUser() {
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class)
                    .add(Property.forName(UserProperties.EMAIL.get()).eq(testUserMail));
            List userEntity = detachedCriteria.getExecutableCriteria(hbsession).list();
            if (userEntity.isEmpty()) {
                Transaction tx = hbsession.beginTransaction();
                UserEntity user = new UserEntity();
                user.setEmail(testUserMail);
                user.setPassword(testUserPassword);
                user.setInscriptionDate(Date.valueOf(LocalDate.now()));
                user.setValidationToken(TokenGenerator.GetTokenSHA256());
                user.setEmailValidation(0);
                user.setUuid(UUID.randomUUID().toString());
                hbsession.saveOrUpdate(user);
                tx.commit();
            }
        } catch (TransactionRequiredException e) {
            e.fillInStackTrace();
        }
    }

}