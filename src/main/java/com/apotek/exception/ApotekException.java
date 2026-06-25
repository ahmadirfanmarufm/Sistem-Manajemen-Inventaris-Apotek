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
