package com.tbd.lab1.controllers;
import com.tbd.lab1.entities.Vol_HabilidadEntity;
import com.tbd.lab1.repositories.Vol_HabilidadRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/voluntarioHabilidad")
public class Vol_HabilidadController{

    @Autowired
    private Vol_HabilidadRepositoryImpl volHabilidadRepository;

    @GetMapping
    public ResponseEntity<List<Vol_HabilidadEntity>> findAll(){
        List<Vol_HabilidadEntity> voluntarioHabilidad = volHabilidadRepository.findAll();
        return new ResponseEntity<>(voluntarioHabilidad, HttpStatus.OK);
    }
    @GetMapping("/{idVoluntarioHabilidad}")
    public ResponseEntity<Vol_HabilidadEntity> findById(@PathVariable Long idVoluntarioHabilidad){
        Vol_HabilidadEntity voluntarioHabilidad = volHabilidadRepository.findById(idVoluntarioHabilidad);
        if(voluntarioHabilidad != null){
            return new ResponseEntity<>(voluntarioHabilidad, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(voluntarioHabilidad, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Vol_HabilidadEntity voluntarioHabilidad){
        volHabilidadRepository.create(voluntarioHabilidad);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idVoluntarioHabilidad}")
    public ResponseEntity<Void> update(@PathVariable Long idVoluntarioHabilidad, @RequestBody Vol_HabilidadEntity voluntarioHabilidad){
        voluntarioHabilidad.setId_habilidad(idVoluntarioHabilidad);
        volHabilidadRepository.update(voluntarioHabilidad);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{idVoluntarioHabilidad}")
    public ResponseEntity<Void> delete(@PathVariable Long idVoluntarioHabilidad){
        volHabilidadRepository.delete(idVoluntarioHabilidad);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
