package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.service.HibernateSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.TransactionRequiredException;
import java.nio.charset.Charset;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    private Session hbsession = HibernateSession.getSession();

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));



    @Test
    public void userCanLoginWithPassword() throws Exception {
        makeTestUser();

        mvc.perform(post("/login")
                .param("email", "user@mail.com")
                .param("password", "secret")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        deleteTestUser();
    }

    @Test
    public void userCannotLoginWithBadPassword() throws Exception {

        makeTestUser();

        mvc.perform(post("/login")
                .param("email", "user@mail.com")
                .param("password", "caca")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        deleteTestUser();
    }

    @Test
    public void nonUserCannotLogin() throws Exception {
        mvc.perform(post("/login")
                .param("email", "toto@caca.toto")
                .param("password", "secret")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void nonUserCanRegister() throws Exception {

        mvc.perform(post("/register")
                .param("email", "user@mail.com")
                .param("password", "secret")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated()).andDo(print());

        deleteTestUser();
    }

    private void deleteTestUser() {
        try {
            Transaction tx = hbsession.beginTransaction();
            Criteria cr = hbsession.createCriteria(UserEntity.class);
            cr.add(Restrictions.eq("email", "user@mail.com"));
            UserEntity userEntity = (UserEntity) cr.uniqueResult();
            hbsession.delete(userEntity);
            tx.commit();
        } catch (TransactionRequiredException e) {
            e.fillInStackTrace();
        }
    }

    private void makeTestUser() {
        try {
            Transaction tx = hbsession.beginTransaction();
            UserEntity user = new UserEntity();
            user.setEmail("user@mail.com");
            user.setPassword("secret");
            hbsession.saveOrUpdate(user);
            tx.commit();
        } catch (TransactionRequiredException e) {
            e.fillInStackTrace();
        }
    }
}