/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.model;
import java.time.LocalDate;

/**
 *
 * @author himorii
 */
public class BahanRacikan extends Item {
    private String satuan;

    public BahanRacikan(String Satuan, String idItem, String namaItem, int quantity, int stokMinimum, LocalDate expiredDate, String deskripsi) {
        super(idItem, namaItem, quantity, stokMinimum, expiredDate, deskripsi);
        this.satuan = satuan;
    }

    public String getSatuan() {
        return satuan;
    }

    @Override
    public String displayDetail() {
        return idItem + " | " + namaItem + " | " + satuan;
    } 
}
