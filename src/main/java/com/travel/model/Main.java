package com.travel.model;

import com.travel.dao.*;
import com.travel.dao.entity.User;

public class Main {
    public static void main(String[] args) throws DaoException {
        UserDao userDao = UserDaoFactory.getInstance();

//        User user = new User();
//        user.setLogin("bugaga");
//        user.setPasswordEnc("qwerty");
//        user.setName("mukola");
//        user.setSurname("koala");
//        user.setAge(20);
//        user.setAddress("kopernuka, 14");
//        user.setId(5);
//        user.setRole("manager");
//        userDao.add(user);

//        User me = userDao.getById(1);
//
        userDao.getAll()
                .forEach(System.out::println);
//
//        me.setBanned(false);
//        userDao.update(user);

        User mu = userDao.getById(1);
        mu.setRole("admin");
        userDao.update(mu);



        TourDao tourDao = TourDaoFactory.getInstance();
        tourDao.getAll()
                .forEach(System.out::println);

    }
}
