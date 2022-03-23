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

@WebServlet("/UpdateTour")
public class UpdateTour extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = ((User) req.getSession().getAttribute("loggedUser")).getRole();
        if (!("manager".equals(role) || "admin".equals(role))) {
            resp.sendRedirect("home.jsp");
            return;
        }

        String tourIdSt = req.getParameter("tourId");
        int tourId = new DataProcessor().parsePositiveInt(tourIdSt);
        Tour tour = new TourManager().getTourById(tourId);
        if (tour == null) {
            resp.sendError(400, "this tour is not exist");
            return;
        }
        new TourManager().changeHot(tour);

        int userId = ((User) req.getSession().getAttribute("loggedUser")).getId();
        boolean madeOrder = new OrderManager().isExist(userId, tourId);

        req.setAttribute("madeOrder", madeOrder);
        req.setAttribute("tour", tour);
        req.getRequestDispatcher("tour.jsp")
                .forward(req, resp);
    }
}
