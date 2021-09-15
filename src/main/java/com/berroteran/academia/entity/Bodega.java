package com.berroteran.academia.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bodega {

    @Id
    @GeneratedValue
    private Integer id;

    private String telefono;
    private String direccion;
    private String activo;


    public Bodega() {
    }

    @Override
    public String toString() {
        return "Bodega{" + "idbodega=" + id + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }

    public Integer getIdbodega() {
        return id;
    }

    public void setIdbodega(Integer idbodega) {
        this.id = idbodega;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

}