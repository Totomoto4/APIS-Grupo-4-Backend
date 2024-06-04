USE `apis-uade-simpsons`;

-- Insertar datos en la tabla PRODUCT_ORDERED
INSERT INTO PRODUCT_ORDERED (PRODUCT_ORDERED_ORDER_ID, PRODUCT_ORDERED_NAME, PRODUCT_ORDERED_PRICE, PRODUCT_ORDERED_QUANTITY) VALUES
(1, 'Comic 1', 9.99, 10),
(1, 'Comic 5', 9.99, 5),
(1, 'Comic 11', 9.99, 5),
(2, 'Comic 15', 9.99, 0),
(2, 'Comic 36', 9.99, 10),
(2, 'Comic 47', 9.99, 6),
(2, 'Comic 245', 9.99, 15),
(3, 'Funko POP Abuelo', 25.00, 0),
(3, 'Funko POP Flanders', 25.00, 10),
(3, 'Funko POP Homero 1', 25.00, 10),
(3, 'Funko POP Homero 2', 30.00, 5),
(4, 'Funko POP Lisa', 25.00, 10),
(4, 'Funko POP Marge', 25.00, 12),
(4, 'Funko POP Moe', 25.00, 10),
(5, 'Guerra de generos', 30.00, 10),
(5, 'Clue', 35.00, 10),
(5, 'Life', 30.00, 10),
(5, 'Monopoly', 35.00, 10),
(5, 'Operando', 30.00, 10),
(6, 'Buzo famila Simpson', 15.00, 5),
(6, 'Buzo Duff', 15.00, 10),
(6, 'Gorro Duff', 10.00, 15),
(7, 'Gorro oveja', 10.00, 12),
(7, 'Remera Homero', 15.00, 10),
(7, 'Remera Duff', 12.00, 15),
(7, 'Remera arbusto', 12.00, 12),
(8, 'Remera Bart', 15.00, 0),
(8, 'Remera Yo no fui', 10.00, 15);

-- Insertar datos en la tabla USERS
INSERT INTO USERS (USER_USERNAME, USER_NAME, USER_LASTNAME, USER_ADMINISTRATOR, USER_EMAIL, USER_PASSWORD_HASH) VALUES
('FranciscoZanon', 'Francisco', 'Zanon', TRUE, 'admin@gmail.com', '12345'),
('LucasPerez', 'Lucas', 'Perez', TRUE, 'admin1@gmail.com', '12345'),
('TizianoGreco', 'Tiziano', 'Greco', TRUE, 'admin2@gmail.com', '12345'),
('MariaLopez', 'Maria', 'Lopez', FALSE, 'mariaLopez@gmail.com', 'qwerty'),
('JuanPerez', 'Juan', 'Perez', TRUE, 'juanPerez@gmail.com', 'abcde'),
('AnaGarcia', 'Ana', 'Garcia', FALSE, 'anaGarcia@gmail.com', 'abcdef'),
('CarlosMartinez', 'Carlos', 'Martinez', TRUE, 'carlosMartinez@gmail.com', '123456');

-- Insertar datos en la tabla ORDERS
INSERT INTO ORDERS (USER_ID, ORDER_DATE_TIME, ORDER_TOTAL, ORDER_CARD_NUMBER, ORDER_ADDRESS) VALUES
(1, '2024-06-01 10:00:00', 100.50, 1234567890123456, '123 Main St, Anytown, USA'),
(2, '2024-06-02 12:30:00', 75.00, 2345678901234567, '456 Elm St, Othertown, USA'),
(3, '2024-06-03 15:45:00', 50.00, 3456789012345678, '789 Oak St, Anothertown, USA'),
(4, '2024-06-04 09:15:00', 120.00, 4567890123456789, '101 Pine St, Towertown, USA'),
(5, '2024-06-05 11:30:00', 80.00, 5678901234567890, '202 Birch St, Villagetown, USA'),
(6, '2024-06-06 14:45:00', 95.00, 6789012345678901, '303 Cedar St, Hamletown, USA'),
(7, '2024-06-07 16:00:00', 110.00, 7890123456789012, '404 Maple St, Boroughville, USA'),
(8, '2024-06-08 17:30:00', 60.00, 8901234567890123, '505 Walnut St, Smalltown, USA');