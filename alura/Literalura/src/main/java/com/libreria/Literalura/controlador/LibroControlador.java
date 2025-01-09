package com.libreria.Literalura.controlador;


import com.libreria.Literalura.consumoApi.ClienteApi;
import com.libreria.Literalura.modelo.Autor;
import com.libreria.Literalura.modelo.Libro;
import com.libreria.Literalura.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private ClienteApi clienteApi;

    @PostMapping("/agregar")
    public ResponseEntity<Libro> agregarLibro(@RequestBody Libro libro) {
        libroServicio.agregarLibro(libro);
        return ResponseEntity.ok(libro);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Libro>> buscarLibrosPorTitulo(@RequestParam String titulo) {
        // Consulta combinada sin duplicados
        List<Libro> libros = libroServicio.buscarLibroPorTitulo(titulo);
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        return ResponseEntity.ok(libroServicio.listarLibros());
    }

    @GetMapping("/librosIdioma")
    public ResponseEntity<List<Libro>> obtenerLibrosPorIdioma(@RequestParam String idioma) {
        return ResponseEntity.ok(libroServicio.listarLibrosPorIdioma(idioma));
    }

    @GetMapping("/todosAutores")
    public ResponseEntity<List<Autor>> obtenerTodosLosAutores() {
        return ResponseEntity.ok(libroServicio.listarAutores());
    }

    @GetMapping("/autoresVivos")
    public ResponseEntity<List<Autor>> obtenerAutoresVivos(@RequestParam int ano) {
        return ResponseEntity.ok(libroServicio.listarAutoresVivosEn(ano));
    }
}
