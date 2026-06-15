/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.apotekertest.ui;

import java.awt.BorderLayout;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;


/**
 *
 * @author himorii
 */
public class InventarisPanel extends javax.swing.JPanel {
    
    private void initPieChart() {

        JFXPanel fxPanel = new JFXPanel();

        distribusiKategoriPanel.setLayout(new BorderLayout());
        distribusiKategoriPanel.add(fxPanel, BorderLayout.CENTER);

        Platform.runLater(() -> {

            PieChart pieChart = new PieChart();
            pieChart.setTitle("Distribusi per Kategori");

            PieChart.Data otc =
                    new PieChart.Data("Obat OTC", 1247);

            PieChart.Data bahanRacikan =
                    new PieChart.Data("Bahan Racikan", 324);

            PieChart.Data nonObat =
                    new PieChart.Data("Non Obat", 156);

            pieChart.getData().addAll(
                    otc,
                    bahanRacikan,
                    nonObat);

            pieChart.setLegendVisible(true);
            pieChart.setLabelsVisible(true);
            pieChart.setAnimated(false);

            Scene scene =
                    new Scene(new StackPane(pieChart));

            fxPanel.setScene(scene);

            Platform.runLater(() -> {

                PieChart.Data[] dataList = {
                    otc,
                    bahanRacikan,
                    nonObat
                };

                String[] colors = {
                    "#149142",
                    "#2EAE5E",
                    "#6CCF8A"
                };

                double total =
                        otc.getPieValue()
                        + bahanRacikan.getPieValue()
                        + nonObat.getPieValue();

                for (int i = 0; i < dataList.length; i++) {

                    PieChart.Data data = dataList[i];

                    Node node = data.getNode();

                    if (node != null) {

                        node.setStyle(
                                "-fx-pie-color:"
                                + colors[i]
                                + ";");

                        double persen =
                                (data.getPieValue() * 100)
                                / total;

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

                        Tooltip.install(
                                node,
                                tooltip);
                    }
                }
            });
        });
    }
    
