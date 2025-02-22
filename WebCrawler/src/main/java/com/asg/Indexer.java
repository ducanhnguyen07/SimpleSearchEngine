package com.asg;

import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Indexer {
    private static Connection conn = null;

    public Indexer(Document document, String link) throws SQLException, ClassNotFoundException {
        String title = document.title();
        String url = link;
        String text = document.text();

        this.conn = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("insert into pages values(?,?,?)");
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, url);
        preparedStatement.setString(3, text);
        preparedStatement.executeUpdate();
    }
}
