package com.tbd.lab1.entities;

import com.tbd.lab1.serializers.PGgeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.postgis.jdbc.PGgeometry;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class EmergenciaEntity {
    private Long id_emergencia;
    private String asunto;
    private String descripcion;
    private String direccion;
    private Date fecha;
    public Boolean activa;
    private Integer id_institucion;
    private double latitud;
    private double longitud;

    @JsonSerialize (using = PGgeometrySerializer.class)
    private PGgeometry geom;

    public Long getId_emergencia() {
        return id_emergencia;
    }

    public void setId_emergencia(Long id_emergencia) {
        this.id_emergencia = id_emergencia;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public Integer getId_institucion() {
        return id_institucion;
    }

    public void setId_institucion(Integer id_institucion) {
        this.id_institucion = id_institucion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public PGgeometry getGeom() {
        return geom;
    }

    public void setGeom(PGgeometry geom) {
        this.geom = geom;
    }




    @Override
    public String toString() {
        return "EmergenciaEntity{" +
                "id_emergencia=" + id_emergencia +
                ", asunto='" + asunto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fecha=" + fecha +
                ", activa=" + activa +
                ", id_institucion=" + id_institucion +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", geom=" + geom +
                '}';
    }
}

