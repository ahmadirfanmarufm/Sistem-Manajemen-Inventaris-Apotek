package com.mycompany.apotekertest.model;
import java.time.LocalDate;

public abstract class Item {
    protected String idItem;
    protected String namaItem;
    protected int quantity;
    protected int stokMinimum;
    protected LocalDate expiredDate;
    protected String deskripsi;

    public Item(String idItem, String namaItem, int quantity, int stokMinimum, LocalDate expiredDate, String deskripsi) {
        this.idItem = idItem;
        this.namaItem = namaItem;
        this.quantity = quantity;
        this.stokMinimum = stokMinimum;
        this.expiredDate = expiredDate;
        this.deskripsi = deskripsi;
    }

    public String getIdItem() {
        return idItem;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStokMinimum() {
        return stokMinimum;
    }

    public void setStokMinimum(int stokMinimum) {
        this.stokMinimum = stokMinimum;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public boolean isExpired() {
        return expiredDate.isBefore(LocalDate.now());
    }

    public abstract String displayDetail();
}
