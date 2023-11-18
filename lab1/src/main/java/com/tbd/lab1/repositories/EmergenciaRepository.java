package com.tbd.lab1.repositories;

import com.tbd.lab1.entities.EmergenciaEntity;

import java.util.List;


public interface EmergenciaRepository {
    public List<EmergenciaEntity> findAll();
    public EmergenciaEntity create(EmergenciaEntity emergencia);
    public EmergenciaEntity findById(Long id);
    public EmergenciaEntity findByTarea(Long idTarea);
    public EmergenciaEntity update(EmergenciaEntity emergencia);
    public void delete(Long id);
    public EmergenciaEntity getFinalId();
    void cambiarEstado(Long id);

    int countTareasByEmergencia(Long id);
}
