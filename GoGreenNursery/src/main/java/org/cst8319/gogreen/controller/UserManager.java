package org.cst8319.gogreen.controller;

import org.cst8319.gogreen.DTO.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private static UserManager instance;
    private Map<String, User> userMap;

    // Private constructor to prevent instantiation from outside
    private UserManager() {
        userMap = new HashMap<>();
    }

    // Method to get the singleton instance
    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // Method to add a user to the map
    public void addUser(String sessionID, User user) {
        userMap.put(sessionID, user);

    }

    // Method to get a user by sessionID
    public User getUser(String sessionID) {
        if (userMap.containsKey(sessionID)) {
            return userMap.get(sessionID);
        }
        return null;
    }

    // Method to remove a user to the map
    public void removeUserBySeesionId(String sessionID) {
        userMap.remove(sessionID);

    }

}

