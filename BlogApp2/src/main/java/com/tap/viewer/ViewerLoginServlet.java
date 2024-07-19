package com.tap.viewer;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.DAO.UserDaoImp;
import com.tap.packages.User;

public class ViewerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDaoImp userDAO;

    @Override
	public void init() {
        userDAO = new UserDaoImp();
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDAO.loginUser(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole()); // Ensure role is set in session

                if ("Viewer".equals(user.getRole())) {
                    response.sendRedirect("viewer_Home.jsp");
                } else {
                    request.setAttribute("errorMessage", "Invalid role: " + user.getRole());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Invalid email or password");
                request.getRequestDispatcher("viewer_Login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
