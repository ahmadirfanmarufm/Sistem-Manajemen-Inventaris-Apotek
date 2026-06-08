/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.service;

import com.mycompany.apotekertest.exception.ItemNotFoundException;
import com.mycompany.apotekertest.exception.InvalidInputException;
import com.mycompany.apotekertest.model.Transaksi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author Kelompok Kipli
 */
public class TransaksiService {
    private final ArrayList<Transaksi> listTransaksi  = new ArrayList<>();
    private final HashMap<String,Transaksi> mapTransaksi   = new LinkedHashMap<>();

    public void tambahTransaksi(String id, int nominal, String catatan) throws InvalidInputException, DuplicateTransaksiException {
        if (id == null || id.isBlank())  {
            throw new InvalidInputException("ID Transaksi", "tidak boleh kosong");
        }
        if (mapTransaksi.containsKey(id)) {
            throw new DuplicateTransaksiException(id);
        }
        Transaksi t = new Transaksi(id, nominal, catatan);
        listTransaksi.add(t); mapTransaksi.put(id, t);
    }

    public void tambahTransaksi(Transaksi t) {
        if (!mapTransaksi.containsKey(t.getIdTransaksi())) {
            listTransaksi.add(t); mapTransaksi.put(t.getIdTransaksi(), t);
        }
    }
    
    public void hapusTransaksi(String id) throws ItemNotFoundException {
        if (!mapTransaksi.containsKey(id)) {
            throw new ItemNotFoundException(id);
        }
        listTransaksi.removeIf(t -> t.getIdTransaksi().equals(id));
        mapTransaksi.remove(id);
    }
    
    public Transaksi cariTransaksi(String id) throws ItemNotFoundException {
        Transaksi t = mapTransaksi.get(id);
        if (t == null) {
            throw new ItemNotFoundException(id);
        }
        return t;
    }
    
    public int hitungTotal() {
        int total = 0;
        for (Transaksi t : listTransaksi) total += t.getNominalTransaksi();
        return total;
    }
    
    public ArrayList<Transaksi> getData() { 
        return listTransaksi; 
    }
    public int getSize() { 
        return listTransaksi.size(); 
    }

    public static class DuplicateTransaksiException extends Exception {
        public DuplicateTransaksiException(String id) {
            super("ID Transaksi '" + id + "' sudah ada.");
        }
    }
}
