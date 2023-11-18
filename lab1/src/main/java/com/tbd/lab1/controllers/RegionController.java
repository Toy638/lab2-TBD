package com.tbd.lab1.controllers;


import com.tbd.lab1.entities.RegionEntity;
import com.tbd.lab1.repositories.RegionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class RegionController {

    @Autowired
    private RegionRepositoryImpl regionRepository;

    @GetMapping("/api/region")
    public ResponseEntity<List<RegionEntity>> findAll(){
        List<RegionEntity> regiones = regionRepository.findAll();
        return new ResponseEntity<>(regiones, HttpStatus.OK);
    }

    @GetMapping("/api/region/{idRegion}")
    public ResponseEntity<RegionEntity> findById(@PathVariable Long idRegion){
        RegionEntity region = regionRepository.findById(idRegion);
        if(region != null){
            return new ResponseEntity<>(region, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(region, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/region/crear")
    public ResponseEntity<Void> create(@RequestBody RegionEntity region){
        regionRepository.create(region);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/region/{idRegion}")
    public ResponseEntity<Void> update(@PathVariable Long idRegion, @RequestBody RegionEntity region){
        region.setId_region(idRegion);
        regionRepository.update(region);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/region/{idRegion}")
    public ResponseEntity<Void> delete(@PathVariable Long idRegion){
        regionRepository.deleteById(idRegion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }







}
