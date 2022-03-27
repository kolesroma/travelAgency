package com.travel.filter;

import com.google.common.collect.ImmutableSet;
import com.travel.dao.entity.User;
import com.travel.model.Accessor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebFilter(filterName = "AccessManagerFilter")
public class AccessManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * for @ManagerAccess servlets
     * if user is not a manager or admin - send redirect
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String role = ((User) req.getSession().getAttribute("loggedUser")).getRole();
        if (!("manager".equals(role) || "admin".equals(role))) {
            resp.sendRedirect("home.jsp");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
