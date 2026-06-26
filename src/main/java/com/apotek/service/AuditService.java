package com.apotek.service;

import com.apotek.model.Item;
import com.apotek.model.PJApoteker;
import com.apotek.model.User;
import com.apotek.ui.MainApp;
import java.util.HashMap;
import java.util.Map;

public class AuditService {
    public static class HasilAudit {
        private String idBarang;
        private int stokFisik;
        private int stokSistem;
        private int selisih;
        private String status;
        private String tanggal;
        private String auditor;

        public String getIdBarang() {
            return idBarang;
        }

        public void setIdBarang(String idBarang) {
            this.idBarang = idBarang;
        }

        public int getStokFisik() {
            return stokFisik;
        }

        public void setStokFisik(int stokFisik) {
            this.stokFisik = stokFisik;
        }

        public int getStokSistem() {
            return stokSistem;
        }

        public void setStokSistem(int stokSistem) {
            this.stokSistem = stokSistem;
        }

        public int getSelisih() {
            return selisih;
        }

        public void setSelisih(int selisih) {
            this.selisih = selisih;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getAuditor() {
            return auditor;
        }

        public void setAuditor(String auditor) {
            this.auditor = auditor;
        }
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

        hasil.setIdBarang(idBarang);
        hasil.setStokFisik(stokFisik);
        hasil.setStokSistem(stokSistem);
        hasil.setSelisih(selisih);
        hasil.setStatus(status);
        hasil.setTanggal(tanggal);
        hasil.setAuditor(auditor);

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