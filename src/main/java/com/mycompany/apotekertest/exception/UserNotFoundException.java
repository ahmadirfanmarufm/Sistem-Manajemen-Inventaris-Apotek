/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.exception;

/**
 *
 * @author Kelompok Kipli
 */
public class UserNotFoundException extends ApotekException {
    private final String userId;
    
    public UserNotFoundException(String userId) {
        super("USER_NOT_FOUND", "User dengan ID '" + userId + "' tidak terdaftar dalam sistem.");
        this.userId = userId;
    }
    
    public String getUserId() {
        return userId;
    }
}
