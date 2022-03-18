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

@WebServlet("/ShowTourServlet")
public class ShowTourServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idSt = req.getParameter("id");
        int id = new TourManager().parseId(idSt);
        Tour tour = new TourManager().getTourById(id);

        if (tour == null) {
            resp.sendError(400, "this tour is not exist");
            return;
        }

        req.setAttribute("tour", tour);
        req.getRequestDispatcher("tour.jsp")
                .forward(req, resp);
    }
}
