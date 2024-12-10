package com.a2zbuysell.a2zbuysell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

abstract class AbstractDBManager {
    protected Connection conn;

    public AbstractDBManager(String dbUrl) {
        try {
            this.conn = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    // Method to prepare a statement with parameters
    protected PreparedStatement prepareStatement(String query, Object... params) throws SQLException {
        PreparedStatement stmt = this.conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt;
    }

    // Method to execute a SELECT query and return results
    public List<List<Object>> executeQuery(String query, Object... params) throws SQLException {
        try (PreparedStatement stmt = prepareStatement(query, params);
             ResultSet rs = stmt.executeQuery()) {

            List<List<Object>> results = new ArrayList<>();
            int columnCount = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                results.add(row);
            }

            return results;
        }
    }

    // Method to execute INSERT, UPDATE, DELETE
    public int executeUpdate(String query, Object... params) throws SQLException {
        try (PreparedStatement stmt = prepareStatement(query, params)) {
            return stmt.executeUpdate();
        }
    }

    // Abstract method for initializing the connection
    protected abstract String initializeDBPath();

    // Close connection method
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }
}



class DBManager extends AbstractDBManager {

    public DBManager() {
        super(buildDbUrl());
    }

    @Override
    protected String initializeDBPath() {
        return System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "com" + File.separator + "a2zbuysell" + File.separator + "a2zbuysell" + File.separator + "mydatabase.db";
    }

    private static String buildDbUrl() {
        String dbPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "com" + File.separator + "a2zbuysell" + File.separator + "a2zbuysell" + File.separator + "mydatabase.db";
        String url = "jdbc:sqlite:" + dbPath;
        return url;
    }
}