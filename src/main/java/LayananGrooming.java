/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class LayananGrooming extends LayananPetCare {
    private boolean isPotongKuku;
    
    public LayananGrooming(String idLayanan, String namaLayanan, double tarifDasar, boolean isPotongKuku) {
        super(idLayanan, namaLayanan, tarifDasar);
        this.isPotongKuku = isPotongKuku;
    }
    
    @Override
    public double hitungTotalBiaya() {
        double biayaTambahan = isPotongKuku ? 15000 : 0;
        return tarifDasar + biayaTambahan;
    }
}

