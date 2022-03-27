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

@AdminAccess
@WebServlet("/UpdateTour")
public class UpdateTour extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = new DataProcessor().parsePositiveInt(req.getParameter("tourId"));

        Tour tour = getTourWithId(req, tourId);
        boolean updated = new TourManager().update(tour);
        if (!updated) {
            resp.sendError(400, "cannot update");
            return;
        }

        resp.sendRedirect("ShowTour?id=" + tourId);
    }

    private Tour getTourWithId(HttpServletRequest req, int tourId) {
        Tour tour = new TourManager().prepareTour(req);
        tour.setId(tourId);
        return tour;
    }
}
