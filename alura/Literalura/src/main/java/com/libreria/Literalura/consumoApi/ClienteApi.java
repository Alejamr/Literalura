package com.libreria.Literalura.consumoApi;

import com.libreria.Literalura.modelo.Autor;
import com.libreria.Literalura.modelo.Libro;
import com.libreria.Literalura.repositorio.AutorRepository;
import com.libreria.Literalura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ClienteApi {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;

    private final String URL_API = "https://gutendex.com/books";

    // Método para buscar libros por título en la API
    public List<Libro> buscarLibroEnApi(String titulo) {
        String url = URL_API + "?search=" + titulo + "&languages=es,en,fr,de,ru"; // Especifica los idiomas según necesites
        // Obtenemos los resultados directamente de la API como un Map
        Map<String, Object> respuesta = restTemplate.getForObject(url, Map.class);
        List<Libro> librosEncontrados = new ArrayList<>();

        if (respuesta != null && respuesta.get("results") != null) {
            List<Map<String, Object>> resultados = (List<Map<String, Object>>) respuesta.get("results");
            for (Map<String, Object> datosLibro : resultados) {
                String tituloLibro = (String) datosLibro.get("title");
                // Verificar si el título del libro contiene el término de búsqueda
                if (tituloLibro.toLowerCase().contains(titulo.toLowerCase())) {
                    // Verificar si el libro ya existe en la base de datos
                    List<Libro> librosExistentes = libroRepository.findByTituloContainingIgnoreCase(tituloLibro);
                    if (librosExistentes.isEmpty()) {
                        // Obtener los autores del libro
                        List<Map<String, Object>> autoresDatos = (List<Map<String, Object>>) datosLibro.get("authors");
                        if (autoresDatos != null && !autoresDatos.isEmpty()) {
                            // Obtener el primer autor de la lista (suponiendo que el libro tiene al menos un autor)
                            Map<String, Object> datosAutor = autoresDatos.get(0);

                            // Extraer el nombre del autor
                            String nombreAutor = (String) datosAutor.get("name");

                            // Extraer el año de nacimiento, si está disponible
                            Integer añoNacimiento = datosAutor.get("birth_year") != null ? ((Number) datosAutor.get("birth_year")).intValue() : 0;

                            // Extraer el año de fallecimiento, si está disponible
                            Integer añoFallecimiento = datosAutor.get("death_year") != null ? ((Number) datosAutor.get("death_year")).intValue() : 0;

                            // Buscar o crear el autor
                            Autor autor = autorRepository.findByNombre(nombreAutor);
                            if (autor == null) {
                                autor = new Autor(nombreAutor, añoNacimiento, añoFallecimiento);
                                autorRepository.save(autor);
                            }

                            // Crear el objeto Libro
                            String idioma = datosLibro.get("languages") != null && !((List<String>) datosLibro.get("languages")).isEmpty()
                                    ? mapearIdioma(((List<String>) datosLibro.get("languages")).get(0))
                                    : "Desconocido";
                            Libro libro = new Libro(tituloLibro, "Ficción", idioma, autor);
                            libroRepository.save(libro);
                            librosEncontrados.add(libro);
                        }
                    } else {
                        // Si el libro existe en la base de datos, agregarlo a la lista de encontrados
                        librosEncontrados.addAll(librosExistentes);
                    }
                }
            }
        }

        return librosEncontrados;
    }

    // Método para mapear códigos de idioma a nombres completos en español
    private String mapearIdioma(String codigoIdioma) {
        switch (codigoIdioma.toLowerCase()) {
            case "es":
                return "Español";
            case "en":
                return "Inglés";
            case "fr":
                return "Francés";
            case "de":
                return "Alemán";
            case "ru":
                return "Ruso";
            default:
                return "Desconocido";
        }
    }

    public Object buscarLibrosPorTitulo(String titulo) {
        return null;
    }
}
