package com.apotek.model;

import java.util.ArrayList;

/**
 *
 * @author Kelompok Kipli
 */
public class RekapanTransaksi {
    private ArrayList<Transaksi> rekapanTransaksi;
    
    public RekapanTransaksi() {
        this.rekapanTransaksi = new ArrayList<>();
    }
    
    public void tambahTransaksi(Transaksi transaksi) {
        rekapanTransaksi.add(transaksi);
    }
    
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
