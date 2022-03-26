package com.travel.controller;

import com.travel.dao.entity.Tour;
import com.travel.dao.entity.User;
import com.travel.model.Accessor;
import com.travel.model.DataProcessor;
import com.travel.model.OrderManager;
import com.travel.model.TourManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SetHotTour")
public class SetHotTour extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (new Accessor().notManagerOrAdmin(req, resp)) return;

        int tourId = new DataProcessor().parsePositiveInt(req.getParameter("tourId"));
        Tour tour = getTour(resp, tourId);
        if (tour == null) return;

        new TourManager().changeHot(tour);

        int userId = ((User) req.getSession().getAttribute("loggedUser")).getId();
        boolean madeOrder = new OrderManager().isExist(userId, tourId);

        req.setAttribute("madeOrder", madeOrder);
        req.setAttribute("tour", tour);
        req.getRequestDispatcher("tour.jsp")
                .forward(req, resp);
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
