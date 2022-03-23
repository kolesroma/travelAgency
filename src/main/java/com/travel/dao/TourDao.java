package com.travel.dao;

import com.travel.dao.entity.Tour;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TourDao {
    Tour getById(int id) throws DaoException;

    List<Tour> getAll() throws DaoException;

    List<Tour> getPiece(HttpServletRequest req, int skip, int show) throws DaoException;

    List<Tour> getPiece(int skip, int show) throws DaoException;

    void add(Tour tour) throws DaoException;

    int countRowsAllTours() throws DaoException;

    int countRowsFound(HttpServletRequest req) throws DaoException;

    void delete(Tour tour) throws DaoException;

    void update(Tour tour) throws DaoException;
}
