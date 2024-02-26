<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

include '../Conexion.php';

if (!empty($_POST['idvehiculo']) && !empty($_POST['idparqueadero'])) {
    $idvehiculo = $_POST['idvehiculo'];
    $idparqueadero = $_POST['idparqueadero'];

    try {
        $consulta = $base_de_datos->prepare("INSERT INTO tickets (idparqueadero, idvehiculo, horasalida) VALUES (:idparqueadero, :idvehiculo, null)");

        $consulta->bindParam(':idparqueadero', $idparqueadero);
        $consulta->bindParam(':idvehiculo', $idvehiculo);

        $proceso = $consulta->execute();

        if ($proceso) {
            $respuesta = [
                'status' => true,
                'message' => "VEHICULO INGRESADO CORRECTAMENTE"
            ];
            echo json_encode($respuesta);
        } else {
            $respuesta = [
                'status' => false,
                'message' => "ERROR##CLIENT#"
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
