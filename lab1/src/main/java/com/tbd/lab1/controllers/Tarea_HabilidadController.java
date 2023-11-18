package com.tbd.lab1.controllers;
import com.tbd.lab1.entities.Tarea_HabilidadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.tbd.lab1.repositories.Tarea_HabilidadRepositoryImpl;



@RestController
@RequestMapping("api/tareaHabilidad")
public class Tarea_HabilidadController{

    @Autowired
    private Tarea_HabilidadRepositoryImpl tareaHabilidadRepository;

    @GetMapping
    public ResponseEntity<List<Tarea_HabilidadEntity>> findAll(){
        List<Tarea_HabilidadEntity> TareaHabilidad = tareaHabilidadRepository.findAll();
        return new ResponseEntity<>(TareaHabilidad, HttpStatus.OK);
    }

    @GetMapping("/{idTareaHabilidad}")
    public ResponseEntity<Tarea_HabilidadEntity> findById(@PathVariable Long idTareaHabilidad){
        Tarea_HabilidadEntity tareaHabilidad = tareaHabilidadRepository.findById(idTareaHabilidad);
        if(tareaHabilidad != null){
            return new ResponseEntity<>(tareaHabilidad, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(tareaHabilidad, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Tarea_HabilidadEntity tareaHabilidad){
        tareaHabilidadRepository.create(tareaHabilidad);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idTareaHabilidad}")
    public ResponseEntity<Void> update(@PathVariable Long idTareaHabilidad, @RequestBody Tarea_HabilidadEntity tareaHabilidad){
        tareaHabilidad.setId_habilidad(idTareaHabilidad);
        tareaHabilidadRepository.update(tareaHabilidad);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{idTareaHabilidad}")
    public ResponseEntity<Void> delete(@PathVariable Long idTareaHabilidad){
        tareaHabilidadRepository.delete(idTareaHabilidad);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
