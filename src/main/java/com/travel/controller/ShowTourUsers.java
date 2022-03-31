package com.travel.controller;

import com.travel.dao.entity.Order;
import com.travel.dao.entity.User;
import com.travel.model.DataProcessor;
import com.travel.model.ManagerAccess;
import com.travel.model.OrderManager;
import com.travel.model.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ManagerAccess
@WebServlet("/ShowTourUsers")
public class ShowTourUsers extends HttpServlet {
    /**
     * set req param users;
     * show users of current tour on page
     * @param req should contain parameter id (tourId)
     * @param resp include allUsers.jsp; users could be an empty list if no users for this tour
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tourId = new DataProcessor().parsePositiveInt(req.getParameter("id"));
        List<User> users = new UserManager().getUsersByTourId(tourId);

        req.setAttribute("users", users);
        req.getRequestDispatcher("WEB-INF/view/allUsers.jsp")
                .include(req, resp);
    }
}
