package com.m2i.flexiflex.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));



    @Test
    public void userCanLoginWithPassword() throws Exception {
        mvc.perform(post("/login")
                .param("email", "toto@toto.toto")
                .param("password", "secret")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void userCannotLoginWithNoPassword() throws Exception {
        mvc.perform(post("/login")
                .param("email", "toto@toto.toto")
                .param("password", "caca")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void nonUserCannotLogin() throws Exception {
        mvc.perform(post("/login")
                .param("email", "toto@caca.toto")
                .param("password", "caca")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isUnauthorized());
    }
}