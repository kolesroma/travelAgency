package com.travel.controller;

import com.travel.dao.entity.Tour;
import com.travel.dao.entity.User;
import com.travel.model.DataProcessor;
import com.travel.model.OrderManager;
import com.travel.model.TourManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
    /**
     * register current tour for this user;
     * get user from session (unregistered user provokes NPE on this page)
     * @param req should contain parameter tourId and have attribute loggedUser in session
     * @param resp send error if bad req params; send redirect to home.jsp if created order
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = ((User) req.getSession().getAttribute("loggedUser")).getId();

        String tourIdSt = req.getParameter("tourId");
        int tourId = new DataProcessor().parsePositiveInt(tourIdSt);
        Tour tour = new TourManager().getTourById(tourId);
        if (new DataProcessor().isNullSendError(tour, resp, "there is no tour with id " + tourIdSt)) return;

        boolean added = new OrderManager().registerOrder(userId, tourId);
        if (new DataProcessor().isFalseSendError(added, resp, "order already registered")) return;

        resp.sendRedirect("home.jsp");
    }
}
