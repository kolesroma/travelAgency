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
    /**
     * change hot for tour with tourId
     * @param req should contain parameter tourId
     * @param resp send error if bad req params; send redirect to ShowTour if changed
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = new DataProcessor().parsePositiveInt(req.getParameter("tourId"));
        Tour tour = new TourManager().getTourById(tourId);
        if (new DataProcessor().isNullSendError(tour, resp, "this tour is not exist")) return;

        boolean changed = new TourManager().changeHot(tour);
        if (new DataProcessor().isFalseSendError(changed,resp, "lost connection with database, try again later")) return;

        resp.sendRedirect("ShowTour?id=" + tourId);
    }
}
