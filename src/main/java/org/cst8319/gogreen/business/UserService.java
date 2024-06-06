package org.cst8319.gogreen.business;

import org.cst8319.gogreen.DAO.DBConnection;
import org.cst8319.gogreen.DAO.UserDAO;
import org.cst8319.gogreen.DTO.User;

import java.sql.SQLException;

public class UserService {

    public UserDAO userDAO;

    public UserService() throws SQLException {
        this.userDAO = new UserDAO(DBConnection.getConnection());
    }

    public void register(String username, String password, String email) throws SQLException {

        User existingUser = userDAO.getUserByUsername(username);
        if(existingUser != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        String userType = "normal";

        User user = new User(username, password, email, userType);
        userDAO.addUser(user);

    }

    public User login(String username, String password) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        if(user == null) {
            throw new IllegalArgumentException("User does not exist");
        }
        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password");
        }
        return user;
    }
}
