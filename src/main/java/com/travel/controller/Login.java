package com.travel.controller;

import com.travel.dao.entity.User;
import com.travel.model.AuthorizationException;
import com.travel.model.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user;
        try {
            user = new UserManager().login(login, password);
        } catch (AuthorizationException e) {
            e.printStackTrace();
            // send redirect
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }
        req.getSession().setAttribute("loggedUser", user);
        resp.sendRedirect("home.jsp");
    }
}
