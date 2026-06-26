package com.apotek.model;
import java.time.LocalDate;

public abstract class Item {
    protected String idItem;
    protected String namaItem;
    protected int quantity;
    protected int stokMinimum;
    protected LocalDate expiredDate;
    protected String deskripsi;
    
    // Constructor
    public Item(String idItem, String namaItem, int quantity, int stokMinimum, LocalDate expiredDate, String deskripsi) {
        this.idItem = idItem;
        this.namaItem = namaItem;
        this.quantity = quantity;
        this.stokMinimum = stokMinimum;
        this.expiredDate = expiredDate;
        this.deskripsi = deskripsi;
    }
    
    // Mengambil atribut idItem
    public String getIdItem() {
        return idItem;
    }
    
    // Mengambil atribut namaItem
    public String getNamaItem() {
        return namaItem;
    }

    // Mengambil atribut quantity
    public int getQuantity() {
        return quantity;
    }

    // Mengeset atribut quantity berdasarkan parameter yang dimasukkan
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    // Mengambil atribut stokMinimum
    public int getStokMinimum() {
        return stokMinimum;
    }
    
    // Mengeset atribut stokMinimum berdasarkan parameter yang dimasukkan
    public void setStokMinimum(int stokMinimum) {
        this.stokMinimum = stokMinimum;
    }

    // Mengambil atribut expiredDate
    public LocalDate getExpiredDate() {
        return expiredDate;
    }
    
    // Mengambil atribut deskripsi
    public String getDeskripsi() {
        return deskripsi;
    }
    
    // Mengeset atribut deskripsi berdasarkan parameter yang dimasukkan
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    // Mengecek kadaluwarsa item
    public boolean isExpired() {
        return expiredDate.isBefore(LocalDate.now());
    }
    
    public abstract String displayDetail();
}
