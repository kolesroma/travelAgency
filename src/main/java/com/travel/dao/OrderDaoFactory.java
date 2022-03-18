package com.travel.dao;

import com.travel.dao.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoFactory implements OrderDao{
    static final String URL = "jdbc:mysql://localhost:3306?" +
            "autoReconnect=true&" +
            "allowPublicKeyRetrieval=true&" +
            "useSSL=false&" +
            "user=root&" +
            "password=password";

    private static OrderDao instance;

    private OrderDaoFactory() {}

    public static synchronized OrderDao getInstance() {
        if (instance == null) instance = new OrderDaoFactory();
        return instance;
    }

    @Override
    public Order getById(int id) throws DaoException {
        final String SQL = "select * from travelAgency.orders where id = ?";
        Order order;

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new DaoException("there is no order with id " + id);
                order = new Order(rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("tourId"),
                        rs.getString("status"),
                        rs.getInt("discount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot get order", e);
        }

        return order;
    }

    @Override
    public List<Order> getAll() throws DaoException {
        final String SQL = "select * from travelAgency.orders";
        List<Order> orders = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SQL)) {
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("tourId"),
                        rs.getString("status"),
                        rs.getInt("discount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot get order", e);
        }

        return orders;
    }

    @Override
    public void add(int userId, int tourId) throws DaoException {
        final String SQL = "insert into travelAgency.orders values (default, ?, ?, default, default)";

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, userId);
            ps.setInt(2, tourId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot add order", e);
        }
    }

    @Override
    public void delete(Order order) throws DaoException {
        final String SQL = "delete from travelAgency.orders where id = ?";

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, order.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot delete order", e);
        }
    }

    @Override
    public void update(Order order) throws DaoException {
        final String SQL = "update travelAgency.orders " +
                "set userId = ?, tourId = ?, status = ?, discount = ? where id = ?";

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getTourId());
            ps.setString(3, order.getStatus());
            ps.setInt(4, order.getDiscount());
            ps.setInt(5, order.getId());
            int rows = ps.executeUpdate();
            if (rows == 0) throw new DaoException("there is no order with id " + order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot update order", e);
        }
    }
}
