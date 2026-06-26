package com.apotek.ui;

import com.apotek.model.Item;
import com.apotek.model.NonObat;
import com.apotek.model.ObatOTC;
import com.apotek.model.RiwayatStok;
import com.apotek.observer.DashboardObserver;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author himorii
 */
public class InventarisPanel extends javax.swing.JPanel implements DashboardObserver {
    private PieChart pieChart;

    private PieChart.Data otcData;
    private PieChart.Data bahanRacikanData;
    private PieChart.Data nonObatData;
    
    private XYChart.Series<String, Number> masuk;
    private XYChart.Series<String, Number> keluar;
    private XYChart.Data<String, Number>[] masukData;
    private XYChart.Data<String, Number>[] keluarData;

    private void initPieChart() {
        int obatOtcTotal = MainApp.stokService.getSemuaObat().size();
        int bahanRacikanTotal = MainApp.stokService.getSemuaBahanRacikan().size();
        int nonObatTotal = MainApp.stokService.getSemuaNonObat().size();

        JFXPanel fxPie = new JFXPanel();
        distribusiInventarisPanel.setLayout(new BorderLayout());
        distribusiInventarisPanel.add(fxPie);
        
        Platform.runLater(() -> {     
            PieChart pieChart = new PieChart();
            
            pieChart.setTitle("Distribusi Inventaris");

            otcData = new PieChart.Data("Obat OTC", obatOtcTotal);
            bahanRacikanData = new PieChart.Data("Bahan Racikan", bahanRacikanTotal);
            nonObatData = new PieChart.Data("Non Obat", nonObatTotal);

            pieChart.getData().addAll(otcData, bahanRacikanData, nonObatData);

            pieChart.setLegendVisible(true);
            pieChart.setLabelsVisible(true);
            pieChart.setAnimated(true);

            Scene scene = new Scene(new StackPane(pieChart));

            fxPie.setScene(scene);

            Platform.runLater(() -> {
                PieChart.Data[] dataList = {
                    otcData,
                    bahanRacikanData,
                    nonObatData
                };

                String[] colors = {
                    "#149142",
                    "#2EAE5E",
                    "#6CCF8A"
                };

                double total = otcData.getPieValue() + bahanRacikanData.getPieValue() + nonObatData.getPieValue();

                for (int i = 0; i < dataList.length; i++) {

                    PieChart.Data data = dataList[i];

                    Node node = data.getNode();

                    if (node != null) {

                        node.setStyle("-fx-pie-color:" + colors[i] + ";");

                        double persen = (data.getPieValue() * 100) / total;

                        Tooltip tooltip =
                                new Tooltip(
                                        "Kategori : "
                                        + data.getName()
                                        + "\nJumlah : "
                                        + (int) data.getPieValue()
                                        + " item"
                                        + "\nPersentase : "
                                        + String.format("%.1f%%", persen)
                                );

                        tooltip.setStyle(
                                "-fx-background-color:white;"
                                + "-fx-text-fill:#111827;"
                                + "-fx-border-color:#149142;"
                                + "-fx-border-radius:6;"
                                + "-fx-background-radius:6;"
                                + "-fx-font-size:12px;"
                        );

                        Tooltip.install(node, tooltip);
                    }
                }
            });
        });
    }
    
