<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if (!empty($_POST['idticket']) && !empty($_POST['horasalida']) && !empty($_POST['costototal'])) {
    $idticket = $_POST['idticket'];
    $horasalida = $_POST['horasalida'];
    $costototal = $_POST['costototal'];

    try {
        $consulta = $base_de_datos->prepare("UPDATE tickets SET horasalida = :horasalida, costototal = :costototal WHERE idtickets = :idticket");

        $consulta->bindParam(':horasalida', $horasalida);
        $consulta->bindParam(':costototal', $costototal);
        $consulta->bindParam(':idticket', $idticket);

        $proceso = $consulta->execute();

        if ($proceso) {
            $respuesta = [
                'status' => true,
                'message' => "OK##CLIENT##UPDATE"
            ];
            echo json_encode($respuesta);
        } else {
            $respuesta = [
                'status' => false,
                'message' => "ERROR##CLIENT##UPDATE"
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
