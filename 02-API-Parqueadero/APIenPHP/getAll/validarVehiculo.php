<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

include '../Conexion.php';

if (isset($_GET['placa']) && !empty($_GET['placa'])) {
    $placa = $_GET['placa'];

    // Utiliza una consulta preparada para evitar problemas de seguridad.
    $consulta = $base_de_datos->prepare("SELECT rv.*, t.estado AS estado_ticket
        FROM registrarvehiculos rv
        LEFT JOIN tickets t ON rv.idvehiculo = t.idvehiculo
        WHERE rv.placa = :placa
        ORDER BY t.idtickets DESC
        LIMIT 1");
    $consulta->bindParam(':placa', $placa, PDO::PARAM_STR);
    $consulta->execute();

    $datos = $consulta->fetch(PDO::FETCH_ASSOC);

    if (!empty($datos)) {
        if ($datos['estado_ticket'] === 'activo') {
            $respuesta = [
                'status' => false,
                'ingreso' => false,
                'message' => "El vehículo está dentro del parking",
            ];
        } else {
            $respuesta = [
                'status' => true,
                'message' => "OK",
                'data' => $datos,
            ];
        }
    } else {
        $respuesta = [
            'status' => false,
            'ingreso' => true,
            'message' => "ERROR PLACA NO REGISTRADA",
        ];
    }
} else {
    $respuesta = [
        'status' => false,
        'message' => "ERROR DATOS GET",
    ];
}

echo json_encode($respuesta);

?>
