DROP parqueadero_deec;
CREATE DATABASE parqueadero_deec;
USE parqueadero_deec;

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
    FOREIGN KEY (idparqueadero) REFERENCES parqueaderos(idparqueadero)
);