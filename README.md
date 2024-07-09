# ForoHub
<p align="center">
  <img src="/Images/Badge-Spring.png" alt="badge">
</p>
## Descripción del Proyecto

ForoHub es una aplicación de foro en línea construida con Java, Spring Boot, y MySQL. Permite a los usuarios crear, leer, actualizar y eliminar tópicos (hilos de discusión) en diferentes categorías.

## Características

Autenticación y Autorización: Los usuarios deben autenticarse para acceder a las funcionalidades de la API.
CRUD de Tópicos: Los usuarios pueden crear, leer, actualizar y eliminar tópicos.
Validación de Datos: Uso de anotaciones de validación para asegurar la integridad de los datos.
JWT: Implementación de tokens JWT para la autenticación de usuarios.
Requisitos del Sistema

Java 17 o superior
Maven 3.8 o superior
MySQL 8.0 o superior

## Diagrama
![Diagrama de Base de Datos](/Images/Diagrama.png)

## Instalación

1. Clona el repositorio:
    Git clone git@github.com:Pyrooos/Challenge-Foro-Hub.git


2. Configura la base de datos en application.properties:

    spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    api.security.secret=tu_contraseña


## Uso

Para interactuar con la API, utiliza una herramienta como Postman o Insomnia.


## Autenticación

  ###  Registro en usarios via SQL

    USE tu_base_de_datos;
    INSERT INTO usuarios (nombre_usuario, clave) VALUES ('usuario',  'contraseña_hasheada');
### Login
    POST /login

        json
        {
            "login": "usuario",
            "clave": "contraseña"
        }

## Endpoints de la API

### Crear Tópico

    POST /topicos

        json
        {
            "titulo": "Título del Tópico",
            "mensaje": "Mensaje del Tópico",
            "status": "abierto",
            "autor": "Nombre del Autor",
            "curso": "Nombre del Curso",
            "anio": 2024
        }

### Obtener Todos los Tópicos

    GET /topicos

### Obtener un Tópico en especifico
    
    GET /topicos{id}

### Actualizar Tópico

    PUT /topicos/{id}

        json
        {
            "titulo": "Nuevo Título",
            "mensaje": "Nuevo Mensaje",
            "status": "cerrado",
            "autor": "Nuevo Autor",
            "curso": "Nuevo Curso",
            "anio": 2024
        }

### Eliminar Tópico

    DELETE /topicos/{id}

## Seguridad

ForoHub utiliza JWT para la autenticación de usuarios. Los usuarios deben incluir el token JWT en el encabezado Authorization de cada solicitud que requiere autenticación. 
Este aparece en en herramienta que estes usando (Insomnia o Postman) despues de hacer login correctamente y debe introducirse en la peticion que estes solicitando.


## Contribuir

Las contribuciones son bienvenidas. Para contribuir, sigue estos pasos:

Haz un fork del repositorio.
Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
Haz commit de tus cambios (git commit -m 'Agregar nueva funcionalidad').
Haz push a la rama (git push origin feature/nueva-funcionalidad).
Abre un Pull Request.

### Contacto

    Cristian Gutierrez - @Pyrooos - Cristianguher@Outlook.com

    
    