package com.apotek.ui;

import com.apotek.observer.DashboardObserver;
import java.awt.BorderLayout;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import javax.swing.SwingUtilities;


/**
 *
 * @author himorii
 */
public class PenjualanPanel extends javax.swing.JPanel implements DashboardObserver {
    private XYChart.Series<String, Number> pendapatanSeries;
    private XYChart.Data<String, Number>[] pendapatanData;
    private int[] transaksiBulanan;
    
    private String formatCurrency(double value) {

        String[] units = {
            "",
            "Rb",
            "Jt",
            "M",
            "T",
            "P",
            "E"
        };

        int unitIndex = 0;

        while (value >= 1000 && unitIndex < units.length - 1) {
            value /= 1000.0;
            unitIndex++;
        }
        
        if(unitIndex == 0) {
            return String.format("Rp %,.0f", value);
        }

        return String.format("%.1f %s", value, units[unitIndex]);
    }
    
    private void initLineChart() {
        JFXPanel fxPanel = new JFXPanel();

        grafikPenjualanBulananPanel.setLayout(
                new BorderLayout());

        grafikPenjualanBulananPanel.add(
                fxPanel,
                BorderLayout.CENTER);

        Platform.runLater(() -> {

            CategoryAxis xAxis = new CategoryAxis();

            NumberAxis yAxis = new NumberAxis();

            yAxis.setTickLabelFormatter(
                    new StringConverter<Number>() {

                @Override
                public String toString(Number value) {

                    return formatCurrency(
                            value.doubleValue());
                }

                @Override
                public Number fromString(String string) {
                    return 0;
                }
            });

            xAxis.setLabel("Bulan");
            yAxis.setLabel("Pendapatan");

            LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

            lineChart.setAnimated(true);
            lineChart.setLegendVisible(false);
            lineChart.setCreateSymbols(true);
            lineChart.setHorizontalGridLinesVisible(true);
            lineChart.setVerticalGridLinesVisible(false);
            lineChart.setAlternativeColumnFillVisible(false);
            lineChart.setAlternativeRowFillVisible(false);
            
            
            pendapatanSeries = new XYChart.Series<>();

            pendapatanSeries.setName("Pendapatan");

            int tahun = LocalDate.now().getYear();

            int[] omzet = MainApp.transaksiService
                    .hitungOmzetPerBulan(tahun);

            transaksiBulanan = MainApp.transaksiService.hitungTransaksiPerBulan(tahun);

            String[] bulan = {
                "Jan","Feb","Mar","Apr","Mei","Jun",
                "Jul","Agu","Sep","Okt","Nov","Des"
            };

            pendapatanData = new XYChart.Data[12];

            for(int i= 0 ; i < 12; i++){

                pendapatanData[i] =
                        new XYChart.Data<>(
                                bulan[i],
                                omzet[i]
                        );

                pendapatanSeries.getData().add(pendapatanData[i]);
            }

            lineChart.getData().add(pendapatanSeries);
            Platform.runLater(() -> {
                for (XYChart.Data<String, Number> data : pendapatanSeries.getData()) {

                Node node = data.getNode();

                if(node != null){

                    int index = pendapatanSeries.getData().indexOf(data);

                    Tooltip tooltip =
                            new Tooltip(
                                    "Bulan : "
                                    + data.getXValue()
                                    + "\nPendapatan : "
                                    + formatCurrency(
                                            data.getYValue().doubleValue())
                                    + "\nTotal Transaksi : "
                                    + transaksiBulanan[index]
                            );

                    tooltip.setStyle(
                            "-fx-background-color:white;"
                            + "-fx-text-fill:#111827;"
                            + "-fx-border-color:#149142;"
                            + "-fx-font-size:12px;"
                    );

                    Tooltip.install(node, tooltip);
                }
            }
            });
            

            StackPane root =
                    new StackPane(lineChart);

            root.setStyle(
                    "-fx-background-color:white;");

            Scene scene = new Scene(root);

            fxPanel.setScene(scene);

            Platform.runLater(() -> {

                Node plotBackground =
                        lineChart.lookup(
                                ".chart-plot-background");

                if (plotBackground != null) {

                    plotBackground.setStyle(
                            "-fx-background-color:white;");
                }

                /*
                 * STYLE SERIES
                 */
                Node seriesLine =
                        lineChart.lookup(
                                ".default-color0.chart-series-line");

                if (seriesLine != null) {

                    seriesLine.setStyle(
                            "-fx-stroke:#149142;"
                            + "-fx-stroke-width:3px;");
                }

                /*
                 * STYLE DATA POINT
                 */
                for (XYChart.Data<String, Number> data
                        : pendapatanSeries.getData()) {

                    Node node = data.getNode();

                    if (node != null) {

                        node.setStyle(
                                "-fx-background-color:"
                                + "#149142, white;"
                                + "-fx-background-radius:8px;"
                                + "-fx-padding:6px;"
                        );

                        data.nodeProperty().addListener((obs, oldNode, newNode) -> {
                            if (newNode != null) {

                                Tooltip tooltip = new Tooltip(
                                        "Bulan : " + data.getXValue()
                                        + "\nPendapatan : "
                                        + formatCurrency(
                                                data.getYValue().doubleValue()
                                        )
                                );

                                Tooltip.install(newNode, tooltip);
                            }
                        });
                    }
                }

                /*
                 * GRID
                 */
                Set<Node> gridLines =
                        lineChart.lookupAll(
                                ".chart-horizontal-grid-lines");

                for (Node node : gridLines) {

                    node.setStyle(
                            "-fx-stroke:#E5E7EB;");
                }

                /*
                 * AXIS
                 */
                xAxis.setStyle(
                        "-fx-tick-label-fill:#6B7280;"
                        + "-fx-font-size:11px;"
                );

                yAxis.setStyle(
                        "-fx-tick-label-fill:#6B7280;"
                        + "-fx-font-size:11px;"
                );

                /*
                 * TITLE
                 */
                Node title =
                        lineChart.lookup(
                                ".chart-title");

                if (title != null) {

                    title.setStyle(
                            "-fx-text-fill:#111827;"
                            + "-fx-font-size:16px;"
                            + "-fx-font-weight:bold;"
                    );
                }
            });
        });

        grafikPenjualanBulananPanel.revalidate();
        grafikPenjualanBulananPanel.repaint();
    }
    
