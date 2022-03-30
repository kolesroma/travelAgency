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
    /**
     * update tour in database
     * @param req should contain parameters tourId, parameters price, isHot, groupSize, type and hotelStars
     * @param resp send error if bad req params; send redirect to ShowTour with same tourId if updated tour
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = new DataProcessor().parsePositiveInt(req.getParameter("tourId"));

        Tour tour = new TourManager().prepareTour(req);
        tour.setId(tourId);

        boolean updated = new TourManager().update(tour);
        if (new DataProcessor().isFalseSendError(updated, resp, "cannot update")) return;

        resp.sendRedirect("ShowTour?id=" + tourId);
    }
}
