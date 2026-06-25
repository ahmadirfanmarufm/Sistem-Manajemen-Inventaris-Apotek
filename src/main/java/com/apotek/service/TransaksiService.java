package com.apotek.service;

import com.apotek.exception.ItemNotFoundException;
import com.apotek.exception.InvalidInputException;
import com.apotek.model.Transaksi;
import com.apotek.repository.TransaksiRepository;
import java.time.LocalDate;
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
    
    public int hitungTransaksiHariIni() {
    int jumlah = 0;
    LocalDate hariIni = LocalDate.now();
    for (Transaksi t : repository.findAll()) {
        if (t.getTanggalTransaksi().equals(hariIni)) {
            jumlah++;
        }
    }
    return jumlah;
}
    
    public int getTotalTransaksi() {
    return repository.findAll().size();
}
}
