package com.tap.DAO;

import java.sql.SQLException;
import java.util.List;

import com.tap.packages.Blog;

public interface BlogDAOInterface {
    List<Blog> searchBlogsByTitle(String title, int page, int pageSize) throws SQLException;
    List<Blog> getBlogs(int page, int pageSize) throws SQLException;
    void saveBlog(Blog blog) throws SQLException;
    List<Blog> getBlogsByUser(int userId) throws SQLException;
    List<Blog> getAllBlogs() throws SQLException;
    int countAllBlogs() throws SQLException;
    int countBlogsByTitle(String title) throws SQLException;
}
