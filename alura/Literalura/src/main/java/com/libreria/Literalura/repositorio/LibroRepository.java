package com.libreria.Literalura.repositorio;


import com.libreria.Literalura.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l JOIN FETCH l.autor WHERE l.id = :id")
    Libro findByIdWithAutor(Long id);
    // Buscar libros por idioma, ignorando mayúsculas y minúsculas
    List<Libro> findByIdiomaIgnoreCase(String idioma);

    // Buscar libros por título, ignorando mayúsculas y minúsculas
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}
