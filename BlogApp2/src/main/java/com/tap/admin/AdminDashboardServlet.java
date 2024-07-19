package com.tap.admin;

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
import javax.servlet.http.HttpSession;

import com.tap.connection.connection;
import com.tap.packages.Blog;
import com.tap.packages.User;

public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (session != null) ? (User) session.getAttribute("user") : null;
		Connection connect = null;
		if (user == null || !"Admin".equals(user.getRole())) {
			response.sendRedirect("login.jsp");
			return;
		}

		List<Blog> blogs = new ArrayList<>();
		try {

			connect = connection.getConnection();
			String sql = "SELECT * FROM blogs WHERE created_by = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Blog blog = new Blog();
				blog.setId(rs.getInt("id"));
				blog.setTitle(rs.getString("title"));
				blog.setContent(rs.getString("content"));
				blog.setImagePath(rs.getString("image_path"));
				blog.setCreatedBy(rs.getInt("created_by"));
				blogs.add(blog);

				// Log image path for debugging
				System.out.println("Image path for blog " + blog.getId() + ": " + blog.getImagePath());
			}
			rs.close();
			stmt.close();
			connect.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Fetched blogs count: " + blogs.size()); // Debug statement

		request.setAttribute("blogs", blogs);

		request.getRequestDispatcher("admin_Dashboard.jsp").forward(request, response);
	}
}
