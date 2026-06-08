package com.mycompany.apotekertest.service;

import com.mycompany.apotekertest.exception.DuplicateItemException;
import com.mycompany.apotekertest.exception.InvalidInputException;
import com.mycompany.apotekertest.exception.ItemNotFoundException;
import com.mycompany.apotekertest.model.BahanRacikan;
import com.mycompany.apotekertest.model.NonObat;
import com.mycompany.apotekertest.model.NotifikasiStok;
import com.mycompany.apotekertest.model.ObatOTC;
import com.mycompany.apotekertest.stok.StokBahanRacikan;
import com.mycompany.apotekertest.stok.StokNonObat;
import com.mycompany.apotekertest.stok.StokObatOTC;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class StokService {
    private final StokObatOTC stokObatOTC;
    private final StokBahanRacikan stokBahanRacikan;
    private final StokNonObat stokNonObat;
    
    // ================= CONSTRUCTOR =================
    public StokService(StokObatOTC stokObatOTC, StokBahanRacikan stokBahanRacikan, StokNonObat stokNonObat) {
        this.stokObatOTC = stokObatOTC;
        this.stokBahanRacikan = stokBahanRacikan;
        this.stokNonObat = stokNonObat;
    }

    // ================= OBAT OTC =====================
    public void tambahObat(String kategori, double hargaBeli, double hargaJual,
                           String id, String nama, int qty, int stokMin,
                           String expStr, String deskripsi)
            throws DuplicateItemException, InvalidInputException {
        validateId(id); 
        validateNama(nama); 
        validateExp(expStr);
        
        try {
            stokObatOTC.tambah(new ObatOTC(kategori, hargaBeli, hargaJual,
                id, nama, qty, stokMin, LocalDate.parse(expStr), deskripsi));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Tgl Expired", "format harus yyyy-MM-dd");
        }
    }

    public void hapusObat(String id) throws ItemNotFoundException {
        stokObatOTC.hapus(id);
    }
    
    public void updateObat(ObatOTC obat) throws ItemNotFoundException {
        stokObatOTC.update(obat);
    }
 
    public ObatOTC getObatById(String id) throws ItemNotFoundException {
        return stokObatOTC.getById(id);
    }
 
    public ArrayList<ObatOTC> getSemuaObat() {
        return stokObatOTC.getListObat();
    }
 
    public StokObatOTC getStokObatOTC() {
        return stokObatOTC;
    }

    // ================= BAHAN RACIKAN =================
    public void tambahBahanRacikan(String id, String nama, String satuan,
                            int qty, int stokMin, String expStr, String deskripsi)
            throws DuplicateItemException, InvalidInputException {
        validateId(id); 
        validateExp(expStr);
        
        try {
            stokBahanRacikan.tambah(new BahanRacikan(id, nama, satuan, qty, stokMin,
                LocalDate.parse(expStr), deskripsi));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Tgl Expired", "format harus yyyy-MM-dd");
        }
    }

    public void hapusBahanRacikan(String id) throws ItemNotFoundException {
        stokBahanRacikan.hapus(id);
    }
    
    public void updateBahanRacikan(BahanRacikan b) throws ItemNotFoundException {
        stokBahanRacikan.update(b);
    }
    
    public BahanRacikan getBahanById(String id) throws ItemNotFoundException {
        return stokBahanRacikan.getById(id);
    }

    public ArrayList<BahanRacikan> getSemuaBahanRacikan() {
        return stokBahanRacikan.getListBahanRacikan();
    }

    public StokBahanRacikan getStokBahanRacikan() {
        return stokBahanRacikan;
    }

    // ================= NON OBAT =================
    public void tambahNonObat(String kategori, double hargaBeli, double hargaJual,
                               String id, String nama, int qty, int stokMin,
                               String expStr, String deskripsi)
            throws DuplicateItemException, InvalidInputException {
        validateId(id); 
        validateNama(nama); 
        validateExp(expStr);
        
        try {
            stokNonObat.tambah(new NonObat(kategori, hargaBeli, hargaJual,
                id, nama, qty, stokMin, LocalDate.parse(expStr), deskripsi));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Tgl Expired", "format harus yyyy-MM-dd");
        }
    }

    public void hapusNonObat(String id) throws ItemNotFoundException { 
        stokNonObat.hapus(id); 
    }
    
    public void updateNonObat(NonObat n) throws ItemNotFoundException { 
        stokNonObat.update(n); 
    }
    
    public NonObat getNonObatById(String id) throws ItemNotFoundException { 
        return stokNonObat.getById(id); 
    }
    
    public ArrayList<NonObat> getSemuaNonObat() { 
        return stokNonObat.getListNonObat(); 
    }
    
    public StokNonObat getStokNonObat() { 
        return stokNonObat; 
    }
    
    // ================= NOTIFIKASI =================
    public ArrayList<NotifikasiStok> getAllNotifikasi() {
        ArrayList<NotifikasiStok> all = new ArrayList<>();
        all.addAll(stokObatOTC.getListNotifikasi());
        all.addAll(stokBahanRacikan.getListNotifikasi());
        all.addAll(stokNonObat.getListNotifikasi());
        return all;
    }

    // ================= VALIDATOR =================
    private void validateId(String id) throws InvalidInputException {
        if (id == null || id.isBlank()) throw new InvalidInputException("ID Item", "tidak boleh kosong");
    }
    
    private void validateNama(String nama) throws InvalidInputException {
        if (nama == null || nama.isBlank()) throw new InvalidInputException("Nama", "tidak boleh kosong");
    }
    
    private void validateExp(String exp) throws InvalidInputException {
        if (exp == null || exp.isBlank()) throw new InvalidInputException("Tgl Expired", "tidak boleh kosong");
    }
}