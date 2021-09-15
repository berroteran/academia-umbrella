package com.berroteran.academia.entity;

import com.berroteran.academia.pojos.UserInfo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue
    Integer id;

    private String username;
    private String nombre;
    private String apellido;
    private String password;
    private String email;
    private String telefono;
    private Double porcentajecomision;
    private String direccion;
    private String activo;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Rol rol;

    private List<UserInfo> userInfo;



    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellido=" + apellido + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getPorcentajecomision() {
        return porcentajecomision;
    }

    public void setPorcentajecomision(Double porcentajecomision) {
        this.porcentajecomision = porcentajecomision;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}