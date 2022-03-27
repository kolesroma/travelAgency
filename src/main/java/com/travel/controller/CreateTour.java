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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(new Accessor().notAdmin(req, resp)) return;

        boolean added = new TourManager().addTour(req);
        if (!added) {
            resp.sendError(400, "cannot add tour");
            return;
        }

        resp.sendRedirect("createTour.jsp");
    }
}
