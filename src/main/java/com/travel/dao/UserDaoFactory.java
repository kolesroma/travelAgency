package com.travel.dao;

import com.travel.dao.entity.User;
import com.travel.dao.pool.BasicConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoFactory implements UserDao {
    private final static Logger LOGGER = Logger.getLogger(UserDaoFactory.class);

    private static UserDao instance;

    private UserDaoFactory() {
    }

    public static synchronized UserDao getInstance() {
        if (instance == null) instance = new UserDaoFactory();
        return instance;
    }

    @Override
    public User getById(int id) throws DaoException {
        final String SQL = "select * from travelAgency.users where id = ?";
        User user;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) throw new DaoException("there is no user with id " + id);
            user = new User(rs.getInt("id"),
                    rs.getString("login"),
                    rs.getString("passwordEnc"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getInt("age"),
                    rs.getString("address"),
                    rs.getString("role"),
                    rs.getBoolean("isBanned"),
                    rs.getInt("stepDiscount"),
                    rs.getInt("maxDiscount"));
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get user", e);
        } finally {
            closeResources(con, ps, rs);
        }
        return user;
    }

    private void closeResources(Connection con, Statement st, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) BasicConnectionPool.getInstance().releaseConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException("cannot close recourses", e);
        }
    }

    private void closeResources(Connection con, Statement st) {
        try {
            if (st != null) st.close();
            if (con != null) BasicConnectionPool.getInstance().releaseConnection(con);
        } catch (SQLException e) {
            throw new RuntimeException("cannot close recourses", e);
        }
    }

    @Override
    public User getByLogin(String login) throws DaoException {
        final String SQL = "select * from travelAgency.users where login = ?";
        User user;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (!rs.next()) throw new DaoException("there is no user with login " + login);
            user = new User(rs.getInt("id"),
                    rs.getString("login"),
                    rs.getString("passwordEnc"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getInt("age"),
                    rs.getString("address"),
                    rs.getString("role"),
                    rs.getBoolean("isBanned"),
                    rs.getInt("stepDiscount"),
                    rs.getInt("maxDiscount"));
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get user", e);
        } finally {
            closeResources(con, ps, rs);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws DaoException {
        final String SQL = "select * from travelAgency.users";
        List<User> users = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL);
            while (rs.next()) {
                users.add(new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("passwordEnc"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getString("role"),
                        rs.getBoolean("isBanned"),
                        rs.getInt("stepDiscount"),
                        rs.getInt("maxDiscount")));
            }
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get users", e);
        } finally {
            closeResources(con, st, rs);
        }
        return users;
    }

    @Override
    public List<User> getTourUsers(int tourId) throws DaoException {
        final String SQL = "select u.id, u.login, status from travelAgency.orders " +
                "join travelAgency.users u on u.id = orders.userId where tourId = ?";
        List<User> users = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, tourId);
            rs = ps.executeQuery();
            while (rs.next()) {
                User halfUser = new User();
                halfUser.setId(rs.getInt("u.id"));
                halfUser.setLogin(rs.getString("u.login"));
                halfUser.setSurname(rs.getString("status"));
                users.add(halfUser);
            }
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get users", e);
        } finally {
            closeResources(con, ps, rs);
        }
        return users;
    }

    @Override
    public void add(User user) throws DaoException {
        final String SQL = "insert into travelAgency.users values (default, ?, ?, ?, ?, ?, ?, default, default, default, default)";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPasswordEnc());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setInt(5, user.getAge());
            ps.setString(6, user.getAddress());
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot add user", e);
        } finally {
            closeResources(con, ps);
        }
    }

    @Override
    public void setDiscountStepMax(int userId, int step, int max) throws DaoException {
        final String SQL = "update travelAgency.users set stepDiscount = ?, maxDiscount = ? where id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, step);
            ps.setInt(2, max);
            ps.setInt(3, userId);
            int rows = ps.executeUpdate();
            if (rows == 0) throw new DaoException("user not updated: there is no user with id " + userId);
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot add discount", e);
        } finally {
            closeResources(con, ps);
        }
    }

    @Override
    public void delete(User user) throws DaoException {
        final String SQL = "delete from travelAgency.users where id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, user.getId());
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot delete user", e);
        } finally {
            closeResources(con, ps);
        }
    }

    @Override
    public void update(User user) throws DaoException {
        final String SQL = "update travelAgency.users set login = ?, passwordEnc = ?, name = ?, surname = ?," +
                "age = ?, address = ?, role = ? ,isBanned = ? where id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPasswordEnc());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setInt(5, user.getAge());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getRole());
            ps.setBoolean(8, user.isBanned());
            ps.setInt(9, user.getId());
            int rows = ps.executeUpdate();
            if (rows == 0) throw new DaoException("there is no user with id " + user.getId());
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot update user", e);
        } finally {
            closeResources(con, ps);
        }
    }
}
