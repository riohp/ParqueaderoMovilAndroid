-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.30 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para parqueadero_deec
CREATE DATABASE IF NOT EXISTS `parqueadero_deec` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `parqueadero_deec`;

-- Volcando estructura para tabla parqueadero_deec.asignacionvendedorparqueadero
CREATE TABLE IF NOT EXISTS `asignacionvendedorparqueadero` (
  `idasignacion` int NOT NULL AUTO_INCREMENT,
  `idparqueadero` int DEFAULT NULL,
  `idusuario` int DEFAULT NULL,
  PRIMARY KEY (`idasignacion`),
  KEY `idparqueadero` (`idparqueadero`),
  KEY `idusuario` (`idusuario`),
  CONSTRAINT `asignacionvendedorparqueadero_ibfk_1` FOREIGN KEY (`idparqueadero`) REFERENCES `parqueaderos` (`idparqueadero`),
  CONSTRAINT `asignacionvendedorparqueadero_ibfk_2` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla parqueadero_deec.asignacionvendedorparqueadero: ~5 rows (aproximadamente)
DELETE FROM `asignacionvendedorparqueadero`;
INSERT INTO `asignacionvendedorparqueadero` (`idasignacion`, `idparqueadero`, `idusuario`) VALUES
	(2, 2, 5),
	(11, 1, 2),
	(12, 2, 2),
	(13, 1, 5),
	(14, 1, 4);

-- Volcando estructura para tabla parqueadero_deec.parqueaderos
CREATE TABLE IF NOT EXISTS `parqueaderos` (
  `idparqueadero` int NOT NULL AUTO_INCREMENT,
  `nombreparqueadero` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `costotarifacarro` decimal(10,2) NOT NULL,
  `costotarifamoto` decimal(10,2) NOT NULL,
  `costotarifacamion` decimal(10,2) NOT NULL,
  `costotarifacamioneta` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idparqueadero`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla parqueadero_deec.parqueaderos: ~2 rows (aproximadamente)
DELETE FROM `parqueaderos`;
INSERT INTO `parqueaderos` (`idparqueadero`, `nombreparqueadero`, `direccion`, `costotarifacarro`, `costotarifamoto`, `costotarifacamion`, `costotarifacamioneta`) VALUES
	(1, 'Parqueadero A', 'Calle 123, Ciudad', 900.00, 700.00, 1200.00, 1600.00),
	(2, 'Parqueadero B', 'Avenida 456, Ciudad', 6.00, 3.00, 12.00, 9.60);

-- Volcando estructura para tabla parqueadero_deec.registrarvehiculos
CREATE TABLE IF NOT EXISTS `registrarvehiculos` (
  `idvehiculo` int NOT NULL AUTO_INCREMENT,
  `placa` varchar(20) NOT NULL,
  `tipovehiculo` enum('carro','moto','camion','camioneta') NOT NULL,
  `marca` varchar(20) NOT NULL,
  `modelo` varchar(20) NOT NULL,
  `color` varchar(20) NOT NULL,
  `idusuario` int DEFAULT NULL,
  PRIMARY KEY (`idvehiculo`),
  KEY `idusuario` (`idusuario`),
  CONSTRAINT `registrarvehiculos_ibfk_1` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla parqueadero_deec.registrarvehiculos: ~8 rows (aproximadamente)
DELETE FROM `registrarvehiculos`;
INSERT INTO `registrarvehiculos` (`idvehiculo`, `placa`, `tipovehiculo`, `marca`, `modelo`, `color`, `idusuario`) VALUES
	(1, 'XYZ988', 'carro', 'Toyota', 'Camry', 'Azul', 1),
	(2, 'XYZ987', 'moto', 'Honda', 'CBR600', 'Rojo', 1),
	(3, 'DEF456', 'camioneta', 'Ford', 'Escape', 'Verde', 2),
	(4, 'DFT345', 'moto', 'fuidfu', 'uidbfbidsif', 'nisidfnisd', 2),
	(5, '15156', 'carro', 'nissan', 'dasdadas', 'sdada', 2),
	(6, '211das', 'carro', '4324', 'sdfsf', 'fsfs', 2),
	(7, 'SAD213', 'camion', 'honda', 'civic', 'mate', 2),
	(8, 'XYZ988', 'camioneta', 'honda', 'civic', 'red', 2);

-- Volcando estructura para tabla parqueadero_deec.tickets
CREATE TABLE IF NOT EXISTS `tickets` (
  `idtickets` int NOT NULL AUTO_INCREMENT,
  `idparqueadero` int DEFAULT NULL,
  `idvehiculo` int DEFAULT NULL,
  `horaentrada` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `horasalida` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `costototal` decimal(10,2) DEFAULT NULL,
  `estado` enum('activo','inactivo') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'activo',
  PRIMARY KEY (`idtickets`),
  KEY `idvehiculo` (`idvehiculo`),
  KEY `idparqueadero` (`idparqueadero`),
  CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`idvehiculo`) REFERENCES `registrarvehiculos` (`idvehiculo`),
  CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`idparqueadero`) REFERENCES `parqueaderos` (`idparqueadero`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla parqueadero_deec.tickets: ~6 rows (aproximadamente)
DELETE FROM `tickets`;
INSERT INTO `tickets` (`idtickets`, `idparqueadero`, `idvehiculo`, `horaentrada`, `horasalida`, `costototal`, `estado`) VALUES
	(1, 1, 1, '2023-10-19 00:17:26', NULL, NULL, 'activo'),
	(2, 1, 2, '2023-10-19 02:30:17', '2023-10-19 02:32:21', 700.00, 'inactivo'),
	(3, 1, 2, '2023-10-19 05:15:51', '2023-10-19 20:42:02', 11200.00, 'inactivo'),
	(4, 1, 4, '2023-10-20 09:28:58', NULL, NULL, 'activo'),
	(5, 1, 6, '2023-10-20 15:24:24', '2023-10-23 12:55:26', 63000.00, 'inactivo'),
	(6, 1, 7, '2023-10-23 12:53:59', '2023-10-23 12:54:51', 0.00, 'inactivo');

-- Volcando estructura para tabla parqueadero_deec.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `identificacion` varchar(20) NOT NULL,
  `nombreusuario` varchar(255) NOT NULL,
  `correoelectronico` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `rol` enum('administrador','vendedor') NOT NULL,
  `estado` enum('activo','inactivo') NOT NULL DEFAULT 'activo',
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla parqueadero_deec.usuarios: ~5 rows (aproximadamente)
DELETE FROM `usuarios`;
INSERT INTO `usuarios` (`idusuario`, `identificacion`, `nombreusuario`, `correoelectronico`, `contrasena`, `rol`, `estado`) VALUES
	(1, '123456789', 'Admin1', 'admin', 'admin', 'administrador', 'activo'),
	(2, '1007212784', 'Vendedor1', 'este', '123', 'vendedor', 'activo'),
	(3, '43542322', 'admin1 A', 'admina', 'admina', 'administrador', 'activo'),
	(4, '74748342', 'Erick ', 'erick@gmail.com', '123', 'vendedor', 'activo'),
	(5, '45345345', 'Carlos', 'cc', 'cc', 'vendedor', 'inactivo');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
