ForoHub

ForoHub es una aplicación de foro en línea desarrollada con Spring Boot. Permite a los usuarios registrarse, autenticarse y gestionar tópicos en diversos cursos. La aplicación cuenta con funcionalidades de creación, búsqueda, actualización, eliminación y paginación de tópicos. Además, implementa seguridad utilizando Spring Security y JWT para autenticación y autorización.
Requisitos

    Java 11 o superior
    Maven
    PostgreSQL
    Postman (para pruebas de API)

Instalación

    Clonar el repositorio:

    bash

git clone https://github.com/tuusuario/ForoHub.git
cd ForoHub

Configurar la base de datos en el archivo application.properties:

properties

spring.datasource.url=jdbc:postgresql://localhost:5432/forohub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contrasena

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

Ejecutar las migraciones de Flyway para configurar la base de datos:

bash

mvn flyway:migrate

Compilar y ejecutar la aplicación:

bash

    mvn clean install
    mvn spring-boot:run

Endpoints
Autenticación y Registro
Registrar Usuario

    URL: /auth/signup
    Método: POST
    Cuerpo (JSON):

    json

{
    "fullName": "John Doe",
    "email": "johndoe@example.com",
    "password": "password"
}

Respuesta Exitosa (200):

json

    {
        "id": 1,
        "nombre": "John Doe",
        "correoElectronico": "johndoe@example.com",
        "contrasena": "$2a$10$...",
        "perfilId": null
    }

Autenticar Usuario

    URL: /auth/login
    Método: POST
    Cuerpo (JSON):

    json

{
    "email": "johndoe@example.com",
    "password": "password"
}

Respuesta Exitosa (200):

json

    {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }

Gestión de Tópicos
Crear Tópico

    URL: /topicos
    Método: POST
    Cuerpo (JSON):

    json

    {
        "titulo": "Nuevo Tópico",
        "mensaje": "Este es un nuevo tópico.",
        "usuarioId": 1,
        "cursoId": 1
    }

    Autenticación: Se requiere token JWT en el header Authorization: Bearer <token>

Obtener Tópico por ID

    URL: /topicos/{id}
    Método: GET
    Autenticación: Se requiere token JWT en el header Authorization: Bearer <token>

Listar Tópicos con Paginación

    URL: /topicos
    Método: GET
    Parámetros de Consulta:
        cursoNombre (opcional)
        year (opcional)
        page (opcional, default 0)
        size (opcional, default 10)
    Autenticación: Se requiere token JWT en el header Authorization: Bearer <token>

Actualizar Tópico

    URL: /topicos/{id}
    Método: PUT
    Cuerpo (JSON):

    json

    {
        "titulo": "Tópico Actualizado",
        "mensaje": "Este es un tópico actualizado.",
        "usuarioId": 1,
        "cursoId": 1
    }

    Autenticación: Se requiere token JWT en el header Authorization: Bearer <token>

Eliminar Tópico

    URL: /topicos/{id}
    Método: DELETE
    Autenticación: Se requiere token JWT en el header Authorization: Bearer <token>

Seguridad

La aplicación utiliza Spring Security y JWT para manejar la autenticación y autorización. El proceso de autenticación genera un token JWT que debe ser enviado en el header de las solicitudes para acceder a los endpoints protegidos.
Configuración de Seguridad

    JwtRequestFilter: Filtro para interceptar las solicitudes y validar el token JWT.
    JwtService: Servicio para generar y validar tokens JWT.
    MyUserDetailsService: Servicio para cargar los detalles del usuario desde la base de datos.

Errores Comunes
Error 500 al Autenticar Usuario

    Asegúrate de que el endpoint de autenticación está siendo llamado con el cuerpo correcto.
    Verifica que el usuario está registrado y que la contraseña es correcta.
    Revisa los logs para más detalles sobre el error.

ResourceNotFoundException

    Asegúrate de que los IDs utilizados en las solicitudes existen en la base de datos.

Pruebas con Postman

    Registrar Usuario
        Configura un POST request a http://localhost:8080/auth/signup con el cuerpo JSON correspondiente.

    Autenticar Usuario
        Configura un POST request a http://localhost:8080/auth/login con el cuerpo JSON correspondiente.
        Copia el token JWT de la respuesta.

    Crear Tópico
        Configura un POST request a http://localhost:8080/topicos con el cuerpo JSON correspondiente.
        En los headers, agrega Authorization: Bearer <token>.

    Otros Endpoints
        Configura los requests necesarios y asegúrate de incluir el header Authorization con el token JWT.

Contribuciones

Las contribuciones son bienvenidas. Por favor, realiza un fork del repositorio y envía un pull request con tus cambios.
Licencia

Este proyecto está bajo la Licencia MIT. Para más detalles, consulta el archivo LICENSE.
