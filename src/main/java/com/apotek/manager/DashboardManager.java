/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apotek.manager;

import com.apotek.observer.DashboardObserver;
import java.util.ArrayList;

/**
 *
 * @author himorii
 */
public class DashboardManager {

    private final ArrayList<DashboardObserver> observers = new ArrayList<>();

    public void addObserver(DashboardObserver observer){
        observers.add(observer);
    }

    public void removeObserver(DashboardObserver observer){
        observers.remove(observer);
    }

    public void notifyDashboardObservers(){
        for(DashboardObserver observer : observers){
            observer.updateDashboard();
        }

    }

}
