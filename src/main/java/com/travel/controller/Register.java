package com.travel.controller;

import com.travel.dao.UserDao;
import com.travel.dao.UserDaoFactory;
import com.travel.dao.entity.User;
import com.travel.model.AuthorizationException;
import com.travel.model.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String ageSt = req.getParameter("age");
        String address = req.getParameter("address");

        try {
            new UserManager().register(login, password, name, surname, ageSt, address);
        } catch (AuthorizationException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }
        resp.sendRedirect("index.jsp");
    }
}
