package com.apotek.model;

public class PJApoteker extends User {
    public PJApoteker(String userId, String name, String password) {
        super(userId, name, password);
    }

    public void melihatLaporan() {
        System.out.println("Melihat laporan...");
    }
}
