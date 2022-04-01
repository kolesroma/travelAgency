package com.travel.controller;

import com.travel.dao.entity.Tour;
import com.travel.model.DataProcessor;
import com.travel.model.ManagerAccess;
import com.travel.model.TourManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ManagerAccess
@WebServlet("/SetOrderDiscount")
public class SetOrderDiscount extends HttpServlet {
    /**
     * change set discount for tour with id
     * @param req should contain parameters discount and id (tourId)
     * @param resp send error if bad req params; send redirect to ShowTour if set discount
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = new DataProcessor().parsePositiveInt(req.getParameter("id"));
//        Tour tour = new TourManager().getTourById(tourId);
//        if (new DataProcessor().isNullSendError(tour, resp, "this tour is not exist")) return;
//
//        boolean changed = new TourManager().changeHot(tour);
//        if (new DataProcessor().isFalseSendError(changed,resp, "lost connection with database, try again later")) return;
        int discount = new DataProcessor().parsePositiveInt(req.getParameter("discount"));


        resp.sendRedirect("ShowTour?id=" + tourId);
    }
}
