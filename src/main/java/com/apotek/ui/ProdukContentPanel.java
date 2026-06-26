package com.apotek.ui;

import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.util.ArrayList;
import com.apotek.model.ObatOTC;
import com.apotek.model.BahanRacikan;
import com.apotek.model.NonObat;
import java.awt.Frame;

public class ProdukContentPanel extends javax.swing.JPanel {

    public ProdukContentPanel() {
        initComponents();
        headerContainer.setLayout(new BorderLayout());
        headerContainer.add(new HeaderPanel(MainApp.stokService), BorderLayout.CENTER);
        contentScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        loadObatOTCTable();
        loadBahanRacikanTable();
        loadNonObatTable();
    }
    
    public void refreshSemuaTabel() {
    loadObatOTCTable();
    loadBahanRacikanTable();
    loadNonObatTable();
}
    
    
    private void loadObatOTCTable() {
        ArrayList<ObatOTC> listObat = MainApp.stokService.getSemuaObat();
        String[] kolom = {"ID", "Nama Obat", "Kategori", "Stok", "Harga Beli", "Harga Jual", "Minimum Stok", "Expired Date", "Deskripsi"};
        Object[][] data = new Object[listObat.size()][kolom.length];

        for (int i = 0; i < listObat.size(); i++) {
            ObatOTC obat = listObat.get(i);
            data[i][0] = obat.getIdItem();
            data[i][1] = obat.getNamaItem();
            data[i][2] = obat.getKategori();
            data[i][3] = obat.getQuantity();
            data[i][4] = obat.getHargaBeli();
            data[i][5] = obat.getHargaJual();
            data[i][6] = obat.getStokMinimum();
            data[i][7] = obat.getExpiredDate();
            data[i][8] = obat.getDeskripsi();
        }

        DefaultTableModel model = new DefaultTableModel(data, kolom) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelObatOTC.setModel(model);
        tabelObatOTC.getTableHeader().setReorderingAllowed(false); // gak bisa drag pindah posisi kolom
        tabelObatOTC.getTableHeader().setResizingAllowed(false);   // gak bisa drag resize lebar kolom
        lblShowDataObatOTC.setText("Menampilkan " + listObat.size() + " dari " + listObat.size() + " data");
    }

    private void loadBahanRacikanTable() {
        ArrayList<BahanRacikan> listBahan = MainApp.stokService.getSemuaBahanRacikan();
        String[] kolom = {"ID", "Nama Bahan", "Satuan", "Stok", "Minimum Stok", "Expired Date", "Deskripsi"};
        Object[][] data = new Object[listBahan.size()][kolom.length];

        for (int i = 0; i < listBahan.size(); i++) {
            BahanRacikan bahan = listBahan.get(i);
            data[i][0] = bahan.getIdItem();
            data[i][1] = bahan.getNamaItem();
            data[i][2] = bahan.getSatuan();
            data[i][3] = bahan.getQuantity();
            data[i][4] = bahan.getStokMinimum();
            data[i][5] = bahan.getExpiredDate();
            data[i][6] = bahan.getDeskripsi();
        }

        DefaultTableModel model = new DefaultTableModel(data, kolom) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelBahanRacikan.setModel(model);
        tabelBahanRacikan.getTableHeader().setReorderingAllowed(false); // gak bisa drag pindah posisi kolom
        tabelBahanRacikan.getTableHeader().setResizingAllowed(false);   // gak bisa drag resize lebar kolom
        lblShowDataBahanRacikan.setText("Menampilkan " + listBahan.size() + " dari " + listBahan.size() + " data");
    }

