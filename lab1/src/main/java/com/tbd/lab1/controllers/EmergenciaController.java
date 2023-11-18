package com.tbd.lab1.controllers;
import com.tbd.lab1.entities.EmergenciaEntity;
import com.tbd.lab1.repositories.EmergenciaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController()
@CrossOrigin("*")
public class EmergenciaController{

    @Autowired
    private EmergenciaRepositoryImpl emergenciaRepository;


    @GetMapping("/api/emergencia")
    public ResponseEntity<List<EmergenciaEntity>> findAll(){
        List<EmergenciaEntity> emergencias = emergenciaRepository.findAll();
        return new ResponseEntity<>(emergencias, HttpStatus.OK);
    }

    @GetMapping("/api/emergencia/{idEmergencia}")
    public ResponseEntity<EmergenciaEntity> findById(@PathVariable Long idEmergencia){
        EmergenciaEntity emergencia = emergenciaRepository.findById(idEmergencia);
        if(emergencia != null){
            return new ResponseEntity<>(emergencia, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(emergencia, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/emergencia/crear")
    public ResponseEntity<Void> create(@RequestBody EmergenciaEntity emergencia){
        emergenciaRepository.create(emergencia);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/emergencia/{idEmergencia}")
    public ResponseEntity<Void> update(@PathVariable Long idEmergencia, @RequestBody EmergenciaEntity emergencia){
        emergencia.setId_emergencia(idEmergencia);
        emergenciaRepository.update(emergencia);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/emergencia/{idEmergencia}")
    public ResponseEntity<Void> delete(@PathVariable Long idEmergencia){
        emergenciaRepository.delete(idEmergencia);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/emergencia/last")
    public ResponseEntity<EmergenciaEntity> getFinalId(){
        EmergenciaEntity emergencia = emergenciaRepository.getFinalId();
        return new ResponseEntity<>(emergencia, HttpStatus.OK);
    }

}
