package com.asg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection conn = null;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn != null) {
            return conn;
        }
        String username = "root";
        String password = "1234";
        String db = "searchengine";

        return getConnection(username, password, db);
    }

    private static Connection getConnection(String username, String password, String db) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/"+db+"?user="+username+"&password="+password);
        return connection;
    }
}
