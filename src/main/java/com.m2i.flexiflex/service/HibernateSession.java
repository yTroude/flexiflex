package com.m2i.flexiflex.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            ex.fillInStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {

        return ourSessionFactory.openSession();
    }
}
