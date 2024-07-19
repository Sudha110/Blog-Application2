package com.tap.viewer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.connection.connection;
import com.tap.packages.Blog;

public class ViewerDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTitle = request.getParameter("searchTitle");
        List<Blog> blogs = new ArrayList<>();
        Connection connect=null;

        try {
        	connect = connection.getConnection();
            String sql = "SELECT * FROM blogs";

            if (searchTitle != null && !searchTitle.trim().isEmpty()) {
                sql += " WHERE title LIKE ?";
            }

            PreparedStatement pstmt = connect.prepareStatement(sql);

            if (searchTitle != null && !searchTitle.trim().isEmpty()) {
                pstmt.setString(1, "%" + searchTitle + "%");
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setImagePath(rs.getString("image_path"));
                blogs.add(blog);
            }

            rs.close();
            pstmt.close();
            connect.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("blogs", blogs);
        request.setAttribute("totalPages", 1);  // Assuming no pagination for now
        request.setAttribute("currentPage", 1); // Assuming first page
        request.getRequestDispatcher("viewer.jsp").forward(request, response);
    }
}
