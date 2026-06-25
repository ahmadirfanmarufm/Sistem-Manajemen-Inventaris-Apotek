/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apotek.exception;

/**
 *
 * @author Kelompok Kipli
 */
public class ApotekException extends Exception {
    private final String kodeError;
    
    public ApotekException(String kodeError, String pesan) {
        super(pesan);
        this.kodeError = kodeError;
    }
    
    public String getKodeError() {
        return kodeError;
    }
    
    @Override
    public String toString() {
        return "[" + kodeError + "]" + getMessage();
    }
}
