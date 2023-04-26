USE hlhotelesdb;

INSERT INTO hotel (id, nombre, ciudad, habitaciones_disp)
VALUES 
(1, 'Hotel Miramar', 'Barcelona', 100),
(2, 'Hotel Ritz', 'Madrid', 50),
(3, 'Hotel Marbella', 'Málaga', 75),
(4, 'Hotel Valencia', 'Valencia', 80),
(5, 'Hotel San Sebastian', 'San Sebastián', 60);

INSERT INTO usuario (dni, contrasenya, nombre) VALUES ("0000000A", "1234567Ab*", "usuario");

INSERT INTO habitacion (tipoHabitacion, personas, precio)
VALUES 
('SUITE', 4, 140),
('SUITE', 5, 180),
('DELUXE', 2, 90),
('ESTANDAR', 1, 65),
('CAMA_EXTRAGRANDE', 2, 80);

SELECT * FROM hlhotelesdb.hotel;
SELECT * FROM hlhotelesdb.usuario;