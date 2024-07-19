package com.tap.blog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.connection.connection;
import com.tap.packages.Blog;

public class BlogsDisplay extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 System.out.println("hello im in display....");
        List<Blog> blogs = new ArrayList<>();
       
        try (Connection conn = connection.getConnection()) {
            String sql = "SELECT * FROM blogs";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setImagePath(rs.getString("image_path"));
                blogs.add(blog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("blogs", blogs);

        String role = (String) session.getAttribute("role");
        if ("Admin".equalsIgnoreCase(role)) {
            response.sendRedirect("admin_Dashboard.jsp");
        } else if ("Viewer".equalsIgnoreCase(role)) {
            response.sendRedirect("viewer_Dashboard.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
