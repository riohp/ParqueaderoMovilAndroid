<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if (!empty($_GET['idparqueadero'])) {
    $idparqueadero = $_GET['idparqueadero'];

    try {
        $consulta = $base_de_datos->prepare("SELECT * FROM tickets WHERE idparqueadero = :idparqueadero");
        $consulta->bindParam(':idparqueadero', $idparqueadero);
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
                'message' => "ERROR##CLIENT##SELECT"
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
?>
