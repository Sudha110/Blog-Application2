package com.tap.admin;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.DAO.UserDaoImp;
import com.tap.packages.User;

public class AdminRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDaoImp userDAO;

    @Override
	public void init() {
        userDAO = new UserDaoImp();
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            User existingUser = userDAO.getUserByEmail(email);
            if (existingUser != null) {
                request.setAttribute("error", "Email already registered.");
                request.getRequestDispatcher("admin_Register.jsp").forward(request, response);
                return;
            }

            User user = new User(name, email, password, role);

            if ("Admin".equals(user.getRole())) {
            	userDAO.registerUser(user);
                response.sendRedirect("admin_Login.jsp");
            } else {
                request.setAttribute("error", "Invalid role: " + user.getRole());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to register user. Please try again.");
            request.getRequestDispatcher("admin_Register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred. Please try again.");
            request.getRequestDispatcher("admin_Register.jsp").forward(request, response);
        }
    }
}
