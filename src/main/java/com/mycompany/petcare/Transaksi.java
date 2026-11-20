package com.mycompany.petcare;

import jakarta.persistence.*;

@Entity
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaksi")
    private int idTransaksi;

    @Column(name = "nama_hewan")
    private String namaHewan;

    @Column(name = "jenis_hewan")
    private String jenisHewan;

    @Column(name = "catatan_khusus")
    private String catatanKhusus;

    @Column(name = "jenis_layanan")
    private String jenisLayanan;

    @Column(name = "detail_layanan")
    private String detailLayanan;

    @Column(name = "total_biaya")
    private double totalBiaya;

    public Transaksi() {}

    public Transaksi(String namaHewan, String jenisHewan, String catatanKhusus, String jenisLayanan, String detailLayanan, double totalBiaya) {
        this.namaHewan = namaHewan;
        this.jenisHewan = jenisHewan;
        this.catatanKhusus = catatanKhusus;
        this.jenisLayanan = jenisLayanan;
        this.detailLayanan = detailLayanan;
        this.totalBiaya = totalBiaya;
    }

    // Getter
    public int getIdTransaksi() { return idTransaksi; }
    public String getNamaHewan() { return namaHewan; }
    public String getJenisHewan() { return jenisHewan; }
    public String getCatatanKhusus() { return catatanKhusus; }
    public String getJenisLayanan() { return jenisLayanan; }
    public String getDetailLayanan() { return detailLayanan; }
    public double getTotalBiaya() { return totalBiaya; }
}