package com.travel.dao;

import com.travel.dao.entity.User;
import com.travel.dao.pool.ConnectionPool;

import java.util.List;

public interface UserDao {
    void setConnectionPool(ConnectionPool connectionPool);

    User getById(int id) throws DaoException;

    User getByLogin(String login) throws DaoException;

    List<User> getAll() throws DaoException;

    List<User> getTourUsers(int tourId) throws DaoException;

    void add(User user) throws DaoException;

    void setDiscountStepMax(int userId, int step, int max) throws DaoException;

    void delete(User user) throws DaoException;

    void update(User user) throws DaoException;
}
