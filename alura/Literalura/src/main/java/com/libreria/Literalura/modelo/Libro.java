package com.libreria.Literalura.modelo;

import jakarta.persistence.*;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String tema;
    private String idioma;

    @ManyToOne(fetch = FetchType.EAGER)
// Esto es importante, ajusta la carga para evitar consultas repetidas.
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {}

    public Libro(String titulo, String tema, String idioma, Autor autor) {
        this.titulo = titulo;
        this.tema = tema;
        this.idioma = idioma;
        this.autor = autor;
    }

    // Getters y Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTema() { return tema; }

    public void setTema(String tema) { this.tema = tema; }

    public String getIdioma() { return idioma; }

    public void setIdioma(String idioma) { this.idioma = idioma; }

    public Autor getAutor() { return autor; }

    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", tema='" + tema + '\'' +
                ", idioma='" + idioma + '\'' +
                ", autor='" + (autor != null ? autor.getNombre() : "Sin autor") + '\'' +
                '}';
    }
}
