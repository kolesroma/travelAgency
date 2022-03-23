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

@WebServlet("/RegisterOrderServlet")
public class RegisterOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = ((User) req.getSession().getAttribute("loggedUser")).getId();
        String tourIdSt = req.getParameter("tourId");
        int tourId = new DataProcessor().parsePositiveInt(tourIdSt);

        Tour tour = new TourManager().getTourById(tourId);
        if (tour == null) {
            resp.sendError(400, "there is no tour with id " + tourIdSt);
            return;
        }

        boolean added = new OrderManager().registerOrder(userId, tourId);
        if (!added) {
            resp.sendError(400, "order already registered");
            return;
        }

        resp.sendRedirect("home.jsp");
    }
}
