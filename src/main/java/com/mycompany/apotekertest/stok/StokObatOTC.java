package com.mycompany.apotekertest.stok;

import com.mycompany.apotekertest.exception.DuplicateItemException;
import com.mycompany.apotekertest.exception.ItemNotFoundException;
import com.mycompany.apotekertest.model.ObatOTC;
import com.mycompany.apotekertest.model.Item;
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
        cekDanBuatNotifikasi(obat);
    }

    @Override
    public void hapus(String id) throws ItemNotFoundException {
        validasiAda(id);
        mapObat.remove(id);
        listObat.removeIf(o -> o.getIdItem().equals(id));
        hapusDariStruktur(id);
        
    }

    @Override
    public void update(Item item) throws ItemNotFoundException {
        validasiAda(item.getIdItem());
        ObatOTC data = (ObatOTC) item;
        mapObat.put(data.getIdItem(), data);
        for(int x = 0 ; x < listObat.size(); x++) {
            if(listObat.get(x).getIdItem().equals(data.getIdItem())) {
                listObat.set(x, data);
                break;
            }
        }
        updateDiStruktur(data);
        cekDanBuatNotifikasi(data);
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
