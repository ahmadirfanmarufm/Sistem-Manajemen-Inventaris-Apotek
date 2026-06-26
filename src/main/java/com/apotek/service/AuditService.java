package com.apotek.service;

import com.apotek.model.HasilAudit; // Import model yang baru dipindahkan
import java.util.HashMap;
import java.util.Map;

public class AuditService {

    private static final Map<String, HasilAudit> riwayatAudit = new HashMap<>();

    public static void simpanHasilAudit(String idBarang, int stokFisik, int stokSistem,
                                        int selisih, String status, String tanggal, String auditor) {
        // Menggunakan konstruktor baru agar instansiasi lebih bersih dan rapi
        HasilAudit hasil = new HasilAudit(idBarang, stokFisik, stokSistem, selisih, status, tanggal, auditor);
        riwayatAudit.put(idBarang, hasil);
    }

    public static HasilAudit getHasilAudit(String idBarang) {
        return riwayatAudit.get(idBarang);
    }
}