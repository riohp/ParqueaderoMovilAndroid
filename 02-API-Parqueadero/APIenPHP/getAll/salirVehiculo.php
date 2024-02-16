<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if (!empty($_GET['placa'])) {
    $placa = $_GET['placa'];
    try {
        $consulta = $base_de_datos->prepare("SELECT DATE_FORMAT(t.horaentrada, '%H:%i:%s') AS horaentrada, t.idtickets, v.marca, v.color, v.tipovehiculo FROM tickets AS t
            LEFT JOIN registrarvehiculos AS v ON t.idvehiculo = v.idvehiculo
            WHERE v.placa = :placa AND t.estado = 'activo'");
        $consulta->bindParam(':placa', $placa);
        $consulta->execute();

        $datos = $consulta->fetchAll(PDO::FETCH_ASSOC);

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
                'message' => "ERROR##CLIENT##SELECT",
                'data' => []
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
        'message' => "ERROR##DATOS##GET"
    ];
    echo json_encode($respuesta);
}
