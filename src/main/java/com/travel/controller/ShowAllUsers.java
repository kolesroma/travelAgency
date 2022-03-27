package com.travel.controller;

import com.travel.dao.entity.Tour;
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
import java.util.List;

@AdminAccess
@WebServlet("/ShowAllUsers")
public class ShowAllUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = ((User) req.getSession().getAttribute("loggedUser")).getRole();
        if (!("manager".equals(role) || "admin".equals(role))) {
            resp.sendRedirect("home.jsp");
            return;
        }

        List<User> users = new UserManager().getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("WEB-INF/view/allUsers.jsp")
                .include(req, resp);
    }
}
