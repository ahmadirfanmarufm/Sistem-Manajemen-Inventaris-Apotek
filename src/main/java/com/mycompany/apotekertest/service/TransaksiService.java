/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.service;
import com.mycompany.apotekertest.model.Transaksi;

/**
 *
 * @author himorii
 */
public class TransaksiService {
    private Transaksi[] list = new Transaksi[100];
    private int size = 0;

    // TAMBAH TRANSAKSI
    public void tambahTransaksi(Transaksi t) {
        list[size++] = t;
    }

    // HAPUS TRANSAKSI
    public void hapusTransaksi(String id) {
        for (int i = 0; i < size; i++) {
            if (list[i].getIdTransaksi().equals(id)) {
                for (int j = i; j < size - 1; j++) {
                    list[j] = list[j + 1];
                }
                size--;
                break;
            }
        }
    }

    // CARI TRANSAKSI
    public Transaksi cariTransaksi(String id) {
        for (int i = 0; i < size; i++) {
            if (list[i].getIdTransaksi().equals(id)) {
                return list[i];
            }
        }
        return null;
    }

    // TOTAL NOMINAL
    public int hitungTotal() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            total += list[i].getNominalTransaksi();
        }
        return total;
    }

    // GET ALL DATA
    public Transaksi[] getData() {
        return list;
    }

    public int getSize() {
        return size;
    }
}
