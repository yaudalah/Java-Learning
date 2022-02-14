package com.example.belajar.service;

import com.example.belajar.dto.LoginDTO;
import com.example.belajar.dto.PelangganDTO;
import com.example.belajar.model.Pelanggan;
import com.example.belajar.repository.PelangganRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class ServicePelangganImpl implements ServicePelanggan {

    private final PelangganRepository pelangganRepository;

    @Override
    public boolean createAccountPelanggan(Pelanggan pelanggan) {
        log.info("Akun pelanggan dengan nama {} telah terbuat",pelanggan.getName());
        List<Pelanggan> daftarPelanggan = pelangganRepository.findAll();
        for (Pelanggan p : daftarPelanggan) {
            if (p.getId().equals(pelanggan.getId()) || p.getEmail().equals(pelanggan.getEmail())) {
                log.info("Akun pelanggan dengan email {} telah terdaftar", pelanggan.getEmail());
                return false;
            }
        }
        pelanggan.setJoinDate(Date.valueOf(LocalDate.now()));
        pelangganRepository.save(pelanggan);
        return true;
    }

    @Override
    public Object login(LoginDTO loginDTO) {
        List<Pelanggan> lstacc = pelangganRepository.findAll();
        for (Pelanggan plg : lstacc) {
            if (plg.getEmail().equals(loginDTO.getEmail()) && plg.getPassword().equals(loginDTO.getPassword())) {
                LoginDTO plgs = new LoginDTO();
                plgs.setEmail(plg.getEmail());
                plgs.setPassword(plg.getEmail());
            }

        }
    }


    @Override
    public List<PelangganDTO> getAllPelanggan() {
        return  pelangganRepository
                .findAll()
                .stream()
                .map(this::convertToPelangganDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PelangganDTO> findById(String id) {
        return pelangganRepository
                .findById(id)
                .stream()
                .map(this::convertToPelangganDTO)
                .collect(Collectors.toList());

    }

    private PelangganDTO convertToPelangganDTO (Pelanggan pelanggan) {
        PelangganDTO pelangganDTO = new PelangganDTO();
        pelangganDTO.setId(pelanggan.getId());
        pelangganDTO.setName(pelanggan.getName());
        pelangganDTO.setEmail(pelanggan.getEmail());
        pelangganDTO.setPhoneNumber(pelanggan.getPhoneNumber());
        return pelangganDTO;
    }

    @Override
    public Pelanggan updatePelanggan(Pelanggan newPelanggan, String id) {
        log.info("Akun pelanggan dengan nama {} telah diupdate", newPelanggan.getName());
        Pelanggan pelanggan = pelangganRepository
                .findById(id).orElse(new Pelanggan());
        pelanggan.setName(newPelanggan.getName());
        pelanggan.setEmail(newPelanggan.getEmail());
        pelanggan.setPhoneNumber(newPelanggan.getPhoneNumber());
        return pelangganRepository.save(pelanggan);
    }

    @Override
    public void deletePelangganById(String id) {
        log.info("Pelanggan dengan id {} telah dihapus dari database", id);
        pelangganRepository.deleteById(id);
    }

}
