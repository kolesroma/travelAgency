package com.travel.model;

import com.travel.dao.DaoException;
import com.travel.dao.TourDao;
import com.travel.dao.TourDaoFactory;
import com.travel.dao.entity.Tour;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class TourManager {
    final static Logger LOGGER = Logger.getLogger(TourManager.class);

    private final TourDao tourDao;
    private final int pageCapacity = 4;

    public TourManager() {
        this.tourDao = TourDaoFactory.getInstance();
    }

    /**
     * provide a list with number of pageCapacity elements in it
     * @param page should be in possible range in database or List will be empty
     * @return a List with number of pageCapacity elements if page in correct range;
     * empty List if incorrect range
     */
    public List<Tour> getToursOnPage(int page) {
        try {
            return tourDao.getPiece(pageCapacity * (page - 1), pageCapacity);
        } catch (DaoException e) {
            LOGGER.debug("got empty user list" + "\n\t" + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * @param req need for getting parameters and parsing SQL
     * @param page should be in possible range in database or List will be empty
     * @return a List with number of pageCapacity elements if page in correct range;
     * empty List if incorrect range
     */
    public List<Tour> getToursOnPage(HttpServletRequest req, int page) {
        try {
            return tourDao.getPiece(req, pageCapacity * (page - 1), pageCapacity);
        } catch (DaoException e) {
            LOGGER.debug("got empty user list" + "\n\t" + e.getMessage());
            return new ArrayList<>();
        }
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
     * @return max possible page; 1 if error
     */
    public int getMaxPageAllTours() {
        try {
            return (int) Math.ceil((double) tourDao.countRowsAllTours() / pageCapacity);
        } catch (DaoException e) {
            LOGGER.debug("cannot get max page" + "\n\t" + e.getMessage());
            return 1; // should never happen
        }
    }

    /**
     * @return max possible page; 1 if error
     */
    public int getMaxPageFound(HttpServletRequest req) {
        try {
            return (int) Math.ceil((double) tourDao.countRowsFound(req) / pageCapacity);
        } catch (DaoException e) {
            LOGGER.debug("cannot get max page" + "\n\t" + e.getMessage());
            return 1; // should never happen
        }
    }

    /**
     * @return Tour with id as a parameter; null if Tour not found
     */
    public Tour getTourById(int id) {
        try {
            return tourDao.getById(id);
        } catch (DaoException e) {
            LOGGER.debug("got null tour" + "\n\t" + e.getMessage());
            return null;
        }
    }

    /**
     * set isHot true if isHot was false;
     * set isHot false if isHot was true
     */
    public void changeHot(Tour tour) {
        tour.setHot(!tour.isHot());
        try {
            tourDao.update(tour);
            LOGGER.debug("successfully changed hot for tour " + tour);
        } catch (DaoException e) {
            LOGGER.debug("cannot change hot for tour " + tour + "\n\t" + e.getMessage());
        }
    }


    public boolean addTour(HttpServletRequest req) {
        Tour tour = prepareTour(req);
        try {
            tourDao.add(tour);
        } catch (DaoException e) {
            LOGGER.debug("cannot add tour with parameters " + tour);
            return false;
        }
        LOGGER.debug("successfully added tour " + tour);
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
            LOGGER.debug("cannot update tour with parameters " + tour);
            return false;
        }
        LOGGER.debug("successfully updated tour with parameters " + tour);
        return true;
    }
}
