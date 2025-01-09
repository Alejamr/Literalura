package com.libreria.Literalura;


import com.libreria.Literalura.modelo.Autor;
import com.libreria.Literalura.modelo.Libro;
import com.libreria.Literalura.servicio.LibroServicio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Menu {

    public static void main(String[] args) {
        SpringApplication.run(Menu.class, args);
    }

    @Bean
    public CommandLineRunner run(LibroServicio libroServicio) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n=== Menú ===");
                System.out.println("1- Buscar libro por título");
                System.out.println("2- Listar libros registrados");
                System.out.println("3- Listar autores registrados");
                System.out.println("4- Listar autores vivos en un determinado año");
                System.out.println("5- Listar libros por idioma");
                System.out.println("0- Salir");

                System.out.print("Seleccione una opción: ");
                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                } else {
                    opcion = -1; // Opción inválida
                }
                scanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el título del libro: ");
                        String titulo = scanner.nextLine();
                        List<Libro> libros = libroServicio.buscarLibroPorTitulo(titulo);
                        if (libros.isEmpty()) {
                            System.out.println("No se ha encontrado el libro.");
                        } else {
                            System.out.println("Libros encontrados:");
                            libros.forEach(libro -> System.out.println(libro));
                        }
                        break;
                    case 2:
                        List<Libro> todosLosLibros = libroServicio.listarLibros();
                        if (todosLosLibros.isEmpty()) {
                            System.out.println("No hay libros registrados.");
                        } else {
                            System.out.println("Todos los libros registrados:");
                            todosLosLibros.forEach(libro -> System.out.println(libro));
                        }
                        break;
                    case 3:
                        List<Autor> todosLosAutores = libroServicio.listarAutores();
                        if (todosLosAutores.isEmpty()) {
                            System.out.println("No hay autores registrados.");
                        } else {
                            System.out.println("Autores registrados:");
                            todosLosAutores.forEach(autor -> System.out.println(autor));
                        }
                        break;
                    case 4:
                        System.out.print("Ingrese el año: ");
                        if (scanner.hasNextInt()) {
                            int ano = scanner.nextInt();
                            scanner.nextLine(); // Consumir salto de línea
                            List<Autor> autoresVivos = libroServicio.listarAutoresVivosEn(ano);
                            if (autoresVivos.isEmpty()) {
                                System.out.println("No se encontraron autores vivos en " + ano + ".");
                            } else {
                                System.out.println("Autores vivos en " + ano + ":");
                                autoresVivos.forEach(autor -> System.out.println(autor));
                            }
                        } else {
                            System.out.println("Año inválido.");
                            scanner.nextLine(); // Consumir entrada inválida
                        }
                        break;
                    case 5:
                        System.out.print("Ingrese el idioma: ");
                        String idiomaBusqueda = scanner.nextLine();
                        List<Libro> librosPorIdioma = libroServicio.listarLibrosPorIdioma(idiomaBusqueda);
                        if (librosPorIdioma.isEmpty()) {
                            System.out.println("No se encontraron libros en el idioma " + idiomaBusqueda + ".");
                        } else {
                            System.out.println("Libros en el idioma " + idiomaBusqueda + ":");
                            librosPorIdioma.forEach(libro -> System.out.println(libro));
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 0);
            scanner.close();
        };
    }
}
