-- Adminer 4.8.1 MySQL 8.0.29-21 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `Cliente`;
CREATE TABLE `Cliente` (
  `dni` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `contrasenya` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Cliente` (`dni`, `nombre`, `contrasenya`) VALUES
('69466234A',	'Laura Ramiro',	'root'),
('78944983Y',	'Sara Martínez',	'cisco'),
('98244184E',	'Iván Soto',	'Cont4');

DROP TABLE IF EXISTS `Habitacion`;
CREATE TABLE `Habitacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` enum('estandar','deluxe','suite') COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'estandar',
  `personas` int NOT NULL DEFAULT '1',
  `precio` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Habitacion` (`id`, `tipo`, `personas`, `precio`) VALUES
(1,	'estandar',	1,	30),
(2,	'estandar',	2,	55),
(3,	'estandar',	3,	70),
(4,	'estandar',	4,	85),
(5,	'deluxe',	4,	230),
(6,	'deluxe',	5,	260),
(7,	'suite',	4,	410),
(8,	'suite',	5,	435);

DROP TABLE IF EXISTS `Hotel`;
CREATE TABLE `Hotel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `ciudad` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `habitaciones_disp` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Hotel` (`id`, `nombre`, `ciudad`, `habitaciones_disp`) VALUES
(1,	'HL MADRID',	'Madrid',	87),
(2,	'HL BARCELONA',	'Barcelona',	45),
(3,	'HL BILBAO',	'Bilbao',	66),
(4,	'HL VALENCIA',	'Valencia',	95),
(5,	'HL SEVILLA',	'Sevilla',	20);

DROP TABLE IF EXISTS `Reserva`;
CREATE TABLE `Reserva` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cliente` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hotel` int NOT NULL,
  `habitacion` int NOT NULL,
  `fecha_ini` date NOT NULL,
  `fecha_fin` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cliente` (`cliente`),
  KEY `hotel` (`hotel`),
  KEY `habitacion` (`habitacion`),
  CONSTRAINT `Reserva_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `Cliente` (`dni`) ON DELETE CASCADE,
  CONSTRAINT `Reserva_ibfk_2` FOREIGN KEY (`hotel`) REFERENCES `Hotel` (`id`) ON DELETE CASCADE,
  CONSTRAINT `Reserva_ibfk_3` FOREIGN KEY (`habitacion`) REFERENCES `Habitacion` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Reserva` (`id`, `cliente`, `hotel`, `habitacion`, `fecha_ini`, `fecha_fin`) VALUES
(1,	'69466234A',	1,	2,	'2021-02-28',	'2021-03-05'),
(2,	'69466234A',	2,	1,	'2022-06-10',	'2022-06-17'),
(3,	'78944983Y',	3,	5,	'2023-01-21',	'2023-01-25'),
(4,	'98244184E',	5,	5,	'2023-01-25',	'2023-01-30'),
(5,	'98244184E',	4,	5,	'2023-02-15',	'2023-02-17'),
(6,	'98244184E',	5,	5,	'2023-03-01',	'2023-03-09');

-- 2023-03-29 09:53:12
