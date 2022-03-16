package com.travel.model;

import com.travel.dao.DaoException;
import com.travel.dao.UserDao;
import com.travel.dao.UserDaoFactory;
import com.travel.dao.entity.User;

public class LogUser {
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
}
