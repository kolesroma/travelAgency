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

@WebServlet("/ShowTour")
public class ShowTour extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = new DataProcessor().parsePositiveInt(req.getParameter("id"));
        Tour tour = new TourManager().getTourById(tourId);
        if (new DataProcessor().isNullSendError(tour, resp, "this tour is not exist")) return;

        int userId = ((User) req.getSession().getAttribute("loggedUser")).getId();
        boolean madeOrder = new OrderManager().isExist(userId, tourId);

        req.setAttribute("madeOrder", madeOrder);
        req.setAttribute("tour", tour);
        req.getRequestDispatcher("WEB-INF/view/tour.jsp")
                .forward(req, resp);
    }
}
