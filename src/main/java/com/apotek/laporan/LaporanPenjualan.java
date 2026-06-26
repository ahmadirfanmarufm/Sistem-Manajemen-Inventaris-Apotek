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
    
    public LaporanPenjualan(LocalDate awal, LocalDate akhir) {
        super(awal, akhir); this.rekapanTransaksi = new ArrayList<>();
    }
    
    public void tambahRekapan(RekapanTransaksi r) { 
        rekapanTransaksi.add(r); 
    }
    
    public ArrayList<RekapanTransaksi> getRekapanTransaksi() { 
        return rekapanTransaksi; 
    }
    
    public int hitungLaba() {
        int t = 0; for (RekapanTransaksi r : rekapanTransaksi) t += r.hitungTotalNominal(); return t;
    }
    
    @Override
    public void cetakLaporan() {
        System.out.println("===== LAPORAN PENJUALAN | " + periodeAwal + " s.d. " + periodeAkhir + " =====");
        System.out.println("Total: Rp" + hitungLaba());
    }
}
