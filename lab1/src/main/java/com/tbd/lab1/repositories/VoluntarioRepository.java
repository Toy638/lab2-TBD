package com.tbd.lab1.repositories;

import com.tbd.lab1.entities.VoluntarioEntity;

import java.util.List;

public interface VoluntarioRepository {

    public List<VoluntarioEntity> findAll();
    public void create(VoluntarioEntity habilidad);
    public VoluntarioEntity findById(Long id);
    public void update(VoluntarioEntity habilidad);
    public void delete(Long id);
}
