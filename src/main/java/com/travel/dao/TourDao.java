package com.travel.dao;

import com.travel.dao.entity.Tour;

import java.util.List;

public interface TourDao {
    Tour getById(int id) throws DaoException;

    List<Tour> getAll() throws DaoException;

    void add(Tour tour) throws DaoException;

    void delete(Tour tour) throws DaoException;

    void update(Tour tour) throws DaoException;
}
