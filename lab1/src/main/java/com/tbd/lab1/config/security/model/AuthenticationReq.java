package com.tbd.lab1.config.security.model;

import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@AllArgsConstructor
public class AuthenticationReq implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private String email;

    private String clave;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
