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

/**
 *
 * @author himorii
 */
public class StokService {
    private StokObatOTC stokObatOTC = new StokObatOTC(5);
    private StokBahanRacikan stokBahan = new StokBahanRacikan(5);
    private StokNonObat stokNonObat = new StokNonObat(5);

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

        stokObatOTC.tambah(obat);
    }

    public void hapusObat(String id) {
        stokObatOTC.hapus(id);
    }

    public ObatOTC[] getSemuaObat() {
        return stokObatOTC.getList();
    }

    public int getJumlahObat() {
        return stokObatOTC.getSize();
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

        stokBahan.tambah(bahan);
    }

    public void hapusBahanRacikan(String id) {
        stokBahan.hapus(id);
    }

    public BahanRacikan[] getSemuaBahan() {
        return stokBahan.getList();
    }

    public int getJumlahBahan() {
        return stokBahan.getSize();
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

        stokNonObat.tambah(item);
    }

    public void hapusNonObat(String id) {
        stokNonObat.hapus(id);
    }

    public NonObat[] getSemuaNonObat() {
        return stokNonObat.getList();
    }

    public int getJumlahNonObat() {
        return stokNonObat.getSize();
    }
}
