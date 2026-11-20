package com.mycompany.petcare;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransaksiForm extends JPanel {

    private JTextField txtNamaHewan, txtCatatan, txtJumlahHari, txtTarifDasar;
    private JComboBox<String> cmbJenisHewan, cmbJenisLayanan;
    private JCheckBox chkPotongKuku;
    private JButton btnSimpan, btnCetak;
    private JTable tblTransaksi;
    private DefaultTableModel tableModel;
    private TransaksiDAO transaksiDAO;

    public TransaksiForm() {
        // 1. Inisialisasi Spring DAO
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        transaksiDAO = context.getBean(TransaksiDAO.class);

        // 2. Pengaturan Ukuran & Layout JPanel
        setPreferredSize(new Dimension(800, 550));
        setLayout(new BorderLayout(10, 10));

        // 3. Panel Input Form (Bagian Atas)
        JPanel panelForm = new JPanel(new GridLayout(7, 2, 8, 8));
        panelForm.setBorder(BorderFactory.createTitledBorder("Data Transaksi Layanan PetCare"));

        txtNamaHewan = new JTextField();
        cmbJenisHewan = new JComboBox<>(new String[]{"Kucing", "Anjing", "Kelinci"});
        txtCatatan = new JTextField();
        cmbJenisLayanan = new JComboBox<>(new String[]{"Grooming", "Penitipan"});
        chkPotongKuku = new JCheckBox("Tambah Potong Kuku (+Rp 15.000)");
        txtJumlahHari = new JTextField("1");
        txtJumlahHari.setEnabled(false);
        txtTarifDasar = new JTextField();

        panelForm.add(new JLabel("Nama Hewan:"));
        panelForm.add(txtNamaHewan);
        panelForm.add(new JLabel("Jenis Hewan:"));
        panelForm.add(cmbJenisHewan);
        panelForm.add(new JLabel("Catatan Khusus:"));
        panelForm.add(txtCatatan);
        panelForm.add(new JLabel("Jenis Layanan:"));
        panelForm.add(cmbJenisLayanan);
        panelForm.add(new JLabel("Opsi Grooming:"));
        panelForm.add(chkPotongKuku);
        panelForm.add(new JLabel("Jumlah Hari (Penitipan):"));
        panelForm.add(txtJumlahHari);
        panelForm.add(new JLabel("Tarif Dasar (Rp):"));
        panelForm.add(txtTarifDasar);

        // 4. Panel Tombol (Tengah)
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnSimpan = new JButton("Simpan Transaksi");
        btnCetak = new JButton("Cetak Laporan (JasperReport)");
        panelTombol.add(btnSimpan);
        panelTombol.add(btnCetak);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        // 5. Tabel Data Transaksi (Bagian Bawah)
        String[] kolom = {"ID", "Nama Hewan", "Jenis", "Catatan", "Layanan", "Detail", "Total Biaya"};
        tableModel = new DefaultTableModel(kolom, 0);
        tblTransaksi = new JTable(tableModel);
        add(new JScrollPane(tblTransaksi), BorderLayout.CENTER);

        // 6. Event Ubah Input Sesuai Jenis Layanan
        cmbJenisLayanan.addActionListener(e -> {
            boolean isGrooming = cmbJenisLayanan.getSelectedItem().equals("Grooming");
            chkPotongKuku.setEnabled(isGrooming);
            txtJumlahHari.setEnabled(!isGrooming);
        });

        // 7. Event Simpan & Cetak
        btnSimpan.addActionListener(e -> simpanData());
        btnCetak.addActionListener(e -> cetakLaporan());

        // Muat data awal
        loadDataTabel();
    }

    private void simpanData() {
        try {
            String namaHewan = txtNamaHewan.getText().trim();
            String jenisHewan = cmbJenisHewan.getSelectedItem().toString();
            String catatan = txtCatatan.getText().trim();
            String jenisLayanan = cmbJenisLayanan.getSelectedItem().toString();
            double tarifDasar = Double.parseDouble(txtTarifDasar.getText().trim());

            if (namaHewan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama hewan wajib diisi!");
                return;
            }

            LayananPetCare layanan;
            String detailLayanan;

            if (jenisLayanan.equals("Grooming")) {
                boolean potongKuku = chkPotongKuku.isSelected();
                layanan = new LayananGrooming("LG", "Grooming", tarifDasar, potongKuku);
                detailLayanan = potongKuku ? "Plus Potong Kuku" : "Grooming Standar";
            } else {
                int hari = Integer.parseInt(txtJumlahHari.getText().trim());
                layanan = new LayananPenitipan("LP", "Penitipan", tarifDasar, hari);
                detailLayanan = hari + " Hari";
            }

            double totalBiaya = layanan.hitungTotalBiaya();

            Transaksi trx = new Transaksi(namaHewan, jenisHewan, catatan, jenisLayanan, detailLayanan, totalBiaya);
            transaksiDAO.saveTransaksi(trx);

            JOptionPane.showMessageDialog(this, "Transaksi tersimpan! Total: Rp " + totalBiaya);
            resetForm();
            loadDataTabel();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Pastikan Tarif Dasar dan Jumlah Hari diisi angka!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error simpan: " + ex.getMessage());
        }
    }

    private void loadDataTabel() {
        tableModel.setRowCount(0);
        List<Transaksi> list = transaksiDAO.getAllTransaksi();
        for (Transaksi t : list) {
            tableModel.addRow(new Object[]{
                t.getIdTransaksi(),
                t.getNamaHewan(),
                t.getJenisHewan(),
                t.getCatatanKhusus(),
                t.getJenisLayanan(),
                t.getDetailLayanan(),
                t.getTotalBiaya()
            });
        }
    }

    private void resetForm() {
        txtNamaHewan.setText("");
        txtCatatan.setText("");
        txtTarifDasar.setText("");
        txtJumlahHari.setText("1");
        chkPotongKuku.setSelected(false);
    }

    private void cetakLaporan() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petcare", "root", "");
            String reportPath = "src/main/resources/laporan_transaksi.jrxml";

            JasperReport jr = JasperCompileManager.compileReport(reportPath);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal mencetak laporan: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
