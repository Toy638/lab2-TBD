package com.tbd.lab1.repositories;

import com.tbd.lab1.entities.Vol_HabilidadEntity;

import java.util.List;

public interface Vol_HabilidadRepository {

    List<Vol_HabilidadEntity> findAll();
    void create(Vol_HabilidadEntity habilidad);
    Vol_HabilidadEntity findById(Long id);
    void update(Vol_HabilidadEntity habilidad);
    void delete(Long id);
    List<Vol_HabilidadEntity> findByIdHabilidad(Long idHabilidad);
    List<Vol_HabilidadEntity> findByIdVoluntario(Long idVoluntario);
}