    private void initBarChart() {
        int[] stokMasuk = new int[12];
        int[] stokKeluar = new int[12];

        for(RiwayatStok r : MainApp.stokService.getRiwayatStok()){
            int bulan = r.getTanggal().getMonthValue()-1;
            
            if(r.getTipe().equals("MASUK")){
                stokMasuk[bulan] += r.getJumlah();
            } else {
                stokKeluar[bulan] += r.getJumlah();
            }
        }
        
        JFXPanel fxPanel = new JFXPanel();

        aktivitasStokBulananPanel.setLayout(new BorderLayout());
        aktivitasStokBulananPanel.add(fxPanel, BorderLayout.CENTER);
        

        Platform.runLater(() -> {

            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel("Bulan");
            yAxis.setLabel("Jumlah");

            BarChart<String, Number> barChart =
                    new BarChart<>(xAxis, yAxis);

            barChart.setTitle("Aktivitas Stok Bulanan");

            String[] bulan={
                "Jan","Feb","Mar","Apr","Mei","Jun",
                "Jul","Agu","Sep","Okt","Nov","Des"
            };

            masuk = new XYChart.Series<>();
            masuk.setName("Stok Masuk");

            keluar = new XYChart.Series<>();
            keluar.setName("Stok Keluar");
            
            masukData = new XYChart.Data[12];
            keluarData = new XYChart.Data[12];
            
            masukData = new XYChart.Data[12];
            keluarData = new XYChart.Data[12];
            
            for(int i = 0; i < 12; i++){
                masukData[i] = new XYChart.Data<>(bulan[i], stokMasuk[i]);
                keluarData[i] = new XYChart.Data<>(bulan[i], stokKeluar[i]);

                masuk.getData().add(masukData[i]);
                keluar.getData().add(keluarData[i]);
            }

            barChart.getData().addAll(masuk, keluar);
            
            Platform.runLater(() -> {
                for (Node node : barChart.lookupAll(".default-color0.chart-bar")) {
                    node.setStyle("-fx-bar-fill:#149142;");
                }

                for (Node node : barChart.lookupAll(".default-color1.chart-bar")) {

                    node.setStyle("-fx-bar-fill:#81C784;");
                }
            });

            Scene scene = new Scene(new StackPane(barChart));

            fxPanel.setScene(scene);
            
            for(XYChart.Data<String,Number> data : masuk.getData()){
                if(data.getNode() != null) {
                    Tooltip tooltip = new Tooltip(
                        "Stok Masuk\n"
                        + data.getXValue()
                        + "\n"
                        + data.getYValue().intValue()
                        + " unit"
                    );
                    Tooltip.install(data.getNode(),tooltip);
                }
            }
            
            for(XYChart.Data<String,Number> data : keluar.getData()){
                if(data.getNode() != null) {
                    Tooltip tooltip = new Tooltip(
                        "Stok Keluar\n"
                        + data.getXValue()
                        + "\n"
                        + data.getYValue().intValue()
                        + " unit"
                    );
                    Tooltip.install(data.getNode(),tooltip);
                }
            }
        });
    }
    
    private void refreshBarChart() {
        Platform.runLater(() -> {
            int[] stokMasuk = new int[12];
            int[] stokKeluar = new int[12];

            for(RiwayatStok r : MainApp.stokService.getRiwayatStok()){
                int bulan = r.getTanggal().getMonthValue()-1;

                if(r.getTipe().equals("MASUK")){
                    stokMasuk[bulan] += r.getJumlah();
                } else {
                    stokKeluar[bulan] += r.getJumlah();
                }
            }

            for(int i = 0; i < 12; i++){
                masukData[i].setYValue(stokMasuk[i]);
                keluarData[i].setYValue(stokKeluar[i]);
            }
        });
    }
    
    private void refreshPieChart(){
        Platform.runLater(() -> {
            otcData.setPieValue(MainApp.stokService.getSemuaObat().size());
            bahanRacikanData.setPieValue(MainApp.stokService.getSemuaBahanRacikan().size());
            nonObatData.setPieValue(MainApp.stokService.getSemuaNonObat().size());
        });
    }
    
    private void refreshRingkasan(){
        refreshPieChart();
        refreshBarChart();
        refreshAlert();
    }
    
    @Override
    public void updateDashboard(){
        SwingUtilities.invokeLater(() -> {
            refreshRingkasan();
        });
    }
    
