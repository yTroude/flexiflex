package com.m2i.flexiflex.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(EmailValidationController.class)
public class EmailValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ShouldValidateEmailOrNot() throws Exception {
        this.mockMvc.perform(get("/email_validation?key1=7e62acd8a69b4b27bd9b7c6fe1af006eea890e151af4eb29b37802b6b0f07664&key2=0")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Validation success")));
//        this.mockMvc.perform(get("/email_validation?key=7e62acd8a69b4b27bd9b7c6fe1af006eea890e151af4eb29b37802b6b0f07664&id=1")).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Validation failure")));
//        this.mockMvc.perform(get("/email_validation?key=54eaa93444577f299d5cbdda60d958c4d051541b1d0b9fbc90d5a23066a7e0ad&id=0")).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Validation failure")));
//        this.mockMvc.perform(get("/email_validation?key=54eaa93444577f299d5cbdda60d958c4d051541b1d0b9fbc90d5a23066a7e0ad&id=1")).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Validation success")));
    }
}