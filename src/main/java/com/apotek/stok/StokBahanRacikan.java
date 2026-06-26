package com.apotek.stok;

import com.apotek.exception.DuplicateItemException;
import com.apotek.exception.ItemNotFoundException;
import com.apotek.model.BahanRacikan;
import com.apotek.model.Item;
import com.apotek.model.PJApoteker;
import com.apotek.model.User;
import com.apotek.service.LoginSession;
import com.apotek.ui.MainApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class StokBahanRacikan extends Stok {
    private final HashMap<String, BahanRacikan> mapBahan;
    private final ArrayList<BahanRacikan> listBahanRacikan;
 
    public StokBahanRacikan(int minimumStok) {
        super(minimumStok);
        this.mapBahan = new LinkedHashMap<>();
        this.listBahanRacikan = new ArrayList<>();
    }
    
    public StokBahanRacikan(){
        this.mapBahan = new LinkedHashMap<>();
        this.listBahanRacikan = new ArrayList<>();
    }

    @Override
    public void tambah(Item item) throws DuplicateItemException {
        validasiDuplikat(item.getIdItem());
        BahanRacikan bahan = (BahanRacikan) item;
        mapBahan.put(bahan.getIdItem(), bahan);
        listBahanRacikan.add(bahan);
        simpan(bahan);
        cekDanBuatNotifikasiStok(bahan);
        cekDanBuatNotifikasiExpired(bahan);
        
        User user = LoginSession.getCurrentUser();
        
        String pelaku;
        
        if(user == null) {
            pelaku = "Sistem";
            return;
        }
       
        String role = user instanceof PJApoteker ? "PJ Apoteker" : "Apoteker";
        
        pelaku = user.getName() + " ( " + role + ")";
        
        MainApp.notifikasiManager.kirimNotifikasi(
            "SUCCESS",
            "Barang ditambahkan",
            item.getNamaItem() + " berhasil ditambahkan ke inventaris oleh " + pelaku,
            "Rendah"
        ); 
    }

    @Override
    public void hapus(String id) throws ItemNotFoundException {
        validasiAda(id);
        mapBahan.remove(id);
        listBahanRacikan.removeIf(b -> b.getIdItem().equals(id));
        hapusDariStruktur(id);
        
        User user = LoginSession.getCurrentUser();
        
        String pelaku;
        
        if(user == null) {
            pelaku = "Sistem";
        }
       
        String role = user instanceof PJApoteker ? "PJ Apoteker" : "Apoteker";
        pelaku = user.getName() + " ( " + role + ")";
        
        String nama = getById(id).getNamaItem();
        
        MainApp.notifikasiManager.kirimNotifikasi(
            "SUCCESS",
            "Barang Dihapus",
            nama + " berhasil dihapus dari inventaris oleh " + pelaku,
            "Rendah"
        ); 
    }

    @Override
    public void update(Item item) throws ItemNotFoundException {
        validasiAda(item.getIdItem());
        BahanRacikan data = (BahanRacikan) item;
        
        BahanRacikan bahanRacikanLama = getById(data.getIdItem());
        int qtyLama = bahanRacikanLama.getQuantity();

        mapBahan.put(data.getIdItem(), data);
        for(int x = 0 ; x < listBahanRacikan.size(); x++) {
            if(listBahanRacikan.get(x).getIdItem().equals(data.getIdItem())) {
                listBahanRacikan.set(x, data);
                break;
            }
        }
        updateDiStruktur(data);
        cekDanBuatNotifikasiStok(data);
        cekDanBuatNotifikasiExpired(data);
        
        BahanRacikan bahanRacikanBaru = getById(data.getIdItem());
        int qtyBaru = bahanRacikanBaru.getQuantity();
        
        User user = LoginSession.getCurrentUser();
        
        String pelaku;
        
        if(user == null) {
            pelaku = "Sistem";
        }
       
        String role = user instanceof PJApoteker ? "PJ Apoteker" : "Apoteker";
        
        pelaku = user.getName() + " ( " + role + ")";
        
        if(!bahanRacikanLama.getNamaItem().equalsIgnoreCase(bahanRacikanBaru.getNamaItem())) {
            MainApp.notifikasiManager.kirimNotifikasi(
                "INFO",
                "Nama Barang Diubah",
                "Nama barang diubah dari '"
                    + bahanRacikanLama.getNamaItem()
                    + "' menjadi '"
                    + bahanRacikanBaru.getNamaItem()
                    + "' oleh " + pelaku,
                "Rendah"
            );
        } else if(!bahanRacikanLama.getSatuan().equalsIgnoreCase(bahanRacikanBaru.getSatuan())) {
            MainApp.notifikasiManager.kirimNotifikasi(
                "INFO",
                "Satuan Diubah",
                bahanRacikanBaru.getNamaItem()
                    + " diubah satuan dari "
                    + bahanRacikanLama.getSatuan()
                    + " ke "
                    + bahanRacikanBaru.getSatuan() + " oleh " 
                    + pelaku,
                "Rendah"
            );
        } else if(!bahanRacikanLama.getDeskripsi().equalsIgnoreCase(bahanRacikanBaru.getDeskripsi())) {
            MainApp.notifikasiManager.kirimNotifikasi(
                "INFO",
                "Deskripsi Diubah",
                "Deskripsi barang "
                    + bahanRacikanBaru.getNamaItem()
                    + " telah diperbarui oleh " 
                    + pelaku,
                "Rendah"
            );
        } else {
            StringBuilder perubahan = new StringBuilder();
            if(!bahanRacikanLama.getNamaItem().equalsIgnoreCase(bahanRacikanBaru.getNamaItem())) {
                perubahan.append("Nama, ");
            }

            if(!bahanRacikanLama.getSatuan().equalsIgnoreCase(bahanRacikanBaru.getSatuan())) {
                perubahan.append("Satuan, ");
            }

            if(!bahanRacikanLama.getDeskripsi().equalsIgnoreCase(bahanRacikanBaru.getDeskripsi())) {
                perubahan.append("Deskripsi, ");
            }

            if(bahanRacikanLama.getQuantity() != bahanRacikanBaru.getQuantity()) {
                perubahan.append("Stok, ");
            }

            if(perubahan.length() > 0) {
                String field = perubahan.substring(0,perubahan.length() - 2);
                MainApp.notifikasiManager.kirimNotifikasi(
                    "INFO",
                    "Data Barang Diperbarui",
                    "Perubahan pada "
                        + bahanRacikanBaru.getNamaItem()
                        + ": "
                        + field
                        + ". Dilakukan oleh"
                        + pelaku,
                    "Rendah"
                );
            }
        }
    }

    @Override
    public void displayStok() {
        if(listBahanRacikan.isEmpty()) {
            System.out.println("Stok bahan racikan kosong.");
        }
        for(BahanRacikan b : listBahanRacikan) {
            System.out.println(b.displayDetail());
        }
    }

    public BahanRacikan getById(String id) throws ItemNotFoundException {
        BahanRacikan b = mapBahan.get(id);
        if(b == null) throw new ItemNotFoundException(id);
        return b;
    }

    public ArrayList<BahanRacikan> getListBahanRacikan() {
        return listBahanRacikan;
    }

    public HashMap<String, BahanRacikan> getMapBahanRacikan() {
        return mapBahan;
    }
}
