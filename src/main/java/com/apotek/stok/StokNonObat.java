package com.apotek.stok;


import com.apotek.exception.DuplicateItemException;
import com.apotek.exception.ItemNotFoundException;
import com.apotek.model.Item;
import com.apotek.model.NonObat;
import com.apotek.model.PJApoteker;
import com.apotek.model.User;
import com.apotek.service.LoginSession;
import com.apotek.ui.MainApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class StokNonObat extends Stok {
    private final HashMap<String, NonObat> mapNonObat;
    private final ArrayList<NonObat> listNonObat;
    
    public StokNonObat(int minimumStok) {
        super(minimumStok);
        this.mapNonObat = new LinkedHashMap<>();
        this.listNonObat = new ArrayList<>();
    }
    
    public StokNonObat() {
        this.mapNonObat = new LinkedHashMap<>();
        this.listNonObat = new ArrayList<>();
    }
    
    @Override
    public void tambah(Item item) throws DuplicateItemException {
        validasiDuplikat(item.getIdItem());
        NonObat nonObat = (NonObat) item;
        mapNonObat.put(nonObat.getIdItem(), nonObat);
        listNonObat.add(nonObat);
        simpan(nonObat);
        cekDanBuatNotifikasiStok(nonObat);
        cekDanBuatNotifikasiExpired(nonObat);
        
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
        mapNonObat.remove(id);
        listNonObat.removeIf(n -> n.getIdItem().equals(id));
        hapusDariStruktur(id);
        
        User user = LoginSession.getCurrentUser();
        
        String pelaku;

        if(user == null) {
            pelaku = "Sistem";
            return;
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
        NonObat data = (NonObat) item;
        
        NonObat nonObatLama = getById(data.getIdItem());
        int qtyLama = nonObatLama.getQuantity();

        mapNonObat.put(data.getIdItem(), data);
        for(int x = 0 ; x < listNonObat.size(); x++) {
            if(listNonObat.get(x).getIdItem().equals(data.getIdItem())) {
                listNonObat.set(x, data);
                break;
            }
        }
        updateDiStruktur(data);
        cekDanBuatNotifikasiStok(data);
        cekDanBuatNotifikasiExpired(data);
        
        NonObat nonObatBaru = getById(data.getIdItem());
        int qtyBaru = nonObatBaru.getQuantity();
        
        User user = LoginSession.getCurrentUser();
        
        String pelaku;

        if(user == null) {
            pelaku = "Sistem";
        }
       
        String role = user instanceof PJApoteker ? "PJ Apoteker" : "Apoteker";
        
        pelaku = user.getName() + " ( " + role + ")";
        
        if(!nonObatLama.getNamaItem().equalsIgnoreCase(nonObatBaru.getNamaItem())) {
            MainApp.notifikasiManager.kirimNotifikasi(
                "INFO",
                "Nama Barang Diubah",
                "Nama barang diubah dari '"
                    + nonObatLama.getNamaItem()
                    + "' menjadi '"
                    + nonObatBaru.getNamaItem()
                    + "' oleh " + pelaku,
                "Rendah"
            );
        } else if(!nonObatLama.getKategori().equalsIgnoreCase(nonObatBaru.getKategori())) {
            MainApp.notifikasiManager.kirimNotifikasi(
                "INFO",
                "Kategori Diubah",
                nonObatBaru.getNamaItem()
                    + " dipindahkan dari kategori "
                    + nonObatLama.getKategori()
                    + " ke "
                    + nonObatBaru.getKategori() + " oleh " 
                    + pelaku,
                "Rendah"
            );
        } else if(!nonObatLama.getDeskripsi().equalsIgnoreCase(nonObatBaru.getDeskripsi())) {
            MainApp.notifikasiManager.kirimNotifikasi(
                "INFO",
                "Deskripsi Diubah",
                "Deskripsi barang "
                    + nonObatBaru.getNamaItem()
                    + " telah diperbarui oleh " 
                    + pelaku,
                "Rendah"
            );
        } else {
            StringBuilder perubahan = new StringBuilder();
            if(!nonObatLama.getNamaItem().equalsIgnoreCase(nonObatBaru.getNamaItem())) {
                perubahan.append("Nama, ");
            }

            if(!nonObatLama.getKategori().equalsIgnoreCase(nonObatBaru.getKategori())) {
                perubahan.append("Kategori, ");
            }

            if(!nonObatLama.getDeskripsi().equalsIgnoreCase(nonObatBaru.getDeskripsi())) {
                perubahan.append("Deskripsi, ");
            }

            if(nonObatLama.getQuantity() != nonObatBaru.getQuantity()) {
                perubahan.append("Stok, ");
            }

            if(perubahan.length() > 0) {
                String field = perubahan.substring(0,perubahan.length() - 2);
                MainApp.notifikasiManager.kirimNotifikasi(
                    "INFO",
                    "Data Barang Diperbarui",
                    "Perubahan pada "
                        + nonObatBaru.getNamaItem()
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
       if(listNonObat.isEmpty()) {
            System.out.println("Stok non-obat kosong.");
        }
        for(NonObat n : listNonObat) {
            System.out.println(n.displayDetail());
        }
    }

    public NonObat getById(String id) throws ItemNotFoundException {
        NonObat n = mapNonObat.get(id);
        if(n == null) throw new ItemNotFoundException(id);
        return n;
    }

    public ArrayList<NonObat> getListNonObat() {
        return listNonObat;
    }

    public HashMap<String, NonObat> getMapNonObat() {
        return mapNonObat;
    }
}
