package com.travel.dao;

import com.travel.dao.entity.Tour;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDaoFactory implements TourDao {
    final static Logger LOGGER = Logger.getLogger(TourDaoFactory.class);

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
            LOGGER.error("error in database", e);
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
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get tours", e);
        }
        return tours;
    }

    /**
     * query have to be processed
     */
    @Override
    public List<Tour> getPiece(HttpServletRequest req, int skip, int show) throws DaoException {
        String SQL = prepareSQL(req);
        return getTours(skip, show, SQL);
    }

    @Override
    public List<Tour> getPiece(int skip, int show) throws DaoException {
        final String SQL = "select * from travelAgency.tours order by isHot desc limit ?, ?";
        return getTours(skip, show, SQL);
    }

    private List<Tour> getTours(int skip, int show, String SQL) throws DaoException {
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
            LOGGER.error("error in database", e);
            throw new DaoException("cannot get tours", e);
        }
        return tours;
    }

    private String prepareSQL(HttpServletRequest req) {
        boolean priceFlag = true;
        boolean groupFlag = true;

        List<String> types = new ArrayList<>();
        if ("on".equals(req.getParameter("vacation"))) types.add("'vacation'");
        if ("on".equals(req.getParameter("excursion"))) types.add("'excursion'");
        if ("on".equals(req.getParameter("shopping"))) types.add("'shopping'");

        List<String> hotels = new ArrayList<>();
        if ("on".equals(req.getParameter("star1"))) hotels.add("1");
        if ("on".equals(req.getParameter("star2"))) hotels.add("2");
        if ("on".equals(req.getParameter("star3"))) hotels.add("3");
        if ("on".equals(req.getParameter("star4"))) hotels.add("4");
        if ("on".equals(req.getParameter("star5"))) hotels.add("5");

        String priceFrom = req.getParameter("priceFrom");
        String priceTo = req.getParameter("priceTo");
        try {
            Integer.parseInt(priceFrom);
            Integer.parseInt(priceTo);
        } catch (NumberFormatException e) {
            priceFlag = false;
        }
        String groupFrom = req.getParameter("groupFrom");
        String groupTo = req.getParameter("groupTo");
        try {
            Integer.parseInt(groupFrom);
            Integer.parseInt(groupTo);
        } catch (NumberFormatException e) {
            groupFlag = false;
        }

        StringBuilder sb = new StringBuilder("select * from travelAgency.tours where true ");
        if (!types.isEmpty()) sb.append(String.format("and type in (%s) ", String.join(", ", types)));
        if (!hotels.isEmpty()) sb.append(String.format("and hotelStars in (%s) ", String.join(", ", hotels)));
        if (priceFlag) sb.append(String.format("and price between %s and %s ", priceFrom, priceTo));
        if (groupFlag) sb.append(String.format("and groupSize between %s and %s ", groupFrom, groupTo));

        sb.append("order by isHot desc ");
        sb.append("limit ?, ? ");

        return sb.toString();
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
            LOGGER.error("error in database", e);
            throw new DaoException("cannot add tour", e);
        }
    }

    @Override
    public int countRowsAllTours() throws DaoException {
        final String SQL = "select count(*) from travelAgency.tours";
        return getRows(SQL);
    }

    @Override
    public int countRowsFound(HttpServletRequest req) throws DaoException {
        final String SQL = prepareSQL(req)
                .replace("*", "count(*)")
                .replace("limit ?, ?", "");
        return getRows(SQL);
    }

    private int getRows(String SQL) throws DaoException {
        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(SQL)) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot count rows", e);
        }
    }

    /**
     * delete tour with id which has no orders (or orders is canceled)
     */
    @Override
    public void deleteIfNoOrders(int tourId) throws DaoException {
        final String SQL = "delete from travelAgency.tours where id = ? " +
                "and not exists(select * from travelAgency.orders where tourId = ? and status != 'canceled')";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, tourId);
            ps.setInt(2, tourId);
            int rows = ps.executeUpdate();
            if (rows == 0) throw new DaoException("cannot delete tour, there is orders");
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
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
            ps.setInt(6, tour.getId());
            int rows = ps.executeUpdate();
            if (rows == 0) throw new DaoException("there is no tour with id " + tour.getId());
        } catch (SQLException e) {
            LOGGER.error("error in database", e);
            throw new DaoException("cannot update tour", e);
        }
    }
}
