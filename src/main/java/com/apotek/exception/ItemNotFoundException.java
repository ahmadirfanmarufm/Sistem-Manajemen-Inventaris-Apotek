/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
