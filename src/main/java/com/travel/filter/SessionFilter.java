package com.travel.filter;

import com.travel.dao.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebFilter(filterName = "SessionFilter")
public class SessionFilter implements Filter {
    private static final Set<String> IGNORE_PATHS = Set.of("/index.jsp", "/", "/Login", "/register.jsp", "/Register", "/styles/error.css");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * give access to page if current path is available to visit for not logged user;
     * otherwise check whether session has logged user:
     * he could access every page if yes; he could not if no (redirecting)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getRequestURI().substring(req.getContextPath().length());
        if (!IGNORE_PATHS.contains(path)) {
            User user = (User) req.getSession().getAttribute("loggedUser");
            if (user == null) {
                resp.sendRedirect("index.jsp");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
