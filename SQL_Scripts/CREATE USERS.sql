-- Seleccionar BD
USE `apis-uade-simpsons`;

-- Eliminar la tabla USERS si ya existe
DROP TABLE IF EXISTS USERS;

-- Crear la tabla USERS con la estructura proporcionada
CREATE TABLE USERS (
    USER_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_USERNAME VARCHAR(255) NOT NULL,
    USER_NAME VARCHAR(255),
    USER_LASTNAME VARCHAR(255),
    USER_ADMINISTRATOR BOOLEAN,
    USER_EMAIL VARCHAR(255) UNIQUE,
    USER_PASSWORD_HASH VARCHAR(255)
);

-- Insertar datos en la tabla USERS
INSERT INTO USERS (USER_USERNAME, USER_NAME, USER_LASTNAME, USER_ADMINISTRATOR, USER_EMAIL, USER_PASSWORD_HASH) VALUES
('FranciscoZanon', 'Francisco', 'Zanon', TRUE, 'admin@gmail.com', '12345'),
('LucasPerez', 'Lucas', 'Zanon', TRUE, 'admin1@gmail.com', '12345'),
('TizianoGreco', 'Tiziano', 'Perez', TRUE, 'admin2@gmail.com', '12345'),
('MariaLopez', 'Maria', 'Lopez', FALSE, 'mariaLopez@gmail.com', 'qwerty'),
('JuanPerez', 'Juan', 'Perez', TRUE, 'juanPerez@gmail.com', 'abcde'),
('AnaGarcia', 'Ana', 'Garcia', FALSE, 'anaGarcia@gmail.com', 'abcdef'),
('CarlosMartinez', 'Carlos', 'Martinez', TRUE, 'carlosMartinez@gmail.com', '123456');