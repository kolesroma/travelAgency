package com.travel.controller;

import com.travel.dao.entity.User;
import com.travel.model.Accessor;
import com.travel.model.DataProcessor;
import com.travel.model.ManagerAccess;
import com.travel.model.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ManagerAccess
@WebServlet("/ShowUser")
public class ShowUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser(req, resp);
        if (user == null) return;

        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/view/userInfo.jsp")
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
