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
    /**
     * change status for oder in database
     * @param req should contain parameter orderId
     * @param resp send error if bad req params; send redirect to ShowUserOrders with same id if changed status
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderIdSt = req.getParameter("orderId");
        int orderId = new DataProcessor().parsePositiveInt(orderIdSt);
        Order order = new OrderManager().getById(orderId);
        if (new DataProcessor().isNullSendError(order, resp, "there is no order with id " + orderIdSt)) return;

        String status = req.getParameter("status");
        order.setStatus(status);
        boolean updated = new OrderManager().update(order);
        if (new DataProcessor().isFalseSendError(updated, resp, "there is no order with id " + orderIdSt)) return;

        resp.sendRedirect("ShowUserOrders?id=" + order.getUserId());
    }
}
