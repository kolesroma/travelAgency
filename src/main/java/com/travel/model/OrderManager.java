package com.travel.model;

import com.travel.dao.*;
import com.travel.dao.entity.Order;
import com.travel.dao.entity.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    final static Logger LOGGER = Logger.getLogger(OrderManager.class);

    private final OrderDao orderDao;

    public OrderManager() {
        this.orderDao = OrderDaoFactory.getInstance();
    }

    /**
     * pair of userId and tourId are unique;
     * add order to database if there is no such order yet; otherwise do not add
     * @param userId id of user
     * @param tourId id of tour
     * @return true if order was registered; false if not
     */
    public boolean registerOrder(int userId, int tourId) {
        try {
            orderDao.add(userId, tourId);
        } catch (DaoException e) {
            LOGGER.debug("cannot register order with userId " + userId + " and tourId " + tourId + "\n\t" + e.getMessage());
            return false;
        }
        LOGGER.debug("successfully register order with userId " + userId + " and tourId " + tourId);
        return true;
    }

    /**
     * says whether the order exists
     * @param userId of this order
     * @param tourId of this order
     * @return true if exists; false if not exist or exception
     */
    public boolean isExist(int userId, int tourId) {
        Order order;
        try {
            order = orderDao.getByUserIdTourId(userId, tourId);
        } catch (DaoException e) {
            LOGGER.debug("user #" + userId + " did not registered in tour#" + tourId + "\n\t" + e.getMessage());
            return false;
        }
        return order != null;
    }

    /**
     * @return List of user orders; could be an empty List
     */
    public List<Order> getOrdersByUserId(int userId) {
        try {
            return orderDao.getUserOrders(userId);
        } catch (DaoException e) {
            LOGGER.debug("cannot get orders with userId " + userId + "\n\t got empty List" + "\n\t" + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * @param orderId positive int
     * @return Order with id; null if not found
     */
    public Order getById(int orderId) {
        try {
            return orderDao.getById(orderId);
        } catch (DaoException e) {
            LOGGER.debug("cannot get order with id " + orderId + "\n\t" + "got null" + "\n\t" + e.getMessage());
            return null;
        }
    }

    /**
     * @return true if updated; false if not updated
     */
    public boolean update(Order order) {
        try {
            orderDao.update(order);
        } catch (DaoException e) {
            LOGGER.debug("cannot update order " + order + "\n\t" + e.getMessage());
            return false;
        }
        LOGGER.debug("successfully updated order " + order);
        return true;
    }
}
