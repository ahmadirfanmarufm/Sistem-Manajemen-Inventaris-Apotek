/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.model;

/**
 *
 * @author himorii
 */
public class User {
    protected String userId;
    protected String name;
    protected String password;
    
    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }
    
    public boolean login(String inputUser, String inputPass) {
        return userId.equals(inputUser) && password.equals(inputPass);
    }
    
    public void logout() {
        System.out.println("Logout success");
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
 }
