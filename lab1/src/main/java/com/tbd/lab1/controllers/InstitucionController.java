package com.tbd.lab1.controllers;
import com.tbd.lab1.entities.InstitucionEntity;
import com.tbd.lab1.repositories.InstitucionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/institucion")
public class InstitucionController{

    @Autowired
    private InstitucionRepositoryImpl institucionRepository;

    @GetMapping
    public ResponseEntity<List<InstitucionEntity>> findAll(){
        List<InstitucionEntity> institucion = institucionRepository.findAll();
        return new ResponseEntity<>(institucion, HttpStatus.OK);
    }

    @GetMapping("/{idInstitucion}")
    public ResponseEntity<InstitucionEntity> findById(@PathVariable Long idInstitucion){
        InstitucionEntity institucion = institucionRepository.findById(idInstitucion);
        if(institucion != null){
            return new ResponseEntity<>(institucion, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(institucion, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody InstitucionEntity institucion){
        institucionRepository.create(institucion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idInstitucion}")
    public ResponseEntity<Void> update(@PathVariable Long idInstitucion, @RequestBody InstitucionEntity institucion){
        institucion.setIdInstitucion(idInstitucion);
        institucionRepository.update(institucion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{idInstitucion}")
    public ResponseEntity<Void> delete(@PathVariable Long idInstitucion){
        institucionRepository.delete(idInstitucion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
