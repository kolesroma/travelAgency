package com.travel.controller;

import com.travel.dao.entity.Order;
import com.travel.model.DataProcessor;
import com.travel.model.ManagerAccess;
import com.travel.model.OrderManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ManagerAccess
@WebServlet("/ChangeOrderStatus")
public class ChangeOrderStatus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = new DataProcessor().parsePositiveInt(req.getParameter("orderId"));
        Order order = new OrderManager().getById(orderId);

        if (order == null) {
            resp.sendError(400, "there is no order with id " + orderId);
            return;
        }

        if (!isUpdated(req, order)) {
            resp.sendError(400, "there is no order with id " + orderId);
            return;
        }

        resp.sendRedirect("ShowUserOrders?id=" + order.getUserId());
    }

    private boolean isUpdated(HttpServletRequest req, Order order) {
        String status = req.getParameter("status");
        order.setStatus(status);
        return new OrderManager().update(order);
    }
}
