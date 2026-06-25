package com.apotek.stok;

import com.apotek.exception.DuplicateItemException;
import com.apotek.exception.ItemNotFoundException;
import com.apotek.model.Item;


/**
 *
 * @author Kelompok Kipli
 */
public interface Manageable {
    public void tambah(Item item) throws DuplicateItemException;
    public void hapus(String id) throws ItemNotFoundException;
    public void update(Item item) throws ItemNotFoundException;
}
