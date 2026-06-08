/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.service;

import com.mycompany.apotekertest.model.User;

/**
 *
 * @author KelompokKipli
 */
public class LoginSession {
    public static User currentUser;
    
    public static void login(User user) {
        currentUser = user;
    }
    
    public static void logout() {
        currentUser = null;
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
