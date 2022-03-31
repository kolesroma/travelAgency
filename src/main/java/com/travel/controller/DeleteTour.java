package com.travel.controller;

import com.travel.dao.entity.User;
import com.travel.model.AdminAccess;
import com.travel.model.DataProcessor;
import com.travel.model.TourManager;
import com.travel.model.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AdminAccess
@WebServlet("/DeleteTour")
public class DeleteTour extends HttpServlet {
    /**
     * delete tour from database if no orders for it
     * @param req should contain parameter id (tourId)
     * @param resp send error if bad req params; send redirect to home.jsp
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = new DataProcessor().parsePositiveInt(req.getParameter("id"));
        boolean deleted = new TourManager().deleteIfNoOrders(tourId);
        if (new DataProcessor().isFalseSendError(deleted, resp, "cannot delete - there is orders for this tour or tour does not exist")) return;

        resp.sendRedirect("home.jsp");
    }
}
