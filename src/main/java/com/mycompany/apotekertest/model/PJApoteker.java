/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.model;

/**
 *
 * @author himorii
 */
public class PJApoteker extends User {
    public PJApoteker(String userId, String name, String password) {
        super(userId, name, password);
    }

    public void melihatLaporan() {
        System.out.println("Melihat laporan...");
    }
}
