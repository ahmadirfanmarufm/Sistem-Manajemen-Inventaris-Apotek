/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.model;

/**
 *
 * @author himorii
 */
public class Apoteker extends User {
    private String shift;

    public Apoteker(String userId, String name, String password, String shift) {
        super(userId, name, password);
        this.shift = shift;
    }

    public void melihatStok() {
        System.out.println("Melihat stok...");
    }

    public void laporkanTransaksi() {
        System.out.println("Laporan transaksi...");
    }
}
