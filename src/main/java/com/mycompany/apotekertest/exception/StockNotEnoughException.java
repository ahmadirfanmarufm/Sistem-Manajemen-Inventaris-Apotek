package com.mycompany.apotekertest.exception;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kelompok Kipli
 */
public class StockNotEnoughException extends ApotekException {
    private final String namaItem;
    private final int stokSaatIni;
    private final int stokMinimum;
    
    public StockNotEnoughException(String namaItem, int stokSaatIni, int stokMinimum) {
        super("STOCK_NOT_ENOUGH", "Stok " + namaItem + " hampir habis! " + "Sisa: " + stokSaatIni + ", Minimum: " + stokMinimum);
        this.namaItem = namaItem;
        this.stokSaatIni = stokSaatIni;
        this.stokMinimum = stokMinimum;
    }
    
    public String getNamaItem() {
        return namaItem;
    }
    
    public int getStokSaatIni() {
        return stokSaatIni;
    }
    
    public int getStokMinimum() {
        return stokMinimum;
    }
}
