/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apotekertest.stok;

import com.mycompany.apotekertest.exception.DuplicateItemException;
import com.mycompany.apotekertest.exception.ItemNotFoundException;
import com.mycompany.apotekertest.model.Item;


/**
 *
 * @author Kelompok Kipli
 */
public interface Manageable {
    public void tambah(Item item) throws DuplicateItemException;
    public void hapus(String id) throws ItemNotFoundException;
    public void update(Item item) throws ItemNotFoundException;
}
