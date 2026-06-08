/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.model;

/**
 *
 * @author Kelompok Kipli
 */
public class Transaksi {
    private String idTransaksi;
    private int nominalTransaksi;
    private String catatanTransaksi;

    public Transaksi(String idTransaksi, int nominalTransaksi, String catatanTransaksi) {
        this.idTransaksi = idTransaksi;
        this.nominalTransaksi = nominalTransaksi;
        this.catatanTransaksi = catatanTransaksi;
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
    
    public void displayInfoTransaksi() {
        System.out.println(idTransaksi + " - " + nominalTransaksi + " - " + catatanTransaksi);
    }
}
