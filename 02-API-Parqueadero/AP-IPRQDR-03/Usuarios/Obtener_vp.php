<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

$consultarParkingsURL = "http://localhost/APIenPHP/Parqueaderos/Obtener_P.php";
$respuesta_parkings = file_get_contents($consultarParkingsURL);
$datos_parkings = json_decode($respuesta_parkings, true);

// Modifica la consulta para filtrar usuarios con estado "activo"
$consulta = $base_de_datos->query("SELECT * FROM usuarios WHERE rol = 'vendedor' AND estado = 'activo'");
$datos = $consulta->fetchAll();

$respuesta['registros'] = $datos;
$respuesta['parkings'] = $datos_parkings['registros'];
echo json_encode($respuesta);
?>
