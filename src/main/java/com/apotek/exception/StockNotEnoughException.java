package com.apotek.exception;

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
