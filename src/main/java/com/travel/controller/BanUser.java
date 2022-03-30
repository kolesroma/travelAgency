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
    /**
     * change ban for user with id as parameter
     * @param req should contain parameter id (userId)
     * @param resp send error if bad req params; send redirect to ShowUser with same id if changed ban
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idSt = req.getParameter("id");
        int userId = new DataProcessor().parsePositiveInt(idSt);
        User user = new UserManager().getById(userId);
        if (new DataProcessor().isNullSendError(user, resp, "there is no user with id " + idSt)) return;

        boolean changed = new UserManager().changeBan(user);
        if(new DataProcessor().isFalseSendError(changed, resp, "lost connection with database, try again later")) return;

        resp.sendRedirect("ShowUser?id=" + userId);
    }
}
