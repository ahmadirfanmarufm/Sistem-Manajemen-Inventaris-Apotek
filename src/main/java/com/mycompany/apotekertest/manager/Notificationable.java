/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.manager;

import com.mycompany.apotekertest.model.Notifikasi;
import java.util.ArrayList;

/**
 *
 * @author himorii
 */
public interface Notificationable {  
    public void kirimNotifikasi(String tipe, String judul, String pesan, String prioritas);
    ArrayList<Notifikasi> getAllNotifikasi();
    public int getJumlahBelumDibaca();
    public void tandaiDibaca(String idNotifikasi);
    public void hapusNotifikasi(String idNotifikasi);
}
