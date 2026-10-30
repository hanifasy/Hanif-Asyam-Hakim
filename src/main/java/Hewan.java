/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class Hewan {
    private String idHewan;
    private String nama;
    private String jenis;
    private String catatanKhusus;
    
    public Hewan(String idHewan, String nama, String jenis, String catatanKhusus) {
        this.idHewan = idHewan;
        this.nama = nama;
        this.jenis = jenis;
        this.catatanKhusus = catatanKhusus;
    }
    
    // Getter dan Setter
    public String getIdHewan() { return idHewan; }
    public void setIdHewan(String idHewan) {this.idHewan = idHewan;}
    
    public String getNama() { return nama; }
    public void setNama(String nama) {this.nama = nama;}
    
    public String getJenis() { return jenis; }
    public void setJenis(String jenis) {this.jenis = jenis;}
    
    public String getCatatanKhusus() { return catatanKhusus; }
    public void setCatatanKhusus(String catatanKhusus) {this.catatanKhusus = catatanKhusus;}
}