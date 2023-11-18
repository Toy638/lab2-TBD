package com.tbd.lab1.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Tarea_HabilidadEntity {
    private Long id_tarea_habilidad;
    private Long id_tarea;
    private Long id_habilidad;

    public Long getId_tarea_habilidad() {
        return id_tarea_habilidad;
    }

    public void setId_tarea_habilidad(Long idTareaHabilidad) {
        this.id_tarea_habilidad = idTareaHabilidad;
    }

    public Long getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(Long idTarea) {
        this.id_tarea = idTarea;
    }

    public Long getId_habilidad() {
        return id_habilidad;
    }

    public void setId_habilidad(Long idHabilidad) {
        this.id_habilidad = idHabilidad;
    }

    @Override
    public String toString() {
        return "Tarea_HabilidadEntity{" +
                "id_tarea_habilidad=" + id_tarea_habilidad +
                ", id_tarea=" + id_tarea +
                ", id_habilidad=" + id_habilidad +
                '}';
    }
}