    private void loadNonObatTable() {
        ArrayList<NonObat> listNonObat = MainApp.stokService.getSemuaNonObat();
        String[] kolom = {"ID", "Nama Produk", "Kategori", "Stok", "Harga Beli", "Harga Jual", "Minimum Stok", "Expired Date", "Deskripsi"};
        Object[][] data = new Object[listNonObat.size()][kolom.length];

        for (int i = 0; i < listNonObat.size(); i++) {
            NonObat non = listNonObat.get(i);
            data[i][0] = non.getIdItem();
            data[i][1] = non.getNamaItem();
            data[i][2] = non.getKategori();
            data[i][3] = non.getQuantity();
            data[i][4] = non.getHargaBeli();
            data[i][5] = non.getHargaJual();
            data[i][6] = non.getStokMinimum();
            data[i][7] = non.getExpiredDate();
            data[i][8] = non.getDeskripsi();
        }
        
        

        DefaultTableModel model = new DefaultTableModel(data, kolom) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableNonObat.setModel(model);
        tableNonObat.getTableHeader().setReorderingAllowed(false); // gak bisa drag pindah posisi kolom
        tableNonObat.getTableHeader().setResizingAllowed(false);   // gak bisa drag resize lebar kolom
        lblShowDataBahanRacikan1.setText("Menampilkan " + listNonObat.size() + " dari " + listNonObat.size() + " data");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        contentScrollPane = new javax.swing.JScrollPane();
        contentPanel = new javax.swing.JPanel();
        headerContainer = new javax.swing.JPanel();
        lblTitleObatOTC = new javax.swing.JLabel();
        lblDescriptionObatOTC = new javax.swing.JLabel();
        btnTambahObatOTC = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelBahanRacikan = new javax.swing.JTable();
        lblShowDataObatOTC = new javax.swing.JLabel();
        lblTitleBahanRacikan = new javax.swing.JLabel();
        lblDescriptionBahanRacikan = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelObatOTC = new javax.swing.JTable();
        tabelObatOTC.getTableHeader().setReorderingAllowed(false);
        btnTambahBahanRacikan = new javax.swing.JButton();
        lblShowDataBahanRacikan = new javax.swing.JLabel();
        lblTitleNonObat = new javax.swing.JLabel();
        lblDescriptionNonObat = new javax.swing.JLabel();
        btnTambahNonObat = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableNonObat = new javax.swing.JTable();
        lblShowDataBahanRacikan1 = new javax.swing.JLabel();
<<<<<<< HEAD
        btnNextNonObat = new javax.swing.JButton();
        lblHalamanNonObat = new javax.swing.JLabel();
        btnPreviousNonObat = new javax.swing.JButton();
        editObatOTC = new javax.swing.JButton();
        editBahanRacikan = new javax.swing.JButton();
        editNonObat = new javax.swing.JButton();
=======
>>>>>>> 0ce9a0c084ebcea576ba9e3de21249ac04b1e934

        jRadioButton1.setText("jRadioButton1");

        setPreferredSize(new java.awt.Dimension(800, 800));

        contentPanel.setBackground(new java.awt.Color(240, 252, 245));

        headerContainer.setMinimumSize(new java.awt.Dimension(100, 80));
        headerContainer.setPreferredSize(new java.awt.Dimension(0, 80));

        javax.swing.GroupLayout headerContainerLayout = new javax.swing.GroupLayout(headerContainer);
        headerContainer.setLayout(headerContainerLayout);
        headerContainerLayout.setHorizontalGroup(
            headerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        headerContainerLayout.setVerticalGroup(
            headerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        lblTitleObatOTC.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleObatOTC.setForeground(new java.awt.Color(20, 145, 66));
        lblTitleObatOTC.setText("Manajemen Obat OTC");

        lblDescriptionObatOTC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescriptionObatOTC.setForeground(new java.awt.Color(42, 137, 79));
        lblDescriptionObatOTC.setText(" Kelola inventaris obat over-the-counter");

        btnTambahObatOTC.setBackground(new java.awt.Color(20, 145, 66));
        btnTambahObatOTC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTambahObatOTC.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahObatOTC.setText("Tambah Obat OTC");
        btnTambahObatOTC.setBorder(null);
        btnTambahObatOTC.addActionListener(this::btnTambahObatOTCActionPerformed);

        tabelBahanRacikan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabelBahanRacikan);

        lblShowDataObatOTC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblShowDataObatOTC.setForeground(new java.awt.Color(42, 137, 79));
        lblShowDataObatOTC.setText("Menampilkan 0 dari 15 data");

        lblTitleBahanRacikan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleBahanRacikan.setForeground(new java.awt.Color(20, 145, 66));
        lblTitleBahanRacikan.setText("Manajemen Bahan Racikan");

        lblDescriptionBahanRacikan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescriptionBahanRacikan.setForeground(new java.awt.Color(42, 137, 79));
        lblDescriptionBahanRacikan.setText("Kelola inventaris bahan untuk meracik obat");

        tabelObatOTC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tabelObatOTC);

        btnTambahBahanRacikan.setBackground(new java.awt.Color(20, 145, 66));
        btnTambahBahanRacikan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTambahBahanRacikan.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahBahanRacikan.setText("Tambah Bahan Racikan");
        btnTambahBahanRacikan.setMaximumSize(new java.awt.Dimension(148, 25));
        btnTambahBahanRacikan.setMinimumSize(new java.awt.Dimension(148, 25));
        btnTambahBahanRacikan.setPreferredSize(new java.awt.Dimension(148, 25));
        btnTambahBahanRacikan.addActionListener(this::btnTambahBahanRacikanActionPerformed);

        lblShowDataBahanRacikan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblShowDataBahanRacikan.setForeground(new java.awt.Color(42, 137, 79));
        lblShowDataBahanRacikan.setText("Menampilkan 0 dari 15 data");

        lblTitleNonObat.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleNonObat.setForeground(new java.awt.Color(20, 145, 66));
        lblTitleNonObat.setText("Manajemen Non Obat");

        lblDescriptionNonObat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescriptionNonObat.setForeground(new java.awt.Color(42, 137, 79));
        lblDescriptionNonObat.setText("Kelola inventaris produk non-obat");

        btnTambahNonObat.setBackground(new java.awt.Color(20, 145, 66));
        btnTambahNonObat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTambahNonObat.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahNonObat.setText("Tambah Non Obat");
        btnTambahNonObat.setMaximumSize(new java.awt.Dimension(148, 25));
        btnTambahNonObat.setMinimumSize(new java.awt.Dimension(148, 25));
        btnTambahNonObat.setPreferredSize(new java.awt.Dimension(148, 25));
        btnTambahNonObat.addActionListener(this::btnTambahNonObatActionPerformed);

        tableNonObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tableNonObat);

