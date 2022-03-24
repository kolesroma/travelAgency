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

@WebServlet("/CreateTour")
public class CreateTour extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = ((User) req.getSession().getAttribute("loggedUser")).getRole();
        if (!"admin".equals(role)) {
            resp.sendRedirect("home.jsp");
            return;
        }

        boolean added = new TourManager().addTour(req);
        if (!added) {
            resp.sendError(400, "cannot add tour");
            return;
        }

        resp.sendRedirect("createTour.jsp");
    }
}
