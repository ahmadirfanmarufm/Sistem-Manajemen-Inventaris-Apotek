package com.apotek.exception;

/**
 *  
 * @author Kelompok Kipli
 */
public class DuplicateItemException extends ApotekException {
    private final String idItem;
    
    public DuplicateItemException(String idItem) {
        super("DUPLICATE_ITEM", "Item dengan ID '" + idItem + "' sudah ada. Gunakan update untuk mengubah data.");
        this.idItem = idItem;
    }
    
    public String getIdItem() {
        return idItem;
    }
}
