package com.travel.model;

import com.travel.dao.DaoException;
import com.travel.dao.TourDao;
import com.travel.dao.TourDaoFactory;
import com.travel.dao.entity.Tour;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class TourManager {
    private final TourDao tourDao;
    private final int pageCapacity = 4;

    public TourManager() {
        this.tourDao = TourDaoFactory.getInstance();
    }

    /**
     * @param page should be in possible range in database or List will be empty
     * @return a List with number of pageCapacity elements if page in correct range;
     * empty List if incorrect range
     */
    public List<Tour> getToursOnPage(int page) {
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
        tour.setHot(!tour.isHot());
        try {
            tourDao.update(tour);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


    public boolean addTour(HttpServletRequest req) {
        Tour tour = prepareTour(req);
        try {
            tourDao.add(tour);
        } catch (DaoException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * gets params from req, processes them and create a new Tour with such information
     * @param req should include params price, isHot, groupSize, type and hotelStars
     * @return Tour with such information (except setting id)
     */
    public Tour prepareTour(HttpServletRequest req) {
        int price = new DataProcessor().parsePositiveInt(req.getParameter("price"));
        boolean isHot = "on".equals(req.getParameter("isHot"));
        int groupSize = new DataProcessor().parsePositiveInt(req.getParameter("groupSize"));
        String type = req.getParameter("type");
        int hotelStars = new DataProcessor().parsePositiveInt(req.getParameter("hotelStars"));

        Tour tour = new Tour();
        tour.setPrice(price);
        tour.setHot(isHot);
        tour.setGroupSize(groupSize);
        tour.setType(type);
        tour.setHotelStars(hotelStars);
        return tour;
    }

    public boolean update(Tour tour) {
        try {
            tourDao.update(tour);
        } catch (DaoException e) {
            return false;
        }
        return true;
    }
}
