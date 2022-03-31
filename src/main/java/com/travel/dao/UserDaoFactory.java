package com.travel.dao;

import com.travel.dao.entity.Order;
import com.travel.dao.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoFactory implements UserDao {
    final static Logger LOGGER = Logger.getLogger(UserDaoFactory.class);

    static final String URL = "jdbc:mysql://localhost:3306?" +
            "autoReconnect=true&" +
            "allowPublicKeyRetrieval=true&" +
            "useSSL=false&" +
            "user=root&" +
            "password=password";

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
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new DaoException("there is no user with id " + id);
                user = new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("passwordEnc"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getString("role"),
                        rs.getBoolean("isBanned"));
            }
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get user", e);
        }
        return user;
    }

    @Override
    public User getByLogin(String login) throws DaoException {
        final String SQL = "select * from travelAgency.users where login = ?";
        User user;
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new DaoException("there is no user with login " + login);
                user = new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("passwordEnc"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getString("role"),
                        rs.getBoolean("isBanned"));
            }
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get user", e);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws DaoException {
        final String SQL = "select * from travelAgency.users";
        List<User> users = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SQL)) {
            while (rs.next()) {
                users.add(new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("passwordEnc"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getString("role"),
                        rs.getBoolean("isBanned")));
            }
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get users", e);
        }
        return users;
    }

    @Override
    public List<User> getTourUsers(int tourId) throws DaoException{
        final String SQL = "select u.id, u.login, status from travelAgency.orders " +
                "join travelAgency.users u on u.id = orders.userId where tourId = ?";
        List<User> users = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, tourId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User halfUser = new User();
                    halfUser.setId(rs.getInt("u.id"));
                    halfUser.setLogin(rs.getString("u.login"));
                    halfUser.setSurname(rs.getString("status"));
                    users.add(halfUser);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get users", e);
        }
        return users;
    }

    @Override
    public void add(User user) throws DaoException {
        final String SQL = "insert into travelAgency.users values (default, ?, ?, ?, ?, ?, ?, default, default)";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
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
        }
    }

    @Override
    public void delete(User user) throws DaoException {
        final String SQL = "delete from travelAgency.users where id = ?";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, user.getId());
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot delete user", e);
        }
    }

    @Override
    public void update(User user) throws DaoException {
        final String SQL = "update travelAgency.users set login = ?, passwordEnc = ?, name = ?, surname = ?," +
                "age = ?, address = ?, role = ? ,isBanned = ? where id = ?";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
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
        }
    }
}
