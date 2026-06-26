package com.apotek.model;

import java.util.ArrayList;

/**
 *
 * @author Kelompok Kipli
 */
public class RekapanTransaksi {
    private ArrayList<Transaksi> rekapanTransaksi;
    
    // Construcor
    public RekapanTransaksi() {
        this.rekapanTransaksi = new ArrayList<>();
    }
    
    // menambahkan transaksi ke dalam rekapan transaksi (grouping)
    public void tambahTransaksi(Transaksi transaksi) {
        rekapanTransaksi.add(transaksi);
    }
    
    // Menghitung total laba/nominal transaksi
    public int hitungTotalNominal() {
        int total = 0;
        for(Transaksi t : rekapanTransaksi) {
            total += t.getNominalTransaksi();
        }
        return total;
    }
    
    public Transaksi cariTransaksi(String id) {
        for(Transaksi t : rekapanTransaksi) {
            if(t.getIdTransaksi().equals(id)) return t;
        }
        return null;
    }
    
    public ArrayList<Transaksi> getRekapanTransaksi() {
        return rekapanTransaksi;
    }
}
