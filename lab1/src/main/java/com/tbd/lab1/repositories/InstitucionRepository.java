package com.tbd.lab1.repositories;

import com.tbd.lab1.entities.InstitucionEntity;

import java.util.List;

public interface InstitucionRepository{
    List<InstitucionEntity> findAll();
    void create(InstitucionEntity institucion);
    //public InstitucionEntity create(InstitucionEntity institucion)
    InstitucionEntity findById(Long id);
    void update(InstitucionEntity institucion);
    //public InstitucionEntity update(InstitucionEntity institucion)
    void delete(Long id);
    //public boolean deleteInstitucionById(Long id)

}
