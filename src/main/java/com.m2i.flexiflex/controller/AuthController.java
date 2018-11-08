package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.response.UserResponse;
import com.m2i.flexiflex.service.HibernateSession;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final Session session = HibernateSession.getSession();

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestParam String email, @RequestParam String password) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserResponse> login(@RequestParam String email, @RequestParam String password) {

        String pattern = "from com.m2i.flexiflex.entity.UserEntity U where U.email = '%s'";

        try {
            UserEntity user = (UserEntity) session.createQuery(String.format(pattern, email)).list().get(0);

            if (user.getPassword().equals(password)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            //
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
}



