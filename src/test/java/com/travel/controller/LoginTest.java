package com.travel.controller;

import com.travel.dao.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LoginTest {
    @Test
    void testDoPost() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = createSession();

        when(req.getParameter("login")).thenReturn("testUser");
        when(req.getParameter("password")).thenReturn("testPass");
        when(req.getSession()).thenReturn(session);

        Login servlet = new Login();
        servlet.doPost(req, resp);

        User expected = new User(48, "testUser", "b62a565853f37fb1ec1efc287bfcebf9", "testName", "testSurname", 20, "testAddress, 99", "client", false, 0, 0);

        Assertions.assertEquals(expected, session.getAttribute("loggedUser"));
    }

    private HttpSession createSession() {
        return new HttpSession() {
            private final Map<String, Object> attributes = new HashMap<>();

            @Override
            public long getCreationTime() {
                return 0;
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public long getLastAccessedTime() {
                return 0;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public void setMaxInactiveInterval(int i) {

            }

            @Override
            public int getMaxInactiveInterval() {
                return 0;
            }

            @Override
            public HttpSessionContext getSessionContext() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return attributes.get(s);
            }

            @Override
            public Object getValue(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public String[] getValueNames() {
                return new String[0];
            }

            @Override
            public void setAttribute(String s, Object o) {
                attributes.put(s, o);
            }

            @Override
            public void putValue(String s, Object o) {
            }

            @Override
            public void removeAttribute(String s) {
            }

            @Override
            public void removeValue(String s) {
            }

            @Override
            public void invalidate() {
            }

            @Override
            public boolean isNew() {
                return false;
            }
        };
    }
}
