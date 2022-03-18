package com.travel.model;

import com.travel.dao.DaoException;
import com.travel.dao.TourDao;
import com.travel.dao.TourDaoFactory;
import com.travel.dao.entity.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourManager {
    private final int pageCapacity = 4;

    /**
     * @param page should be in possible range in database
     * @return a List with number of pageCapacity elements if page in correct range.
     * But could provide an empty List
     */
    public List<Tour> getPiece(int page) {
        TourDao tourDao = TourDaoFactory.getInstance();
        List<Tour> tours = new ArrayList<>();

        try {
            tours = tourDao.getPiece(pageCapacity * (page - 1), pageCapacity);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return tours;
    }

    /**
     * @param pageSt parse to int if possible,
     *               otherwise set default value.
     * @return int page that cannot be less than 1 and bigger than max page
     */
    public int parsePage(String pageSt) {
        int page;
        try {
            page = Integer.parseInt(pageSt);
        } catch (NumberFormatException e) {
            page = 1;
        }
        int maxPage = getMaxPage();
        if (page <= 0) {
            page = 1;
        } else if (page > maxPage) {
            page = maxPage;
        }
        return page;
    }

    /**
     * @return max possible page. if error returns 1
     */
    public int getMaxPage() {
        TourDao tourDao = TourDaoFactory.getInstance();
        int maxPage;
        try {
            maxPage = (int) Math.ceil((double) tourDao.countRows() / pageCapacity);
        } catch (DaoException e) {
            e.printStackTrace();
            maxPage = 1; // should never happen
        }
        return maxPage;
    }
}
