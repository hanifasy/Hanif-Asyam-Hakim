/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class MainPetCare {
    public static void main(String[] args) {
        Hewan pet1 = new Hewan("H01", "Mimi", "Kucing", "Alergi sampo wangi");
        
        LayananPetCare layanan1 = new LayananGrooming ("LG01", "Grooming Lengkap", 50000, true);
        LayananPetCare layanan2 = new LayananPenitipan("LP01", "Penitipan VIP", 75000, 3);
        
        System.out.println("--- INFORMASI HEWAN ---");
        System.out.println("Nama Pet: " + pet1.getNama() + " (" + pet1.getJenis() + ")");
        System.out.println("Catatan: " + pet1.getCatatanKhusus());
        
        System.out.println("\n--- PERHITUNGAN BIAYA TRANSAKSI ---");
        
        layanan1.tampilkanRincian();
        System.out.println("Total Biaya Grooming: Rp " + layanan1.hitungTotalBiaya());
        
        System.out.println();
        
        layanan1.tampilkanRincian();
        System.out.println("Total Biaya Penitipan: Rp " + layanan2.hitungTotalBiaya());
    }
}