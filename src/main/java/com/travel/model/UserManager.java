package com.travel.model;

import com.travel.dao.DaoException;
import com.travel.dao.UserDao;
import com.travel.dao.UserDaoFactory;
import com.travel.dao.entity.User;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    final static Logger LOGGER = Logger.getLogger(UserManager.class);

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
            LOGGER.debug("user cannot log in: " + e.getMessage());
            return null;
        }
        if (!user.getPasswordEnc().equals(cryptPassword(password))) {
            LOGGER.debug(login + " cannot log in, wrong password");
            return null;
        }
        LOGGER.debug("successfully logged in " + user);
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
        User user = new User();
        user.setLogin(login);
        user.setPasswordEnc(cryptPassword(password));
        user.setName(name);
        user.setSurname(surname);
        user.setAge(new DataProcessor().parsePositiveInt(ageSt));
        user.setAddress(address);
        try {
            userDao.add(user);
        } catch (DaoException e) {
            LOGGER.debug("cannot register user with parameters: " + user + "\n\t" + e.getCause().getMessage());
            return false;
        }
        LOGGER.debug("successfully registered " + user);
        return true;
    }

    /**
     * crypt password
     * @param password String with length >= 5
     * @return encrypted password String;
     * empty String if password is null or length < 5 that cause constraint passwordEnc with length 0 violation
     */
    private String cryptPassword(String password) {
        if (password == null || password.length() < 5) return "";
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (byte aByte : bytes) {
                s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return s.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.warn("cannot crypt password" + "\n\t" + e.getMessage());
            return null;
        }
    }

    /**
     * @return List of users; empty List if exception
     */
    public List<User> getAll() {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            LOGGER.debug("got empty user list");
            return new ArrayList<>();
        }
    }

    /**
     * @return User with id; null if not found
     */
    public User getById(int id) {
        try {
            return userDao.getById(id);
        } catch (DaoException e) {
            LOGGER.debug("got user null" + "\n\t" + e.getMessage());
            return null;
        }
    }

    /**
     * set isBanned true if isBanned was false;
     * set isBanned false if isBanned was true
     */
    public boolean changeBan(User user) {
        user.setBanned(!user.isBanned());
        try {
            userDao.update(user);
            LOGGER.debug("successfully changed ban for user " + user);
            return true;
        } catch (DaoException e) {
            LOGGER.debug("cannot change ban for user " + user + "\n\t" + e.getMessage());
            return false;
        }
    }
}
