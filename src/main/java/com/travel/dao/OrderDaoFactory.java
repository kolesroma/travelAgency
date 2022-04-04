package com.travel.dao;

import com.travel.dao.entity.Order;
import com.travel.dao.pool.BasicConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoFactory implements OrderDao {
    private final static Logger LOGGER = Logger.getLogger(OrderDaoFactory.class);

    private static OrderDao instance;

    private OrderDaoFactory() {
    }

    public static synchronized OrderDao getInstance() {
        if (instance == null) instance = new OrderDaoFactory();
        return instance;
    }

    @Override
    public Order getById(int id) throws DaoException {
        final String SQL = "select * from travelAgency.orders where id = ?";
        Order order;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) throw new DaoException("there is no order with id " + id);
            order = new Order(rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getInt("tourId"),
                    rs.getString("status"),
                    rs.getInt("discount"));
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get order", e);
        } finally {
            closeResources(con, ps, rs);
        }
        return order;
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
    public Order getByUserIdTourId(int userId, int tourId) throws DaoException {
        final String SQL = "select * from travelAgency.orders where userId = ? and tourId = ?";
        Order order;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, userId);
            ps.setInt(2, tourId);
            rs = ps.executeQuery();
            if (!rs.next()) throw new DaoException("cannot get order");
            order = new Order(rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getInt("tourId"),
                    rs.getString("status"),
                    rs.getInt("discount"));
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get order", e);
        } finally {
            closeResources(con, ps, rs);
        }
        return order;
    }

    @Override
    public List<Order> getAll() throws DaoException {
        final String SQL = "select * from travelAgency.orders";
        List<Order> orders = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery(SQL);
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("tourId"),
                        rs.getString("status"),
                        rs.getInt("discount")));
            }
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get order", e);
        } finally {
            closeResources(con, ps, rs);
        }
        return orders;
    }

    @Override
    public List<Order> getUserOrders(int userId) throws DaoException {
        final String SQL = "select * from travelAgency.orders where userId = ?";
        List<Order> orders = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("tourId"),
                        rs.getString("status"),
                        rs.getInt("discount")));
            }
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get orders", e);
        } finally {
            closeResources(con, ps, rs);
        }
        return orders;
    }

    @Override
    public void add(int userId, int tourId) throws DaoException {
        final String SQL = "insert into travelAgency.orders values (default, ?, ?, default, default)";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, userId);
            ps.setInt(2, tourId);
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot add order", e);
        } finally {
            closeResources(con, ps);
        }
    }

    @Override
    public void delete(Order order) throws DaoException {
        final String SQL = "delete from travelAgency.orders where id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, order.getId());
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot delete order", e);
        } finally {
            closeResources(con, ps);
        }
    }

    @Override
    public void update(Order order) throws DaoException {
        final String SQL = "update travelAgency.orders " +
                "set userId = ?, tourId = ?, status = ?, discount = ? where id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BasicConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getTourId());
            ps.setString(3, order.getStatus());
            ps.setInt(4, order.getDiscount());
            ps.setInt(5, order.getId());
            int rows = ps.executeUpdate();
            if (rows == 0) throw new DaoException("there is no order with id " + order.getId());
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot update order", e);
        } finally {
            closeResources(con, ps);
        }
    }
}
