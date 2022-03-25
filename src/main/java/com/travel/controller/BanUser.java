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

        User user = getUser(req, resp);
        if (user == null) return;

        new UserManager().changeBan(user);

        req.setAttribute("user", user);
        req.getRequestDispatcher("userInfo.jsp")
                .forward(req, resp);
    }

    private User getUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int userId = new DataProcessor().parsePositiveInt(req.getParameter("id"));
        User user = new UserManager().getById(userId);
        if (user == null) {
            resp.sendError(400, "there is no user with id " + userId);
            return null;
        }
        return user;
    }
}
