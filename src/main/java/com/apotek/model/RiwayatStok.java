/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apotek.model;

import java.time.LocalDateTime;

/**
 *
 * @author himorii
 */
public class RiwayatStok {
    private String idItem;
    private String namaItem;
    private String tipe;
    private int jumlah;
    private LocalDateTime tanggal;

    public RiwayatStok(String idItem,
                       String namaItem,
                       String tipe,
                       int jumlah,
                       LocalDateTime tanggal){

        this.idItem = idItem;
        this.namaItem = namaItem;
        this.tipe = tipe;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
    }
    
    public String getTipe(){
        return tipe;
    }

    public int getJumlah(){
        return jumlah;
    }

    public LocalDateTime getTanggal(){
        return tanggal;
    }
}
