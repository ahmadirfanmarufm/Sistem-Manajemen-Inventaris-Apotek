/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.stok;

/**
 *
 * @author himorii
 */
public abstract class Stok implements Manageable {
    protected int minimumStok;

    public Stok(int minimumStok) {
        this.minimumStok = minimumStok;
    }

    public int getMinimumStok() {
        return minimumStok;
    }

    public void setMinimumStok(int minimumStok) {
        this.minimumStok = minimumStok;
    }
    
    public abstract void displayStok();
}
