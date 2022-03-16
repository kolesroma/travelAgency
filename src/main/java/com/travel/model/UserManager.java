package com.travel.model;

import com.travel.dao.DaoException;
import com.travel.dao.UserDao;
import com.travel.dao.UserDaoFactory;
import com.travel.dao.entity.User;

public class UserManager {
    public User login(String login, String password) throws AuthorizationException {
        UserDao userDao = UserDaoFactory.getInstance();
        User user;
        try {
            user = userDao.getByLogin(login);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new AuthorizationException("wrong login or/and password", e);
        }
        if (!user.getPasswordEnc().equals(password)) {
            throw new AuthorizationException("wrong login or/and password");
        }
        return user;
    }

    public void register(String login, String password, String name, String surname, String ageSt, String address) throws AuthorizationException {
        UserDao userDao = UserDaoFactory.getInstance();

        if (login.length() == 0 || password.length() == 0 || name.length() == 0 ||
                surname.length() == 0 || ageSt.length() == 0 || address.length() == 0) {
            throw new AuthorizationException("empty field registration");
        }

        int age;
        try {
            age = Integer.parseInt(ageSt);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new AuthorizationException("age must be a number", e);
        }

        User user = new User();
        user.setLogin(login);
        user.setPasswordEnc(password);
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        user.setAddress(address);

        try {
            userDao.add(user);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new AuthorizationException("cannot register user, try change a login", e);
        }
    }
}
