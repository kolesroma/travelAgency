package com.travel.controller;

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
@WebServlet("/SetDiscountStepMax")
public class SetDiscountStepMax extends HttpServlet {
    /**
     * set discount step and max discount for user with userId
     * @param req should contain parameters step, max and userId
     * @param resp send error if bad req params; send redirect to ShowUser if set
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = new DataProcessor().parsePositiveInt(req.getParameter("userId"));
        int step = new DataProcessor().parsePositiveInt(req.getParameter("step"));
        int max = new DataProcessor().parsePositiveInt(req.getParameter("max"));

        boolean set = new UserManager().setDiscountStepMax(userId, step, max);
        if (new DataProcessor().isFalseSendError(set, resp, "cannot set: values are wrong")) return;

        resp.sendRedirect("ShowUser?id=" + userId);
    }
}
