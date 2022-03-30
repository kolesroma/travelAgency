package com.travel.controller;

import com.travel.model.DataProcessor;
import com.travel.model.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Register")
public class Register extends HttpServlet {
    /**
     * add a new user to database
     * @param req should contain parameters login and password, name, surname, age and address
     * @param resp send error if bad req params; send redirect to index.jsp if registered
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String ageSt = req.getParameter("age");
        String address = req.getParameter("address");

        boolean registered = new UserManager().register(login, password, name, surname, ageSt, address);
        if (new DataProcessor().isFalseSendError(registered, resp, "registration error")) return;

        resp.sendRedirect("index.jsp");
    }
}
