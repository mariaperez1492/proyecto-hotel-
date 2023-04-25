USE hlhotelesdb;

INSERT INTO hotel (id, nombre, ciudad, habitaciones_disp)
VALUES 
(1, 'Hotel Miramar', 'Barcelona', 100),
(2, 'Hotel Ritz', 'Madrid', 50),
(3, 'Hotel Marbella', 'Málaga', 75),
(4, 'Hotel Valencia', 'Valencia', 80),
(5, 'Hotel San Sebastian', 'San Sebastián', 60);

INSERT INTO usuario VALUES ("0000000A", "usuario", "1234567Ab*");

SELECT * FROM hlhotelesdb.hotel;
SELECT * FROM hlhotelesdb.usuario;