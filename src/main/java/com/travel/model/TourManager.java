package com.travel.model;

import com.travel.dao.DaoException;
import com.travel.dao.TourDao;
import com.travel.dao.TourDaoFactory;
import com.travel.dao.entity.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourManager {
    public List<Tour> getPiece(int page) {
        TourDao tourDao = TourDaoFactory.getInstance();
        List<Tour> tours = new ArrayList<>();

        try {
            tours = tourDao.getPiece(4 * page - 4, 4);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return tours;
    }
}
