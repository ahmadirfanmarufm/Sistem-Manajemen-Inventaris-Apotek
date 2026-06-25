package com.apotek.model;

import java.time.LocalDate;
/**
 *
 * @author nelse
 */
public class StokMasuk {
    private String idTransaksi;
    private LocalDate tanggal;
    private String idBarang;
    private String namaBarang;
    private String kategori; // "Obat OTC" / "Bahan Racikan" / "Non Obat"
    private int jumlahMasuk;
    private String pemasok;

    public StokMasuk(String idTransaksi, LocalDate tanggal, String idBarang, String namaBarang,
                      String kategori, int jumlahMasuk, String pemasok) {
        this.idTransaksi = idTransaksi;
        this.tanggal = tanggal;
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.jumlahMasuk = jumlahMasuk;
        this.pemasok = pemasok;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getKategori() {
        return kategori;
    }

    public int getJumlahMasuk() {
        return jumlahMasuk;
    }

    public String getPemasok() {
        return pemasok;
    }
}
