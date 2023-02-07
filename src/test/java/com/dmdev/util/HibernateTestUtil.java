package com.dmdev.util;


import com.dmdev.entity.*;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testcontainers.containers.MySQLContainer;

@UtilityClass
public final class HibernateTestUtil {

     private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8");

     static {
        mysql.start();
    }

    public static SessionFactory buildSessionFactory(){
        Configuration configuration = HibernateUtil.buildConfiguration();
        configuration.setProperty("hibernate.connection.url", mysql.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", mysql.getUsername());
        configuration.setProperty("hibernate.connection.password", mysql.getPassword());
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Chat.class);
        configuration.addAnnotatedClass(UserChat.class);
        configuration.addAnnotatedClass(Process.class);
        configuration.addAnnotatedClass(Manager.class);
        configuration.configure();
        return configuration.buildSessionFactory();
    }



}
