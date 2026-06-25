package com.apotek.laporan;

import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kelompok Kipli
 */
public abstract class Laporan {
    protected LocalDate periodeAwal;
    protected LocalDate periodeAkhir;
    public Laporan(LocalDate periodeAwal, LocalDate periodeAkhir) {
        this.periodeAwal = periodeAwal; this.periodeAkhir = periodeAkhir;
    }
    
    public LocalDate getPeriodeAwal()  { return periodeAwal; }
    public LocalDate getPeriodeAkhir() { return periodeAkhir; }
    public abstract void cetakLaporan();
}
