package com.m2i.flexiflex.controller.factories;

import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.entity.properties.UserProperties;
import com.m2i.flexiflex.service.HibernateSession;
import com.m2i.flexiflex.service.TokenGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import javax.persistence.TransactionRequiredException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class UserFactory {
    private static String testUserMail = "user@mail.com";
    private static String testUserbirthDate = "1986-01-01";

    public static String getTestUserbirthDate() {
        return testUserbirthDate;
    }

    public static void setTestUserbirthDate(String testUserbirthDate) {
        UserFactory.testUserbirthDate = testUserbirthDate;
    }

    public static String getTestUserMail() {
        return testUserMail;
    }

    public static void setTestUserMail(String testUserMail) {
        UserFactory.testUserMail = testUserMail;
    }

    public static String getTestUserPassword() {
        return testUserPassword;
    }

    public static void setTestUserPassword(String testUserPassword) {
        UserFactory.testUserPassword = testUserPassword;
    }

    private static String testUserPassword = "secret";
    private static Session hbsession = HibernateSession.getSession();

    public static void deleteTestUser() {
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class)
                    .add(Property.forName(UserProperties.EMAIL).eq(testUserMail));
            List userEntity = detachedCriteria.getExecutableCriteria(hbsession).list();
            if (!userEntity.isEmpty()) {
                Transaction tx = hbsession.beginTransaction();
                hbsession.delete(userEntity.get(0));
                tx.commit();
            }
        } catch (TransactionRequiredException e) {
            e.fillInStackTrace();
        }
    }

    public static UserEntity makeTestUser() {
        UserEntity user = new UserEntity();
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class)
                    .add(Property.forName(UserProperties.EMAIL).eq(testUserMail));
            List userEntity = detachedCriteria.getExecutableCriteria(hbsession).list();
            if (userEntity.isEmpty()) {
                Transaction tx = hbsession.beginTransaction();
                user.setEmail(testUserMail);
                user.setPassword(testUserPassword);
                user.setInscriptionDate(Date.valueOf(LocalDate.now()));
                user.setValidationToken(TokenGenerator.GetTokenSHA256());
                user.setEmailValidation(1);
                user.setUuid(UUID.randomUUID().toString());
                hbsession.saveOrUpdate(user);
                tx.commit();
            }
        } catch (TransactionRequiredException e) {
            e.fillInStackTrace();
        }

        return user;
    }
}
