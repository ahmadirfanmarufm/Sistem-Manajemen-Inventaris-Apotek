package com.apotek.laporan;

import com.apotek.model.BahanRacikan;
import com.apotek.model.NonObat;
import com.apotek.model.ObatOTC;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Kelompok Kipli
 */
public class LaporanStok extends Laporan {
    private ArrayList<ObatOTC> listStokObatOTC;
    private ArrayList<BahanRacikan> listStokBahanRacikan;
    private ArrayList<NonObat> listStokNonObat;
    public LaporanStok(LocalDate awal, LocalDate akhir) {
        super(awal, akhir);
        listStokObatOTC = new ArrayList<>(); 
        listStokBahanRacikan = new ArrayList<>(); 
        listStokNonObat = new ArrayList<>();
    }
    
    public void setListObatOTC(ArrayList<ObatOTC> l) { 
        this.listStokObatOTC = l; 
    }
    
    public void setListBahanRacikan(ArrayList<BahanRacikan> l) { 
        this.listStokBahanRacikan = l; 
    }
    
    public void setListNonObat(ArrayList<NonObat> l) { 
        this.listStokNonObat = l; 
    }
    
    public ArrayList<ObatOTC> getListObatOTC() { 
        return listStokObatOTC; 
    }
    
    public ArrayList<BahanRacikan> getListBahanRacikan() { 
        return listStokBahanRacikan; 
    }
    
    public ArrayList<NonObat> getListNonObat() { 
        return listStokNonObat; 
    }
    
    @Override
    public void cetakLaporan() {
        System.out.println("===== LAPORAN STOK | " + periodeAwal + " s.d. " + periodeAkhir + " =====");
        listStokObatOTC.forEach(o -> System.out.println(o.displayDetail()));
        listStokBahanRacikan.forEach(b -> System.out.println(b.displayDetail()));
        listStokNonObat.forEach(n -> System.out.println(n.displayDetail()));
    }
}
