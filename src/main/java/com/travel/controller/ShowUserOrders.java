package com.travel.controller;

import com.travel.dao.entity.Order;
import com.travel.dao.entity.Tour;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ManagerAccess
@WebServlet("/ShowUserOrders")
public class ShowUserOrders extends HttpServlet {
    /**
     * set req param map orderTour;
     * show orders of current user on page;
     * @param req should contain parameter id (userId)
     * @param resp send forward to orders.jsp; orders could be an empty list if no order for this user
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = new DataProcessor().parsePositiveInt(req.getParameter("id"));

        req.setAttribute("orderTour", new OrderManager().getOrderTourMap(userId));
        req.getRequestDispatcher("WEB-INF/view/orders.jsp")
                .forward(req, resp);
    }
}
