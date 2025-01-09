package com.libreria.Literalura.repositorio;

import com.libreria.Literalura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Encontrar autor por nombre
    Autor findByNombre(String nombre);

    // Encontrar autores vivos en un año específico
    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :anio AND (a.anioFallecimiento > :anio OR a.anioFallecimiento = 0)")
    List<Autor> findAutoresVivosEn(int anio);
}
