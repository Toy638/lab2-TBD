package com.tbd.lab1.controllers;
import com.tbd.lab1.entities.VoluntarioEntity;
import com.tbd.lab1.repositories.VoluntarioRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/voluntario")
public class VoluntarioController{

    @Autowired
    private VoluntarioRepositoryImpl voluntarioRepository;

    @GetMapping
    public ResponseEntity<List<VoluntarioEntity>> findAll(){
        List<VoluntarioEntity> voluntarios = voluntarioRepository.findAll();
        return new ResponseEntity<>(voluntarios, HttpStatus.OK);
    }

    @GetMapping("/{idVoluntario}")
    public ResponseEntity<VoluntarioEntity> findById(@PathVariable Long idVoluntario){
        VoluntarioEntity voluntarios = voluntarioRepository.findById(idVoluntario);
        if(voluntarios != null){
            return new ResponseEntity<>(voluntarios, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(voluntarios, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody VoluntarioEntity voluntarios){
        voluntarioRepository.create(voluntarios);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idVoluntario}")
    public ResponseEntity<Void> update(@PathVariable Long idVoluntario, @RequestBody VoluntarioEntity voluntario){
        voluntario.setId_voluntario(idVoluntario);
        voluntarioRepository.update(voluntario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{idVoluntario}")
    public ResponseEntity<Void> delete(@PathVariable Long idVoluntario){
        voluntarioRepository.delete(idVoluntario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
