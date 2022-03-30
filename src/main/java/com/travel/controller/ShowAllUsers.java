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
import java.util.List;

@ManagerAccess
@WebServlet("/ShowAllUsers")
public class ShowAllUsers extends HttpServlet {
    /**
     * set req param users
     * show all users
     * @param resp include allUsers.jsp (no users in list if exception)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = new UserManager().getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("WEB-INF/view/allUsers.jsp")
                .include(req, resp);
    }
}
