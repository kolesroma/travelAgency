package com.travel.model;

import com.travel.dao.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Accessor {
    public boolean notManagerOrAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String role = ((User) req.getSession().getAttribute("loggedUser")).getRole();
        if (!("manager".equals(role) || "admin".equals(role))) {
            resp.sendRedirect("home.jsp");
            return true;
        }
        return false;
    }

    public boolean notAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String role = ((User) req.getSession().getAttribute("loggedUser")).getRole();
        if (!"admin".equals(role)) {
            resp.sendRedirect("home.jsp");
            return true;
        }
        return false;
    }
}
