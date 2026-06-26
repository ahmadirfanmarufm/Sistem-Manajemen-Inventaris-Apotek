package com.apotek.model;

import java.time.LocalDate;

/**
 *
 * @author Kelompok Kipli
 */
public class NotifikasiStok {
    private String idNotifikasi;
    private String pesan;
    private LocalDate tanggal;
    private boolean statusBaca;
    
    public NotifikasiStok(String idNotifikasi, String pesan) {
        this.idNotifikasi = idNotifikasi;
        this.pesan = pesan;
        this.tanggal = LocalDate.now();
        this.statusBaca = false;
    }
    
    public void buatNotifikasi(String pesanBaru) {
        this.pesan = pesanBaru;
        this.tanggal = LocalDate.now();
        this.statusBaca = false;
    }
    
    public void tampilkanNotifikasi() {
        String status = statusBaca ? "[DIBACA]" : "[BELUM DIBACA]";
        System.out.println(status + " [" + tanggal + "] " + idNotifikasi + ": " + pesan);
    }
    
    public void tandaDibaca() {
        this.statusBaca = true;
    }
    
    public boolean isStatusBaca() {
        return statusBaca;
    }
    
    public String getPesan() {
        return pesan;
    }
    
    public LocalDate getTanggal() {
        return tanggal;
    }
}
