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
