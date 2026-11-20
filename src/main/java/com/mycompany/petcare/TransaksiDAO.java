package com.mycompany.petcare;

import java.util.List;

public interface TransaksiDAO {
    void saveTransaksi(Transaksi transaksi);
    List<Transaksi> getAllTransaksi();
}