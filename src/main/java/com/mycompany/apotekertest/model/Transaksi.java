/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.model;
import java.time.LocalDate;
/**
 *
 * @author Kelompok Kipli
 */
public class Transaksi {
    private String idTransaksi;
    private int nominalTransaksi;
    private String catatanTransaksi;
    private LocalDate tanggalTransaksi;

    public Transaksi(String idTransaksi, int nominalTransaksi, String catatanTransaksi) {
        this.idTransaksi = idTransaksi;
        this.nominalTransaksi = nominalTransaksi;
        this.catatanTransaksi = catatanTransaksi;
        this.tanggalTransaksi = LocalDate.now(); // TAMBAHKAN INI: otomatis catat tanggal hari ini
    }
    
    public Transaksi(String idTransaksi, int nominalTransaksi, String catatanTransaksi, LocalDate tanggal) {

        this.idTransaksi = idTransaksi;
        this.nominalTransaksi = nominalTransaksi;
        this.catatanTransaksi = catatanTransaksi;
        this.tanggalTransaksi = tanggal;

    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public int getNominalTransaksi() {
        return nominalTransaksi;
    }

    public String getCatatanTransaksi() {
        return catatanTransaksi;
    }

    public LocalDate getTanggalTransaksi() {
        return tanggalTransaksi;
    }
    
    public void setTanggalTransaksi(LocalDate tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }
    
    
    public void displayInfoTransaksi() {
        System.out.println(idTransaksi + " - " + nominalTransaksi + " - " + catatanTransaksi);
    }
}
