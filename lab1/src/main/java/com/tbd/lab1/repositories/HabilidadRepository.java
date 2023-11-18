package com.tbd.lab1.repositories;

import com.tbd.lab1.entities.HabilidadEntity;

import java.util.List;

public interface HabilidadRepository {

    /*public int countHabilidades();*/

    /*public int newId();*/

    List<HabilidadEntity> getAllHabilidades();
    HabilidadEntity getHabilidadById(long id);
    void createHabilidad(HabilidadEntity habilidad);
    //public HabilidadEntity create(HabilidadEntity habilidad)
    void updateHabilidad(HabilidadEntity habilidad);
    //public HabilidadEntity update(HabilidadEntity habilidad)
    void deleteHabilidadById(long id);
    //public boolean deleteHabilidadById(Long id)


}
