package com.travel.controller;

import com.travel.dao.entity.Tour;
import com.travel.dao.entity.User;
import com.travel.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ManagerAccess
@WebServlet("/SetHotTour")
public class SetHotTour extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = new DataProcessor().parsePositiveInt(req.getParameter("tourId"));
        Tour tour = getTour(resp, tourId);
        if (tour == null) return;

        new TourManager().changeHot(tour);

        resp.sendRedirect("ShowTour?id=" + tourId);
    }

    private Tour getTour(HttpServletResponse resp, int tourId) throws IOException {
        Tour tour = new TourManager().getTourById(tourId);
        if (tour == null) {
            resp.sendError(400, "this tour is not exist");
            return null;
        }
        return tour;
    }
}
