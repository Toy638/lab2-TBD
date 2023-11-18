package com.tbd.lab1.controllers;
import com.tbd.lab1.entities.HabilidadEntity;
import com.tbd.lab1.repositories.HabilidadRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/habilidades")
public class HabilidadController{

    @Autowired
    private HabilidadRepositoryImpl habilidadRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<HabilidadEntity>> findAll(){
        List<HabilidadEntity> habilidad = habilidadRepository.getAllHabilidades();
        return new ResponseEntity<>(habilidad, HttpStatus.OK);
    }

    @GetMapping("/{idHabilidad}")
    public ResponseEntity<HabilidadEntity> findById(@PathVariable Long idHabilidad){
        HabilidadEntity habilidad = habilidadRepository.getHabilidadById(idHabilidad);
        if(habilidad != null){
            return new ResponseEntity<>(habilidad, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(habilidad, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody HabilidadEntity habilidad){
        habilidadRepository.createHabilidad(habilidad);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idHabilidad}")
    public ResponseEntity<Void> update(@PathVariable Long idHabilidad, @RequestBody HabilidadEntity habilidad){
        habilidad.setId_habilidad(idHabilidad);
        habilidadRepository.updateHabilidad(habilidad);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{idHabilidad}")
    public ResponseEntity<Void> delete(@PathVariable Long idHabilidad){
        habilidadRepository.deleteHabilidadById(idHabilidad);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
