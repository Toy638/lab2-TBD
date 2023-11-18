package com.tbd.lab1.controllers;
import com.tbd.lab1.entities.Eme_HabilidadEntity;
import com.tbd.lab1.repositories.Eme_HabilidadRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/eme_habilidades")
public class Eme_HabilidadController{
    @Autowired
    private Eme_HabilidadRepositoryImpl emeHabilidadRepository;

    @GetMapping

    public ResponseEntity<List<Eme_HabilidadEntity>> findAll(){
        List<Eme_HabilidadEntity> habilidades = emeHabilidadRepository.findAll();
        return new ResponseEntity<>(habilidades, HttpStatus.OK);
    }

    @GetMapping("/{idHabilidad}")
    public ResponseEntity<Eme_HabilidadEntity> findById(@PathVariable Long idHabilidad){
        Eme_HabilidadEntity habilidad = emeHabilidadRepository.findById(idHabilidad);
        if(habilidad != null){
            return new ResponseEntity<>(habilidad, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(habilidad, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/idEmergencia/{idEmergencia}")
    public ResponseEntity<List<Eme_HabilidadEntity>> findByIdEmergency(@PathVariable Long idEmergencia){
        List<Eme_HabilidadEntity> habilidades = emeHabilidadRepository.findByIdEmergencia(idEmergencia);
        if(habilidades != null && !habilidades.isEmpty()){
            return new ResponseEntity<>(habilidades, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(habilidades, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Eme_HabilidadEntity habilidad){
        emeHabilidadRepository.create(habilidad);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idHabilidad}")
    public ResponseEntity<Void> update(@PathVariable Long idHabilidad, @RequestBody Eme_HabilidadEntity habilidad){
        habilidad.setId_eme_habilidad(idHabilidad);
        emeHabilidadRepository.update(habilidad);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{idHabilidad}")
    public ResponseEntity<Void> delete(@PathVariable Long idHabilidad){
        emeHabilidadRepository.delete(idHabilidad);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
