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
        btnNextNonObat = new javax.swing.JButton();
        lblHalamanNonObat = new javax.swing.JLabel();
        btnPreviousNonObat = new javax.swing.JButton();
        editObatOTC = new javax.swing.JButton();
        editBahanRacikan = new javax.swing.JButton();
        editNonObat = new javax.swing.JButton();

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

        contentPanel.add(headerContainer);

        lblTitleObatOTC.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleObatOTC.setForeground(new java.awt.Color(20, 145, 66));
        lblTitleObatOTC.setText("Manajemen Obat OTC");
        contentPanel.add(lblTitleObatOTC);

        lblDescriptionObatOTC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescriptionObatOTC.setForeground(new java.awt.Color(42, 137, 79));
        lblDescriptionObatOTC.setText(" Kelola inventaris obat over-the-counter");
        contentPanel.add(lblDescriptionObatOTC);

        btnTambahObatOTC.setBackground(new java.awt.Color(20, 145, 66));
        btnTambahObatOTC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTambahObatOTC.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahObatOTC.setText("Tambah Obat OTC");
        btnTambahObatOTC.setBorder(null);
        btnTambahObatOTC.addActionListener(this::btnTambahObatOTCActionPerformed);
        contentPanel.add(btnTambahObatOTC);

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

        contentPanel.add(jScrollPane2);

        lblShowDataObatOTC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblShowDataObatOTC.setForeground(new java.awt.Color(42, 137, 79));
        lblShowDataObatOTC.setText("Menampilkan 0 dari 15 data");
        contentPanel.add(lblShowDataObatOTC);

        lblTitleBahanRacikan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleBahanRacikan.setForeground(new java.awt.Color(20, 145, 66));
        lblTitleBahanRacikan.setText("Manajemen Bahan Racikan");
        contentPanel.add(lblTitleBahanRacikan);

        lblDescriptionBahanRacikan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescriptionBahanRacikan.setForeground(new java.awt.Color(42, 137, 79));
        lblDescriptionBahanRacikan.setText("Kelola inventaris bahan untuk meracik obat");
        contentPanel.add(lblDescriptionBahanRacikan);

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

        contentPanel.add(jScrollPane3);

        btnTambahBahanRacikan.setBackground(new java.awt.Color(20, 145, 66));
        btnTambahBahanRacikan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTambahBahanRacikan.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahBahanRacikan.setText("Tambah Bahan Racikan");
        btnTambahBahanRacikan.setMaximumSize(new java.awt.Dimension(148, 25));
        btnTambahBahanRacikan.setMinimumSize(new java.awt.Dimension(148, 25));
        btnTambahBahanRacikan.setPreferredSize(new java.awt.Dimension(148, 25));
        btnTambahBahanRacikan.addActionListener(this::btnTambahBahanRacikanActionPerformed);
        contentPanel.add(btnTambahBahanRacikan);

        lblShowDataBahanRacikan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblShowDataBahanRacikan.setForeground(new java.awt.Color(42, 137, 79));
        lblShowDataBahanRacikan.setText("Menampilkan 0 dari 15 data");
        contentPanel.add(lblShowDataBahanRacikan);

        lblTitleNonObat.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitleNonObat.setForeground(new java.awt.Color(20, 145, 66));
        lblTitleNonObat.setText("Manajemen Non Obat");
        contentPanel.add(lblTitleNonObat);

        lblDescriptionNonObat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescriptionNonObat.setForeground(new java.awt.Color(42, 137, 79));
        lblDescriptionNonObat.setText("Kelola inventaris produk non-obat");
        contentPanel.add(lblDescriptionNonObat);

        btnTambahNonObat.setBackground(new java.awt.Color(20, 145, 66));
        btnTambahNonObat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTambahNonObat.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahNonObat.setText("Tambah Non Obat");
        btnTambahNonObat.setMaximumSize(new java.awt.Dimension(148, 25));
        btnTambahNonObat.setMinimumSize(new java.awt.Dimension(148, 25));
        btnTambahNonObat.setPreferredSize(new java.awt.Dimension(148, 25));
        btnTambahNonObat.addActionListener(this::btnTambahNonObatActionPerformed);
        contentPanel.add(btnTambahNonObat);

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

        contentPanel.add(jScrollPane4);

        lblShowDataBahanRacikan1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblShowDataBahanRacikan1.setForeground(new java.awt.Color(42, 137, 79));
        lblShowDataBahanRacikan1.setText("Menampilkan 0 dari 15 data");
        contentPanel.add(lblShowDataBahanRacikan1);

        btnNextNonObat.setBackground(new java.awt.Color(20, 145, 66));
        btnNextNonObat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNextNonObat.setForeground(new java.awt.Color(255, 255, 255));
        btnNextNonObat.setText("Selanjutnya");
        contentPanel.add(btnNextNonObat);

        lblHalamanNonObat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHalamanNonObat.setText("Halaman 0 dari 0");
        contentPanel.add(lblHalamanNonObat);

        btnPreviousNonObat.setBackground(new java.awt.Color(20, 145, 66));
        btnPreviousNonObat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPreviousNonObat.setForeground(new java.awt.Color(255, 255, 255));
        btnPreviousNonObat.setText("Sebelumnya");
        contentPanel.add(btnPreviousNonObat);

        editObatOTC.setBackground(new java.awt.Color(128, 128, 128));
        editObatOTC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        editObatOTC.setForeground(new java.awt.Color(255, 255, 255));
        editObatOTC.setText("Edit Obat");
        editObatOTC.addActionListener(this::editObatOTCActionPerformed);
        contentPanel.add(editObatOTC);

        editBahanRacikan.setBackground(new java.awt.Color(128, 128, 128));
        editBahanRacikan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        editBahanRacikan.setForeground(new java.awt.Color(255, 255, 255));
        editBahanRacikan.setText("Edit Bahan Racikan");
        editBahanRacikan.addActionListener(this::editBahanRacikanActionPerformed);
        contentPanel.add(editBahanRacikan);

        editNonObat.setBackground(new java.awt.Color(128, 128, 128));
        editNonObat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        editNonObat.setForeground(new java.awt.Color(255, 255, 255));
        editNonObat.setText("Edit Non Obat");
        editNonObat.addActionListener(this::editNonObatActionPerformed);
        contentPanel.add(editNonObat);

        contentScrollPane.setViewportView(contentPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(contentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(contentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1059, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
    private javax.swing.JButton btnNextNonObat;
    private javax.swing.JButton btnPreviousNonObat;
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
    private javax.swing.JLabel lblHalamanNonObat;
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
