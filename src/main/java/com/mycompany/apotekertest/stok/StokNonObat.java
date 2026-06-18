package com.mycompany.apotekertest.stok;

import com.mycompany.apotekertest.exception.DuplicateItemException;
import com.mycompany.apotekertest.exception.ItemNotFoundException;
import com.mycompany.apotekertest.model.Item;
import com.mycompany.apotekertest.model.NonObat;
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
        cekDanBuatNotifikasi(nonObat);
    }
    
    @Override
    public void hapus(String id) throws ItemNotFoundException {
        validasiAda(id);
        mapNonObat.remove(id);
        listNonObat.removeIf(n -> n.getIdItem().equals(id));
        hapusDariStruktur(id);
    }
    
    @Override
    public void update(Item item) throws ItemNotFoundException {
        validasiAda(item.getIdItem());
        NonObat data = (NonObat) item;
        mapNonObat.put(data.getIdItem(), data);
        for(int x = 0 ; x < listNonObat.size(); x++) {
            if(listNonObat.get(x).getIdItem().equals(data.getIdItem())) {
                listNonObat.set(x, data);
                break;
            }
        }
        updateDiStruktur(data);
        cekDanBuatNotifikasi(data);
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
