package com.travel.controller;

import com.travel.dao.entity.Tour;
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
        String pageSt = req.getParameter("page");
        int maxPage = new TourManager().getMaxPageForAll();
        int page = new TourManager().parsePage(pageSt, maxPage);
        List<Tour> tours = new TourManager().getPiece(page);

        req.setAttribute("maxPage", maxPage);
        req.setAttribute("path", "ShowToursServlet");
        req.setAttribute("tours", tours);
        req.setAttribute("page", page);
        req.getRequestDispatcher("tours.jsp")
                .forward(req, resp);
    }
}
