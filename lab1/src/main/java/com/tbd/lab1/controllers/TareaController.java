package com.tbd.lab1.controllers;
import com.tbd.lab1.entities.TareaEntity;
import com.tbd.lab1.repositories.TareaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tarea")
public class TareaController{
    @Autowired
    private TareaRepositoryImpl tareaRepository;


    @GetMapping
    public ResponseEntity<List<TareaEntity>> findAll(){
        List<TareaEntity> tarea = tareaRepository.findAll();
        return new ResponseEntity<>(tarea, HttpStatus.OK);
    }

    @GetMapping("/{idTarea}")
    public ResponseEntity<TareaEntity> findById(@PathVariable Long idTarea){
        TareaEntity tarea = tareaRepository.findById(idTarea);
        if(tarea != null){
            return new ResponseEntity<>(tarea, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(tarea, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TareaEntity tarea){
        tareaRepository.create(tarea);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{idTarea}")
    public ResponseEntity<Void> update(@PathVariable Long idTarea, @RequestBody TareaEntity tarea){
        tarea.setId_tarea(idTarea);
        tareaRepository.update(tarea);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{idTarea}")
    public ResponseEntity<Void> delete(@PathVariable Long idTarea){
        tareaRepository.delete(idTarea);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
