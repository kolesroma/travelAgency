package com.travel.controller;

import com.travel.dao.entity.Order;
import com.travel.dao.entity.Tour;
import com.travel.dao.entity.User;
import com.travel.model.OrderManager;
import com.travel.model.TourManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowMyOrders")
public class ShowMyOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = ((User) req.getSession().getAttribute("loggedUser")).getId();
        List<Order> orders = new OrderManager().getOrdersByUserId(userId);

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("WEB-INF/view/orders.jsp")
                .forward(req, resp);
    }
}
