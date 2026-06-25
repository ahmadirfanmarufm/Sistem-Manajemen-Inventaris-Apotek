package com.apotek.exception;

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
