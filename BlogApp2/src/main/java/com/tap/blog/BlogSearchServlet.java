package com.tap.blog;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.DAO.BlogDaoImp;
import com.tap.packages.Blog;

public class BlogSearchServlet extends HttpServlet {
	private BlogDaoImp blogService = new BlogDaoImp();
	private static final int PAGE_SIZE = 5; // Number of blogs per page

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchTitle = request.getParameter("searchTitle");
		String pageStr = request.getParameter("page");
		int page = (pageStr != null && !pageStr.isEmpty()) ? Integer.parseInt(pageStr) : 1;

		List<Blog> blogs;
		int totalBlogs=-1;
		if (searchTitle != null && !searchTitle.trim().isEmpty()) {
			blogs = blogService.searchBlogsByTitle(searchTitle, page, PAGE_SIZE);
			try {
				totalBlogs = blogService.countBlogsByTitle(searchTitle);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			blogs = blogService.getBlogs(page, PAGE_SIZE);
			try {
				totalBlogs = blogService.countAllBlogs();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int totalPages = (int) Math.ceil((double) totalBlogs / PAGE_SIZE);

		request.setAttribute("blogs", blogs);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("searchTitle", searchTitle);
		request.getRequestDispatcher("/search_Blog.jsp").forward(request, response);
	}
}
