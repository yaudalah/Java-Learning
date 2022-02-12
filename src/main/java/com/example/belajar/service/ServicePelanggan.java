package com.example.belajar.service;

import com.example.belajar.dto.PelangganDTO;
import com.example.belajar.model.Pelanggan;

import java.util.List;


public interface ServicePelanggan {
    boolean createAccountPelanggan(Pelanggan pelanggan);
    List<PelangganDTO> getAllPelanggan();
    List<PelangganDTO> findById(String id);
    Pelanggan updatePelanggan(Pelanggan pelanggan, String id);
    void deletePelangganById(String id);
}
