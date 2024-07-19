package com.tap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.connection.connection;
import com.tap.packages.Blog;

public class BlogDaoImp {
    private static final String SELECT_BLOGS_PAGINATED = "SELECT * FROM blogs LIMIT ? OFFSET ?";
    private static final String SELECT_BLOGS_BY_TITLE_PAGINATED = "SELECT * FROM blogs WHERE title LIKE ? LIMIT ? OFFSET ?";
    private static final String COUNT_ALL_BLOGS = "SELECT COUNT(*) FROM blogs";
    private static final String COUNT_BLOGS_BY_TITLE = "SELECT COUNT(*) FROM blogs WHERE title LIKE ?";
    private static final String SELECT_BLOGS_BY_USER = "SELECT * FROM blogs WHERE created_by = ?";

    public List<Blog> searchBlogsByTitle(String title, int page, int pageSize) {
        List<Blog> blogs = new ArrayList<>();
        Connection connect = null;
        try {
            int offset = (page - 1) * pageSize;
            connect = connection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(SELECT_BLOGS_BY_TITLE_PAGINATED);
            preparedStatement.setString(1, "%" + title + "%");
            preparedStatement.setInt(2, pageSize);
            preparedStatement.setInt(3, offset);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setImagePath(rs.getString("image_path"));
                blogs.add(blog);
            }
        } catch (SQLException | ClassNotFoundException e) {
            printException(e);
        } finally {
            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return blogs;
    }

    private void printException(Exception e) {
        if (e instanceof SQLException) {
            // Handle SQLException
            SQLException sqlException = (SQLException) e;
            // Print SQL exception details
            System.err.println("SQLState: " + sqlException.getSQLState());
            System.err.println("Error Code: " + sqlException.getErrorCode());
            System.err.println("Message: " + sqlException.getMessage());
            Throwable t = sqlException.getCause();
            while (t != null) {
                System.out.println("Cause: " + t);
                t = t.getCause();
            }
        } else if (e instanceof ClassNotFoundException) {
            // Handle ClassNotFoundException
            ClassNotFoundException cnfException = (ClassNotFoundException) e;
            System.err.println("Class not found: " + cnfException.getMessage());
        }
    }
    public List<Blog> getBlogs(int page, int pageSize) {
        List<Blog> blogs = new ArrayList<>();
        Connection connect = null;
        try {
            int offset = (page - 1) * pageSize;
            connect = connection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(SELECT_BLOGS_PAGINATED);
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setImagePath(rs.getString("image_path"));
                blogs.add(blog);
            }
        } catch (SQLException | ClassNotFoundException e) {
            printException(e);
        } finally {
            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return blogs;
    }

    public void saveBlog(Blog blog) throws SQLException {
        String INSERT_BLOG_SQL = "INSERT INTO blogs (title, content, image_path, created_by) VALUES (?, ?, ?, ?)";
        Connection connect = null;
        try {
            connect = connection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(INSERT_BLOG_SQL);
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getContent());
            preparedStatement.setString(3, blog.getImagePath());
            preparedStatement.setInt(4, blog.getCreatedBy());
           preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            printException(e);
        } finally {
            if (connect != null) {
                connect.close();
            }
        }
    }

    public List<Blog> getBlogsByUser(int userId) throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        Connection connect = null;
        try {
            connect = connection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(SELECT_BLOGS_BY_USER);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setImagePath(rs.getString("image_path"));
                blog.setCreatedBy(rs.getInt("created_by"));
                blogs.add(blog);
            }
        }catch (SQLException | ClassNotFoundException e) {
            printException(e);
        } finally {
            if (connect != null) {
                connect.close();
            }
        }
        return blogs;
    }


    public List<Blog> getAllBlogs() throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        Connection connect = null;
        try {
            connect = connection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM blogs");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setImagePath(rs.getString("image_path"));
                blog.setCreatedBy(rs.getInt("created_by"));
                blogs.add(blog);
            }
        } catch (SQLException | ClassNotFoundException e) {
            printException(e);
        } finally {
            if (connect != null) {
                connect.close();
            }
        }
        return blogs;
    }

    public int countAllBlogs() throws SQLException {
        Connection connect = null;
        try {
            connect = connection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(COUNT_ALL_BLOGS);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }catch (SQLException | ClassNotFoundException e) {
            printException(e);
        } finally {
            if (connect != null) {
                connect.close();
            }
        }
        return 0;
    }

    public int countBlogsByTitle(String title) throws SQLException {
        Connection connect = null;
        try {
            connect = connection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(COUNT_BLOGS_BY_TITLE);
            preparedStatement.setString(1, "%" + title + "%");
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            printException(e);
        } finally {
        	 if (connect != null) {
                 connect.close();
             }
        }
        return 0;
    }
}
