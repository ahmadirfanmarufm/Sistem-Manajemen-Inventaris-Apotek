package com.apotek.model;

public class Apoteker extends User {
    private String shift;

    public Apoteker(String userId, String name, String password, String shift) {
        super(userId, name, password);
        this.shift = shift;
    }
    
    public String getShift() {
        return shift;
    }

    public void melihatStok() {
        System.out.println("Melihat stok...");
    }

    public void laporkanTransaksi() {
        System.out.println("Laporan transaksi...");
    }
}
