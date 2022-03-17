package com.travel.controller;

import com.travel.dao.DaoException;
import com.travel.dao.TourDao;
import com.travel.dao.TourDaoFactory;
import com.travel.dao.entity.Tour;
import com.travel.model.TourException;
import com.travel.model.TourManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowToursServlet")
public class ShowToursServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tour> tours;

        String pageSt = req.getParameter("page");
        int page;

        if (pageSt == null) {
            resp.sendError(400, "page should be denoted");
            return;
        }
        try {
            page = Integer.parseInt(pageSt);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(400, "invalid page");
            return;
        }
        if (page < 0) {
            page = 1;
        }
        TourDao tourDao = TourDaoFactory.getInstance();
        int maxPage;
        try {
            maxPage = (int) Math.ceil((double) tourDao.countRows() / 4);
        } catch (DaoException e) {
            e.printStackTrace();
            return;
        }
        if (page > maxPage) {
            page = maxPage;
        }

        // tours have to be a list with 4 elements
        tours = new TourManager().getPiece(page);

        req.setAttribute("tours", tours);
        req.setAttribute("page", page);
        req.getRequestDispatcher("tours.jsp")
                .forward(req, resp);
    }
}
