package com.mycompany.apotekertest.repository;

import com.mycompany.apotekertest.exception.ItemNotFoundException;
import com.mycompany.apotekertest.model.Transaksi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kelompok Kipli
 */
public class TransaksiRepository {
    private final HashMap<String, Transaksi> dataTransaksi;
    
    public TransaksiRepository() {
        this.dataTransaksi = new LinkedHashMap<>();
    }
    
    public void simpan(Transaksi transaksi) {
        dataTransaksi.put(transaksi.getIdTransaksi(), transaksi);
    }
    
    public void hapus(String idTransaksi) throws ItemNotFoundException {
        if(!dataTransaksi.containsKey(idTransaksi)) {
            throw new ItemNotFoundException(idTransaksi);
        }
        
        dataTransaksi.remove(idTransaksi);
    }
    
    public Transaksi cariById(String idTransaksi) throws ItemNotFoundException {
        Transaksi transaksi = dataTransaksi.get(idTransaksi);
        
        if(transaksi == null) {
            throw new ItemNotFoundException(idTransaksi);
        }
        
        return transaksi;
    }
    
    public ArrayList<Transaksi> findAll() {
        return new ArrayList<>(dataTransaksi.values());
    }
    
    public boolean exists(String idTransaksi) {
        return dataTransaksi.containsKey(idTransaksi);
    }
    
    public int count() {
        return dataTransaksi.size();
    }
    
    public HashMap<String, Transaksi> getDataTransaksi() {
        return dataTransaksi;
    }
}
