package com.example.badlist;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnect {
    Connection connection;
    String user;
    String pass;
    String host;
    String dbname;

    public dbconnect(String user, String pass, String host, String dbname) {
        this.user = user;
        this.pass = pass;
        this.host = host;
        this.dbname = dbname;
    }

    public void connect() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + dbname + "?characterEncoding=UTF8", user, pass);
            System.out.println("Connected to the database successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Ovladač nebyl nalezen pro MySQL database: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Chyba spojenín s dazabází: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Chyba při zavření DB: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
