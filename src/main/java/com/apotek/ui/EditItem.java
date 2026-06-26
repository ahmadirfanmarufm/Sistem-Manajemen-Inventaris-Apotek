package com.apotek.ui;

import com.apotek.exception.DuplicateItemException;
import com.apotek.exception.InvalidInputException;
import com.apotek.exception.ItemNotFoundException;
import com.apotek.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

public class EditItem extends javax.swing.JPanel {
    
    private ArrayList<Item> daftarItem = new ArrayList<>();
    private Item selectedItem; // item yang sedang aktif diedit
    
    public EditItem() {
        initComponents();
        //Menyembunyikan semua field khusus di awal
        kategoriObatInput.setVisible(false);
        lblKategoriObat.setVisible(false);
        hargaBeliInput.setVisible(false);
        lblHargaBeli.setVisible(false);
        hargaJualInput.setVisible(false);
        lblHargaJual.setVisible(false);
        satuanInput.setVisible(false);
        kategoriInput.setVisible(false);
        lblKategori.setVisible(false);
        loadNamaItem();
        NamaItem.setEditable(true); // Buka kunci ketikan
        searchFilter(); // Pasang logika filter
        //Agar posisi field tidak bergeser saat component dihide 
        ((javax.swing.GroupLayout) this.getLayout()).setHonorsVisibility(false);

    }
    
