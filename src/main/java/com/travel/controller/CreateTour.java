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
@WebServlet("/CreateTour")
public class CreateTour extends HttpServlet {
    /**
     * create a new tour
     * @param req should contain parameters price, isHot, groupSize, type and hotelStars
     * @param resp send error if bad req params; send redirect to createTour.jsp if created tour
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Tour tour = new TourManager().prepareTour(req);

        boolean added = new TourManager().addTour(tour);
        if (new DataProcessor().isFalseSendError(added, resp, "cannot add tour")) return;

        resp.sendRedirect("createTour.jsp");
    }
}
