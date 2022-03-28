package com.travel.controller;

import com.travel.dao.entity.User;
import com.travel.model.DataProcessor;
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

        User user = new UserManager().login(login, password);
        if (new DataProcessor().isNullSendError(user, resp, "error authorization")) return;

        req.getSession().setAttribute("loggedUser", user);
        resp.sendRedirect("home.jsp");
    }
}
