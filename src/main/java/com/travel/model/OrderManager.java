package com.travel.model;

import com.travel.dao.*;
import com.travel.dao.entity.Order;
import com.travel.dao.entity.User;

public class OrderManager {
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


    public Order getById(int tourId) {
        return null;
    }

    public boolean registerOrder(int userId, int tourId) {
        OrderDao orderDao = OrderDaoFactory.getInstance();
        try {
            orderDao.add(userId, tourId);
        } catch (DaoException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
