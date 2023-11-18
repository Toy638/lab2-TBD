package com.tbd.lab1.entities;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TareaEntity {
    private Long id_tarea;
    private String asunto_tarea;

    private Boolean estado_tarea;

    private Long id_emergencia;

    public Long getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(Long id_tarea) {
        this.id_tarea = id_tarea;
    }

    public String getAsunto_tarea() {
        return asunto_tarea;
    }

    public void setAsunto_tarea(String asunto_tarea) {
        this.asunto_tarea = asunto_tarea;
    }

    public Boolean getEstado_tarea() {
        return estado_tarea;
    }

    public void setEstado_tarea(Boolean estado_tarea) {
        this.estado_tarea = estado_tarea;
    }

    public Long getId_emergencia() {
        return id_emergencia;
    }

    public void setId_emergencia(Long id_emergencia) {
        this.id_emergencia = id_emergencia;
    }

    @Override
    public String toString() {
        return "TareaEntity{" +
                "id_tarea=" + id_tarea +
                ", asunto_tarea='" + asunto_tarea + '\'' +
                ", estado_tarea=" + estado_tarea +
                ", id_emergencia=" + id_emergencia +
                '}';
    }
}
