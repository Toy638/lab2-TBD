package com.tbd.lab1.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Vol_HabilidadEntity {

    private Long id_vol_habilidad;
    private Long id_voluntario;
    private Long id_habilidad;


    public Long getId_vol_habilidad() {
        return id_vol_habilidad;
    }

    public void setId_vol_habilidad(Long id_vol_habilidad) {
        this.id_vol_habilidad = id_vol_habilidad;
    }

    public Long getId_voluntario() {
        return id_voluntario;
    }

    public void setId_voluntario(Long id_voluntario) {
        this.id_voluntario = id_voluntario;
    }

    public Long getId_habilidad() {
        return id_habilidad;
    }

    public void setId_habilidad(Long id_habilidad) {
        this.id_habilidad = id_habilidad;
    }

    @Override
    public String toString() {
        return "Vol_HabilidadEntity{" +
                "id_vol_habilidad=" + id_vol_habilidad +
                ", id_voluntario=" + id_voluntario +
                ", id_habilidad=" + id_habilidad +
                '}';
    }
}




/*import lombok.AllArgsConstructor;
        import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RankingEntity {
    private Long id_ranking;
    private Long id_tarea;
    private Long id_voluntario;
    private Long puntaje;

    public Long getId_ranking() {
        return id_ranking;
    }

    public void setId_ranking(Long idRanking) {
        this.id_ranking = idRanking;
    }

    public Long getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(Long idTarea) {
        this.id_tarea = idTarea;
    }

    public Long getId_voluntario() {
        return id_voluntario;
    }

    public void setId_voluntario(Long idVoluntario) {
        this.id_voluntario = idVoluntario;
    }

    public Long getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Long puntaje) {
        this.puntaje = puntaje;
    }
}*/