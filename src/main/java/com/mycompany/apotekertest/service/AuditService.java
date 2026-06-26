package com.mycompany.apotekertest.service;

import com.mycompany.apotekertest.model.Item;
import com.mycompany.apotekertest.model.PJApoteker;
import com.mycompany.apotekertest.model.User;
import com.mycompany.apotekertest.ui.MainApp;
import java.util.HashMap;
import java.util.Map;

public class AuditService {
    public static class HasilAudit {
        public String idBarang;
        public int stokFisik;
        public int stokSistem;
        public int selisih;
        public String status;
        public String tanggal;
        public String auditor;
    }
    
    private final Map<String, HasilAudit> riwayatAudit;

    public AuditService() {
        this.riwayatAudit = new HashMap<>();
    }

    public void simpanHasilAudit(
            String idBarang, int stokFisik, int stokSistem,
            int selisih, String status, String tanggal, String auditor
    ) {
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
    
    private void kirimNotifikasiAudit(Item item, int stokFisik, int stokSistem, int selisih,String auditor){
        if(selisih == 0){
            MainApp.notifikasiManager.kirimNotifikasi(
                "SUCCESS",
                "Audit Stok Selesai",
                item.getNamaItem()
                + " telah diaudit oleh "
                + auditor
                + ". Stok fisik sesuai dengan stok sistem ("
                + stokFisik
                + " unit).",
                "Rendah"
            );
        } else {
            MainApp.notifikasiManager.kirimNotifikasi(
                "WARNING",
                "Selisih Stok Ditemukan",
                item.getNamaItem()
                + " memiliki selisih "
                + Math.abs(selisih)
                + " unit."
                + " Stok sistem: "
                + stokSistem
                + " unit, stok fisik: "
                + stokFisik
                + " unit."
                + " Audit dilakukan oleh "
                + auditor,
                "Tinggi"
            );
        }
    }
    
    public void prosesAudit(Item item, int stokFisik, String tanggal) {
        int stokSistem = item.getQuantity();

        int selisih = stokFisik - stokSistem;

        String status = (selisih == 0)
                ? "Sesuai"
                : "Selisih";

        User user = LoginSession.getCurrentUser();

        String auditor;

        if(user == null){
            auditor = "Sistem";
        } else {
            String role = user instanceof PJApoteker ? "PJ Apoteker" : "Apoteker";
            auditor = user.getName() + " (" + role + ")";
        }

        simpanHasilAudit(item.getIdItem(), stokFisik, stokSistem, selisih, status, tanggal, auditor);

        kirimNotifikasiAudit(item, stokFisik, stokSistem, selisih,auditor);
    }

    public HasilAudit getHasilAudit(String idBarang) {
        return riwayatAudit.get(idBarang);
    }
}