        lblShowDataBahanRacikan1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblShowDataBahanRacikan1.setForeground(new java.awt.Color(42, 137, 79));
        lblShowDataBahanRacikan1.setText("Menampilkan 0 dari 15 data");

<<<<<<< HEAD
        btnNextNonObat.setBackground(new java.awt.Color(20, 145, 66));
        btnNextNonObat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNextNonObat.setForeground(new java.awt.Color(255, 255, 255));
        btnNextNonObat.setText("Selanjutnya");

        lblHalamanNonObat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHalamanNonObat.setText("Halaman 0 dari 0");

        btnPreviousNonObat.setBackground(new java.awt.Color(20, 145, 66));
        btnPreviousNonObat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPreviousNonObat.setForeground(new java.awt.Color(255, 255, 255));
        btnPreviousNonObat.setText("Sebelumnya");

        editObatOTC.setBackground(new java.awt.Color(128, 128, 128));
        editObatOTC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        editObatOTC.setForeground(new java.awt.Color(255, 255, 255));
        editObatOTC.setText("Edit Obat");
        editObatOTC.addActionListener(this::editObatOTCActionPerformed);

        editBahanRacikan.setBackground(new java.awt.Color(128, 128, 128));
        editBahanRacikan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        editBahanRacikan.setForeground(new java.awt.Color(255, 255, 255));
        editBahanRacikan.setText("Edit Bahan Racikan");
        editBahanRacikan.addActionListener(this::editBahanRacikanActionPerformed);

