package com.m2i.flexiflex.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingsController.class)
public class GreetingsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getGreeting() throws Exception {
                mvc.perform(get("/greeting?name=toto")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}