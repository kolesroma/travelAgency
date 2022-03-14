package com.travel.dao;

import java.util.List;

public interface UserDao {
    User getById(int id) throws DaoException;

    List<User> getAll() throws DaoException;

    void add(User user) throws DaoException;

    void delete(User user) throws DaoException;

    void update(User user) throws DaoException;
}
