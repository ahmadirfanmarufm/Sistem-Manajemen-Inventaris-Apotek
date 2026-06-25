package com.apotek.model;
import java.time.LocalDate;

public class BahanRacikan extends Item {
    private String satuan;

    public BahanRacikan(String idItem, String namaItem, String satuan, int quantity, int stokMinimum, LocalDate expiredDate, String deskripsi) {
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
