/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.service;
import com.mycompany.apotekertest.model.User;
import com.mycompany.apotekertest.model.Apoteker;
import com.mycompany.apotekertest.model.PJApoteker;
/**
 *
 * @author himorii
 */
public class UserService {
    private User[] users = new User[50];
    private int size = 0;

    public UserService() {
        addUser(new Apoteker("A01", "Rafly", "123", "Pagi"));
        addUser(new PJApoteker("P01", "Nelsen", "123"));
    }

    public void addUser(User u) {
        users[size++] = u;
    }

    public User login(String id, String password) {
        for (int i = 0; i < size; i++) {
            if (users[i].getUserId().equals(id)
                && users[i].getPassword().equals(password)) {
                return users[i];
            }
        }
        return null;
    }

    public User[] getAll() {
        return users;
    }
}
