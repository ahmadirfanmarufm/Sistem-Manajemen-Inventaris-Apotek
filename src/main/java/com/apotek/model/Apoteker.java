package com.apotek.model;

public class Apoteker extends User {
    private String shift;
    
    // Constructor
    public Apoteker(String userId, String name, String password, String shift) {
        super(userId, name, password);
        this.shift = shift;
    }
    
    // Mengambil shift untuk dicatat
    public String getShift() {
        return shift;
    }
}
