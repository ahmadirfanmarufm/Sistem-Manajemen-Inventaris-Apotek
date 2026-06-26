package com.apotek.model;
import java.time.LocalDate;

public class BahanRacikan extends Item {
    private String satuan;
    
    // Constructor
    public BahanRacikan(String idItem, String namaItem, String satuan, int quantity, int stokMinimum, LocalDate expiredDate, String deskripsi) {
        super(idItem, namaItem, quantity, stokMinimum, expiredDate, deskripsi);
        this.satuan = satuan;
    }

    // Mengambil satuan
    public String getSatuan() {
        return satuan;
    }
    
    // Menampilkan detail objek dengan format [Id Item]|[Nama Item]|[Kategori]
    @Override
    public String displayDetail() {
        return idItem + " | " + namaItem + " | " + satuan;
    } 
}
