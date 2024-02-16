<?php
header("Access-Control-Allow-Origin: * ");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if (!empty($_GET['placa'])) {
    $placa = $_GET['placa'];

    // Utiliza una consulta preparada para evitar problemas de seguridad.
    $consulta = $base_de_datos->prepare("SELECT rv.placa, rv.tipovehiculo, tk.horaentrada, tk.estado, IFNULL(tk.horasalida, 'NO DISPONIBLE') AS horasalida, IFNULL(tk.costototal, 'NO DISPONIBLE') AS costototal FROM tickets tk LEFT JOIN registrarvehiculos rv ON tk.idvehiculo = rv.idvehiculo WHERE rv.placa = :placa");
    $consulta->bindParam(':placa', $placa, PDO::PARAM_STR);
    $consulta->execute();

    $datos = $consulta->fetchAll();
    $respuesta['registros'] = $datos;
    echo json_encode($respuesta);
} else {
    $respuesta = [
        'status' => false,
        'message' => "ERROR##DATOS##GET",
        '$_GET' => $_GET,
        '$_POST' => $_POST
    ];
    echo json_encode($respuesta);
}
?>
