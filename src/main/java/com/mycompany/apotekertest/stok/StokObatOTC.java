/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.stok;

import com.mycompany.apotekertest.model.ObatOTC;
import com.mycompany.apotekertest.model.Item;

/**
 *
 * @author himorii
 */
public class StokObatOTC extends Stok {
        private ObatOTC[] list = new ObatOTC[100];
    private int size = 0;

    public StokObatOTC(int minimumStok) {
        super(minimumStok);
    }

    @Override
    public void tambah(Item item) {
        list[size++] = (ObatOTC) item;
    }

    @Override
    public void hapus(String id) {
        for (int i = 0; i < size; i++) {
            if (list[i].getIdItem().equals(id)) {
                for (int j = i; j < size - 1; j++) {
                    list[j] = list[j + 1];
                }
                size--;
                break;
            }
        }
    }

    @Override
    public void update(Item item) {
        ObatOTC data = (ObatOTC) item;

        for (int i = 0; i < size; i++) {
            if (list[i].getIdItem().equals(data.getIdItem())) {
                list[i] = data;
                break;
            }
        }
    }

    @Override
    public void displayStok() {
        for (int i = 0; i < size; i++) {
            System.out.println(list[i].displayDetail());
        }
    }

    public ObatOTC[] getList() {
        return list;
    }

    public int getSize() {
        return size;
    }
}
