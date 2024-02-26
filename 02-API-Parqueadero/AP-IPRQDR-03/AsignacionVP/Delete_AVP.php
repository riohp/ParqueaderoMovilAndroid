<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if (!empty($_POST['idasignacion'])) {
    $idasignacion = $_POST['idasignacion'];

    try {
        $consulta = $base_de_datos->prepare("DELETE FROM asignacionvendedorparqueadero WHERE idasignacion = :idasignacion");

        $consulta->bindParam(':idasignacion', $idasignacion);

        $proceso = $consulta->execute();

        if ($proceso) {
            $respuesta = [
                'status' => true,
                'message' => "OK##CLIENT##DELETE",
                'idasignacion' => $idasignacion
            ];
            echo json_encode($respuesta);
        } else {
            $respuesta = [
                'status' => false,
                'message' => "ERROR##CLIENT##DELETE"
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
} else {
    $respuesta = [
        'status' => false,
        'message' => "ERROR##DATOS##POST"
    ];
    echo json_encode($respuesta);
}
?>
