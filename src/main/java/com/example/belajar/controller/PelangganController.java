package com.example.belajar.controller;

import com.example.belajar.dto.PelangganDTO;
import com.example.belajar.model.Pelanggan;
import com.example.belajar.service.ServicePelanggan;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/belajar/pelanggan/")
public class PelangganController {

    private final ServicePelanggan servicePelanggan;

    @ResponseBody
    @PostMapping("save/")
    public ResponseEntity<Object> createAccountPelanggan(@RequestBody Pelanggan pelanggan) {
        Object dataPelanggan = servicePelanggan.createAccountPelanggan(pelanggan);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/belajar/pelanggan/save/").toUriString());
        if (dataPelanggan.equals(false)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(uri,HttpStatus.CREATED);
    }


    @ResponseBody
    @GetMapping("getAll/")
    public ResponseEntity<List<PelangganDTO>> getAllPelanggan() {
        List<PelangganDTO> dataPelanggan = servicePelanggan.getAllPelanggan();
        if (dataPelanggan == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dataPelanggan,HttpStatus.OK);

    }

    @ResponseBody
    @GetMapping("get/{id}/")
    public ResponseEntity<Object> getPelanggan(@PathVariable String id) {
        Object dataPelanggan = servicePelanggan.findById(id);
        if (dataPelanggan == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dataPelanggan,HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("update/{id}/")
    public  ResponseEntity<Pelanggan> updatePelanggan(@RequestBody Pelanggan pelanggan , @PathVariable String id) {
        Pelanggan dataPelanggan = servicePelanggan.updatePelanggan(pelanggan, id);
        if (dataPelanggan == null) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(dataPelanggan, HttpStatus.CREATED);
    }

    @ResponseBody
    @DeleteMapping("{id}/")
    void deletePelanggan(@PathVariable String id) {
        servicePelanggan.deletePelangganById(id);
    }
}
