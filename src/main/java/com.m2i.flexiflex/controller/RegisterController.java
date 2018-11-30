package com.m2i.flexiflex.controller;

import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.entity.properties.UserProperties;
import com.m2i.flexiflex.service.HibernateSession;
import com.m2i.flexiflex.service.TokenGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class RegisterController {

    private final Session session = HibernateSession.getSession();

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestParam String email, @RequestParam String password, @RequestParam String birthdate) {

        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class)
                    .add(Property.forName(UserProperties.EMAIL).eq(email));

            if (detachedCriteria.getExecutableCriteria(session).list().isEmpty()){
                Transaction tx = session.beginTransaction();
                UserEntity user = new UserEntity();
                user.setEmail(email);
                user.setPassword(password);
                user.setInscriptionDate(Date.valueOf(LocalDate.now()));
                user.setValidationToken(TokenGenerator.GetTokenSHA256());
                user.setEmailValidation(0);
                user.setUuid(UUID.randomUUID().toString());
                user.setBirthDate(birthdate);
                session.save(user);
                tx.commit();

                return new ResponseEntity(HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/finalregister", method = RequestMethod.POST)
    public ResponseEntity finalRegister(
            @RequestParam String uuid,
            @RequestParam String validation_token,
            @RequestParam String firstname,
            @RequestParam String lastname
    ) {
        try {
            Session session = HibernateSession.getSession();
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class).add(Property.forName(UserProperties.UUID).eq(uuid));
            List<UserEntity> userEntity = detachedCriteria.getExecutableCriteria(session).list();
            if (!userEntity.isEmpty() && userEntity.get(0).getValidationToken().equals(validation_token)) {
                Transaction tx = session.beginTransaction();
                userEntity.get(0).setFirstName(firstname);
                userEntity.get(0).setLastName(lastname);
                tx.commit();
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
