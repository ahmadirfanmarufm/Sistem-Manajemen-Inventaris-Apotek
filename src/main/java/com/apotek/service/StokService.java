package com.apotek.service;

import com.apotek.stok.StokObatOTC;
import com.apotek.stok.StokNonObat;
import com.apotek.stok.StokBahanRacikan;
import com.apotek.model.NotifikasiStok;
import com.apotek.model.BahanRacikan;
import com.apotek.model.NonObat;
import com.apotek.model.ObatOTC;
import com.apotek.exception.DuplicateItemException;
import com.apotek.exception.InvalidInputException;
import com.apotek.exception.ItemNotFoundException;
import com.apotek.ui.MainApp;
import com.apotek.manager.DashboardManager;
import com.apotek.model.Item;
import com.apotek.model.Notifikasi;
import com.apotek.model.RiwayatStok;
import com.apotek.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class StokService {
    private final StokObatOTC stokObatOTC;
    private final StokBahanRacikan stokBahanRacikan;
    private final StokNonObat stokNonObat;
    private final DashboardManager dashboardManager;
    private final ArrayList<Notifikasi> daftarNotifikasi = new ArrayList<>();
    private ArrayList<RiwayatStok> riwayatStok = new ArrayList<>();
    
    // ================= CONSTRUCTOR =================
    public StokService(StokObatOTC stokObatOTC, StokBahanRacikan stokBahanRacikan, StokNonObat stokNonObat, DashboardManager dashboardManager) {
        this.stokObatOTC = stokObatOTC;
        this.stokBahanRacikan = stokBahanRacikan;
        this.stokNonObat = stokNonObat;
        this.dashboardManager = dashboardManager;
    }

    // ================= OBAT OTC =====================
    public void tambahObat(String kategori, double hargaBeli, double hargaJual,
                           String id, String nama, int qty, int stokMin,
                           String expStr, String deskripsi)
            throws DuplicateItemException, InvalidInputException {
        validateId(id); 
        validateNama(nama); 
        validateExp(expStr);
        
        try {
            stokObatOTC.tambah(new ObatOTC(kategori, hargaBeli, hargaJual,
                id, nama, qty, stokMin, LocalDate.parse(expStr), deskripsi));
            MainApp.dashboardManager.notifyDashboardObservers();
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Tgl Expired", "format harus yyyy-MM-dd");
        }
    }

    public void hapusObat(String id) throws ItemNotFoundException {
        stokObatOTC.hapus(id);
        dashboardManager.notifyDashboardObservers();
    }
    
    public void updateObat(ObatOTC obat) throws ItemNotFoundException {
        stokObatOTC.update(obat);
        dashboardManager.notifyDashboardObservers();
    }
 
    public ObatOTC getObatById(String id) throws ItemNotFoundException {
        return stokObatOTC.getById(id);
    }
 
    public ArrayList<ObatOTC> getSemuaObat() {
        return stokObatOTC.getListObat();
    }
    
    public StokObatOTC getStokObatOTC() {
        return stokObatOTC;
    }

    // ================= BAHAN RACIKAN =================
    public void tambahBahanRacikan(String id, String nama, String satuan,
            int qty, int stokMin, String expStr, String deskripsi)
            throws DuplicateItemException, InvalidInputException {
        validateId(id); 
        validateExp(expStr);
        
        try {
            stokBahanRacikan.tambah(new BahanRacikan(id, nama, satuan, qty, stokMin,
                LocalDate.parse(expStr), deskripsi));
            dashboardManager.notifyDashboardObservers();
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Tgl Expired", "format harus yyyy-MM-dd");
        }
    }

    public void hapusBahanRacikan(String id) throws ItemNotFoundException {
        stokBahanRacikan.hapus(id);
        dashboardManager.notifyDashboardObservers();
    }
    
    public void updateBahanRacikan(BahanRacikan b) throws ItemNotFoundException {
        stokBahanRacikan.update(b);
        dashboardManager.notifyDashboardObservers();
    }
    
    public BahanRacikan getBahanById(String id) throws ItemNotFoundException {
        return stokBahanRacikan.getById(id);
    }

    public ArrayList<BahanRacikan> getSemuaBahanRacikan() {
        return stokBahanRacikan.getListBahanRacikan();
    }

    public StokBahanRacikan getStokBahanRacikan() {
        return stokBahanRacikan;
    }

    // ================= NON OBAT =================
    public void tambahNonObat(String kategori, double hargaBeli, double hargaJual,
                               String id, String nama, int qty, int stokMin,
                               String expStr, String deskripsi)
            throws DuplicateItemException, InvalidInputException {
        validateId(id); 
        validateNama(nama); 
        validateExp(expStr);
        
        try {
            stokNonObat.tambah(new NonObat(kategori, hargaBeli, hargaJual,
                id, nama, qty, stokMin, LocalDate.parse(expStr), deskripsi));
            dashboardManager.notifyDashboardObservers();
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Tgl Expired", "format harus yyyy-MM-dd");
        }
    }

    public void hapusNonObat(String id) throws ItemNotFoundException { 
        stokNonObat.hapus(id); 
        dashboardManager.notifyDashboardObservers();
    }
    
    public void updateNonObat(NonObat n) throws ItemNotFoundException { 
        stokNonObat.update(n);
        dashboardManager.notifyDashboardObservers();
    }
    
    public NonObat getNonObatById(String id) throws ItemNotFoundException { 
        return stokNonObat.getById(id); 
    }
    
    public ArrayList<NonObat> getSemuaNonObat() { 
        return stokNonObat.getListNonObat(); 
    }
    
    public StokNonObat getStokNonObat() { 
        return stokNonObat; 
    }
    
    // ================ STOK ====================
    public void kurangiStok(Item item, int jumlahKeluar, String alasan) throws ItemNotFoundException {
        item.setQuantity(item.getQuantity() - jumlahKeluar);

        if(item instanceof ObatOTC) {
            updateObat((ObatOTC) item);
        }
        else if(item instanceof BahanRacikan) {
            updateBahanRacikan((BahanRacikan) item);
        }
        else if(item instanceof NonObat) {
            updateNonObat((NonObat) item);
        }
        
        User user = LoginSession.getCurrentUser();
        
        String pelaku;
        String role;
        String judul;
        String prioritas;
        
        
        if(user == null) {
            pelaku = "Sistem";
            return;
        }
       
        if(user.getClass().getSimpleName().equalsIgnoreCase("PJApoteker")) {
            role = "PJ Apoteker";
        } else {
            role = "Apoteker";
        }
        
        pelaku = user.getName() + " (" + role + ")";

        switch(alasan) {

            case "Terjual":
                judul = "Penjualan Produk";
                prioritas = "Rendah";
                break;

            case "Produk Rusak":
                judul = "Produk Rusak";
                prioritas = "Sedang";
                break;

            case "Produk Kadaluarsa":
                judul = "Produk Kadaluarsa";
                prioritas = "Tinggi";
                break;

            case "Retur ke Pemasok":
                judul = "Retur Produk";
                prioritas = "Sedang";
                break;

            default:
                judul = "Stok Keluar";
                prioritas = "Rendah";
        }

        MainApp.notifikasiManager.kirimNotifikasi(
            "INFO",
            judul,
            item.getNamaItem()
                + " keluar sebanyak "
                + jumlahKeluar
                + " unit karena "
                + alasan
                + ". Sisa stok: "
                + item.getQuantity()
                + " unit. Dilakukan oleh "
                + pelaku,
            prioritas
        );
        
        riwayatStok.add(
            new RiwayatStok(
                    item.getIdItem(),
                    item.getNamaItem(),
                    "KELUAR",
                    jumlahKeluar,
                    LocalDateTime.now()
            )
        );
        
        dashboardManager.notifyDashboardObservers();
    }
    
    public void tambahStok(Item item, int jumlahMasuk, String pemasok) throws ItemNotFoundException {

        int stokLama = item.getQuantity();
        item.setQuantity(stokLama + jumlahMasuk);

        if (item instanceof ObatOTC) {
            updateObat((ObatOTC) item);
        }
        else if (item instanceof BahanRacikan) {
            updateBahanRacikan((BahanRacikan) item);
        }
        else if (item instanceof NonObat) {
            updateNonObat((NonObat) item);
        }

        User user = LoginSession.getCurrentUser();

        String pelaku;
        String role;

        if(user == null){
            pelaku = "Sistem";
        }else{
            role = user.getClass().getSimpleName().equals("PJApoteker")
                    ? "PJ Apoteker"
                    : "Apoteker";

            pelaku = user.getName() + " (" + role + ")";
        }

        MainApp.notifikasiManager.kirimNotifikasi(
            "SUCCESS",
            "Stok Masuk",
            item.getNamaItem()
            + " menerima stok sebanyak "
            + jumlahMasuk
            + " unit dari pemasok "
            + pemasok
            + ". Stok sekarang "
            + item.getQuantity()
            + " unit. Diproses oleh "
            + pelaku,
            "Rendah"
        );
        
        riwayatStok.add(
            new RiwayatStok(
                    item.getIdItem(),
                    item.getNamaItem(),
                    "MASUK",
                    jumlahMasuk,
                    LocalDateTime.now()
            )
        );
        
        dashboardManager.notifyDashboardObservers();
    }
    
    public ArrayList<RiwayatStok> getRiwayatStok(){
        return riwayatStok;
    }
    
    // ================= NOTIFIKASI =================
    public ArrayList<Notifikasi> getAllNotifikasi() {
        return MainApp.notifikasiManager.getAllNotifikasi();
    }
    
    public int getJumlahNotifikasiBelumDibaca() {
        return MainApp.notifikasiManager.getJumlahBelumDibaca();
    }
    
    public int getJumlahStokMenipis() {
        int total = 0;
        
        for(ObatOTC obat : stokObatOTC.getListObat()) {
            if(obat.getQuantity() <= obat.getStokMinimum()) {
                total++;
            }
        }
        
        for(BahanRacikan bahanRacikan : stokBahanRacikan.getListBahanRacikan()) {
            if(bahanRacikan.getQuantity() <= bahanRacikan.getStokMinimum()) {
                total++;
            }
        }
        
        for(NonObat nonObat : stokNonObat.getListNonObat()) {
            if(nonObat.getQuantity() <= nonObat.getStokMinimum()) {
                total++;
            }
        }
        
        return total;
    }
    
    public void tambahNotifikasi(String tipe, String judul, String pesan, String prioritas, LocalDateTime tanggal) {
        MainApp.notifikasiManager.kirimNotifikasi(tipe,judul,pesan,prioritas);
    }
    
    public ArrayList<Notifikasi> getNotifikasi() {
        return daftarNotifikasi;
    }

    // ================= VALIDATOR =================
    private void validateId(String id) throws InvalidInputException {
        if (id == null || id.isBlank()) throw new InvalidInputException("ID Item", "tidak boleh kosong");
    }
    
    private void validateNama(String nama) throws InvalidInputException {
        if (nama == null || nama.isBlank()) throw new InvalidInputException("Nama", "tidak boleh kosong");
    }
    
    private void validateExp(String exp) throws InvalidInputException {
        if (exp == null || exp.isBlank()) throw new InvalidInputException("Tgl Expired", "tidak boleh kosong");
    }

    private void cekDanBuatNotifikasiStok(ObatOTC obat) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}