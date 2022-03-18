package com.travel.dao;

import com.travel.dao.entity.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDaoFactory implements TourDao {
    static final String URL = "jdbc:mysql://localhost:3306?" +
            "autoReconnect=true&" +
            "allowPublicKeyRetrieval=true&" +
            "useSSL=false&" +
            "user=root&" +
            "password=password";

    private static TourDao instance;

    private TourDaoFactory() {
    }

    public static synchronized TourDao getInstance() {
        if (instance == null) instance = new TourDaoFactory();
        return instance;
    }

    @Override
    public Tour getById(int id) throws DaoException {
        final String SQL = "select * from travelAgency.tours where id = ?";
        Tour tour;

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new DaoException("there is no tour with id " + id);
                tour = new Tour(rs.getInt("id"),
                        rs.getInt("price"),
                        rs.getBoolean("isHot"),
                        rs.getInt("groupSize"),
                        rs.getString("type"),
                        rs.getInt("hotelStars"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot get tour", e);
        }

        return tour;
    }

    @Override
    public List<Tour> getAll() throws DaoException {
        final String SQL = "select * from travelAgency.tours";
        List<Tour> tours = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SQL)) {
            while (rs.next()) {
                tours.add(new Tour(rs.getInt("id"),
                        rs.getInt("price"),
                        rs.getBoolean("isHot"),
                        rs.getInt("groupSize"),
                        rs.getString("type"),
                        rs.getInt("hotelStars")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot get tours", e);
        }

        return tours;
    }

    @Override
    public List<Tour> getPiece(int skip, int show) throws DaoException {
        final String SQL = "select * from travelAgency.tours limit ?, ?";
        List<Tour> tours = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, skip);
            ps.setInt(2, show);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tours.add(new Tour(rs.getInt("id"),
                            rs.getInt("price"),
                            rs.getBoolean("isHot"),
                            rs.getInt("groupSize"),
                            rs.getString("type"),
                            rs.getInt("hotelStars")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot get tours", e);
        }
        return tours;
    }

    @Override
    public void add(Tour tour) throws DaoException {
        final String SQL = "insert into travelAgency.tours values (default, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, tour.getPrice());
            ps.setBoolean(2, tour.isHot());
            ps.setInt(3, tour.getGroupSize());
            ps.setString(4, tour.getType());
            ps.setInt(5, tour.getHotelStars());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot add tour", e);
        }
    }

    @Override
    public int countRows() throws DaoException {
        final String SQL = "select count(*) from travelAgency.tours";

        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SQL)) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot count rows", e);
        }
    }

    @Override
    public void delete(Tour tour) throws DaoException {
        final String SQL = "delete from travelAgency.tours where id = ?";

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, tour.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot delete tour", e);
        }
    }

    @Override
    public void update(Tour tour) throws DaoException {
        final String SQL = "update travelAgency.tours " +
                "set price = ?, isHot = ?, groupSize = ?, type = ?, hotelStars = ? where id = ?";

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, tour.getPrice());
            ps.setBoolean(2, tour.isHot());
            ps.setInt(3, tour.getGroupSize());
            ps.setString(4, tour.getType());
            ps.setInt(5, tour.getHotelStars());
            int rows = ps.executeUpdate();
            if (rows == 0) throw new DaoException("there is no tour with id " + tour.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("cannot update tours", e);
        }
    }
}
