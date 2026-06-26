package com.mycompany.apotekertest.stok;

import com.mycompany.apotekertest.exception.DuplicateItemException;
import com.mycompany.apotekertest.exception.ItemNotFoundException;
import com.mycompany.apotekertest.exception.StockNotEnoughException;
import com.mycompany.apotekertest.model.Item;
import com.mycompany.apotekertest.model.Notifikasi;
import com.mycompany.apotekertest.ui.MainApp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Stok implements Manageable {
    protected int minimumStok;
    protected ArrayList<Item> listItem;
    protected HashMap<String, Item> mapItem;

    public Stok(int minimumStok) {
        this.minimumStok = minimumStok;
        this.listItem = new ArrayList<>();
        this.mapItem = new LinkedHashMap<>();
    }
    
    public Stok() {
        this.minimumStok = minimumStok;
        this.listItem = new ArrayList<>();
        this.mapItem = new LinkedHashMap<>();
    }
    
    protected void validasiDuplikat(String id) throws DuplicateItemException {
        if(mapItem.containsKey(id)) {
            throw new DuplicateItemException(id);
        }
    }
    
    protected void validasiAda(String id) throws ItemNotFoundException {
        if(!mapItem.containsKey(id)) {
            throw new ItemNotFoundException(id);
        }
    }
    
    protected void cekDanBuatNotifikasiStok(Item item) {
        int batasMinimum = item.getStokMinimum();
        
        if (item.getQuantity() <= batasMinimum) {
            if(!item.isNotifikasiKritisAktif()) {
                StockNotEnoughException ex = new StockNotEnoughException(item.getNamaItem(), item.getQuantity(), batasMinimum);

                MainApp.notifikasiManager.kirimNotifikasi(
                        "CRITICAL",
                        "Stok Kritis",
                        ex.getMessage(),
                        "Tinggi"
                );
                item.setNotifikasiKritisAktif(true);
            }
        } else {
            if(item.isNotifikasiKritisAktif()) {
                MainApp.notifikasiManager.kirimNotifikasi(
                    "INFO",
                    "Stok kembali normal",
                    item.getNamaItem() + " kini memiliki stok " + item.getQuantity(),
                    "Rendah"
                );
            }
            item.setNotifikasiKritisAktif(false);
        }
    }
    
    protected void cekDanBuatNotifikasiExpired(Item item) {
        LocalDate sekarang = LocalDate.now();
        LocalDate expired = item.getExpiredDate();
        
        long sisaHari = expired.toEpochDay() - sekarang.toEpochDay();
        
        if(sisaHari < 0) {
            if(!item.isNotifikasiExpiredAktif()) {
                MainApp.notifikasiManager.kirimNotifikasi(
                    "CRITICAL",
                    "Barang Expired",
                    item.getNamaItem() + " telah expired",
                    "Tinggi"
                );
                
                item.setNotifikasiExpiredAktif(true);
            }
            return;
        } 
        
        if(sisaHari <= 30) {
            if(!item.isNotifikasiExpiredAktif()) {
                MainApp.notifikasiManager.kirimNotifikasi(
                    "WARNING",
                    "Barang akan expired",
                    item.getNamaItem() + " akan expired dalam " + sisaHari + " hari",
                    "Sedang"
                );
                
                item.setNotifikasiExpiredAktif(true);
            }
        } else {
            item.setNotifikasiExpiredAktif(false);
        }
    }
    
    protected void simpan(Item item) {
        mapItem.put(item.getIdItem(), item);
        listItem.add(item);
    }
    
    protected void hapusDariStruktur(String id) {
        mapItem.remove(id);
        listItem.removeIf(i -> i.getIdItem().equals(id));
    }
    
    protected void updateDiStruktur(Item item) {
        mapItem.put(item.getIdItem(), item);
        for(int x = 0; x < listItem.size(); x++) {
            if(listItem.get(x).getIdItem().equals(item.getIdItem())) {
                listItem.set(x, item);
                break;
            }
        }
    }

    public int getMinimumStok() {
        return minimumStok;
    }

    public void setMinimumStok(int minimumStok) {
        this.minimumStok = minimumStok;
    }
    
    public ArrayList<Item> getListItem() {
        return listItem;
    }

        
    public HashMap<String, Item> getMapItem() {
        return mapItem;
    }

    public boolean itemAda(String id) {
        return mapItem.containsKey(id);
    }
    
    public abstract void displayStok();
}
