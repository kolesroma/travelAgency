package com.travel.controller;

import com.travel.dao.DaoException;
import com.travel.dao.UserDao;
import com.travel.dao.UserDaoFactory;
import com.travel.dao.entity.User;
import com.travel.dao.pool.BasicConnectionPool;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.*;

public class RegisterTest {
    private static final String URL = "jdbc:derby:memory:TRAVELAGENCY;create=true";

    public static final String CREATE_TABLE_USERS = "create table TRAVELAGENCY.users (  id int primary key default 1,  login varchar(30) not null,  passwordEnc varchar(32) not null,  name varchar(50) not null,  surname varchar(50) not null,  age int not null,  address varchar(50) not null,  role varchar(20) default 'client' not null,  isBanned int not null default 0,  stepDiscount int default 0 not null,  maxDiscount int default 0 not null )";

    public static final String DROP_TABLE_USERS = "drop table TRAVELAGENCY.users";

    @BeforeAll
    static void beforeAll() throws SQLException {
        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement()) {
            st.execute(CREATE_TABLE_USERS);
        }
    }

    @AfterAll
    static void afterAll() throws SQLException {
        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement()) {
            st.execute(DROP_TABLE_USERS);
        }
    }

    @Test
    void testDB() throws SQLException, DaoException {
        Connection con = DriverManager.getConnection(URL);
        User user = createTestUser();

        UserDao userDao = UserDaoFactory.getInstance();
        BasicConnectionPool cp = mock(BasicConnectionPool.class);
        when(cp.getConnection()).thenReturn(con);

        userDao.setConnectionPool(cp);
        assertEquals(userDao.getAll().size(), 0); // before inserting should not be any user
        userDao.add(user);
        assertEquals(userDao.getAll().size(), 1); // after inserting size should be exactly 1 user
    }

    @Test
    void testDoPost() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getParameter("login")).thenReturn("testUser");
        when(req.getParameter("password")).thenReturn("testPass");
        when(req.getParameter("name")).thenReturn("testName");
        when(req.getParameter("surname")).thenReturn("testSurname");
        when(req.getParameter("age")).thenReturn("20");
        when(req.getParameter("address")).thenReturn("testAddress, 99");

//        assertThrows(RuntimeException.class,
//                () -> doThrow(new RuntimeException("must do redirect")).when(resp).sendRedirect("index.jsp"));


        Register servlet = new Register();
        servlet.doPost(req, resp);
    }

    private User createTestUser() {
        User user = new User();
        user.setLogin("testUser");
        user.setPasswordEnc("testPass");
        user.setName("testName");
        user.setSurname("testSurname");
        user.setAge(20);
        user.setAddress("testAddress, 99");
        return user;
    }
}
