package com.travel.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {
    private static final String URL = "jdbc:mysql://localhost:3306?" +
            "autoReconnect=true&" +
            "allowPublicKeyRetrieval=true&" +
            "useSSL=false&" +
            "user=root&" +
            "password=password";

    private static final int INITIAL_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 100;
    private static final int MAX_SECONDS_TIMEOUT = 1;

    private final List<Connection> availableConnections;
    private final List<Connection> usingConnections;

    private static ConnectionPool instance;

    private BasicConnectionPool() {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection());
        }
        this.availableConnections = pool;
        this.usingConnections = new ArrayList<>();
    }

    public synchronized static ConnectionPool getInstance() {
        if (instance == null) instance = new BasicConnectionPool();
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (availableConnections.isEmpty()) {
            if (usingConnections.size() < MAX_POOL_SIZE) {
                availableConnections.add(createConnection());
            } else {
                throw new RuntimeException("Maximum pool size reached, no available connections!");
            }
        }

        Connection connection = availableConnections.remove(availableConnections.size() - 1);

        if (!connection.isValid(MAX_SECONDS_TIMEOUT)) {
            connection = createConnection();
        }

        usingConnections.add(connection);

        System.out.println("available:" + availableConnections.size());
        System.out.println("used:" + usingConnections.size());
        return connection;
    }

    @Override
    public synchronized boolean releaseConnection(Connection connection) {
        availableConnections.add(connection);
        usingConnections.remove(connection);
        System.out.println("available:" + availableConnections.size());
        System.out.println("using:" + usingConnections.size());
        return true;
    }

    private static Connection createConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException("cannot get connection:", e);
        }
    }

    public int getSize() {
        return availableConnections.size() + usingConnections.size();
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
