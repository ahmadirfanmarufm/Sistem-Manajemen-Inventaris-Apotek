package com.apotek.model;
import java.time.LocalDate;

public class NonObat extends Item {
    private String kategori;
    private double hargaBeli;
    private double hargaJual;
    
    // Constructor
    public NonObat(String kategori, double hargaBeli, double hargaJual, String idItem, String namaItem, int quantity, int stokMinimum, LocalDate expiredDate, String deskripsi) {
        super(idItem, namaItem, quantity, stokMinimum, expiredDate, deskripsi);
        this.kategori = kategori;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }
    
    // Mengambil atribut kategori
    public String getKategori() {
        return kategori;
    }
    
    // Mengambil atribut hargaBeli
    public double getHargaBeli() {
        return hargaBeli;
    }

    // Mengambil atribut hargaJual
    public double getHargaJual() {
        return hargaJual;
    }

    // Mengeset atribut hargaJual sesuai parameter yang diinputkan
    public void setHargaJual(double hargaJual) {
        this.hargaJual = hargaJual;
    }

    @Override
    // Menampilkan detail objek dengan format [Id Item]|[Nama Item]|[Kategori]
    public String displayDetail() {
        return idItem + " | " + namaItem + " | " + kategori;
    }
}
