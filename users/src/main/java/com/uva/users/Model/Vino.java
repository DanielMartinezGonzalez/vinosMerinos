package com.uva.users.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "VINO")
@NamedQueries({
@NamedQuery(
name = "Vino.findByDenomincacionOrdenadoNombreDesc",
query = "SELECT v FROM Vino v WHERE v.denominacion = ?1 ORDER BY v.nombreComercial DESC"),
@NamedQuery(
name = "Vino.findByDenominacionYCategoria",
query = "SELECT v FROM Vino v WHERE v.denominacion = ?1 AND v.categoria = ?2")})
public class Vino {

    @Id
    @GeneratedValue
    private Integer Id;
    @Column(name = "nombre_omercial")
    private String nombreComercial;
    private String denominacion;
    private String categoria;
    @Column(nullable = false)
    private Float precio;
    private Integer bodega_id;

    Vino() {
    }

    Vino(String nombre_comercial, String denominacion, String categoria, Float precio, Integer bodega) {
        this.nombreComercial = nombre_comercial;
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

    
    public String getNombreComercial() {
        return this.nombreComercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombreComercial = nombre_comercial;
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
