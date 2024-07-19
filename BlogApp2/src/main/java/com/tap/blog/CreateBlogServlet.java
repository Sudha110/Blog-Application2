package com.tap.blog;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.tap.DAO.BlogDaoImp;
import com.tap.packages.Blog;
import com.tap.packages.User;

@MultipartConfig
public class CreateBlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("admin_Login.jsp");
            return;
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Part filePart = request.getPart("image");

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

        // Ensure the upload directory exists
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Check if file already exists and rename it if necessary
        Path filePath = Paths.get(uploadPath + File.separator + fileName);
        int count = 1;
        String newFileName = fileName;
        while (Files.exists(filePath)) {
            newFileName = fileName.replaceFirst("(\\.)([^.]*)$", "_" + count + "$1$2");
            filePath = Paths.get(uploadPath + File.separator + newFileName);
            count++;
        }

        try {
            // Save the file
            Files.copy(filePart.getInputStream(), filePath);

            // Save blog details to the database
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setContent(content);
            blog.setImagePath(newFileName);
            blog.setCreatedBy(user.getId()); // Set the created_by field

            // Call your DAO or service method to save the blog
            BlogDaoImp blogDAO = new BlogDaoImp();
            blogDAO.saveBlog(blog);

            response.sendRedirect("admin_dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "File upload failed: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to save blog to database: " + e.getMessage());
            request.getRequestDispatcher("create_blog.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
