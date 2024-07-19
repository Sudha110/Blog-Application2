package com.tap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.tap.connection.connection;
import com.tap.packages.User;

public class UserDaoImp {
    private static final String INSERT_USERS_SQL = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    public void registerUser(User user) throws SQLException {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        try {
            connect = connection.getConnection();
            preparedStatement = connect.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            preparedStatement.setString(4, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            printException(e);
            throw new SQLException(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
    }

    public User loginUser(String email, String password) throws SQLException {
        User user = null;
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        try {
            connect = connection.getConnection();
            preparedStatement = connect.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                if (BCrypt.checkpw(password, rs.getString("password"))) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            printException(e);
            throw new SQLException(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
        return user;
    }

    public User getUserByEmail(String email) throws SQLException {
        User user = null;
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        try {
            connect = connection.getConnection();
            preparedStatement = connect.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            printException(e);
            throw new SQLException(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
        return user;
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


}
