/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class LayananPetCare {
    protected String idLayanan;
    protected String namaLayanan;
    protected double tarifDasar;
    
    public LayananPetCare(String idLayanan, String namaLayanan, double tarifDasar) {
        this.idLayanan = idLayanan;
        this.namaLayanan = namaLayanan;
        this.tarifDasar = tarifDasar;
    }
    
    // Method yang akan di override (Polymorphism)
    public double hitungTotalBiaya() {
        return tarifDasar;
    }
    
    public void tampilkanRincian() {
        System.out.println("Layanan: " + namaLayanan + " | Tarif Dasar: Rp " + tarifDasar);
    } 
}