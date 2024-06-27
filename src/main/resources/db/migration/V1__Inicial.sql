-- Creación de la tabla Perfil
CREATE TABLE perfil (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Creación de la tabla Usuario
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    perfil_id INTEGER,
    FOREIGN KEY (perfil_id) REFERENCES perfil (id)
);

-- Creación de la tabla Curso
CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

-- Creación de la tabla Tópico
CREATE TABLE topico (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    autor_id INTEGER,
    curso_id INTEGER,
    FOREIGN KEY (autor_id) REFERENCES usuario (id),
    FOREIGN KEY (curso_id) REFERENCES curso (id)
);

-- Creación de la tabla Respuesta
CREATE TABLE respuesta (
    id SERIAL PRIMARY KEY,
    mensaje TEXT NOT NULL,
    topico_id INTEGER,
    fecha_creacion TIMESTAMP NOT NULL,
    autor_id INTEGER,
    solucion BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (topico_id) REFERENCES topico (id),
    FOREIGN KEY (autor_id) REFERENCES usuario (id)
);
