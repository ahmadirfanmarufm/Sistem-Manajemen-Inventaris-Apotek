/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apotek.exception;

/**
 *
 * @author Kelompok Kipli
 */
public class InvalidInputException extends ApotekException {
    private final String namaField;
    
    public InvalidInputException(String namaField, String alasan) {
        super("INVALID_INPUT", "Input tidak valid pada field '" + namaField + "': " + alasan);
        this.namaField = namaField;
    }
    
    public String getNamaField() {
        return namaField;
    }
}