    private void refreshLineChart(){

        Platform.runLater(() -> {

            int tahun = LocalDate.now().getYear();

            int[] omzet = MainApp.transaksiService.hitungOmzetPerBulan(tahun);

            transaksiBulanan = MainApp.transaksiService.hitungTransaksiPerBulan(tahun);

            for(int i = 0; i < 12; i++){

                pendapatanData[i].setYValue(omzet[i]);
            }
        });
    }
    
    @Override
    public void updateDashboard(){
        SwingUtilities.invokeLater(() -> {
            refreshLineChart();
        });
    }
    
    /**
     * Creates new form RingkasanPanel
     */
    public PenjualanPanel() {
        initComponents();
        initLineChart();
        
        MainApp.dashboardManager.addObserver(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grafikPenjualanBulananPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableStokRendah = new javax.swing.JTable();
        rataRataPenjualanPanel = new javax.swing.JPanel();
        labelTotalProduk2 = new javax.swing.JLabel();
        rataRataPenjualan = new javax.swing.JLabel();
        labelKenaikanDanKeturunanRataRataPenjualan = new javax.swing.JLabel();
        totalTransaksiPanel = new javax.swing.JPanel();
        labelTotalTransaksi = new javax.swing.JLabel();
        totalTransaksi = new javax.swing.JLabel();
        labelKenaikanDanKeturunanTotalTransaksi = new javax.swing.JLabel();
        totalPendapatanPanel = new javax.swing.JPanel();
        labelTotalPendapatan = new javax.swing.JLabel();
        totalPendapatan = new javax.swing.JLabel();
        labelKenaikanDanKeturunanTotalPendapatan = new javax.swing.JLabel();
        periodeTotalPendapatan = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelDataRiwayatTransaksi = new javax.swing.JLabel();
        btnPrevious = new javax.swing.JButton();
        labelPageDataRiwayatTransaksi = new javax.swing.JLabel();
        btnNext1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(746, 455));
        setMinimumSize(new java.awt.Dimension(100, 100));
        setPreferredSize(new java.awt.Dimension(746, 455));

        grafikPenjualanBulananPanel.setBackground(new java.awt.Color(255, 255, 255));
        grafikPenjualanBulananPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(20, 145, 66), 1, true));
        grafikPenjualanBulananPanel.setPreferredSize(new java.awt.Dimension(320, 245));

        javax.swing.GroupLayout grafikPenjualanBulananPanelLayout = new javax.swing.GroupLayout(grafikPenjualanBulananPanel);
        grafikPenjualanBulananPanel.setLayout(grafikPenjualanBulananPanelLayout);
        grafikPenjualanBulananPanelLayout.setHorizontalGroup(
            grafikPenjualanBulananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        grafikPenjualanBulananPanelLayout.setVerticalGroup(
            grafikPenjualanBulananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Riwayat Transaksi");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("5 Jan s/d 15 Jan 2026");

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(20, 145, 66), 1, true));

        tableStokRendah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nama Produk", "Stok", "Status"
            }
        ));
        jScrollPane2.setViewportView(tableStokRendah);

        rataRataPenjualanPanel.setBackground(new java.awt.Color(255, 255, 255));
        rataRataPenjualanPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(208, 232, 216)));
        rataRataPenjualanPanel.setForeground(new java.awt.Color(42, 137, 79));

        labelTotalProduk2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTotalProduk2.setForeground(new java.awt.Color(42, 137, 79));
        labelTotalProduk2.setText("Rata-rata / Transaksi");

        rataRataPenjualan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rataRataPenjualan.setForeground(new java.awt.Color(42, 137, 79));
        rataRataPenjualan.setText("0");

        labelKenaikanDanKeturunanRataRataPenjualan.setForeground(new java.awt.Color(42, 137, 79));
        labelKenaikanDanKeturunanRataRataPenjualan.setText("+0%");

        javax.swing.GroupLayout rataRataPenjualanPanelLayout = new javax.swing.GroupLayout(rataRataPenjualanPanel);
        rataRataPenjualanPanel.setLayout(rataRataPenjualanPanelLayout);
        rataRataPenjualanPanelLayout.setHorizontalGroup(
            rataRataPenjualanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rataRataPenjualanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rataRataPenjualanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotalProduk2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addComponent(rataRataPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelKenaikanDanKeturunanRataRataPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        rataRataPenjualanPanelLayout.setVerticalGroup(
            rataRataPenjualanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rataRataPenjualanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTotalProduk2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rataRataPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelKenaikanDanKeturunanRataRataPenjualan)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        totalTransaksiPanel.setBackground(new java.awt.Color(255, 255, 255));
        totalTransaksiPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(208, 232, 216)));
        totalTransaksiPanel.setForeground(new java.awt.Color(42, 137, 79));

        labelTotalTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTotalTransaksi.setForeground(new java.awt.Color(42, 137, 79));
        labelTotalTransaksi.setText("Total Transaksi");

        totalTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalTransaksi.setForeground(new java.awt.Color(42, 137, 79));
        totalTransaksi.setText("0");

        labelKenaikanDanKeturunanTotalTransaksi.setForeground(new java.awt.Color(250, 0, 0));
        labelKenaikanDanKeturunanTotalTransaksi.setText("-0%");

        javax.swing.GroupLayout totalTransaksiPanelLayout = new javax.swing.GroupLayout(totalTransaksiPanel);
        totalTransaksiPanel.setLayout(totalTransaksiPanelLayout);
        totalTransaksiPanelLayout.setHorizontalGroup(
            totalTransaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalTransaksiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totalTransaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotalTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(totalTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelKenaikanDanKeturunanTotalTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        totalTransaksiPanelLayout.setVerticalGroup(
            totalTransaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalTransaksiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTotalTransaksi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelKenaikanDanKeturunanTotalTransaksi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        totalPendapatanPanel.setBackground(new java.awt.Color(255, 255, 255));
        totalPendapatanPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(208, 232, 216)));
        totalPendapatanPanel.setForeground(new java.awt.Color(42, 137, 79));

        labelTotalPendapatan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTotalPendapatan.setForeground(new java.awt.Color(42, 137, 79));
        labelTotalPendapatan.setText("Total Pendapatan");

        totalPendapatan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalPendapatan.setForeground(new java.awt.Color(42, 137, 79));
        totalPendapatan.setText("0");

        labelKenaikanDanKeturunanTotalPendapatan.setForeground(new java.awt.Color(42, 137, 79));
        labelKenaikanDanKeturunanTotalPendapatan.setText("+0%");

        periodeTotalPendapatan.setForeground(new java.awt.Color(42, 137, 79));
        periodeTotalPendapatan.setText("5 Jan s/d 15 Jan 2026");

        javax.swing.GroupLayout totalPendapatanPanelLayout = new javax.swing.GroupLayout(totalPendapatanPanel);
        totalPendapatanPanel.setLayout(totalPendapatanPanelLayout);
        totalPendapatanPanelLayout.setHorizontalGroup(
            totalPendapatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPendapatanPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(totalPendapatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotalPendapatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalPendapatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(totalPendapatanPanelLayout.createSequentialGroup()
                        .addComponent(labelKenaikanDanKeturunanTotalPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(periodeTotalPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))))
        );
        totalPendapatanPanelLayout.setVerticalGroup(
            totalPendapatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPendapatanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTotalPendapatan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(totalPendapatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelKenaikanDanKeturunanTotalPendapatan)
                    .addComponent(periodeTotalPendapatan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Grafik Penjualan Bulanan");

        labelDataRiwayatTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelDataRiwayatTransaksi.setForeground(new java.awt.Color(42, 137, 79));
        labelDataRiwayatTransaksi.setText("Menampilkan 0 dari 0 transaksi");

        btnPrevious.setBackground(new java.awt.Color(20, 145, 66));
        btnPrevious.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPrevious.setForeground(new java.awt.Color(255, 255, 255));
        btnPrevious.setText("Sebelumnya");

        labelPageDataRiwayatTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelPageDataRiwayatTransaksi.setForeground(new java.awt.Color(42, 137, 79));
        labelPageDataRiwayatTransaksi.setText("Halaman 0 dari 0");

        btnNext1.setBackground(new java.awt.Color(20, 145, 66));
        btnNext1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNext1.setForeground(new java.awt.Color(255, 255, 255));
        btnNext1.setText("Selanjutnya");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(totalPendapatanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(totalTransaksiPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rataRataPenjualanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 129, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelDataRiwayatTransaksi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPrevious)
                                .addGap(18, 18, 18)
                                .addComponent(labelPageDataRiwayatTransaksi)
                                .addGap(18, 18, 18)
                                .addComponent(btnNext1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grafikPenjualanBulananPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE))
                        .addGap(41, 41, 41))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(totalTransaksiPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalPendapatanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rataRataPenjualanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(grafikPenjualanBulananPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPrevious, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelPageDataRiwayatTransaksi)
                        .addComponent(btnNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelDataRiwayatTransaksi))
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JPanel grafikPenjualanBulananPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDataRiwayatTransaksi;
    private javax.swing.JLabel labelKenaikanDanKeturunanRataRataPenjualan;
    private javax.swing.JLabel labelKenaikanDanKeturunanTotalPendapatan;
    private javax.swing.JLabel labelKenaikanDanKeturunanTotalTransaksi;
    private javax.swing.JLabel labelPageDataRiwayatTransaksi;
    private javax.swing.JLabel labelTotalPendapatan;
    private javax.swing.JLabel labelTotalProduk2;
    private javax.swing.JLabel labelTotalTransaksi;
    private javax.swing.JLabel periodeTotalPendapatan;
    private javax.swing.JLabel rataRataPenjualan;
    private javax.swing.JPanel rataRataPenjualanPanel;
    private javax.swing.JTable tableStokRendah;
    private javax.swing.JLabel totalPendapatan;
    private javax.swing.JPanel totalPendapatanPanel;
    private javax.swing.JLabel totalTransaksi;
    private javax.swing.JPanel totalTransaksiPanel;
    // End of variables declaration//GEN-END:variables
}