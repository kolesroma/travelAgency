package com.travel.model;

import com.travel.dao.DaoException;
import com.travel.dao.TourDao;
import com.travel.dao.TourDaoFactory;
import com.travel.dao.entity.Tour;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class TourManager {
    private final int pageCapacity = 4;

    /**
     * @param page should be in possible range in database or List will be empty
     * @return a List with number of pageCapacity elements if page in correct range;
     * empty List if incorrect range
     */
    public List<Tour> getToursOnPage(int page) {
        TourDao tourDao = TourDaoFactory.getInstance();
        List<Tour> tours;
        try {
            tours = tourDao.getPiece(pageCapacity * (page - 1), pageCapacity);
        } catch (DaoException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return tours;
    }

    /**
     * @param req need for getting parameters and parsing SQL
     * @param page should be in possible range in database or List will be empty
     * @return a List with number of pageCapacity elements if page in correct range;
     * empty List if incorrect range
     */
    public List<Tour> getToursOnPage(HttpServletRequest req, int page) {
        TourDao tourDao = TourDaoFactory.getInstance();
        List<Tour> tours;
        try {
            tours = tourDao.getPiece(req, pageCapacity * (page - 1), pageCapacity);
        } catch (DaoException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return tours;
    }

    /**
     * provide page in range [1; maxPage], not out of it
     * @param page any int
     * @return 1 if page < 1; maxPage if page > maxPage; page if in range [1; maxPage]
     */
    public int setPageInCorrectRange(int page, int maxPage) {
        if (page < 1) {
            page = 1;
        } else if (page > maxPage) {
            page = maxPage;
        }
        return page;
    }

    /**
     * @return max possible page. if error returns 1
     */
    public int getMaxPageAllTours() {
        TourDao tourDao = TourDaoFactory.getInstance();
        int maxPage;
        try {
            maxPage = (int) Math.ceil((double) tourDao.countRowsAllTours() / pageCapacity);
        } catch (DaoException e) {
            e.printStackTrace();
            return 1; // should never happen
        }
        return maxPage;
    }

    public int getMaxPageFound(HttpServletRequest req) {
        TourDao tourDao = TourDaoFactory.getInstance();
        int maxPage;
        try {
            maxPage = (int) Math.ceil((double) tourDao.countRowsFound(req) / pageCapacity);
        } catch (DaoException e) {
            e.printStackTrace();
            return 1; // should never happen
        }
        return maxPage;
    }

    /**
     * @return Tour with id as a parameter; null if Tour not found
     */
    public Tour getTourById(int id) {
        TourDao tourDao = TourDaoFactory.getInstance();
        Tour tour;
        try {
            tour = tourDao.getById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            return null;
        }
        return tour;
    }

    /**
     * set isHot true if isHot was false;
     * set isHot false if isHot was true
     */
    public void changeHot(Tour tour) {
        TourDao tourDao = TourDaoFactory.getInstance();
        tour.setHot(!tour.isHot());
        try {
            tourDao.update(tour);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
