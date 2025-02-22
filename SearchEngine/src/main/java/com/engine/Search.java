package com.engine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");

        try {
            Connection conn = DatabaseConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select pageTitle, pageLink, (length(lower(pageText)) - length(replace(lower(pageText), lower('" + keyword +"'), '')))/length('" + keyword + "') as numsOfWord from pages order by numsOfWord\n" + "desc limit 30;");
            ArrayList<SearchResult> listWebPage = new ArrayList<>();
            while (rs.next()) {
                SearchResult sr = new SearchResult(
                        rs.getString("pageTitle"), rs.getString("pageLink"));
                listWebPage.add(sr);
            }
            request.setAttribute("listWebPage", listWebPage);
            request.getRequestDispatcher("/search.jsp").forward(request, response);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        } catch (SQLException | IOException | ClassNotFoundException | ServletException e) {
            e.printStackTrace();
        }

    }
}