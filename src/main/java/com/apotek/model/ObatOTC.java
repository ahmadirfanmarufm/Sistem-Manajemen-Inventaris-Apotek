package com.apotek.model;
import java.time.LocalDate;

public class ObatOTC extends Item {
    private String kategoriObat;
    private double hargaBeli;
    private double hargaJual;
    
    // Constructor
    public ObatOTC(String kategoriObat, double hargaBeli, double hargaJual, String idItem, String namaItem, int quantity, int stokMinimum, LocalDate expiredDate, String deskripsi) {
        super(idItem, namaItem, quantity, stokMinimum, expiredDate, deskripsi);
        this.kategoriObat = kategoriObat;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }
    
    // Mengambil atribut kategoriObat
    public String getKategori() {
        return kategoriObat;
    }

    // Mengambil atribut hargaBeli
    public double getHargaBeli() {
        return hargaBeli;
    }
    
    // Mengambil atribut hargaJual
    public double getHargaJual() {
        return hargaJual;
    }
    
    // Mengeset atribut hargaJual sesuai parameter yang dimasukkan
    public void setHargaJual(double hargaJual) {
        this.hargaJual = hargaJual;
    }
    
    // Menampilkan detail objek dengan format [Id Item]|[Nama Item]|[Kategori]
    @Override
    public String displayDetail() {
        return idItem + " | " + namaItem + " | " + kategoriObat;
    }
}
