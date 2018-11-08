package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.persistence.UserEntity;
import com.m2i.flexiflex.service.HibernateSession;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @RequestMapping("/greeting")
    public Object greeting() {

        Session session = HibernateSession.getSession();
        UserEntity user = new UserEntity();
        user.setEmail("coucou@mail.com");
        user.setPassword("toto");
        user.setId(2);

        session.save("flexiflex.user", (Object) user);
//
        return (Object) session.get(UserEntity.class, 2);

    }
}



