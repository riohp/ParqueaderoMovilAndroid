
CREATE DATABASE prqdr_db_02;
USE prqdr_db_02;

CREATE TABLE parqueaderos (
    idparqueadero INT PRIMARY KEY AUTO_INCREMENT,
    nombreparqueadero VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    costotarifacarro DECIMAL(10, 2) NOT NULL,
    costotarifamoto DECIMAL(10, 2) NOT NULL,
    costotarifacamion DECIMAL(10, 2) NOT NULL,
    costotarifacamioneta DECIMAL(10, 2) NOT NULL
);

-- Tabla de usuarios
CREATE TABLE usuarios (
    idusuario INT PRIMARY KEY AUTO_INCREMENT,
    identificacion VARCHAR(20) NOT NULL,
    nombreusuario VARCHAR(255) NOT NULL,
    correoelectronico VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol ENUM('administrador', 'vendedor') NOT NULL,
    estado ENUM('activo', 'inactivo') NOT NULL DEFAULT 'activo'
);

-- Tabla de Asignación de usuarios a Parqueaderos
CREATE TABLE asignacionvendedorparqueadero (
    idasignacion INT PRIMARY KEY AUTO_INCREMENT,
    idparqueadero INT,
    idusuario INT,
    FOREIGN KEY (idparqueadero) REFERENCES parqueaderos(idparqueadero),
    FOREIGN KEY (idusuario) REFERENCES usuarios(idusuario)
);

CREATE TABLE registrarvehiculos (
    idvehiculo INT PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(20) NOT NULL,
    tipovehiculo ENUM('carro', 'moto', 'camion', 'camioneta') NOT NULL,
    marca VARCHAR(20) NOT NULL,
    modelo VARCHAR(20) NOT NULL,
    color VARCHAR(20) NOT NULL,
    idusuario INT,
    FOREIGN KEY (idusuario) REFERENCES usuarios(idusuario)
);

-- Tabla de Historial de Parqueo
CREATE TABLE tickets (
    idtickets INT PRIMARY KEY AUTO_INCREMENT,
    idparqueadero INT,
    idvehiculo INT,
    horaentrada TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    horasalida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    costototal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idvehiculo) REFERENCES registrarvehiculos(idvehiculo),
    FOREIGN KEY (idparqueadero) REFERENCES parqueaderos(idparqueadero),
    estado ENUM('activo', 'inactivo') NOT NULL
);

INSERT INTO `parqueaderos` (`idparqueadero`, `nombreparqueadero`, `direccion`, `costotarifacarro`, `costotarifamoto`, `costotarifacamion`, `costotarifacamioneta`) VALUES
	(1, 'Parqueadero A', 'Calle 123, Ciudad', 900.00, 700.00, 1200.00, 1600.00),
	(2, 'Parqueadero B', 'Avenida 456, Ciudad', 6.00, 3.00, 12.00, 9.60);

INSERT INTO `usuarios` (`idusuario`, `identificacion`, `nombreusuario`, `correoelectronico`, `contrasena`, `rol`, `estado`) VALUES
	(1, '123456789', 'Admin1', 'admin', 'admin', 'administrador', 'activo'),
	(2, '1007212784', 'Vendedor1', 'este', '123', 'vendedor', 'activo'),
	(3, '43542322', 'admin1 A', 'admina', 'admina', 'administrador', 'activo'),
	(4, '74748342', 'Erick ', 'erick@gmail.com', '123', 'vendedor', 'activo'),
	(5, '45345345', 'Carlos', 'cc', 'cc', 'vendedor', 'inactivo');

INSERT INTO `registrarvehiculos` (`idvehiculo`, `placa`, `tipovehiculo`, `marca`, `modelo`, `color`, `idusuario`) VALUES
	(1, 'XYZ988', 'carro', 'Toyota', 'Camry', 'Azul', 1),
	(2, 'XYZ987', 'moto', 'Honda', 'CBR600', 'Rojo', 1),
	(3, 'DEF456', 'camioneta', 'Ford', 'Escape', 'Verde', 2),
	(4, 'DFT345', 'moto', 'fuidfu', 'uidbfbidsif', 'nisidfnisd', 2),
	(5, '15156', 'carro', 'nissan', 'dasdadas', 'sdada', 2),
	(6, '211das', 'carro', '4324', 'sdfsf', 'fsfs', 2),
	(7, 'SAD213', 'camion', 'honda', 'civic', 'mate', 2),
	(8, 'XYZ988', 'camioneta', 'honda', 'civic', 'red', 2);

INSERT INTO `asignacionvendedorparqueadero` (`idasignacion`, `idparqueadero`, `idusuario`) VALUES
	(2, 2, 5),
	(11, 1, 2),
	(12, 2, 2),
	(13, 1, 5),
	(14, 1, 4);

INSERT INTO `tickets` (`idtickets`, `idparqueadero`, `idvehiculo`, `horaentrada`, `horasalida`, `costototal`, `estado`) VALUES
	(1, 1, 1, '2023-10-19 00:17:26', NULL, NULL, 'activo'),
	(2, 1, 2, '2023-10-19 02:30:17', '2023-10-19 02:32:21', 700.00, 'inactivo'),
	(3, 1, 2, '2023-10-19 05:15:51', '2023-10-19 20:42:02', 11200.00, 'inactivo'),
	(4, 1, 4, '2023-10-20 09:28:58', NULL, NULL, 'activo'),
	(5, 1, 6, '2023-10-20 15:24:24', '2023-10-23 12:55:26', 63000.00, 'inactivo'),
	(6, 1, 7, '2023-10-23 12:53:59', '2023-10-23 12:54:51', 0.00, 'inactivo');