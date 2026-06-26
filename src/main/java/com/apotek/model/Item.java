package com.apotek.model;
import java.time.LocalDate;

public abstract class Item {
    protected String idItem;
    protected String namaItem;
    protected int quantity;
    protected int stokMinimum;
    protected LocalDate expiredDate;
    protected String deskripsi;
    private boolean notifikasiKritisAktif;
    private boolean notifikasiExpiredAktif;

    public Item(String idItem, String namaItem, int quantity, int stokMinimum, LocalDate expiredDate, String deskripsi) {
        this.idItem = idItem;
        this.namaItem = namaItem;
        this.quantity = quantity;
        this.stokMinimum = stokMinimum;
        this.expiredDate = expiredDate;
        this.deskripsi = deskripsi;
        this.notifikasiKritisAktif = false;
        this.notifikasiExpiredAktif = false;
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
    
    public boolean isNotifikasiKritisAktif() {
        return notifikasiKritisAktif;
    }

    public void setNotifikasiKritisAktif(boolean notifikasiKritisAktif) {
        this.notifikasiKritisAktif = notifikasiKritisAktif;
    }
    
    public boolean isNotifikasiExpiredAktif() {
        return notifikasiExpiredAktif;
    }

    public void setNotifikasiExpiredAktif(boolean notifikasiExpiredAktif) {
        this.notifikasiExpiredAktif = notifikasiExpiredAktif;
    }

    public abstract String displayDetail();
}
