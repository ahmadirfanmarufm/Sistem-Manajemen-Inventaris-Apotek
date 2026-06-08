/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
<<<<<<< HEAD
=======
import java.time.format.DateTimeParseException;
>>>>>>> 1ccd6b4 (feat: implement PBO architecture with services, exceptions, collections)
import java.util.ArrayList;

/**
 *
 * @author Kelompok Kipli
 */
public class StokService {
<<<<<<< HEAD
    private ArrayList<ObatOTC> stokObatOTC = new ArrayList<>(); 
    private ArrayList<BahanRacikan> stokBahan = new ArrayList<>();
    private ArrayList<NonObat> stokNonObat = new ArrayList<>(); 

    // ================= OBAT =================
    public void tambahObat(
            String id, String nama, String kategori,
            int qty, int stokMin,
            double hargaBeli, double hargaJual,
            String deskripsi
    ) {

        ObatOTC obat = new ObatOTC(
                kategori,
                hargaBeli,
                hargaJual,
                id,
                nama,
                qty,
                stokMin,
                LocalDate.parse("2026-12-31"),
                deskripsi
        );

        stokObatOTC.add(obat);
    }

    public void hapusObat(String id) {
        stokObatOTC.removeIf(obat -> obat.getIdItem().equals(id));
    }

    public ArrayList<ObatOTC> getSemuaObat() {
        return stokObatOTC;
    }

    public int getJumlahObat() {
        return stokObatOTC.size();
=======
    private final StokObatOTC stokObatOTC;
    private final StokBahanRacikan stokBahanRacikan;
    private final StokNonObat stokNonObat;
    
    // ================= CONSTRUCTOR =================
    public StokService(StokObatOTC stokObatOTC, StokBahanRacikan stokBahanRacikan, StokNonObat stokNonObat) {
        this.stokObatOTC = stokObatOTC;
        this.stokBahanRacikan = stokBahanRacikan;
        this.stokNonObat = stokNonObat;
    }

    // ================== OBAT =======================
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
>>>>>>> 1ccd6b4 (feat: implement PBO architecture with services, exceptions, collections)
    }


    // ================= BAHAN RACIKAN =================
<<<<<<< HEAD
    public void tambahBahanRacikan(
            String id, String nama,
            String satuan,
            int qty, int stokMin,
            String deskripsi
    ) {

        BahanRacikan bahan = new BahanRacikan(
                id,
                nama,
                satuan,
                qty,
                stokMin,
                LocalDate.of(2026, 12, 31),
                deskripsi
        );

        stokBahan.add(bahan);
    }

    public void hapusBahanRacikan(String id) {
        stokBahan.removeIf(bahan -> bahan.getIdItem().equals(id));
    }

    public ArrayList<BahanRacikan> getSemuaBahan() {
        return stokBahan;
    }

    public int getJumlahBahan() {
        return stokBahan.size();
=======
    public void tambahBahanRacikan(String id, String nama, String satuan,
                            int qty, int stokMin, String expStr, String deskripsi)
            throws DuplicateItemException, InvalidInputException {
        validateId(id); validateExp(expStr);
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
>>>>>>> 1ccd6b4 (feat: implement PBO architecture with services, exceptions, collections)
    }


    // ================= NON OBAT =================
<<<<<<< HEAD
    public void tambahNonObat(
            String id, String nama, String kategori,
            int qty, int stokMin,
            double hargaBeli, double hargaJual,
            String deskripsi
    ) {

        NonObat item = new NonObat(
                kategori,
                hargaBeli,
                hargaJual,
                id,
                nama,
                qty,
                stokMin,
                LocalDate.parse("2026-12-31"),
                deskripsi
        );

        stokNonObat.add(item);
    }

    public void hapusNonObat(String id) {
        stokNonObat.removeIf(NonObat -> NonObat.getIdItem().equals(id));
    }

    public ArrayList<NonObat> getSemuaNonObat() {
        return stokNonObat;
    }

    public int getJumlahNonObat() {
        return stokNonObat.size();
=======
    public void tambahNonObat(String kategori, double hargaBeli, double hargaJual,
                               String id, String nama, int qty, int stokMin,
                               String expStr, String deskripsi)
            throws DuplicateItemException, InvalidInputException {
        validateId(id); validateNama(nama); validateExp(expStr);
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

    private void validateId(String id) throws InvalidInputException {
        if (id == null || id.isBlank()) throw new InvalidInputException("ID Item", "tidak boleh kosong");
    }
    private void validateNama(String nama) throws InvalidInputException {
        if (nama == null || nama.isBlank()) throw new InvalidInputException("Nama", "tidak boleh kosong");
    }
    private void validateExp(String exp) throws InvalidInputException {
        if (exp == null || exp.isBlank()) throw new InvalidInputException("Tgl Expired", "tidak boleh kosong");
>>>>>>> 1ccd6b4 (feat: implement PBO architecture with services, exceptions, collections)
    }
}
