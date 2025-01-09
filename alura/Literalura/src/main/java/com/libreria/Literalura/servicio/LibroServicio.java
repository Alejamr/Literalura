package com.libreria.Literalura.servicio;

import com.libreria.Literalura.consumoApi.ClienteApi;
import com.libreria.Literalura.modelo.Autor;
import com.libreria.Literalura.modelo.Libro;
import com.libreria.Literalura.repositorio.AutorRepository;
import com.libreria.Literalura.repositorio.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ClienteApi clienteApi;

    @Transactional
    public Libro obtenerLibroConAutor(Long id) {
        return libroRepository.findByIdWithAutor(id); // Usamos la consulta con JOIN FETCH
    }

    // Buscar libro por título: primero API, luego local
    public List<Libro> buscarLibroPorTitulo(String titulo) {
        // Intentar buscar en la API
        List<Libro> librosApi = clienteApi.buscarLibroEnApi(titulo);

        // Eliminar duplicados entre la API y la base de datos
        Set<Libro> librosUnicos = new HashSet<>(librosApi); // Usar HashSet para evitar duplicados

        if (!librosApi.isEmpty()) {
            // Si se encontraron libros en la API, agregar los que faltan desde la base de datos
            List<Libro> librosLocal = libroRepository.findByTituloContainingIgnoreCase(titulo);
            librosUnicos.addAll(librosLocal); // Añadir libros de la base de datos (si no están en la API)
        } else {
            // Si no se encontraron en la API, buscar solo en la base de datos
            List<Libro> librosLocal = libroRepository.findByTituloContainingIgnoreCase(titulo);
            librosUnicos.addAll(librosLocal);
        }

        return List.copyOf(librosUnicos); // Devolver una lista sin duplicados
    }

    // Listar todos los libros
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    // Listar todos los autores
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    // Listar autores vivos en un año específico
    public List<Autor> listarAutoresVivosEn(int ano) {
        return autorRepository.findAutoresVivosEn(ano);
    }

    // Listar libros por idioma
    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }

    public void agregarLibro(Libro libro) {
        libroRepository.save(libro); // Guardar el libro en la base de datos
    }
}
