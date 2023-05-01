USE hlhotelesdb;

INSERT INTO hotel (id, nombre, ciudad, habitaciones_disp)
VALUES 
(1, 'Hotel Miramar', 'Barcelona', 100),
(2, 'Hotel Ritz', 'Madrid', 50),
(3, 'Hotel Marbella', 'Málaga', 75),
(4, 'Hotel Valencia', 'Valencia', 80),
(5, 'Hotel San Sebastian', 'San Sebastián', 60);

INSERT INTO usuario (dni, contrasenya, nombre) 
VALUES 
("0000000A", "1234567Ab*", "usuario"),
("0000", "admin", "admin");

INSERT INTO habitacion (numhabitacion, personas, precio, tipoHabitacion)
VALUES 
(1, 4, 140, 'SUITE'),
(2, 5, 180, 'SUITE'),
(3, 2, 90, 'DELUXE'),
(4, 1, 65, 'ESTANDAR'),
(5, 2, 80, 'CAMA_EXTRAGRANDE');

INSERT INTO reserva (id, cliente, fecha_fin, fecha_ini, habitacion, hotel)
VALUES
(1, "0000000A", '2023-04-12', '2023-04-01', 1, 1),
(2, "0000000A", '2023-04-20', '2023-04-11', 2, 2);

SELECT * FROM hlhotelesdb.hotel;
SELECT * FROM hlhotelesdb.usuario;