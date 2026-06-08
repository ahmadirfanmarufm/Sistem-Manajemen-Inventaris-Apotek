/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.stok;

import com.mycompany.apotekertest.exception.DuplicateItemException;
import com.mycompany.apotekertest.exception.ItemNotFoundException;
import com.mycompany.apotekertest.exception.StockNotEnoughException;
import com.mycompany.apotekertest.model.Item;
import com.mycompany.apotekertest.model.NotifikasiStok;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author KelompokKipli
 */
public abstract class Stok implements Manageable {
    protected int minimumStok;
    protected ArrayList<Item> listItem;
    protected HashMap<String, Item> mapItem;
    protected ArrayList<NotifikasiStok> listNotifikasi;
    protected HashMap<String, NotifikasiStok> mapNotifikasi;

    public Stok(int minimumStok) {
        this.minimumStok = minimumStok;
        this.listItem = new ArrayList<>();
        this.mapItem = new LinkedHashMap<>();
        this.listNotifikasi = new ArrayList<>();
        this.mapNotifikasi = new HashMap<>();
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
    
    protected void cekDanBuatNotifikasi(Item item) {
        if(item.getQuantity() <= minimumStok) {
            StockNotEnoughException ex = new StockNotEnoughException(item.getNamaItem(), item.getQuantity(), minimumStok);
            
            String idNotif = "NOTIF-" + item.getIdItem();
            NotifikasiStok notif;
            
            if(mapNotifikasi.containsKey(idNotif)) {
                notif = mapNotifikasi.get(idNotif);
                notif.buatNotifikasi(ex.getMessage());
            } else {
                notif = new NotifikasiStok(idNotif, ex.getMessage());
                mapNotifikasi.put(idNotif, notif);
                listNotifikasi.add(notif);
            }
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
    
    public ArrayList<NotifikasiStok> getListNotifikasi() {
        return listNotifikasi;
    }
    
    public HashMap<String, NotifikasiStok> getMapNotifikasi() {
        return mapNotifikasi;
    }

    public boolean itemAda(String id) {
        return mapItem.containsKey(id);
    }
    
    public abstract void displayStok();
}
