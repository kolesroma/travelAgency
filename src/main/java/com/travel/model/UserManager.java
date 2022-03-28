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

    /**
     * register user and return status whether user was registered
     * @param login could be any string; if null of empty return false
     * @param password could be any string; return false if null or empty or length < 5
     * @param name could be any string; return false if null or empty
     * @param surname could be any string; return false if null or empty
     * @param ageSt could be any string; return false if null or empty or int representation < 0
     * @param address could be any string; return false if null or empty
     * @return true if user was registered (therefore all params should be right); false if was not registered
     */
    public boolean register(String login, String password, String name, String surname, String ageSt, String address) {
        if (password.length() < 5) return false;

        int age = new DataProcessor().parsePositiveInt(ageSt);
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
            return false;
        }
        return true;
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
