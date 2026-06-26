package com.apotek.laporan;

import com.apotek.model.RekapanTransaksi;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Kelompok Kipli
 */
public class LaporanPenjualan extends Laporan {
    private ArrayList<RekapanTransaksi> rekapanTransaksi;
    
    // Constructor
    public LaporanPenjualan(LocalDate awal, LocalDate akhir) {
        super(awal, akhir); this.rekapanTransaksi = new ArrayList<>();
    }
    
    // Menambah rekapan transaksi
    public void tambahRekapan(RekapanTransaksi r) { 
        rekapanTransaksi.add(r); 
    }
    
    // Mengambil semua rekapan transaksi dalam atribut rekapanTransaksi
    public ArrayList<RekapanTransaksi> getRekapanTransaksi() { 
        return rekapanTransaksi; 
    }
    
    // Method untuk menghitung seluruh laba dari rekapan transaksi yang ada pada sistem
    public int hitungLaba() {
        int t = 0; 
        for (RekapanTransaksi r : rekapanTransaksi) {
            // Mengambil nilai nominal seluruh transaksi dalam rekapan transaksi kemudian dijumlahkan dan dimasukkan ke dalam t
            t += r.hitungTotalNominal();
            
        }
        return t;
    }
    
    // Mencetak laporan penjualan
    @Override
    public void cetakLaporan() {
        System.out.println("===== LAPORAN PENJUALAN | " + periodeAwal + " s.d. " + periodeAkhir + " =====");
        System.out.println("Total: Rp" + hitungLaba());
    }
}
