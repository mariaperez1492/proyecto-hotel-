USE hlhotelesdb;

INSERT INTO hotel (id, nombre, ciudad, habitaciones_disp)
VALUES 
(1, 'Hotel Miramar', 'Barcelona', 4),
(2, 'Hotel Ritz', 'Madrid', 50),
(3, 'Hotel Marbella', 'Málaga', 75),
(4, 'Hotel Valencia', 'Valencia', 80),
(5, 'Hotel San Sebastian', 'San Sebastián', 60);

INSERT INTO usuario (dni, contrasenya, nombre) 
VALUES 
("0000000A", "1234567Ab*", "usuario"),
("0000", "admin", "admin"),
("0000000B", "1234567Ab*", "usuario2");

INSERT INTO habitacion (numhabitacion, personas, precio, tipoHabitacion)
VALUES 
(1, 4, 140, 'SUITE'),
(2, 5, 180, 'SUITE'),
(3, 2, 90, 'DELUXE'),
(4, 1, 65, 'ESTANDAR'),
(5, 2, 80, 'CAMA_EXTRAGRANDE');

INSERT INTO reserva (id, fecha_ini, fecha_fin, pension, precio) VALUES (1, '2023-05-10', '2023-05-15', "spa, piscina", 390),  (2, '2023-05-10', '2023-05-15', "spa, piscina", 390);
INSERT INTO reserva_cliente (ID_OID, DNI_ID) VALUES (1, "0000000A"), (2, "0000000B");
INSERT INTO reserva_habitacion(ID_OID, NUMHABITACION_ID) VALUES (1,1), (2,1);
INSERT INTO reserva_hotel(ID_OID, ID_ID) VALUES (1,1), (2,2);

SELECT * FROM hlhotelesdb.hotel;
SELECT * FROM hlhotelesdb.usuario;