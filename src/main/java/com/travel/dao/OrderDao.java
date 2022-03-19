package com.travel.dao;

import com.travel.dao.entity.Order;

import java.util.List;

public interface OrderDao {
    Order getById(int id) throws DaoException;

    Order getByUserIdTourId(int userId, int tourId) throws DaoException;

    List<Order> getAll() throws DaoException;

    List<Order> getUserOrders(int userId) throws DaoException;

    void add(int userId, int tourId) throws DaoException;

    void delete(Order order) throws DaoException;

    void update(Order order) throws DaoException;
}
