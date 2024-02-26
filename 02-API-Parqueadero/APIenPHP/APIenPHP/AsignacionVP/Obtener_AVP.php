<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

$consulta = $base_de_datos->query("SELECT * FROM asignacionvendedorparqueadero");
$datos = $consulta->fetchAll();

$respuesta['registros'] = $datos;
echo json_encode($respuesta);
?>