package com.travel.filter;

import com.travel.dao.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = {"*.html", "*.jsp"})
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getRequestURI().substring(req.getContextPath().length());
        if (!("/index.jsp".equals(path) || "/register.jsp".equals(path) || "/LoginServlet".equals(path)|| "/".equals(path))) {
            User user = (User) req.getSession().getAttribute("loggedUser");
            if (user == null) resp.sendRedirect("index.jsp");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
