<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if (!empty($_POST['idvehiculo']) && !empty($_POST['idparqueadero']) && !empty($_POST['estado'])) {

    $idvehiculo = $_POST['idvehiculo'];
    $idparqueadero = $_POST['idparqueadero'];
    $estado = $_POST['estado'];

    try {
        $consulta = $base_de_datos->prepare("INSERT INTO vehiculosparqueadero (idvehiculo, idparqueadero, estado) VALUES (:idvehiculo, :idparqueadero, :estado)");

        $consulta->bindParam(':idvehiculo', $idvehiculo);
        $consulta->bindParam(':idparqueadero', $idparqueadero);
        $consulta->bindParam(':estado', $estado);

        $proceso = $consulta->execute();

        if ($proceso) {
            $respuesta = [
                'status' => true,
                'message' => "OK##CLIENT##INSERT"
            ];
            echo json_encode($respuesta);
        } else {
            $respuesta = [
                'status' => false,
                'message' => "ERROR##CLIENT##INSERT"
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
