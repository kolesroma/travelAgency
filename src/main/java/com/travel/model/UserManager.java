package com.travel.model;

import com.travel.dao.DaoException;
import com.travel.dao.UserDao;
import com.travel.dao.UserDaoFactory;
import com.travel.dao.entity.Tour;
import com.travel.dao.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = UserDaoFactory.getInstance();
    }


    /**
     * gets user from database if login and password are right
     * @param login value that typed user
     * @param password value that typed user
     * @return user if login and password are right; null if not found
     */
    public User login(String login, String password) {
        User user;
        try {
            user = userDao.getByLogin(login);
        } catch (DaoException e) {
            return null;
        }
        if (!user.getPasswordEnc().equals(password)) {
            return null;
        }
        return user;
    }

    public void register(String login, String password, String name, String surname, String ageSt, String address) throws AuthorizationException {
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

    /**
     * @return List of users; empty List if exception
     */
    public List<User> getAll() {
        List<User> users;
        try {
            users = userDao.getAll();
        } catch (DaoException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return users;
    }

    /**
     * @return User with id; null if not found
     */
    public User getById(int id) {
        User user;
        try {
            user = userDao.getById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    /**
     * set isBanned true if isBanned was false;
     * set isBanned false if isBanned was true
     */
    public void changeBan(User user) {
        user.setBanned(!user.isBanned());
        try {
            userDao.update(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
