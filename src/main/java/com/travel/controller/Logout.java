package com.travel.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
    /**
     * invalidates session for current user
     * @param req clear session, in particular parameter loggedUser
     * @param resp send redirect to index.jsp
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("index.jsp");
    }
}
