package com.tap.blog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.tap.connection.connection;
import com.tap.packages.Blog;

@MultipartConfig
public class UpdateBlogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection connect=null;
        int blogId = Integer.parseInt(request.getParameter("id"));
        Blog blog = null;

        try {
        	connect=connection.getConnection();
            String sql = "SELECT * FROM blogs WHERE id = ?";
            PreparedStatement pstmt = connect.prepareStatement(sql);
            pstmt.setInt(1, blogId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setImagePath(rs.getString("image_path")); // Ensure this matches your column name
            }

            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving blog: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("blog", blog);
        RequestDispatcher dispatcher = request.getRequestDispatcher("update_Blog.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection connect=null;
    	String idParam = request.getParameter("id");
        System.out.println("Received id: " + idParam); // Debugging log

        if (idParam == null || idParam.isEmpty()) {
            System.out.println("ID parameter is missing.");
            response.sendRedirect("admin_Dashboard.jsp");
            return;
        }

        int id = Integer.parseInt(idParam);
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Part imagePart = request.getPart("image");

        System.out.println("Received title: " + title); // Debugging log
        System.out.println("Received content: " + content); // Debugging log

        try {
        	connect=connection.getConnection();
        	String sql;
            PreparedStatement pstmt;

            if (imagePart != null && imagePart.getSize() > 0) {
                String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
                InputStream fileContent = imagePart.getInputStream();

                // Save image to server
                File uploads = new File(getServletContext().getRealPath("/") + "uploads");
                if (!uploads.exists()) {
                    uploads.mkdir();
                }
                File file = new File(uploads, fileName);
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = fileContent.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }
                }

                sql = "UPDATE blogs SET title = ?, content = ?, image_path = ? WHERE id = ?";
                pstmt = connect.prepareStatement(sql);
                pstmt.setString(1, title);
                pstmt.setString(2, content);
                pstmt.setString(3, fileName);
                pstmt.setInt(4, id);
            } else {
                sql = "UPDATE blogs SET title = ?, content = ? WHERE id = ?";
                pstmt = connect.prepareStatement(sql);
                pstmt.setString(1, title);
                pstmt.setString(2, content);
                pstmt.setInt(3, id);
            }

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Blog post updated successfully.");
            } else {
                System.out.println("No blog post found with the given ID.");
            }

            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("admin_dashboard");
    }
}
