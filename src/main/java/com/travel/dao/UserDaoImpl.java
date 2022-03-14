package com.travel.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    final static String URL = "jdbc:mysql://localhost:3306?" +
            "autoReconnect=true&" +
            "useSSL=false&" +
            "user=root&" +
            "password=password";

    private static UserDao instance;

    private UserDaoImpl() {
    }

    public static synchronized UserDao getInstance() {
        if (instance == null) instance = new UserDaoImpl();
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
                if (!rs.next()) throw new DaoException("there is no such user");
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
            e.printStackTrace();
            throw new DaoException("cannot get users", e);
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
            throw new DaoException("cannot delete user", e);
        }
    }

    @Override
    public void update(User user) {
        String SQL = "update";
    }
}

class F {
    public static void main(String[] args) throws DaoException {
        UserDao userDao = UserDaoImpl.getInstance();

        User user = new User();
        user.setLogin("bugaga");
        user.setPasswordEnc("qwerty");
        user.setName("mukola");
        user.setSurname("koala");
        user.setAge(20);
        user.setAddress("kopernuka, 14");

//        userDao.add(user);

        User du = new User();
        du.setId(3);
        userDao.delete(du);


        List<User> users = userDao.getAll();
        users.forEach(System.out::println);
    }
}
