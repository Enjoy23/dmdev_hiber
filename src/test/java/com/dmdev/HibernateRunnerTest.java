package com.dmdev;


import com.dmdev.entity.*;
import com.dmdev.util.HibernateTestUtil;
import com.dmdev.util.HibernateUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

class HibernateRunnerTest {


    @Test
    public void checkH2(){
        @Cleanup SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

       Company google = Company.builder()
                .name("Google")
                .build();
       session.persist(google);

       Programmer programmer = Programmer.builder()
               .username("ivan@gmail.com")
               .language(Language.JAVA)
               .company(google)
               .build();
       session.persist(programmer);

        Manager manager = Manager.builder()
                .username("kolya@gmail.com")
                .projectName("Starter")
                .company(google)
                .build();
        session.persist(manager);
        session.flush();
        session.clear();

        Programmer programmer1 = session.find(Programmer.class,1L);
        User manager1 = session.find(User.class,2L);
        System.out.println(manager1.getUsername());
        session.getTransaction().commit();
    }

    @Test
    public void addUserAndChatTogetherInDB(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Chat chat = session.find(Chat.class,2L);

        User user = session.find(User.class,18L);

//        UserChat userChat = UserChat.builder()
//                .createdAt(Instant.now())
//                .createdBy(user.getPersonalInfo().getFirstname())
//                .build();

//        userChat.setChat(chat);
//        userChat.setUser(user);
//
//        session.persist(userChat);


        session.getTransaction().commit();

    }



    @Test
    void checkElementCollection(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = session.find(Company.class, 15);

        company.getLocale().add(LocaleInfo.of("EN","Description on english"));
        company.getLocale().add(LocaleInfo.of("RU","Описание на русском"));
        company.getLocale().add(LocaleInfo.of("DE","Beschreibung auf Deutsch"));

        session.getTransaction().commit();
    }




    @Test
    void checkManyToMany(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();


        User user = session.find(User.class, 18L);
        Chat chat = session.find(Chat.class,1L);

//        UserChat userChat = UserChat.builder()
//                .createdAt(Instant.now())
//                .createdBy(user.getPersonalInfo().getFirstname())
//                .build();
//        userChat.setUser(user);
//        userChat.setChat(chat);
//        session.persist(userChat);

        session.getTransaction().commit();
    }

    @Test
    void deleteCompany(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = session.get(Company.class, 4);
        session.remove(company);


        session.getTransaction().commit();

    }

    @Test
    void addNewUserToCompany(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

//        Company faceBook = Company.builder()
//                .name("FaceBook")
//                .build();
//
//        User sveta = User.builder()
//                .username("sveta@gmail.com").build();
//
//        faceBook.addUser(sveta);
//        session.persist(faceBook);


        session.getTransaction().commit();
    }

    @Test
    void oneToMany(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Company company = session.get(Company.class, 1);
        System.out.println(company.getUsers());

        session.getTransaction().commit();
    }

    @Test
    void checkReflectionAPI() throws SQLException, IllegalAccessException {
//        User user = User.builder()
//                .username("ivan@gmail.com")
                //.firstname("Ivan")
                //.lastname("Ivanov")
               // .birthDate(new Birthday(LocalDate.of(2000,1,19)))
//                .build();

        String SQL = """
                insert
                into
                %s
                (%s)
                values
                (%s)
                """;


//        String tableName = ofNullable(user.getClass().getAnnotation(Table.class))
//                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
//                .orElse(user.getClass().getName());
//
//        Field[] declaredFields = user.getClass().getDeclaredFields();
//        String columnNames = Arrays.stream(declaredFields)
//                .map(field -> ofNullable(field.getAnnotation(Column.class))
//                        .map(Column::name)
//                        .orElse(field.getName()))
//                .collect(joining(", "));

//        String columnValues = Arrays.stream(declaredFields)
//                .map(field -> "?")
//                .collect(joining(", "));
//
//        System.out.println(SQL.formatted(tableName,columnNames,columnValues));
//
//        Connection connection = null;
//        PreparedStatement preparedStatement =
//                connection.prepareStatement(SQL.formatted(tableName, columnNames, columnValues));
//
//        for (Field declaredField : declaredFields) {
//            declaredField.setAccessible(true);
//            preparedStatement.setObject(1,declaredField.get(user));
//        }
    }

}