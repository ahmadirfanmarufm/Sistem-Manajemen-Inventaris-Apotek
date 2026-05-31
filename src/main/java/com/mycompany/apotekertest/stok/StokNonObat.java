/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.stok;
import com.mycompany.apotekertest.model.Item;
import com.mycompany.apotekertest.model.NonObat;

/**
 *
 * @author himorii
 */
public class StokNonObat extends Stok {
    private NonObat[] list = new NonObat[100];
    private int size = 0;
    
    public StokNonObat(int minimumStok) {
        super(minimumStok);
    }
    
    @Override
    public void tambah(Item item) {
        if (size < list.length) {
            list[size++] = (NonObat) item;
        }
    }
    
    @Override
    public void hapus(String id) {
        for(int i = 0; i < size; i++) {
            if(list[i].getIdItem().equals(id)) {
                for(int j = i; j < size - 1; j++) {
                    list[j] = list[j + 1];
                }
                
                list[size - 1] = null;
                size--;
                break;
            }
        }
    }
    
    @Override
    public void update(Item item) {
        NonObat data = (NonObat) item;

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

    public NonObat[] getList() {
        return list;
    }

    public int getSize() {
        return size;
    }

    public NonObat getById(String id) {
        for (int i = 0; i < size; i++) {
            if (list[i].getIdItem().equals(id)) {
                return list[i];
            }
        }
        return null;
    }
}
