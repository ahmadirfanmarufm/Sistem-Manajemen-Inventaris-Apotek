/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.service;

import com.mycompany.apotekertest.exception.ItemNotFoundException;
import com.mycompany.apotekertest.exception.InvalidInputException;
import com.mycompany.apotekertest.manager.DashboardManager;
import com.mycompany.apotekertest.model.Transaksi;
import com.mycompany.apotekertest.repository.TransaksiRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Kelompok Kipli
 */
public class TransaksiService {
    private final TransaksiRepository repository;
    private final DashboardManager dashboardManager;
    private List<Runnable> listeners = new ArrayList<>();
    
    public TransaksiService(DashboardManager dashboardManager) {
        this.dashboardManager = dashboardManager;
        repository = new TransaksiRepository();
        loadDummyData();
    }

    private void loadDummyData() {
        try {
            tambahTransaksi(
                    "TRX001",
                    250000,
                    "Paracetamol", LocalDate.of(LocalDate.now().getYear(), 1, 5));

            cariTransaksi("TRX001")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 1, 5));


            tambahTransaksi(
                    "TRX002",
                    175000,
                    "Vitamin C", LocalDate.of(LocalDate.now().getYear(), 1, 5));

            cariTransaksi("TRX002")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 1, 18));


            tambahTransaksi(
                    "TRX003",
                    420000,
                    "Amoxicillin", LocalDate.of(LocalDate.now().getYear(), 1, 5));

            cariTransaksi("TRX003")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 2, 3));


            tambahTransaksi(
                    "TRX004",
                    380000,
                    "OBH", LocalDate.of(LocalDate.now().getYear(), 1, 5));

            cariTransaksi("TRX004")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 2, 19));


            tambahTransaksi(
                    "TRX005",
                    520000,
                    "Betadine", LocalDate.of(LocalDate.now().getYear(), 1, 5));

            cariTransaksi("TRX005")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 3, 12));


            tambahTransaksi(
                    "TRX006",
                    630000,
                    "Paracetamol", LocalDate.of(LocalDate.now().getYear(), 1, 5));

            cariTransaksi("TRX006")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 4, 9));


            tambahTransaksi(
                    "TRX007",
                    470000,
                    "Vitamin", LocalDate.of(LocalDate.now().getYear(), 1, 5));

            cariTransaksi("TRX007")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 5, 25));


            tambahTransaksi(
                    "TRX008",
                    815000,
                    "Masker", LocalDate.of(LocalDate.now().getYear(), 1, 5));

            cariTransaksi("TRX008")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 6, 26));


            tambahTransaksi(
                    "TRX009",
                    720000,
                    "Hand Sanitizer", LocalDate.of(LocalDate.now().getYear(), 1, 5));

            cariTransaksi("TRX009")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 7, 11));


            tambahTransaksi(
                    "TRX010",
                    940000,
                    "Insulin", LocalDate.of(LocalDate.now().getYear(), 1, 5));

            cariTransaksi("TRX010")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 8, 4));


            tambahTransaksi(
                    "TRX011",
                    1100000,
                    "Infus", LocalDate.of(LocalDate.now().getYear(), 1, 5));

            cariTransaksi("TRX011")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 9, 15));


            tambahTransaksi(
                    "TRX012",
                    980000,
                    "Antibiotik", LocalDate.of(LocalDate.now().getYear(), 1, 5));

            cariTransaksi("TRX012")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 10, 3));


            tambahTransaksi(
                "TRX013",
                1350000,
                "Vitamin",
                LocalDate.of(LocalDate.now().getYear(), 1, 5)
            );

            cariTransaksi("TRX013")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 11, 21));


            tambahTransaksi(
                "TRX014",
                1550000,
                "Paket Obat",
                LocalDate.of(LocalDate.now().getYear(), 1, 5)
            );

            cariTransaksi("TRX014")
                    .setTanggalTransaksi(LocalDate.of(LocalDate.now().getYear(), 12, 28));

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    public void tambahTransaksi(String id,int nominal, String catatan, LocalDate tanggal) throws InvalidInputException, DuplicateTransaksiException {
        if (id == null || id.isBlank()) {
            throw new InvalidInputException("ID Transaksi", "tidak boleh kosong");
        }

        if (repository.exists(id)) {
            throw new DuplicateTransaksiException(id);
        }

        repository.simpan(new Transaksi(id, nominal, catatan, tanggal));
        dashboardManager.notifyDashboardObservers();
    }
    
    public void hapusTransaksi(String id) throws ItemNotFoundException {
        repository.hapus(id);
        dashboardManager.notifyDashboardObservers();
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
    
    public int[] hitungTransaksiPerBulan(int tahun) {
        int[] jumlah = new int[12];
        
        for(Transaksi t : repository.findAll()) {
            LocalDate tanggal = t.getTanggalTransaksi();
            if(tanggal.getYear() == tahun) {
                int bulan = tanggal.getMonthValue() - 1;
                jumlah[bulan]++;
            }
        }
        
        return jumlah;
    }
    
    public int[] hitungOmzetPerBulan(int tahun){
        int[] omzet = new int[12];
        
        for(Transaksi t : repository.findAll()){
            LocalDate tanggal = t.getTanggalTransaksi();
            if(tanggal.getYear() == tahun){
                int bulan = tanggal.getMonthValue() - 1;
                omzet[bulan] += t.getNominalTransaksi();
            }
        }
        
        return omzet;
    }
    
    public int getTotalTransaksi() {
        return repository.findAll().size();
    }
}
