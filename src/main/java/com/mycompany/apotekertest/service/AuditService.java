package com.mycompany.apotekertest.service;

import java.util.HashMap;
import java.util.Map;

public class AuditService {

    public static class HasilAudit {
        public String idBarang;
        public int stokFisik;
        public int stokSistem;
        public int selisih;
        public String status;     // "Sesuai" atau "Selisih"
        public String tanggal;
        public String auditor;
    }

    private static final Map<String, HasilAudit> riwayatAudit = new HashMap<>();

    public static void simpanHasilAudit(String idBarang, int stokFisik, int stokSistem,
                                         int selisih, String status, String tanggal, String auditor) {
        HasilAudit hasil = new HasilAudit();
        hasil.idBarang = idBarang;
        hasil.stokFisik = stokFisik;
        hasil.stokSistem = stokSistem;
        hasil.selisih = selisih;
        hasil.status = status;
        hasil.tanggal = tanggal;
        hasil.auditor = auditor;
        riwayatAudit.put(idBarang, hasil);
    }

    public static HasilAudit getHasilAudit(String idBarang) {
        return riwayatAudit.get(idBarang);
    }
}