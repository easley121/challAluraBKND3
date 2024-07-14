-- Creación de la tabla Perfil
CREATE TABLE IF NOT EXISTS perfil (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Creación de la tabla Usuario
CREATE TABLE IF NOT EXISTS usuario (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    perfil_id INTEGER,
    FOREIGN KEY (perfil_id) REFERENCES perfil (id)
);

-- Creación de la tabla Curso
CREATE TABLE IF NOT EXISTS curso (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

-- Creación de la tabla Tópico
CREATE TABLE IF NOT EXISTS topico (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(255) NOT NULL,
    usuario_id INTEGER,
    curso_id INTEGER,
    activo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE CASCADE,
    FOREIGN KEY (curso_id) REFERENCES curso (id) ON DELETE CASCADE
);

-- Creación de la tabla Respuesta
CREATE TABLE IF NOT EXISTS respuesta (
    id SERIAL PRIMARY KEY,
    mensaje TEXT NOT NULL,
    topico_id INTEGER,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    autor_id INTEGER,
    solucion BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (topico_id) REFERENCES topico (id) ON DELETE CASCADE,
    FOREIGN KEY (autor_id) REFERENCES usuario (id) ON DELETE CASCADE
);

-- Crear índices para mejorar el rendimiento de las búsquedas
CREATE INDEX IF NOT EXISTS idx_usuario_nombre ON usuario (nombre);