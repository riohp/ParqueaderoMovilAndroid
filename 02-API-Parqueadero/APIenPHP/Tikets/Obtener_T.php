<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

try {
    $consulta = $base_de_datos->prepare("SELECT * FROM tickets WHERE estado = 'activo' ORDER BY idtickets DESC LIMIT 1");
    $consulta->execute();

    $datos = $consulta->fetch(PDO::FETCH_ASSOC);

    if ($datos) {
        $respuesta = [
            'status' => true,
            'message' => "OK##CLIENT##SELECT",
            'data' => $datos
        ];
        echo json_encode($respuesta);
    } else {
        $respuesta = [
            'status' => false,
            'message' => "No hay registros activos"
        ];
        echo json_encode($respuesta);
    }
} catch (Exception $e) {
    $respuesta = [
        'status' => false,
        'message' => "ERROR##SQL",
        'exception' => $e
    ];
    echo json_encode($respuesta);
}
?>
