package com.apotek.model;
import java.time.LocalDate;

public class NonObat extends Item {
    private String kategori;
    private double hargaBeli;
    private double hargaJual;

    public NonObat(String kategori, double hargaBeli, double hargaJual, String idItem, String namaItem, int quantity, int stokMinimum, LocalDate expiredDate, String deskripsi) {
        super(idItem, namaItem, quantity, stokMinimum, expiredDate, deskripsi);
        this.kategori = kategori;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }
    
    public String getKategori() {
        return kategori;
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
        return idItem + " | " + namaItem + " | " + kategori;
    }
}