        editNonObat.setBackground(new java.awt.Color(128, 128, 128));
        editNonObat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        editNonObat.setForeground(new java.awt.Color(255, 255, 255));
        editNonObat.setText("Edit Non Obat");
        editNonObat.addActionListener(this::editNonObatActionPerformed);

=======
>>>>>>> 0ce9a0c084ebcea576ba9e3de21249ac04b1e934
        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
<<<<<<< HEAD
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTitleObatOTC)
                            .addComponent(lblDescriptionObatOTC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editObatOTC)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambahObatOTC, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                        .addComponent(lblShowDataBahanRacikan1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPreviousNonObat)
                        .addGap(18, 18, 18)
                        .addComponent(lblHalamanNonObat)
                        .addGap(18, 18, 18)
                        .addComponent(btnNextNonObat))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                        .addComponent(lblShowDataBahanRacikan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 437, Short.MAX_VALUE)
                        .addComponent(btnPreviousBahanRacikan)
                        .addGap(18, 18, 18)
                        .addComponent(lblHalamanBahanRacikan)
                        .addGap(18, 18, 18)
                        .addComponent(btnNextBahanRacikan))
=======
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
>>>>>>> 0ce9a0c084ebcea576ba9e3de21249ac04b1e934
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(lblShowDataBahanRacikan)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                        .addComponent(lblShowDataObatOTC)
<<<<<<< HEAD
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPreviousObatOTC)
                        .addGap(18, 18, 18)
                        .addComponent(lblHalamanObatOTC)
                        .addGap(18, 18, 18)
                        .addComponent(btnNextObatOTC))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitleBahanRacikan)
                            .addComponent(lblDescriptionBahanRacikan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editBahanRacikan)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambahBahanRacikan, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescriptionNonObat)
                            .addComponent(lblTitleNonObat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editNonObat)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambahNonObat, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
                .addGap(41, 41, 41))
=======
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTitleObatOTC)
                                    .addComponent(lblDescriptionObatOTC))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTambahObatOTC, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                                .addComponent(lblShowDataBahanRacikan1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTitleBahanRacikan)
                                    .addComponent(lblDescriptionBahanRacikan))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 477, Short.MAX_VALUE)
                                .addComponent(btnTambahBahanRacikan, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDescriptionNonObat)
                                    .addComponent(lblTitleNonObat))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTambahNonObat, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4))
                        .addGap(41, 41, 41))))
>>>>>>> 0ce9a0c084ebcea576ba9e3de21249ac04b1e934
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addComponent(headerContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(lblTitleObatOTC, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescriptionObatOTC))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambahObatOTC, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editObatOTC, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblShowDataObatOTC)
                .addGap(34, 34, 34)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(lblTitleBahanRacikan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescriptionBahanRacikan))
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTambahBahanRacikan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editBahanRacikan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
<<<<<<< HEAD
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNextBahanRacikan)
                    .addComponent(lblHalamanBahanRacikan)
                    .addComponent(btnPreviousBahanRacikan)
                    .addComponent(lblShowDataBahanRacikan))
                .addGap(30, 30, 30)
=======
                .addGap(18, 18, 18)
                .addComponent(lblShowDataBahanRacikan)
                .addGap(34, 34, 34)
>>>>>>> 0ce9a0c084ebcea576ba9e3de21249ac04b1e934
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(lblTitleNonObat, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescriptionNonObat))
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTambahNonObat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editNonObat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblShowDataBahanRacikan1)
                .addGap(0, 211, Short.MAX_VALUE))
        );

        contentScrollPane.setViewportView(contentPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
                .addContainerGap())
=======
            .addComponent(contentScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1101, Short.MAX_VALUE)
>>>>>>> 0ce9a0c084ebcea576ba9e3de21249ac04b1e934
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
<<<<<<< HEAD
                .addComponent(contentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1059, javax.swing.GroupLayout.PREFERRED_SIZE)
