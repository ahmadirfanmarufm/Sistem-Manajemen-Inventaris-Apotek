package com.apotek.exception;

/**
 *
 * @author Kelompok Kipli
 */
public class ItemNotFoundException extends ApotekException {
    private final String idItem;
    
    public ItemNotFoundException(String idItem) {
        super("ITEM_NOT_FOUND", "Item dengan ID '" + idItem + "' tidak ditemukan dalam stok.");
        this.idItem = idItem;
    }
    
    public String getIdItem() {
        return idItem;
    }
}
