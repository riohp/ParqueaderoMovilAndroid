<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if (isset($_GET['nombreparqueadero'])) {
    $nombreParqueadero = $_GET['nombreparqueadero']; // Obtén el valor del parámetro
    $consulta = $base_de_datos->prepare("SELECT * FROM parqueaderos WHERE nombreparqueadero = :nombre");
    $consulta->bindParam(':nombre', $nombreParqueadero, PDO::PARAM_STR);
    $consulta->execute();
    $datos = $consulta->fetchAll();
    $respuesta = array();

    if (count($datos) > 0) {
        $respuesta['status'] = true;
        $respuesta['registros'] = $datos;
    } else {
        $respuesta['status'] = false;
        $respuesta['message'] = "Parqueadero no encontrado.";
    }
    
    echo json_encode($respuesta);
} else {
    $respuesta = array();
    $respuesta['status'] = false;
    $respuesta['message'] = "Parámetro 'nombreparqueadero' no proporcionado.";
    echo json_encode($respuesta);
}
?>