=======
                .addComponent(contentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1403, javax.swing.GroupLayout.PREFERRED_SIZE)
>>>>>>> 0ce9a0c084ebcea576ba9e3de21249ac04b1e934
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahObatOTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahObatOTCActionPerformed
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Input Data Obat", true);
        TambahItem panel = new TambahItem();
        panel.setJenisItem("Obat OTC");
                
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        // Refresh semua tabel karena gak tahu pasti jenis item mana yang baru ditambahkan
        loadObatOTCTable();
        loadBahanRacikanTable();
        loadNonObatTable();
    }//GEN-LAST:event_btnTambahObatOTCActionPerformed

    private void btnTambahBahanRacikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahBahanRacikanActionPerformed
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Input Data Obat", true);
        TambahItem panel = new TambahItem();
        panel.setJenisItem("Bahan Racikan");
        
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        // Refresh semua tabel karena gak tahu pasti jenis item mana yang baru ditambahkan
        loadObatOTCTable();
        loadBahanRacikanTable();
        loadNonObatTable();
    }//GEN-LAST:event_btnTambahBahanRacikanActionPerformed

    private void btnTambahNonObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahNonObatActionPerformed
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Input Data Obat", true);
        TambahItem panel = new TambahItem();
        panel.setJenisItem("Non Obat");
        
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        // Refresh semua tabel karena gak tahu pasti jenis item mana yang baru ditambahkan
        loadObatOTCTable();
        loadBahanRacikanTable();
        loadNonObatTable();
    }//GEN-LAST:event_btnTambahNonObatActionPerformed

    private void editObatOTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editObatOTCActionPerformed
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Data Obat", true);
        EditItem panel = new EditItem();
        panel.setJenisItem("Obat OTC");
        
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        // Refresh semua tabel karena gak tahu pasti jenis item mana yang baru ditambahkan
        loadObatOTCTable();
        loadBahanRacikanTable();
        loadNonObatTable();
    }//GEN-LAST:event_editObatOTCActionPerformed

    private void editBahanRacikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBahanRacikanActionPerformed
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Data Obat", true);
        EditItem panel = new EditItem();
        panel.setJenisItem("Bahan Racikan");
        
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        // Refresh semua tabel karena gak tahu pasti jenis item mana yang baru ditambahkan
        loadObatOTCTable();
        loadBahanRacikanTable();
        loadNonObatTable();
    }//GEN-LAST:event_editBahanRacikanActionPerformed

    private void editNonObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editNonObatActionPerformed
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Data Obat", true);
        EditItem panel = new EditItem();
        panel.setJenisItem("Non Obat");
        
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        // Refresh semua tabel karena gak tahu pasti jenis item mana yang baru ditambahkan
        loadObatOTCTable();
        loadBahanRacikanTable();
        loadNonObatTable();
    }//GEN-LAST:event_editNonObatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTambahBahanRacikan;
    private javax.swing.JButton btnTambahNonObat;
    private javax.swing.JButton btnTambahObatOTC;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane contentScrollPane;
    private javax.swing.JButton editBahanRacikan;
    private javax.swing.JButton editNonObat;
    private javax.swing.JButton editObatOTC;
    private javax.swing.JPanel headerContainer;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblDescriptionBahanRacikan;
    private javax.swing.JLabel lblDescriptionNonObat;
    private javax.swing.JLabel lblDescriptionObatOTC;
    private javax.swing.JLabel lblShowDataBahanRacikan;
    private javax.swing.JLabel lblShowDataBahanRacikan1;
    private javax.swing.JLabel lblShowDataObatOTC;
    private javax.swing.JLabel lblTitleBahanRacikan;
    private javax.swing.JLabel lblTitleNonObat;
    private javax.swing.JLabel lblTitleObatOTC;
    private javax.swing.JTable tabelBahanRacikan;
    private javax.swing.JTable tabelObatOTC;
    private javax.swing.JTable tableNonObat;
    // End of variables declaration//GEN-END:variables
}
