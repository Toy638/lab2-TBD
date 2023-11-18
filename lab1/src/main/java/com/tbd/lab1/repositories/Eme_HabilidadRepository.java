package com.tbd.lab1.repositories;

import com.tbd.lab1.entities.Eme_HabilidadEntity;

import java.util.List;

public interface Eme_HabilidadRepository {
    List<Eme_HabilidadEntity> findAll();
    void create(Eme_HabilidadEntity eme_habilidad);
    //public Eme_HabilidadEntity create(Eme_HabilidadEntity eme_habilidad)
    Eme_HabilidadEntity findById(Long id);
    List<Eme_HabilidadEntity> findByIdEmergencia(Long idEmergencia);

    List<Eme_HabilidadEntity> findByIdHabilidad(Long idHabilidad);
    void update(Eme_HabilidadEntity eme_habilidad);
    //public Eme_HabilidadEntity update(Eme_HabilidadEntity eme_habilidad)
    void delete(Long id);
    //public boolean deleteEme_HabilidadById(Long id)
}
