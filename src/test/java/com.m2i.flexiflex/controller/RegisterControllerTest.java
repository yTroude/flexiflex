package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.controller.factories.UserFactory;
import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.entity.properties.UserProperties;
import com.m2i.flexiflex.service.Cryptor;
import com.m2i.flexiflex.service.TokenGenerator;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static com.m2i.flexiflex.controller.factories.UserFactory.deleteTestUser;
import static com.m2i.flexiflex.controller.factories.UserFactory.makeTestUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    @Autowired
    private MockMvc mvc;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void nonUserCanRegister() throws Exception{
        mvc.perform(post("/register")
                .param(UserProperties.EMAIL, UserFactory.getTestUserMail())
                .param(UserProperties.PASSWORD, UserFactory.getTestUserPassword())
                .param(UserProperties.BIRTH_DATE, UserFactory.getTestUserbirthDate())
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        deleteTestUser();
    }

    @Test
    public void registeredUserCannotRegister() throws Exception {
        makeTestUser();

        mvc.perform(post("/register")
                .param(UserProperties.EMAIL, UserFactory.getTestUserMail())
                .param(UserProperties.PASSWORD, UserFactory.getTestUserPassword())
                .param(UserProperties.BIRTH_DATE, UserFactory.getTestUserbirthDate())
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());

        deleteTestUser();
    }

    @Test
    public void validatedUserCanFinalRegister() throws Exception {
        UserEntity userEntity = makeTestUser();
        String firstname = "toto";
        String lastname = "caca";
        String token = userEntity.getValidationToken();


        mvc.perform(post("/finalregister")
                .param(UserProperties.UUID, userEntity.getUuid())
                .param(UserProperties.VALIDATION_TOKEN, token)
                .param(UserProperties.FIRST_NAME, firstname)
                .param(UserProperties.LAST_NAME, lastname))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @After
    public void after() {
        deleteTestUser();
    }
}