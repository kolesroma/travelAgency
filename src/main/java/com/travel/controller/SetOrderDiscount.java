package com.travel.controller;

import com.travel.dao.entity.Order;
import com.travel.dao.entity.Tour;
import com.travel.dao.entity.User;
import com.travel.model.DataProcessor;
import com.travel.model.ManagerAccess;
import com.travel.model.OrderManager;
import com.travel.model.TourManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ManagerAccess
@WebServlet("/SetOrderDiscount")
public class SetOrderDiscount extends HttpServlet {
    /**
     * change set discount for order with id;
     * @param req should contain parameters discount and id (orderId)
     * @param resp send error if bad req params; send redirect to ShowUserOrders if set discount
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = new DataProcessor().parsePositiveInt(req.getParameter("id"));
        Order order = new OrderManager().getById(orderId);
        if (new DataProcessor().isNullSendError(order, resp, "this order is not exist")) return;

        int discount = new DataProcessor().parsePositiveInt(req.getParameter("discount"));
        boolean changed = new OrderManager().changeDiscount(order, discount);
        if (new DataProcessor().isFalseSendError(changed, resp, "lost connection with database, try again later")) return;

        resp.sendRedirect("ShowUserOrders?id=" + order.getUserId());
    }
}
