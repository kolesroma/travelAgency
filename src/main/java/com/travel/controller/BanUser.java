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

@WebServlet("/BanUser")
public class BanUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (new Accessor().notAdmin(req, resp)) return;

        int userId = new DataProcessor().parsePositiveInt(req.getParameter("id"));
        User user = getUser(req, resp, userId);
        if (user == null) return;

        new UserManager().changeBan(user);

        resp.sendRedirect("ShowUser?id=" + userId);
    }

    private User getUser(HttpServletRequest req, HttpServletResponse resp, int userId) throws IOException {
        User user = new UserManager().getById(userId);
        if (user == null) {
            resp.sendError(400, "there is no user with id " + userId);
            return null;
        }
        return user;
    }
}
