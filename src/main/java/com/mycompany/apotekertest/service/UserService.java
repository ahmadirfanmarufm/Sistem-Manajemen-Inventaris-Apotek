/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.service;

import com.mycompany.apotekertest.exception.UserNotFoundException;
import com.mycompany.apotekertest.model.Apoteker;
import com.mycompany.apotekertest.model.PJApoteker;
import com.mycompany.apotekertest.model.User;
import com.mycompany.apotekertest.repository.UserRepository;

import java.util.ArrayList;

/**
 *
 * @author Kelompok Kipli
 */
public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
        loadDummyData();
    }
    
    private void loadDummyData() {
        userRepository.tambahUser(new Apoteker("A01", "Rafly", "123", "Pagi"));
        userRepository.tambahUser(new Apoteker("A02", "Dimas", "123", "Malam"));
        userRepository.tambahUser(new PJApoteker("P01", "Nelsen", "123"));
    }

    public User login(String userId, String password) {
        try {
            return userRepository.login(userId, password);
        } catch (Exception e) {
            return null;
        }
    }

    public void tambahUser(User user) {
        userRepository.tambahUser(user);
    }

    public User cariUser(String userId) throws UserNotFoundException {
        return userRepository.cariUser(userId);
    }

    public void hapusUser(String userId) throws UserNotFoundException {
        userRepository.hapusUser(userId);
    }

    public ArrayList getDaftarApoteker() {
        return userRepository.getDaftarApoteker();
    }
    
    public ArrayList getDaftarPJApoteker() {
        return userRepository.getDaftarPJApoteker();
    }
    
    public int jumlahUser() {
        return userRepository.jumlahUser();
    }
}
