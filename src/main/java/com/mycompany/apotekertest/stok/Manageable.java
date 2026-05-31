/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.stok;

import com.mycompany.apotekertest.model.Item;

/**
 *
 * @author himorii
 */
public interface Manageable {
    public void tambah(Item item);
    public void hapus(String id);
    public void update(Item item);
}