    public void refreshAlert() {
        ArrayList<Item> semuaItem = new ArrayList<>();
        semuaItem.addAll(MainApp.stokService.getSemuaObat());
        semuaItem.addAll(MainApp.stokService.getSemuaBahanRacikan());
        semuaItem.addAll(MainApp.stokService.getSemuaNonObat());
        
        int totalKritis = 0;
        int totalExpired = 0;
        
        LocalDate hariIni = LocalDate.now();

        DefaultTableModel modelStok = new DefaultTableModel(
                new String[]{"Nama Produk", "Kategori", "Stok saat ini", "Minimum stok", "Status"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Item item : semuaItem) {
            long sisaHari = item.getExpiredDate().toEpochDay() - LocalDate.now().toEpochDay();
            
            if(sisaHari < 0) {
                totalExpired++;
            }
            
            if (item.getQuantity() <= item.getStokMinimum()) {
                totalKritis++;
                // Tentukan nama kategori berdasarkan tipe item
                String kategori;
                if (item instanceof ObatOTC) {
                    kategori = ((ObatOTC) item).getKategori();
                } else if (item instanceof NonObat) {
                    kategori = ((NonObat) item).getKategori();
                } else {
                    kategori = "Bahan Racikan";
                }

                // Tentukan status: Habis atau Stok Rendah
                String status;
                if (item.getQuantity() == 0) {
                    status = "Habis";
                } else {
                    status = "Stok Rendah";
                }
                // Tambahkan baris ke tabel
                modelStok.addRow(new Object[]{
                    item.getNamaItem(),
                    kategori,
                    item.getQuantity(),
                    item.getStokMinimum(),
                    status
                });
            }
        }
        
        lblKritis.setText(totalKritis + " Kritis");
        lblExpired.setText(totalExpired + " Expired");

        tableStokRendah.setModel(modelStok);
    
        DefaultTableModel modelExpired = new DefaultTableModel(
                new String[]{"ID", "Nama Barang", "Tanggal Expired", "Sisa Hari", "Status"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Item item : semuaItem) {

            // Hitung selisih hari antara hari ini dan tanggal expired
            long sisaHari = item.getExpiredDate().toEpochDay() - LocalDate.now().toEpochDay();

            // Hanya tampilkan jika sisa hari antara 0 sampai 5
            if (sisaHari >= 0 && sisaHari <= 5) {

                // Kritis jika <= 2 hari lagi, selainnya Mendekati Expired
                String status;
                if(sisaHari <= 2){
                    status = "Kritis";
                }else{
                    status = "Mendekati Expired";
                }

                // Tambahkan baris ke tabel
                modelExpired.addRow(new Object[]{
                    item.getIdItem(),
                    item.getNamaItem(),
                    item.getExpiredDate().toString(),
                    sisaHari + " hari",
                    status
                });
            }
        }

        tabelStokMendekatiExpired.setModel(modelExpired); // tampilkan ke tabel UI
    }
    
    /**
     * Creates new form RingkasanPanel
     */
    public InventarisPanel() {
        initComponents();
        
        MainApp.dashboardManager.addObserver(this);
        
        initPieChart();
        initBarChart();
        
        updateDashboard();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aktivitasStokBulananPanel = new javax.swing.JPanel();
        distribusiInventarisPanel = new javax.swing.JPanel();
        lblExpired = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelStokMendekatiExpired = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lblKritis = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableStokRendah = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(746, 455));
        setMinimumSize(new java.awt.Dimension(100, 100));
        setPreferredSize(new java.awt.Dimension(746, 455));

        aktivitasStokBulananPanel.setBackground(new java.awt.Color(255, 255, 255));
        aktivitasStokBulananPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(20, 145, 66), 1, true));
        aktivitasStokBulananPanel.setPreferredSize(new java.awt.Dimension(320, 245));

        javax.swing.GroupLayout aktivitasStokBulananPanelLayout = new javax.swing.GroupLayout(aktivitasStokBulananPanel);
        aktivitasStokBulananPanel.setLayout(aktivitasStokBulananPanelLayout);
        aktivitasStokBulananPanelLayout.setHorizontalGroup(
            aktivitasStokBulananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        aktivitasStokBulananPanelLayout.setVerticalGroup(
            aktivitasStokBulananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        distribusiInventarisPanel.setBackground(new java.awt.Color(255, 255, 255));
        distribusiInventarisPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(20, 145, 66), 1, true));
        distribusiInventarisPanel.setPreferredSize(new java.awt.Dimension(320, 245));

        javax.swing.GroupLayout distribusiInventarisPanelLayout = new javax.swing.GroupLayout(distribusiInventarisPanel);
        distribusiInventarisPanel.setLayout(distribusiInventarisPanelLayout);
        distribusiInventarisPanelLayout.setHorizontalGroup(
            distribusiInventarisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );
        distribusiInventarisPanelLayout.setVerticalGroup(
            distribusiInventarisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        lblExpired.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblExpired.setForeground(new java.awt.Color(255, 0, 0));
        lblExpired.setText("0 Expired");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Peringatan Stok Rendah");

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(20, 145, 66), 1, true));

        tabelStokMendekatiExpired.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nama Produk", "Sisa Hari", "Status"
            }
        ));
        jScrollPane1.setViewportView(tabelStokMendekatiExpired);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Produk Mendekati Expired");

        lblKritis.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblKritis.setForeground(new java.awt.Color(255, 0, 0));
        lblKritis.setText("0 Kritis");

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(20, 145, 66), 1, true));

        tableStokRendah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama Produk", "Kategori", "Stok saat ini", "Minimum stok", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableStokRendah);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblKritis))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(distribusiInventarisPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblExpired))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addComponent(aktivitasStokBulananPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(aktivitasStokBulananPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(distribusiInventarisPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblExpired)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(lblKritis))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(81, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel aktivitasStokBulananPanel;
    private javax.swing.JPanel distribusiInventarisPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblExpired;
    private javax.swing.JLabel lblKritis;
    private javax.swing.JTable tabelStokMendekatiExpired;
    private javax.swing.JTable tableStokRendah;
    // End of variables declaration//GEN-END:variables
}