    // Fitur search dan filter item
    public void searchFilter() {
        JTextField editor = (JTextField) NamaItem.getEditor().getEditorComponent();
        
        // Membaca input klik dari mouse 
        editor.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            // Apabila mouse memencet combo box, text langsug kosong agar pengguna dapat langsung mengetik
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (editor.getText().equals("Pilih Item...")) {
                    editor.setText("");
                }
            }
        });
        
        editor.addKeyListener(new java.awt.event.KeyAdapter() { // Membaca input keyboard
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) { 
                javax.swing.SwingUtilities.invokeLater(() -> { // Multithreading Swing (memastikan aplikasi tidak macet / freeze saat mengetik)
                    //Mengambil input pengguna dari JTextField editor
                    String input = editor.getText(); 
                    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(); // Membuat component ComboBoxModel yang menyimpan list barang / selection
                    model.addElement("Pilih Item...");

                    // Filter pencarian item berdasarkan input
                    for (Item item : daftarItem) {
                        if (item.getNamaItem().toLowerCase().contains(input.toLowerCase())) {
                            model.addElement(item.getNamaItem()); //Menambahkan item yang sesuai input ke selection / pilihan
                        }
                    }
                    
                    //Mengset model JComboBox NamaItem sesuai model baru yang dihasilkan setelah filter
                    NamaItem.setModel(model);
                    editor.setText(input); // Kembalikan teks yang sedang diketik
                    if (!input.isEmpty()) NamaItem.showPopup(); // Tampilkan dropdown otomatis
                });
            }
        });
    }
    
    public void loadNamaItem(){
    
        daftarItem.clear();
        daftarItem.addAll(MainApp.stokService.getSemuaObat());
        daftarItem.addAll(MainApp.stokService.getSemuaBahanRacikan());
        daftarItem.addAll(MainApp.stokService.getSemuaNonObat());
        
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Pilih Item...");
        
        for (Item item : daftarItem){
            model.addElement(item.getNamaItem());
        }
        
        NamaItem.setModel(model);
    }

    public void setJenisItem(String jenis) {
        JenisItem1.setSelectedItem(jenis);
    }

    //Exception apabila text field tidak diisi
    private String getRequiredText(JTextField field, String namaField) throws InvalidInputException {
        String text = field.getText().trim();
        if (text.isEmpty()) {
            //Menampilkan pesan error apabila field tidak diisi / kosong
            throw new InvalidInputException(namaField, "tidak boleh kosong");
        }
        return text;
    }

    //Parse JTextField ke Integer sekaligus exception apabila field kosong / tidak valid
    private int parseIntField(JTextField field, String namaField) throws InvalidInputException {
        String text = field.getText().trim();
        if (text.isEmpty()) {
            //Menampilkan pesan error apabila field tidak diisi / kosong
            throw new InvalidInputException(namaField, "tidak boleh kosong");
        }
        try {
            //Parse String ke Integer
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            //Menampilkan pesan error apabila field tidak sesuai dengan kriteria number
            throw new InvalidInputException(namaField, "harus berupa angka bulat");
        }
    }

    //Parse JTextField ke double sekaligus exception apabila field kosong / tidak valid
    private double parseDoubleField(JTextField field, String namaField) throws InvalidInputException {
        String text = field.getText().trim();
        if (text.isEmpty()) {
            //Menampilkan pesan error apabila field tidak diisi / kosong
            throw new InvalidInputException(namaField, "tidak boleh kosong");
        }
        try {
            //Parse String ke double
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            //Menampilkan pesan error apabila field tidak sesuai dengan kriteria number
            throw new InvalidInputException(namaField, "harus berupa angka");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        ItemID2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        itemIdField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        NamaItem = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        quantityInput = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        stokMinimumInput = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        deskripsiInput = new javax.swing.JTextArea();
        satuanInput = new javax.swing.JComboBox<>();
        JenisItem1 = new javax.swing.JComboBox<>();
        lblKategoriObat = new javax.swing.JLabel();
        kategoriObatInput = new javax.swing.JTextField();
        lblHargaJual = new javax.swing.JLabel();
        lblHargaBeli = new javax.swing.JLabel();
        lblKategori = new javax.swing.JLabel();
        kategoriInput = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        simpanButton = new javax.swing.JButton();
        hargaJualInput = new javax.swing.JTextField();
        hargaBeliInput = new javax.swing.JTextField();
        expiredDateInput = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Nama Item");

        ItemID2.addActionListener(this::ItemID2ActionPerformed);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(ItemID2, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ItemID2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(0, 145, 55));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Input Data Item");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Item ID");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));

        itemIdField.addActionListener(this::itemIdFieldActionPerformed);
        jPanel3.add(itemIdField, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 38, 198, -1));
        itemIdField.getAccessibleContext().setAccessibleName("ItemID");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nama Item");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));

        NamaItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        NamaItem.addActionListener(this::NamaItemActionPerformed);
        jPanel4.add(NamaItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 32, 220, 30));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Kuantitas");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Kuantitas");
        jPanel10.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        jPanel6.add(quantityInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 190, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Stok Minimum");
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));
        jPanel7.add(stokMinimumInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 230, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Deskripsi");
        jPanel9.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));

        deskripsiInput.setColumns(20);
        deskripsiInput.setRows(5);
        jScrollPane1.setViewportView(deskripsiInput);

        jPanel9.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 26, 480, 162));

        satuanInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Satuan", "Kg", "Gram", "mg", "L", "mL" }));
        satuanInput.addActionListener(this::satuanInputActionPerformed);

        JenisItem1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jenis Item", "Obat OTC", "Bahan Racikan", "Non Obat" }));
        JenisItem1.addActionListener(this::JenisItem1ActionPerformed);

        lblKategoriObat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKategoriObat.setText("KategoriObat");

        kategoriObatInput.addActionListener(this::kategoriObatInputActionPerformed);

        lblHargaJual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHargaJual.setText("Harga Jual");

        lblHargaBeli.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHargaBeli.setText("Harga Beli");

        lblKategori.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKategori.setText("Kategori");

        kategoriInput.addActionListener(this::kategoriInputActionPerformed);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tanggal Kadaluarsa (YYYY-MM-DD)");

        simpanButton.setBackground(new java.awt.Color(0, 145, 55));
        simpanButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        simpanButton.setForeground(new java.awt.Color(255, 255, 255));
        simpanButton.setText("SIMPAN");
        simpanButton.addActionListener(this::simpanButtonActionPerformed);

        expiredDateInput.addActionListener(this::expiredDateInputActionPerformed);

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("HAPUS ITEM");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JenisItem1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                                .addGap(75, 75, 75)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kategoriInput, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblKategori)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(expiredDateInput, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jLabel7))))
                                .addGap(75, 75, 75)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(satuanInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblKategoriObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(kategoriObatInput)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblHargaJual)
                                            .addComponent(hargaJualInput, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblHargaBeli)
                                            .addComponent(hargaBeliInput, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(6, 6, 6)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addComponent(simpanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JenisItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblKategoriObat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(kategoriObatInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHargaJual)
                            .addComponent(lblHargaBeli))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hargaJualInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hargaBeliInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(satuanInput, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblKategori)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kategoriInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expiredDateInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ItemID2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemID2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ItemID2ActionPerformed

    private void satuanInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satuanInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_satuanInputActionPerformed

    private void JenisItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JenisItem1ActionPerformed
        String jenis = JenisItem1.getSelectedItem().toString().trim();
        // Tampilkan sesuai pilihan
        switch (jenis) {

            case "Obat OTC":
                //Memerlukan field untuk input kategori obat, harga beli, dan harga jual
                satuanInput.setVisible(false);
                lblKategori.setVisible(false);
                kategoriInput.setVisible(false);
                kategoriObatInput.setVisible(true);
                lblKategoriObat.setVisible(true);
                hargaBeliInput.setVisible(true);
                lblHargaBeli.setVisible(true);
                hargaJualInput.setVisible(true);
                lblHargaJual.setVisible(true);
                break;

            case "Bahan Racikan":
                //Memerlukan field untuk input satuan
                satuanInput.setVisible(true);
                lblKategori.setVisible(false);
                kategoriInput.setVisible(false);
                lblKategoriObat.setVisible(false);
                kategoriObatInput.setVisible(false);
                lblHargaBeli.setVisible(false);
                hargaBeliInput.setVisible(false);
                lblHargaJual.setVisible(false);
                hargaJualInput.setVisible(false);
                break;

            case "Non Obat":
                //Memerlukan field untuk input kategori, harga beli, dan harga jual
                kategoriInput.setVisible(true);
                lblKategori.setVisible(true);
                hargaBeliInput.setVisible(true);
                lblHargaBeli.setVisible(true);
                hargaJualInput.setVisible(true);
                lblHargaJual.setVisible(true);
                satuanInput.setVisible(false);
                lblKategoriObat.setVisible(false);
                kategoriObatInput.setVisible(false);
                break;
        }
    }//GEN-LAST:event_JenisItem1ActionPerformed

    private void kategoriInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoriInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kategoriInputActionPerformed

    private void kategoriObatInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoriObatInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kategoriObatInputActionPerformed

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed

        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Pilih item yang ingin diedit terlebih dahulu.",
                    "Item Belum Dipilih", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String jenis = JenisItem1.getSelectedItem().toString().trim();

        try {
            String idItem = selectedItem.getIdItem(); // ID tidak diubah saat edit

            JTextField editor = (JTextField) NamaItem.getEditor().getEditorComponent();
            String namaItem = editor.getText().trim();
            if (namaItem.isEmpty()) {
                throw new InvalidInputException("Nama Item", "tidak boleh kosong");
            }

            int quantity = parseIntField(quantityInput, "Kuantitas");
            int stokMinimum = parseIntField(stokMinimumInput, "Stok Minimum");
            String expiredDate = getRequiredText(expiredDateInput, "Tanggal Kadaluarsa");
            String deskripsi = deskripsiInput.getText().trim();
            LocalDate tglExpired = LocalDate.parse(expiredDate);

            switch (jenis) {
                case "Obat OTC" -> {
                    String kategoriObat = getRequiredText(kategoriObatInput, "Kategori Obat");
                    double hargaBeli = parseDoubleField(hargaBeliInput, "Harga Beli");
                    double hargaJual = parseDoubleField(hargaJualInput, "Harga Jual");
                    MainApp.stokService.updateObat(new ObatOTC(kategoriObat, hargaBeli, hargaJual,
                            idItem, namaItem, quantity, stokMinimum, tglExpired, deskripsi));
                }
                case "Bahan Racikan" -> {
                    String satuan = satuanInput.getSelectedItem().toString().trim();
                    if (satuan.equals("Satuan")) {
                        throw new InvalidInputException("Satuan", "harus dipilih");
                    }
                    MainApp.stokService.updateBahanRacikan(new BahanRacikan(idItem, namaItem, satuan,
                            quantity, stokMinimum, tglExpired, deskripsi));
                }
                case "Non Obat" -> {
                    String kategori = getRequiredText(kategoriInput, "Kategori");
                    double hargaBeli = parseDoubleField(hargaBeliInput, "Harga Beli");
                    double hargaJual = parseDoubleField(hargaJualInput, "Harga Jual");
                    MainApp.stokService.updateNonObat(new NonObat(kategori, hargaBeli, hargaJual,
                            idItem, namaItem, quantity, stokMinimum, tglExpired, deskripsi));
                }
            }

            JOptionPane.showMessageDialog(this, "Item berhasil diubah.");
            loadNamaItem();
            resetForm();
            tutupPanel();

        } catch (InvalidInputException | ItemNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal Mengubah Item", JOptionPane.ERROR_MESSAGE);
        } catch (java.time.format.DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Format tanggal harus yyyy-MM-dd",
                    "Gagal Mengubah Item", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_simpanButtonActionPerformed

    private void resetForm() {
        //Mengembalikan panel EditItem ke awal
        JenisItem1.setSelectedIndex(0);
        itemIdField.setText("");
        NamaItem.setSelectedIndex(0);
        selectedItem = null;
        hargaBeliInput.setText("");
        hargaJualInput.setText("");
        deskripsiInput.setText("");
        expiredDateInput.setText("");
        kategoriObatInput.setText("");
        kategoriInput.setText("");
        satuanInput.setSelectedIndex(0);
        stokMinimumInput.setText("");
    }

    private void tutupPanel() {
        //Menutup panel apabila tombol simpan dipencet
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
    }

    private void expiredDateInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expiredDateInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expiredDateInputActionPerformed

    private void NamaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaItemActionPerformed

        String selected = (String) NamaItem.getSelectedItem();

        if (selected == null || selected.equals("Pilih Item...")) {
            selectedItem = null;
           itemIdField.setText("");
            return;
        }

        for (Item item : daftarItem) {
            if (item.getNamaItem().equals(selected)) {
                selectedItem = item;
                populateForm(item);
                break;
            }
        }
    }//GEN-LAST:event_NamaItemActionPerformed

    // Berfungsi mengisi form dengan atribut yang dimiliki Item
    private void populateForm(Item item) {
        itemIdField.setText(item.getIdItem());
        quantityInput.setText(String.valueOf(item.getQuantity()));
        stokMinimumInput.setText(String.valueOf(item.getStokMinimum()));
        expiredDateInput.setText(item.getExpiredDate().toString());
        deskripsiInput.setText(item.getDeskripsi());

        if (item instanceof ObatOTC obat) {
            JenisItem1.setSelectedItem("Obat OTC");
            kategoriObatInput.setText(obat.getKategori());
            hargaBeliInput.setText(String.valueOf(obat.getHargaBeli()));
            hargaJualInput.setText(String.valueOf(obat.getHargaJual()));
        } else if (item instanceof BahanRacikan bahan) {
            JenisItem1.setSelectedItem("Bahan Racikan");
            satuanInput.setSelectedItem(bahan.getSatuan());
        } else if (item instanceof NonObat non) {
            JenisItem1.setSelectedItem("Non Obat");
            kategoriInput.setText(non.getKategori());
            hargaBeliInput.setText(String.valueOf(non.getHargaBeli()));
            hargaJualInput.setText(String.valueOf(non.getHargaJual()));
        }
    }
    
    private void itemIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemIdFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Pilih item yang ingin dihapus terlebih dahulu.",
                    "Item Belum Dipilih", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Yakin ingin menghapus item \"" + selectedItem.getNamaItem() + "\"?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            String idItem = selectedItem.getIdItem();
            if (selectedItem instanceof ObatOTC) {
                MainApp.stokService.hapusObat(idItem);
            } else if (selectedItem instanceof BahanRacikan) {
                MainApp.stokService.hapusBahanRacikan(idItem);
            } else if (selectedItem instanceof NonObat) {
                MainApp.stokService.hapusNonObat(idItem);
            }
            JOptionPane.showMessageDialog(this, "Item berhasil dihapus.");
            loadNamaItem();
            resetForm();
            tutupPanel();
        } catch (ItemNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal Menghapus Item", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ItemID2;
    private javax.swing.JComboBox<String> JenisItem1;
    private javax.swing.JComboBox<String> NamaItem;
    private javax.swing.JTextArea deskripsiInput;
    private javax.swing.JTextField expiredDateInput;
    private javax.swing.JTextField hargaBeliInput;
    private javax.swing.JTextField hargaJualInput;
    private javax.swing.JTextField itemIdField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kategoriInput;
    private javax.swing.JTextField kategoriObatInput;
    private javax.swing.JLabel lblHargaBeli;
    private javax.swing.JLabel lblHargaJual;
    private javax.swing.JLabel lblKategori;
    private javax.swing.JLabel lblKategoriObat;
    private javax.swing.JTextField quantityInput;
    private javax.swing.JComboBox<String> satuanInput;
    private javax.swing.JButton simpanButton;
    private javax.swing.JTextField stokMinimumInput;
    // End of variables declaration//GEN-END:variables
}
