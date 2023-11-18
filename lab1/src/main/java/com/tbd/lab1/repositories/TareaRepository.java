package com.tbd.lab1.repositories;

import com.tbd.lab1.entities.TareaEntity;

import java.util.List;

public interface TareaRepository {

    List<TareaEntity> findAll();
    void create(TareaEntity habilidad);
    TareaEntity findById(Long id);
    void update(TareaEntity habilidad);
    void delete(Long id);
    List<TareaEntity> findByIdEmergencia(Long id);
    List<TareaEntity> findByRegion(int region);
}
