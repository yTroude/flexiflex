package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.persistence.UserEntity;
import com.m2i.flexiflex.service.HibernateSession;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingsController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public UserEntity greeting(@RequestParam(value="name", defaultValue="World") String name) {

        Session session = HibernateSession.getSession();
        UserEntity user = new UserEntity();
        user.setEmail("coucou@mail.com");
        user.setPassword("caca");

        session.save(user);



        return user;

    }
}



