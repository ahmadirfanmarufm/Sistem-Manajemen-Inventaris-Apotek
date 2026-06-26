package com.apotek.stok;

import com.apotek.exception.DuplicateItemException;
import com.apotek.exception.ItemNotFoundException;
import com.apotek.model.ObatOTC;
import com.apotek.model.Item;
import com.apotek.model.PJApoteker;
import com.apotek.model.User;
import com.apotek.service.LoginSession;
import com.apotek.ui.MainApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class StokObatOTC extends Stok {
    private final HashMap<String, ObatOTC> mapObat;
    private final ArrayList<ObatOTC> listObat;

    public StokObatOTC(int minimumStok) {
        super(minimumStok);
        this.mapObat = new LinkedHashMap<>();
        this.listObat = new ArrayList<>();
    }
    
    public StokObatOTC() {
        this.mapObat = new LinkedHashMap<>();
        this.listObat = new ArrayList<>();
    }

    @Override
    public void tambah(Item item) throws DuplicateItemException {
        validasiDuplikat(item.getIdItem());
        ObatOTC obat = (ObatOTC) item;
        mapObat.put(obat.getIdItem(), obat);
        listObat.add(obat);
        simpan(obat);
        cekDanBuatNotifikasiStok(obat);
        cekDanBuatNotifikasiExpired(obat);
        
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
        mapObat.remove(id);
        listObat.removeIf(o -> o.getIdItem().equals(id));
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
        ObatOTC data = (ObatOTC) item;
        
        ObatOTC obatLama = getById(data.getIdItem());
        int qtyLama = obatLama.getQuantity();

        mapObat.put(data.getIdItem(), data);
        for(int x = 0 ; x < listObat.size(); x++) {
            if(listObat.get(x).getIdItem().equals(data.getIdItem())) {
                listObat.set(x, data);
                break;
            }
        }
        updateDiStruktur(data);
        cekDanBuatNotifikasiStok(data);
        cekDanBuatNotifikasiExpired(data);
        
        ObatOTC obatBaru = getById(data.getIdItem());
        int qtyBaru = obatBaru.getQuantity();
        
        User user = LoginSession.getCurrentUser();
        
        String pelaku;
        
        if(user == null) {
            pelaku = "Sistem";
        }
       
        String role = user instanceof PJApoteker ? "PJ Apoteker" : "Apoteker";
        
        pelaku = user.getName() + " ( " + role + ")";
        
        if(!obatLama.getNamaItem().equalsIgnoreCase(obatBaru.getNamaItem())) {
            MainApp.notifikasiManager.kirimNotifikasi(
                "INFO",
                "Nama Barang Diubah",
                "Nama barang diubah dari '"
                    + obatLama.getNamaItem()
                    + "' menjadi '"
                    + obatBaru.getNamaItem()
                    + "' oleh " + pelaku,
                "Rendah"
            );
        } else if(!obatLama.getKategori().equalsIgnoreCase(obatBaru.getKategori())) {
            MainApp.notifikasiManager.kirimNotifikasi(
                "INFO",
                "Kategori Diubah",
                obatBaru.getNamaItem()
                    + " dipindahkan dari kategori "
                    + obatLama.getKategori()
                    + " ke "
                    + obatBaru.getKategori() + " oleh " 
                    + pelaku,
                "Rendah"
            );
        } else if(!obatLama.getDeskripsi().equalsIgnoreCase(obatBaru.getDeskripsi())) {
            MainApp.notifikasiManager.kirimNotifikasi(
                "INFO",
                "Deskripsi Diubah",
                "Deskripsi barang "
                    + obatBaru.getNamaItem()
                    + " telah diperbarui oleh " 
                    + pelaku,
                "Rendah"
            );
        } else {
            StringBuilder perubahan = new StringBuilder();
            if(!obatLama.getNamaItem().equalsIgnoreCase(obatBaru.getNamaItem())) {
                perubahan.append("Nama, ");
            }

            if(!obatLama.getKategori().equalsIgnoreCase(obatBaru.getKategori())) {
                perubahan.append("Kategori, ");
            }

            if(!obatLama.getDeskripsi().equalsIgnoreCase(obatBaru.getDeskripsi())) {
                perubahan.append("Deskripsi, ");
            }

            if(obatLama.getQuantity() != obatBaru.getQuantity()) {
                perubahan.append("Stok, ");
            }

            if(perubahan.length() > 0) {
                String field = perubahan.substring(0,perubahan.length() - 2);
                MainApp.notifikasiManager.kirimNotifikasi(
                    "INFO",
                    "Data Barang Diperbarui",
                    "Perubahan pada "
                        + obatBaru.getNamaItem()
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
        if(listObat.isEmpty()) {
            System.out.println("Stok Obat OTC kosong.");
        }
        for(ObatOTC o : listObat) {
            System.out.println(o.displayDetail());
        }
    }
    
    public ObatOTC getById(String id) throws ItemNotFoundException {
        ObatOTC o = mapObat.get(id);
        if(o == null) throw new ItemNotFoundException(id);
        return o;
    }

    public ArrayList<ObatOTC> getListObat() {
        return listObat;
    }

    public HashMap<String, ObatOTC> getMapObat() {
        return mapObat;
    }
}
