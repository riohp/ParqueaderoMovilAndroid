<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

try {
    $consulta = $base_de_datos->prepare("SELECT SUM(costototal) AS total_costo FROM tickets WHERE estado = 'inactivo'");
    $consulta->execute();

    $datos = $consulta->fetch(PDO::FETCH_ASSOC);

    if ($datos['total_costo'] !== null) {
        $respuesta = [
            'status' => true,
            'message' => "OK##CLIENT##SELECT",
            'total_costo' => $datos['total_costo']
        ];
        echo json_encode($respuesta);
    } else {
        $respuesta = [
            'status' => false,
            'message' => "No hay registros inactivos"
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
