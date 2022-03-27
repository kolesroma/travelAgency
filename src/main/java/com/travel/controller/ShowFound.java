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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageSt = req.getParameter("page");
        int maxPage = new TourManager().getMaxPageFound(req);
        int page = new DataProcessor().parsePositiveInt(pageSt);
        page = new TourManager().setPageInCorrectRange(page, maxPage);
        List<Tour> tours = new TourManager().getToursOnPage(req, page);
        String uri = new DataProcessor().prepareURI(req, "page");

        req.setAttribute("maxPage", maxPage);
        req.setAttribute("path", uri);
        req.setAttribute("tours", tours);
        req.setAttribute("page", page);
        req.getRequestDispatcher("WEB-INF/view/allTours.jsp")
                .forward(req, resp);
    }
}