    private void initBarChart() {

        JFXPanel fxPanel = new JFXPanel();

        pergerakanStokPanel.setLayout(
                new BorderLayout());

        pergerakanStokPanel.add(
                fxPanel,
                BorderLayout.CENTER);

        Platform.runLater(() -> {

            CategoryAxis xAxis =
                    new CategoryAxis();

            NumberAxis yAxis =
                    new NumberAxis();

            xAxis.setLabel("Bulan");
            yAxis.setLabel("Jumlah Barang");

            BarChart<String, Number> chart =
                    new BarChart<>(xAxis, yAxis);

            chart.setTitle("Pergerakan Stok Bulanan");

            XYChart.Series<String, Number> masuk = new XYChart.Series<>();

            masuk.setName("Stok Masuk");

            masuk.getData().add(new XYChart.Data<>("Jan", 320));

            masuk.getData().add(new XYChart.Data<>("Feb", 280));

            masuk.getData().add(new XYChart.Data<>("Mar", 410));

            masuk.getData().add(new XYChart.Data<>("Apr", 390));

            masuk.getData().add(new XYChart.Data<>("Mei", 480));

            masuk.getData().add(new XYChart.Data<>("Jun", 350));

            XYChart.Series<String, Number> keluar = new XYChart.Series<>();

            keluar.setName("Stok Keluar");

            keluar.getData().add(
                    new XYChart.Data<>("Jan", 210));

            keluar.getData().add(
                    new XYChart.Data<>("Feb", 190));

            keluar.getData().add(
                    new XYChart.Data<>("Mar", 270));

            keluar.getData().add(
                    new XYChart.Data<>("Apr", 310));

            keluar.getData().add(
                    new XYChart.Data<>("Mei", 360));

            keluar.getData().add(
                    new XYChart.Data<>("Jun", 240));

            chart.getData().addAll(
                    masuk,
                    keluar);

            Scene scene = new Scene(new StackPane(chart));

            fxPanel.setScene(scene);
            
            chart.setCategoryGap(20);
            chart.setBarGap(4);
            chart.setLegendVisible(true);
            chart.setAnimated(true);
            chart.setAlternativeColumnFillVisible(false);
            chart.setAlternativeRowFillVisible(false);            

            Platform.runLater(() -> {
                Platform.runLater(() -> {
                    for (Node node : chart.lookupAll(".default-color0.chart-bar")) {
                        node.setStyle("-fx-bar-fill:#149142;");
                    }

                    for (Node node : chart.lookupAll(".default-color1.chart-bar")) {

                        node.setStyle("-fx-bar-fill:#81C784;");
                    }
                });

                for (XYChart.Data<String, Number> data : masuk.getData()) {
                    Runnable attach = () -> {

                        Node node = data.getNode();

                        if (node != null) {

                            Tooltip tooltip =
                                    new Tooltip(
                                            "Bulan : "
                                            + data.getXValue()
                                            + "\nJenis : Stok Masuk"
                                            + "\nJumlah : "
                                            + data.getYValue()
                                            + " item"
                                    );

                            tooltip.setShowDelay(
                                    javafx.util.Duration.millis(100));

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
                    };

                    if (data.getNode() != null) {

                        attach.run();

                    } else {

                        data.nodeProperty().addListener(
                                (obs, oldNode, newNode) -> {

                            if (newNode != null) {

                                attach.run();
                            }
                        });
                    }
                }
                
                for (XYChart.Data<String, Number> data : keluar.getData()) {
                    Runnable attach = () -> {
                        Node node = data.getNode();

                        if (node != null) {

                            Tooltip tooltip =
                                    new Tooltip(
                                            "Bulan : "
                                            + data.getXValue()
                                            + "\nJenis : Stok Keluar"
                                            + "\nJumlah : "
                                            + data.getYValue()
                                            + " item"
                                    );

                            tooltip.setShowDelay(
                                    javafx.util.Duration.millis(100));

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
                    };

                    if (data.getNode() != null) {

                        attach.run();

                    } else {

                        data.nodeProperty().addListener(
                                (obs, oldNode, newNode) -> {

                            if (newNode != null) {

                                attach.run();
                            }
                        });
                    }
                }
            });
        });
    }
    
    /**
     * Creates new form RingkasanPanel
     */
    public InventarisPanel() {
        initComponents();
        initPieChart();
        initBarChart();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        distribusiKategoriPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        totalPendapatanPanel2 = new javax.swing.JPanel();
        labelTotalNonObat = new javax.swing.JLabel();
        totalNonObat = new javax.swing.JLabel();
        totalPendapatanPanel1 = new javax.swing.JPanel();
        labelTotalBahanRacikan = new javax.swing.JLabel();
        totalBahanRacikan = new javax.swing.JLabel();
        totalPendapatanPanel = new javax.swing.JPanel();
        labelTotalObatOTC = new javax.swing.JLabel();
        totalObatOTC = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ringkasanInventarisPanel = new javax.swing.JPanel();
        totalProdukStokRendah = new javax.swing.JLabel();
        labelProdukKritis = new javax.swing.JLabel();
        labelProdukExpired = new javax.swing.JLabel();
        labelMendekatiProdukExpired = new javax.swing.JLabel();
        labelStokAman = new javax.swing.JLabel();
        labelProdukStokRendah1 = new javax.swing.JLabel();
        totalProdukKritis = new javax.swing.JLabel();
        totalProdukMendekatiExpired = new javax.swing.JLabel();
        totalProdukExpired = new javax.swing.JLabel();
        totalStokAman = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pergerakanStokPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(746, 455));
        setMinimumSize(new java.awt.Dimension(100, 100));
        setPreferredSize(new java.awt.Dimension(746, 455));

        distribusiKategoriPanel.setBackground(new java.awt.Color(255, 255, 255));
        distribusiKategoriPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(20, 145, 66), 1, true));
        distribusiKategoriPanel.setPreferredSize(new java.awt.Dimension(320, 245));

