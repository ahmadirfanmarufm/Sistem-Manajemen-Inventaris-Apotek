package com.apotek.repository;

import com.apotek.exception.UserNotFoundException;
import com.apotek.model.Apoteker;
import com.apotek.model.PJApoteker;
import com.apotek.model.User;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Kelompok Kipli
 */
public class UserRepository {
    private final HashMap<String, User> dataUser;
    
    public UserRepository() {
        this.dataUser = new HashMap<>();
    }
    
    public void tambahUser(User user) {
        dataUser.put(user.getUserId(), user);
    }
    
    public User cariUser(String userId) throws UserNotFoundException {
        User user = dataUser.get(userId);
        if(user == null) {
            throw new UserNotFoundException(userId);
        }
        return user;
    }
    
    public User login(String userId, String password) throws UserNotFoundException {
        User user = cariUser(userId);
        if(!user.login(userId, password)) {
            throw new IllegalArgumentException("Password salah untuk user: " + userId);
        }
        return user;
    }
    
    public ArrayList<Apoteker> getDaftarApoteker() {
        ArrayList<Apoteker> list = new ArrayList<>();
        for (User u : dataUser.values()) {
            if(u instanceof Apoteker) {
                list.add((Apoteker) u);
            }
        }
        return list;
    }
    
    public ArrayList<PJApoteker> getDaftarPJApoteker() {
        ArrayList<PJApoteker> list = new ArrayList<>();
        for(User u : dataUser.values()) {
            if(u instanceof PJApoteker) {
                list.add((PJApoteker) u);
            }
        }
        return list;
    }
    
    public void hapusUser(String userId) throws UserNotFoundException {
        if(!dataUser.containsKey(userId)) throw new UserNotFoundException(userId);
        dataUser.remove(userId);
    }
    
    public HashMap<String, User> getDataUser() {
        return dataUser;
    }
    
    public int jumlahUser() {
        return dataUser.size();
    }
}
