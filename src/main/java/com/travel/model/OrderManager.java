package com.travel.model;

import com.travel.dao.*;
import com.travel.dao.entity.Order;
import com.travel.dao.entity.User;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private final OrderDao orderDao;

    public OrderManager() {
        this.orderDao = OrderDaoFactory.getInstance();
    }

    public boolean registerOrder(int userId, int tourId) {
        try {
            orderDao.add(userId, tourId);
        } catch (DaoException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * says whether the order exists
     * @param userId of this order
     * @param tourId of this order
     * @return true if exists; false if not exists or exception
     */
    public boolean isExist(int userId, int tourId) {
        Order order;
        try {
            order = orderDao.getByUserIdTourId(userId, tourId);
        } catch (DaoException e) {
            e.printStackTrace();
            return false;
        }
        return order != null;
    }

    /**
     * @return List of user orders; could be an empty List
     */
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders;
        try {
            orders = orderDao.getUserOrders(userId);
        } catch (DaoException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return orders;
    }
}
