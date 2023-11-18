package com.tbd.lab1.repositories;

import com.tbd.lab1.entities.Tarea_HabilidadEntity;

import java.util.List;

public interface Tarea_HabilidadRepository {

    public List<Tarea_HabilidadEntity> findAll();
    public void create(Tarea_HabilidadEntity tarea_habilidad);
    public Tarea_HabilidadEntity findById(Long id);
    public List<Tarea_HabilidadEntity> findByIdTarea(Long idTarea);
    public List<Tarea_HabilidadEntity> findByIdHabilidad(Long idHabilidad);
    public void update(Tarea_HabilidadEntity tarea_habilidad);
    public void delete(Long id);
}
