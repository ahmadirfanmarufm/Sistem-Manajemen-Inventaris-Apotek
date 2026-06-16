/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.service;

import com.mycompany.apotekertest.exception.ItemNotFoundException;
import com.mycompany.apotekertest.exception.InvalidInputException;
import com.mycompany.apotekertest.model.Transaksi;
import com.mycompany.apotekertest.repository.TransaksiRepository;

/**
 *
 * @author Kelompok Kipli
 */
public class TransaksiService {
    private final TransaksiRepository repository;
    
    public TransaksiService() {
        repository = new TransaksiRepository();
    }

    public void tambahTransaksi(String id, int nominal, String catatan) throws InvalidInputException, DuplicateTransaksiException {
        if (id == null || id.isBlank())  {
            throw new InvalidInputException("ID Transaksi", "tidak boleh kosong");
        }
        if (repository.exists(id)) {
            throw new DuplicateTransaksiException(id);
        }
        repository.simpan(new Transaksi(id, nominal, catatan));
    }
    
    public void hapusTransaksi(String id) throws ItemNotFoundException {
        repository.hapus(id);
    }
    
    public Transaksi cariTransaksi(String id) throws ItemNotFoundException {
        return repository.cariById(id);
    }
    
    public int hitungTotal() {
        int total = 0;
        for (Transaksi t : repository.findAll()) {
            total += t.getNominalTransaksi();
        }
        return total;
    }

    public static class DuplicateTransaksiException extends Exception {
        public DuplicateTransaksiException(String id) {
            super("ID Transaksi '" + id + "' sudah ada.");
        }
    }
}
