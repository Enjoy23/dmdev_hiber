package com.dmdev;

import com.dmdev.entity.*;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class HibernateRunner {
    private static final Logger logger = LoggerFactory.getLogger(HibernateRunner.class);

    public HibernateRunner() throws SQLException {
    }

    public static void main(String[] args) {



        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            List<User> query = session.createQuery(
                    "select u FROM User u where u.personalInfo.firstname = 'Igor'"
                    , User.class).list();

//            Programmer programmer1 = session.find(Programmer.class,1L);
//            User manager1 = session.find(User.class,2L);


            session.getTransaction().commit();
        }



    }
}
