/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class LayananPenitipan extends LayananPetCare {
    private int jumlahHari;
    
    public LayananPenitipan(String idLayanan, String namaLayanan, double tarifPerHari, int jumlahHari) {
        super(idLayanan, namaLayanan, tarifPerHari);
        this.jumlahHari = jumlahHari;
    }
    
    @Override
    public double hitungTotalBiaya() {
        return tarifDasar * jumlahHari;
    }
}