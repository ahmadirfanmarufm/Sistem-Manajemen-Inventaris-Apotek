package com.apotek.model;

public class HasilAudit {
    private String idBarang;
    private int stokFisik;
    private int stokSistem;
    private int selisih;
    private String status;    
    private String tanggal;
    private String auditor;

    // Konstruktor Kosong
    public HasilAudit() {}

    // Konstruktor Parameter (Opsional, mempermudah pembuatan objek baru)
    public HasilAudit(String idBarang, int stokFisik, int stokSistem, int selisih, 
                      String status, String tanggal, String auditor) {
        this.idBarang = idBarang;
        this.stokFisik = stokFisik;
        this.stokSistem = stokSistem;
        this.selisih = selisih;
        this.status = status;
        this.tanggal = tanggal;
        this.auditor = auditor;
    }

    // Getter dan Setter
    public String getIdBarang() { return idBarang; }
    public void setIdBarang(String idBarang) { this.idBarang = idBarang; }

    public int getStokFisik() { return stokFisik; }
    public void setStokFisik(int stokFisik) { this.stokFisik = stokFisik; }

    public int getStokSistem() { return stokSistem; }
    public void setStokSistem(int stokSistem) { this.stokSistem = stokSistem; }

    public int getSelisih() { return selisih; }
    public void setSelisih(int selisih) { this.selisih = selisih; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    public String getAuditor() { return auditor; }
    public void setAuditor(String auditor) { this.auditor = auditor; }
}