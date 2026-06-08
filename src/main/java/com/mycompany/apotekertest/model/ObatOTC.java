/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.model;
import java.time.LocalDate;

/**
 *
 * @author Kelompok Kipli
 */
public class ObatOTC extends Item {
    private String kategoriObat;
    private double hargaBeli;
    private double hargaJual;

    public ObatOTC(String kategoriObat, double hargaBeli, double hargaJual, String idItem, String namaItem, int quantity, int stokMinimum, LocalDate expiredDate, String deskripsi) {
        super(idItem, namaItem, quantity, stokMinimum, expiredDate, deskripsi);
        this.kategoriObat = kategoriObat;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }
    
    public String getKategori() {
        return kategoriObat;
    }

    public double getHargaBeli() {
        return hargaBeli;
    }

    public double getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(double hargaJual) {
        this.hargaJual = hargaJual;
    }

    @Override
    public String displayDetail() {
        return idItem + " | " + namaItem + " | " + kategoriObat;
    }
}
