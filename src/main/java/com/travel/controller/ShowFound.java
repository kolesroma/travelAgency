package com.travel.controller;

import com.travel.dao.entity.Tour;
import com.travel.model.DataProcessor;
import com.travel.model.TourManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowFound")
public class ShowFound extends HttpServlet {
    /**
     * set req params tours, page, maxPage and path;
     * show tours on some page by criteria
     * @param req should contain parameter page
     * @param resp send forward to allTours.jsp
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = new DataProcessor().parsePositiveInt(req.getParameter("page"));
        int maxPage = new TourManager().getMaxPageFound(req);
        page = new TourManager().setPageInCorrectRange(page, maxPage);
        List<Tour> tours = new TourManager().getToursOnPage(req, page);

        req.setAttribute("maxPage", maxPage);
        req.setAttribute("path", new DataProcessor().prepareURI(req, "page"));
        req.setAttribute("tours", tours);
        req.setAttribute("page", page);
        req.getRequestDispatcher("WEB-INF/view/allTours.jsp")
                .forward(req, resp);
    }
}
