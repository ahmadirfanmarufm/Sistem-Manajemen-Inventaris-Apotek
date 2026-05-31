/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.service;
import com.mycompany.apotekertest.model.ObatOTC;
import com.mycompany.apotekertest.model.BahanRacikan;
import com.mycompany.apotekertest.model.NonObat;
import com.mycompany.apotekertest.stok.StokObatOTC;
import com.mycompany.apotekertest.stok.StokBahanRacikan;
import com.mycompany.apotekertest.stok.StokNonObat;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author himorii
 */
public class StokService {
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
    }


    // ================= BAHAN RACIKAN =================
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
    }


    // ================= NON OBAT =================
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
    }
}
