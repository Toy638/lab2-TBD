package com.tbd.lab1.entities;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
public class InstitucionEntity {
    private Long id_institucion;
    private String nombre;
    private Date fecha;
    private String direccion;
    private String telefono;
    private Long id_usuario;

    public Long getIdInstitucion() {
        return id_institucion;
    }

    public void setIdInstitucion(Long idInstitucion) {
        this.id_institucion = idInstitucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "InstitucionEntity{" +
                "id_institucion=" + id_institucion +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", id_usuario=" + id_usuario +
                '}';
    }
}