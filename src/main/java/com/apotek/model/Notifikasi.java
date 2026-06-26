/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apotek.model;

import java.time.LocalDateTime;

/**
 *
 * @author Kelompok Kipli
 */
public class Notifikasi {
    private String idNotifikasi;
    private String tipe;
    private String judul;
    private String pesan;
    private String prioritas;
    private LocalDateTime tanggal;
    private boolean statusBaca;
    
    public Notifikasi(String idNotifikasi, String tipe, String judul, String pesan, String prioritas, LocalDateTime tanggal) {
        this.idNotifikasi = idNotifikasi;
        this.tipe = tipe;
        this.judul = judul;
        this.pesan = pesan;
        this.prioritas = prioritas;
        this.tanggal = tanggal;
        this.statusBaca = false;
    }
    
    public void buatNotifikasi(String pesanBaru) {
        this.pesan = pesanBaru;
        this.tanggal = LocalDateTime.now();
        this.statusBaca = false;
    }
    
    public void tampilkanNotifikasi() {
        String status = statusBaca ? "[DIBACA]" : "[BELUM DIBACA]";
        System.out.println(status + " [" + tanggal + "] " + judul + ": " + pesan);
    }
    
    public void tandaiSudahDibaca() {
        this.statusBaca = true;
    }
    
    public boolean isStatusBaca() {
        return statusBaca;
    }

    public String getIdNotifikasi() {
        return idNotifikasi;
    }

    public String getTipe() {
        return tipe;
    }

    public String getJudul() {
        return judul;
    }

    public String getPrioritas() {
        return prioritas;
    }
    
    public String getPesan() {
        return pesan;
    }
    
    public LocalDateTime getTanggal() {
        return tanggal;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }

    public void setTanggal(LocalDateTime tanggal) {
        this.tanggal = tanggal;
    }
    
    public void setStatusBaca(boolean statusBaca) {
        this.statusBaca = statusBaca;
    }
}
