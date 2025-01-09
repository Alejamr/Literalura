# Literalura 📚

### Un sistema de catálogo de libros con integración a la API de Gutendex

---

## 📋 Descripción

**Literalura** es un proyecto en **Java** diseñado para gestionar un catálogo de libros. Se conecta a la API de **Gutendex** para obtener información sobre libros y autores, y organiza estos datos en una base de datos **PostgreSQL**. Además, ofrece funcionalidades como búsquedas avanzadas, estadísticas y un menú interactivo en consola para la experiencia del usuario.

El propósito de este proyecto es demostrar cómo desarrollar una aplicación moderna y bien estructurada con las siguientes características:
- Consumo de APIs REST.
- Almacenamiento y manipulación de datos en una base de datos relacional.
- Organización modular en Java.
- Interacción con el usuario a través de la consola.

---

## 🛠️ Funcionalidades

1. **Consumo de API**: Integración con la API de Gutendex para obtener datos de libros y autores.
2. **Gestión de Datos**: 
   - Análisis de datos JSON.
   - Almacenamiento en base de datos **PostgreSQL**.
3. **Menú Interactivo**:
   - **Buscar libro por título**: Permite buscar un libro en el catálogo por su título.
   - **Listar libros registrados**: Muestra todos los libros disponibles en la base de datos.
   - **Listar autores registrados**: Muestra todos los autores registrados en el sistema.
   - **Listar autores vivos en un año específico**: Permite listar los autores que están vivos en el año especificado.
   - **Listar libros disponibles por idioma**: Muestra una lista de libros disponibles en un idioma específico.
4. **Estructura Modular**:
   - Separación clara de responsabilidades usando capas como `Modelo`, `Servicio`, `Controlador` y `Repositorio`.
5. **Configuración Personalizable**:
   - Configuración centralizada en el archivo `application.properties`.

---

## 🚀 Tecnologías Utilizadas

- **Lenguaje de Programación**: Java 17
- **Framework**: Spring Boot
- **Base de Datos**: PostgreSQL
- **Consumo de API**: JSON y REST
- **Herramientas**:
  - IntelliJ IDEA
  - Postman/Insomnia para pruebas de la API
  - Maven para la gestión de dependencias

---

## 📂 Estructura del Proyecto

```plaintext
com.libreria.literalura
│
├── consumoAPI
│   ├── ClienteApi.java          // Lógica para consumir la API de Gutendex.
│   ├── GutendexResponse.java    // Modelo para mapear la respuesta de la API.
│
├── controlador
│   ├── Configuracion.java       // Configuración de la base de datos.
│   ├── LibroControlador.java    // Controlador para las operaciones de libros.
│
├── modelo
│   ├── Autor.java               // Clase que representa a un autor.
│   ├── Libro.java               // Clase que representa a un libro.
│
├── repositorio
│   ├── AutorRepository.java     // Interfaz de acceso a la base de datos (Autor).
│   ├── LibroRepository.java     // Interfaz de acceso a la base de datos (Libro).
│
├── servicio
│   ├── LibroServicio.java       // Lógica de negocio para los libros.
│
├── resources
│   ├── application.properties   // Archivo de configuración.
│
├── Menu.java                    // Menú interactivo para los usuarios.
├── LiteraluraApplication.java   // Punto de entrada principal de la aplicación.
```

---

## 🔧 Instalación y Configuración

### Requisitos Previos
1. **Java**: Instalar la versión 17 o superior.
2. **PostgreSQL**: Instalar y crear una base de datos llamada `literalura`.
3. **Maven**: Asegúrate de tener Maven instalado.

### Pasos
1. Clona este repositorio:
   ```bash
   git clone https://github.com/Alejamr/Literalura.git
   cd Literalura
   ```
2. Configura la conexión a la base de datos en el archivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```
3. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

---

## 🌐 Endpoints de la API

| Método | Endpoint                    | Descripción                        |
|--------|-----------------------------|------------------------------------|
| GET    | `/api/libros`                | Listar todos los libros registrados. |
| GET    | `/api/libros/{id}`           | Obtener un libro por ID.           |
| GET    | `/api/autores`               | Listar todos los autores registrados. |
| GET    | `/api/autores/{id}`          | Obtener un autor por ID.           |
| GET    | `/api/autores/vivos/{año}`   | Listar autores vivos en un año específico. |
| GET    | `/api/libros/idioma/{idioma}` | Listar libros disponibles por idioma. |

---

## 📈 Ejemplo de Uso

### 1. Menú Interactivo
Al ejecutar el programa, aparecerá un menú con opciones como:
- **Buscar libro por título**: Permite buscar un libro en el catálogo por su título.
- **Listar libros registrados**: Muestra todos los libros disponibles en la base de datos.
- **Listar autores registrados**: Muestra todos los autores registrados en el sistema.
- **Listar autores vivos en un año específico**: Permite listar los autores que están vivos en el año especificado.
- **Listar libros disponibles por idioma**: Muestra una lista de libros disponibles en un idioma específico.
- **Estadísticas**: Proporciona estadísticas útiles sobre los datos.

### 2. Peticiones a la API
Ejemplo con `Postman`:
- Haz una solicitud **GET** al endpoint `/api/libros` para listar libros.
- Consulta autores con **GET** en `/api/autores`.

---

## 🛡️ Mejora Continua

### Próximas Implementaciones
- **Interfaz Gráfica**: Migración a una interfaz de usuario gráfica.
- **Funcionalidad Offline**: Sincronización de datos para uso sin conexión.
- **Recomendaciones**: Algoritmo para recomendar libros basados en intereses.

---

## 👤 Autor

Desarrollado por **[Alejamr](https://github.com/Alejamr)**.  
¡Si tienes ideas o mejoras, no dudes en contribuir!

---
