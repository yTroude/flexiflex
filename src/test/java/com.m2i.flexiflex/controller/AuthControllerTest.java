package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.controller.factories.UserFactory;
import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.entity.properties.UserProperties;
import com.m2i.flexiflex.service.Cryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;
import java.nio.charset.Charset;

import static com.m2i.flexiflex.controller.factories.UserFactory.deleteTestUser;
import static com.m2i.flexiflex.controller.factories.UserFactory.makeTestUser;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    public void nonUserCannotLogin() throws Exception {
        mvc.perform(post("/login")
                .param(UserProperties.EMAIL, "toto@caca.toto")
                .param(UserProperties.PASSWORD, UserFactory.getTestUserPassword())
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void userCannotLoginWithBadPassword() throws Exception {
        makeTestUser();

        mvc.perform(post("/login")
                .param(UserProperties.EMAIL, UserFactory.getTestUserMail())
                .param(UserProperties.PASSWORD, "caca")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        deleteTestUser();
    }

    @Test
    public void userCanLoginWithPassword() throws Exception {
        UserEntity user = makeTestUser();

        String hash = Cryptor.get_SHA_512(user.getUuid(), "489864zge84zh86h7z68h4z6e");
        String value = user.getUuid() + "_" + hash;
        Cookie myCookie = new Cookie("flexiflex", user.getUuid() + "_" + hash);

        mvc.perform(post("/login")
                .param(UserProperties.EMAIL, UserFactory.getTestUserMail())
                .param(UserProperties.PASSWORD, UserFactory.getTestUserPassword())
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());

        assert myCookie.getName().equals("flexiflex");
        assert myCookie.getValue().equals(value);

        deleteTestUser();
    }
}