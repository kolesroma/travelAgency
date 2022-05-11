package com.travel.dao;

import com.travel.dao.entity.User;
import com.travel.dao.pool.BasicConnectionPool;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDaoFactoryTest {
    private static final String URL = "jdbc:derby:memory:TRAVELAGENCY;create=true";

    private static final String CREATE_TABLE_USERS = "create table TRAVELAGENCY.users (  id int primary key default 1,  login varchar(30) not null,  passwordEnc varchar(32) not null,  name varchar(50) not null,  surname varchar(50) not null,  age int not null,  address varchar(50) not null,  role varchar(20) default 'client' not null,  isBanned int not null default 0,  stepDiscount int default 0 not null,  maxDiscount int default 0 not null )";

    private static final String DROP_TABLE_USERS = "drop table TRAVELAGENCY.users";

    private static final String INSERT_TEST_USER = "insert into TRAVELAGENCY.users values (1, 'testLogin', 'testPass', 'testName', 'testSurname', 20, 'testAddress, 99', default, default, default, default)";

    @BeforeEach
    void beforeAll() throws SQLException {
        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement()) {
            st.execute(CREATE_TABLE_USERS);
        }
    }

    @AfterEach
    void afterAll() throws SQLException {
        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement()) {
            st.execute(DROP_TABLE_USERS);
        }
    }

    @Test
    void testGetAll() throws SQLException, DaoException {
        Connection con = DriverManager.getConnection(URL);
        UserDao userDao = getUserDaoWithConnection(con);

        assertEquals(userDao.getAll().size(), 0); // before inserting should not be any user
        con.createStatement().execute(INSERT_TEST_USER);
        assertEquals(userDao.getAll().size(), 1); // after inserting size should be exactly 1 user
    }

    @Test
    void testAdd() throws SQLException, DaoException {
        Connection con = DriverManager.getConnection(URL);
        UserDao userDao = getUserDaoWithConnection(con);
        User user = createTestUser();

        assertEquals(countRows(con), 0); // before inserting should not be any user
        userDao.add(user);
        assertEquals(countRows(con), 1); // after inserting size should be exactly 1 user
    }

    @Test
    void testDelete() throws SQLException, DaoException {
        Connection con = DriverManager.getConnection(URL);
        UserDao userDao = getUserDaoWithConnection(con);
        User user = createTestUser();

        assertEquals(countRows(con), 0); // before inserting should not be any user
        con.createStatement().execute(INSERT_TEST_USER);
        assertEquals(countRows(con), 1); // after inserting size should be exactly 1 user
        userDao.delete(user);
        assertEquals(countRows(con), 0); // after deleting should not be any user
    }

    private int countRows(Connection con) throws SQLException {
        final String SQL = "select count(*) from TRAVELAGENCY.users";
        try (Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(SQL)) {
            rs.next();
            return rs.getInt(1);
        }
    }

    private UserDao getUserDaoWithConnection(Connection con) throws SQLException {
        UserDao userDao = UserDaoFactory.getInstance();
        BasicConnectionPool cp = mock(BasicConnectionPool.class);
        when(cp.getConnection()).thenReturn(con);
        userDao.setConnectionPool(cp);
        return userDao;
    }

    private User createTestUser() {
        User user = new User();
        user.setId(1);
        user.setLogin("testUser");
        user.setPasswordEnc("testPass");
        user.setName("testName");
        user.setSurname("testSurname");
        user.setAge(20);
        user.setAddress("testAddress, 99");
        return user;
    }
}
