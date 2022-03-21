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

@WebServlet("/ShowFound")
public class ShowFound extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageSt = req.getParameter("page");
        int page = new TourManager().parsePage(pageSt);
        List<Tour> tours = new TourManager().getPiece(req, page);

        req.setAttribute("path", "ShowFound");
        req.setAttribute("tours", tours);
        req.setAttribute("page", page);
        req.getRequestDispatcher("tours.jsp")
                .forward(req, resp);
    }
}
