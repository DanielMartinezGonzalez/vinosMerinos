package com.uva.users.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vino")
public class Vino {

    @Id
    @GeneratedValue
    private Integer Id;
    private String nombre_comercial;
    private String denominacion;
    private String categoria;
    @Column(nullable = false)
    private Float precio;
    private Integer bodega_id;

    Vino() {
    }

    Vino(String nombre_comercial, String denominacion, String categoria, Float precio, Integer bodega) {
        this.nombre_comercial = nombre_comercial;
        this.denominacion = denominacion;
        this.categoria = categoria;
        this.precio = precio;
        this.bodega_id = bodega;
    }

    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNombre_comercial() {
        return this.nombre_comercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    public String getDenominacion() {
        return this.denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getBodega_id() {
        return this.bodega_id;
    }

    public void setBodega_id(Integer bodega_id) {
        this.bodega_id = bodega_id;
    }

}
