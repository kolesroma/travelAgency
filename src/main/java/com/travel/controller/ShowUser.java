package com.travel.controller;

import com.travel.dao.entity.User;
import com.travel.model.DataProcessor;
import com.travel.model.ManagerAccess;
import com.travel.model.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ManagerAccess
@WebServlet("/ShowUser")
public class ShowUser extends HttpServlet {
    /**
     * set req param user;
     * show current user on page;
     * @param req should contain parameter id (userId)
     * @param resp send error if bad req params; send forward to userInfo.jsp if good
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idSt = req.getParameter("id");
        int userId = new DataProcessor().parsePositiveInt(idSt);
        User user = new UserManager().getById(userId);
        if (new DataProcessor().isNullSendError(user, resp, "there is no user with id " + idSt)) return;

        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/view/userInfo.jsp")
                .forward(req, resp);
    }
}
