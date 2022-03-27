package com.travel.controller;

import com.travel.dao.entity.Order;
import com.travel.dao.entity.User;
import com.travel.model.Accessor;
import com.travel.model.DataProcessor;
import com.travel.model.ManagerAccess;
import com.travel.model.OrderManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ManagerAccess
@WebServlet("/ShowUserOrders")
public class ShowUserOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (new Accessor().notManagerOrAdmin(req, resp)) return;

        int userId = new DataProcessor().parsePositiveInt(req.getParameter("id"));
        List<Order> orders = new OrderManager().getOrdersByUserId(userId);

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("WEB-INF/view/orders.jsp")
                .forward(req, resp);
    }
}
