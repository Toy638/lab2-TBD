package com.tbd.lab1.entities;


public class UsuarioEntity {

    private Integer id;

    private String email;

    private String password;
    private String rol;

    private Integer id_institucion;


    public UsuarioEntity() {
    }

    public UsuarioEntity(Integer id, String email, String password, String rol, Integer id_institucion) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.id_institucion = id_institucion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getId_institucion() {
        return id_institucion;
    }

    public void setId_institucion(Integer id_institucion) {
        this.id_institucion = id_institucion;
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + rol + '\'' +
                ", id_institucion='" + id_institucion + '\'' +
                '}';
    }

}
