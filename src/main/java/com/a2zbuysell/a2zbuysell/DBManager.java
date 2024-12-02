package com.a2zbuysell.a2zbuysell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

class DBManager {
    Connection conn;

    DBManager(){
        String dbPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "com" + File.separator + "a2zbuysell" + File.separator + "a2zbuysell" + File.separator + "mydatabase.db";
        String url = "jdbc:sqlite:" + dbPath;
        System.out.println(url);

        try (Connection connection = DriverManager.getConnection(url)) {
            this.conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("The database path could be wrong. Please check again....");
            System.out.println(url);
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    // Helper method to prepare a statement with parameters
    private PreparedStatement prepareStatement(String query, Object... params) throws SQLException {
        PreparedStatement stmt = this.conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt;
    }

    // Method to fetch results from a SELECT query
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

    // Method to execute an update (INSERT, UPDATE, DELETE)
    public int executeUpdate(String query, Object... params) throws SQLException {
        try (PreparedStatement stmt = prepareStatement(query, params)) {
            return stmt.executeUpdate();
        }
    }
}