/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apotek.manager;

import com.apotek.model.Notifikasi;
import com.apotek.observer.NotifikasiObserver;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author himorii
 */
public class NotifikasiManager implements Notificationable {
    private final ArrayList<Notifikasi> daftarNotifikasi;
    private ArrayList<NotifikasiObserver> observers;
    
    public NotifikasiManager() {
        daftarNotifikasi = new ArrayList<>();
        observers = new ArrayList<>();
    }
    
    public void addObserver(NotifikasiObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(NotifikasiObserver observer) {
        observers.remove(observers);
    }
    
    private void notifyObservers() {
        for(NotifikasiObserver observer : observers) {
            observer.updateNotifikasi();
        }
    }
    
    @Override
    public void kirimNotifikasi(String tipe, String judul, String pesan, String prioritas) {
        Notifikasi notif = new Notifikasi(
                UUID.randomUUID().toString(),
                tipe,
                judul,
                pesan,
                prioritas,
                LocalDateTime.now()
        );
        daftarNotifikasi.add(notif);
        notifyObservers();
    }
    
    @Override
    public ArrayList<Notifikasi> getAllNotifikasi() {
        return daftarNotifikasi;
    }
    
    @Override
    public int getJumlahBelumDibaca() {
        int total = 0;

        for(Notifikasi notif : daftarNotifikasi) {
            if(!notif.isStatusBaca()) {
                total++;
            }
        }

        return total;
    }

    @Override
    public void tandaiDibaca(String idNotifikasi) {
        for(Notifikasi notif : daftarNotifikasi) {
            if(notif.getIdNotifikasi().equals(idNotifikasi)) {
                notif.setStatusBaca(true);
                notifyObservers();
                return;
            }
        }
    }

    @Override
    public void hapusNotifikasi(String idNotifikasi) {
        daftarNotifikasi.removeIf(notif -> notif.getIdNotifikasi().equals(idNotifikasi));
        notifyObservers();
    }
}
