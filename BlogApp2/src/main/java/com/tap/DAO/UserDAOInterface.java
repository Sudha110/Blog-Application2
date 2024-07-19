package com.tap.DAO;

import java.sql.SQLException;

import com.tap.packages.User;

public interface UserDAOInterface {
    void registerUser(User user) throws SQLException;
    User loginUser(String email, String password) throws SQLException;
    User getUserByEmail(String email) throws SQLException;
}
