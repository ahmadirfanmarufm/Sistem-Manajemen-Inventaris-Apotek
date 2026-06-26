package com.apotek.service;

import com.apotek.model.User;

/**
 *
 * @author KelompokKipli
 */
public class LoginSession {
    private static User currentUser;
    
    public static void login(User user) {
        currentUser = user;
    }
    
    public static void logout() {
        currentUser = null;
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static User setCurrentUser(User user) {
        return currentUser = user;
    }
    
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
