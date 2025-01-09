package com.libreria.Literalura.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer anioNacimiento = 0; // Valor predeterminado
    private Integer anioFallecimiento = 0; // Valor predeterminado

    public Autor() {}

    public Autor(String nombre, Integer anioNacimiento, Integer anioFallecimiento) {
        this.nombre = nombre;
        this.anioNacimiento = (anioNacimiento != null) ? anioNacimiento : 0;
        this.anioFallecimiento = (anioFallecimiento != null) ? anioFallecimiento : 0;
    }

    // Getters y Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getAnioNacimiento() { return anioNacimiento; }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = (anioNacimiento != null) ? anioNacimiento : 0;
    }

    public Integer getAnioFallecimiento() { return anioFallecimiento; }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = (anioFallecimiento != null) ? anioFallecimiento : 0;
    }

    @Override
    public String toString() {
        String nacimiento = (anioNacimiento != null) ? String.valueOf(anioNacimiento) : "Desconocido";
        String fallecimiento = (anioFallecimiento != null) ? String.valueOf(anioFallecimiento) : "Desconocido";

        return "Autor: " + nombre + ", Nacimiento: " + nacimiento + ", Fallecimiento: " + fallecimiento;
    }
}