        javax.swing.GroupLayout distribusiKategoriPanelLayout = new javax.swing.GroupLayout(distribusiKategoriPanel);
        distribusiKategoriPanel.setLayout(distribusiKategoriPanelLayout);
        distribusiKategoriPanelLayout.setHorizontalGroup(
            distribusiKategoriPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );
        distribusiKategoriPanelLayout.setVerticalGroup(
            distribusiKategoriPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Pergerakan Stok");

        totalPendapatanPanel2.setBackground(new java.awt.Color(255, 255, 255));
        totalPendapatanPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(208, 232, 216)));
        totalPendapatanPanel2.setForeground(new java.awt.Color(42, 137, 79));

        labelTotalNonObat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTotalNonObat.setForeground(new java.awt.Color(42, 137, 79));
        labelTotalNonObat.setText("Total Non Obat");

        totalNonObat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalNonObat.setForeground(new java.awt.Color(42, 137, 79));
        totalNonObat.setText("0");

        javax.swing.GroupLayout totalPendapatanPanel2Layout = new javax.swing.GroupLayout(totalPendapatanPanel2);
        totalPendapatanPanel2.setLayout(totalPendapatanPanel2Layout);
        totalPendapatanPanel2Layout.setHorizontalGroup(
            totalPendapatanPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPendapatanPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(totalPendapatanPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotalNonObat, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(totalNonObat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        totalPendapatanPanel2Layout.setVerticalGroup(
            totalPendapatanPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPendapatanPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTotalNonObat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalNonObat, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        totalPendapatanPanel1.setBackground(new java.awt.Color(255, 255, 255));
        totalPendapatanPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(208, 232, 216)));
        totalPendapatanPanel1.setForeground(new java.awt.Color(42, 137, 79));

        labelTotalBahanRacikan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTotalBahanRacikan.setForeground(new java.awt.Color(42, 137, 79));
        labelTotalBahanRacikan.setText("Total Bahan Racikan");

        totalBahanRacikan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalBahanRacikan.setForeground(new java.awt.Color(42, 137, 79));
        totalBahanRacikan.setText("0");

        javax.swing.GroupLayout totalPendapatanPanel1Layout = new javax.swing.GroupLayout(totalPendapatanPanel1);
        totalPendapatanPanel1.setLayout(totalPendapatanPanel1Layout);
        totalPendapatanPanel1Layout.setHorizontalGroup(
            totalPendapatanPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPendapatanPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(totalPendapatanPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotalBahanRacikan, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(totalBahanRacikan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        totalPendapatanPanel1Layout.setVerticalGroup(
            totalPendapatanPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPendapatanPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTotalBahanRacikan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalBahanRacikan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        totalPendapatanPanel.setBackground(new java.awt.Color(255, 255, 255));
        totalPendapatanPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(208, 232, 216)));
        totalPendapatanPanel.setForeground(new java.awt.Color(42, 137, 79));

        labelTotalObatOTC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTotalObatOTC.setForeground(new java.awt.Color(42, 137, 79));
        labelTotalObatOTC.setText("Total Obat OTC");

        totalObatOTC.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalObatOTC.setForeground(new java.awt.Color(42, 137, 79));
        totalObatOTC.setText("0");

        javax.swing.GroupLayout totalPendapatanPanelLayout = new javax.swing.GroupLayout(totalPendapatanPanel);
        totalPendapatanPanel.setLayout(totalPendapatanPanelLayout);
        totalPendapatanPanelLayout.setHorizontalGroup(
            totalPendapatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPendapatanPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(totalPendapatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotalObatOTC, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(totalObatOTC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        totalPendapatanPanelLayout.setVerticalGroup(
            totalPendapatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPendapatanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTotalObatOTC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalObatOTC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Distribusi per Kategori");

        ringkasanInventarisPanel.setBackground(new java.awt.Color(255, 255, 255));
        ringkasanInventarisPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(20, 145, 66), 1, true));
        ringkasanInventarisPanel.setPreferredSize(new java.awt.Dimension(320, 245));

        totalProdukStokRendah.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalProdukStokRendah.setText("0 item");

        labelProdukKritis.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelProdukKritis.setText("Produk Kritis");

        labelProdukExpired.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelProdukExpired.setText("Produk Expired");

        labelMendekatiProdukExpired.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelMendekatiProdukExpired.setText("Produk Mendekati Expired");

        labelStokAman.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelStokAman.setText("Stok Aman");

        labelProdukStokRendah1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelProdukStokRendah1.setText("Produk Stok Rendah");

        totalProdukKritis.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalProdukKritis.setText("0 item");

        totalProdukMendekatiExpired.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalProdukMendekatiExpired.setText("0 item");

        totalProdukExpired.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalProdukExpired.setText("0 item");

        totalStokAman.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalStokAman.setText("0 item");

        javax.swing.GroupLayout ringkasanInventarisPanelLayout = new javax.swing.GroupLayout(ringkasanInventarisPanel);
        ringkasanInventarisPanel.setLayout(ringkasanInventarisPanelLayout);
        ringkasanInventarisPanelLayout.setHorizontalGroup(
            ringkasanInventarisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ringkasanInventarisPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(ringkasanInventarisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ringkasanInventarisPanelLayout.createSequentialGroup()
                        .addComponent(labelMendekatiProdukExpired)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                        .addComponent(totalProdukMendekatiExpired, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ringkasanInventarisPanelLayout.createSequentialGroup()
                        .addComponent(labelProdukExpired)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalProdukExpired, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ringkasanInventarisPanelLayout.createSequentialGroup()
                        .addComponent(labelProdukKritis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalProdukKritis, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ringkasanInventarisPanelLayout.createSequentialGroup()
                        .addComponent(labelStokAman)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalStokAman, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ringkasanInventarisPanelLayout.createSequentialGroup()
                        .addComponent(labelProdukStokRendah1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalProdukStokRendah)))
                .addGap(10, 10, 10))
        );
        ringkasanInventarisPanelLayout.setVerticalGroup(
            ringkasanInventarisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ringkasanInventarisPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(ringkasanInventarisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalProdukStokRendah)
                    .addComponent(labelProdukStokRendah1))
                .addGap(18, 18, 18)
                .addGroup(ringkasanInventarisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalProdukKritis)
                    .addComponent(labelProdukKritis))
                .addGap(18, 18, 18)
                .addGroup(ringkasanInventarisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMendekatiProdukExpired)
                    .addComponent(totalProdukMendekatiExpired))
                .addGap(18, 18, 18)
                .addGroup(ringkasanInventarisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProdukExpired)
                    .addComponent(totalProdukExpired))
                .addGap(18, 18, 18)
                .addGroup(ringkasanInventarisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStokAman)
                    .addComponent(totalStokAman))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ringkasan Inventaris");

        pergerakanStokPanel.setBackground(new java.awt.Color(255, 255, 255));
        pergerakanStokPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(20, 145, 66), 1, true));
        pergerakanStokPanel.setPreferredSize(new java.awt.Dimension(320, 245));

        javax.swing.GroupLayout pergerakanStokPanelLayout = new javax.swing.GroupLayout(pergerakanStokPanel);
        pergerakanStokPanel.setLayout(pergerakanStokPanelLayout);
        pergerakanStokPanelLayout.setHorizontalGroup(
            pergerakanStokPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pergerakanStokPanelLayout.setVerticalGroup(
            pergerakanStokPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 289, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pergerakanStokPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(125, 125, 125)
                            .addComponent(totalPendapatanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(totalPendapatanPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(totalPendapatanPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(distribusiKategoriPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(ringkasanInventarisPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalPendapatanPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalPendapatanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalPendapatanPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(distribusiKategoriPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ringkasanInventarisPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(pergerakanStokPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel distribusiKategoriPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel labelMendekatiProdukExpired;
    private javax.swing.JLabel labelProdukExpired;
    private javax.swing.JLabel labelProdukKritis;
    private javax.swing.JLabel labelProdukStokRendah1;
    private javax.swing.JLabel labelStokAman;
    private javax.swing.JLabel labelTotalBahanRacikan;
    private javax.swing.JLabel labelTotalNonObat;
    private javax.swing.JLabel labelTotalObatOTC;
    private javax.swing.JPanel pergerakanStokPanel;
    private javax.swing.JPanel ringkasanInventarisPanel;
    private javax.swing.JLabel totalBahanRacikan;
    private javax.swing.JLabel totalNonObat;
    private javax.swing.JLabel totalObatOTC;
    private javax.swing.JPanel totalPendapatanPanel;
    private javax.swing.JPanel totalPendapatanPanel1;
    private javax.swing.JPanel totalPendapatanPanel2;
    private javax.swing.JLabel totalProdukExpired;
    private javax.swing.JLabel totalProdukKritis;
    private javax.swing.JLabel totalProdukMendekatiExpired;
    private javax.swing.JLabel totalProdukStokRendah;
    private javax.swing.JLabel totalStokAman;
    // End of variables declaration//GEN-END:variables
}