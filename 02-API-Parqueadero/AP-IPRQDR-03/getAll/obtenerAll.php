<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

include '../Conexion.php';

date_default_timezone_set('America/Bogota');

$fecha_hoy = date('Y-m-d');
$fecha_ayer = date('Y-m-d', strtotime('-1 day'));

// Consulta para obtener información de los tickets
$consulta = $base_de_datos->prepare("SELECT rv.placa, rv.tipovehiculo, rv.marca, rv.modelo, tk.horaentrada, tk.estado, COALESCE(tk.horasalida, 'NO DISPONIBLE') AS horasalida, COALESCE(tk.costototal, 'NO DISPONIBLE') AS costototal 
    FROM tickets tk 
    LEFT JOIN registrarvehiculos rv ON tk.idvehiculo = rv.idvehiculo 
    WHERE (DATE(tk.horaentrada) = :fecha_hoy OR tk.estado = 'activo') AND tk.idparqueadero = :idparqueadero
    ORDER BY tk.horaentrada DESC");

$consulta->bindParam(':fecha_hoy', $fecha_hoy);
$consulta->bindParam(':idparqueadero', $_GET['idpark'], PDO::PARAM_INT);
$consulta->execute();

$datos = $consulta->fetchAll();

// Consulta para obtener el número de vehículos que hubo ayer
$consulta_ayer = $base_de_datos->prepare("SELECT COUNT(*) AS cantidad_vehiculos FROM tickets tk
    WHERE DATE(tk.horaentrada) = :fecha_ayer");

$consulta_ayer->bindParam(':fecha_ayer', $fecha_ayer);
$consulta_ayer->execute();

$resultado_ayer = $consulta_ayer->fetch(PDO::FETCH_ASSOC);

$respuesta = array(
    "registros" => $datos,
    "vehiculos_ayer" => $resultado_ayer['cantidad_vehiculos']
);

echo json_encode($respuesta);
?>
