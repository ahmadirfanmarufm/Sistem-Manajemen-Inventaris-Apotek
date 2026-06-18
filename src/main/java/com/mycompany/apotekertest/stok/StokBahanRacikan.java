package com.mycompany.apotekertest.stok;

import com.mycompany.apotekertest.exception.DuplicateItemException;
import com.mycompany.apotekertest.exception.ItemNotFoundException;
import com.mycompany.apotekertest.model.BahanRacikan;
import com.mycompany.apotekertest.model.Item;
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
        cekDanBuatNotifikasi(bahan);
    }

    @Override
    public void hapus(String id) throws ItemNotFoundException {
        validasiAda(id);
        mapBahan.remove(id);
        listBahanRacikan.removeIf(b -> b.getIdItem().equals(id));
        hapusDariStruktur(id);
    }

    @Override
    public void update(Item item) throws ItemNotFoundException {
        validasiAda(item.getIdItem());
        BahanRacikan data = (BahanRacikan) item;
        mapBahan.put(data.getIdItem(), data);
        for(int x = 0 ; x < listBahanRacikan.size(); x++) {
            if(listBahanRacikan.get(x).getIdItem().equals(data.getIdItem())) {
                listBahanRacikan.set(x, data);
                break;
            }
        }
        updateDiStruktur(data);
        cekDanBuatNotifikasi(data);
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
