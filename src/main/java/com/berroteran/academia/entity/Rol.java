package com.berroteran.academia.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Rol {

    @Id
    private Integer id;

    private String rol;
    private String activo;


    public Rol() {
    }

    @Override
    public String toString() {
        return  rol ;
    }

    public Integer getIdrol() {
        return id;
    }

    public void setIdrol(Integer idrol) {
        this.id = idrol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }


}