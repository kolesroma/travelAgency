package com.travel.controller;

import com.travel.dao.entity.User;
import com.travel.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AdminAccess
@WebServlet("/BanUser")
public class BanUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idSt = req.getParameter("id");
        int userId = new DataProcessor().parsePositiveInt(idSt);
        User user = new UserManager().getById(userId);
        if (new DataProcessor().isNullSendError(user, resp, "there is no user with id " + idSt)) return;

        new UserManager().changeBan(user);

        resp.sendRedirect("ShowUser?id=" + userId);
    }
}
