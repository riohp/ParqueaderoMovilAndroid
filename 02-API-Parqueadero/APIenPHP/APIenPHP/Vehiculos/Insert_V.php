<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if (!empty($_GET['placa']) && !empty($_GET['tipovehiculo']) && !empty($_GET['marca']) && !empty($_GET['modelo']) && !empty($_GET['color']) && !empty($_GET['idusuario'])) {

    if (!empty($_GET['placa'])) {
        $placa = $_GET['placa'];
        $tipovehiculo = $_GET['tipovehiculo'];
        $marca = $_GET['marca'];
        $modelo = $_GET['modelo'];
        $color = $_GET['color'];
        $idusuario = $_GET['idusuario'];
    } else {
        $placa = $_POST['placa'];
        $tipovehiculo = $_POST['tipovehiculo'];
        $marca = $_POST['marca'];
        $modelo = $_POST['modelo'];
        $color = $_POST['color'];
        $idusuario = $_POST['idusuario'];
    }

    try {
        $consulta = $base_de_datos->prepare("INSERT INTO registrarvehiculos (placa, tipovehiculo, marca, modelo, color, idusuario) VALUES (:placa, :tipovehiculo, :marca, :modelo, :color, :idusuario)");

        $consulta->bindParam(':placa', $placa);
        $consulta->bindParam(':tipovehiculo', $tipovehiculo);
        $consulta->bindParam(':marca', $marca);
        $consulta->bindParam(':modelo', $modelo);
        $consulta->bindParam(':color', $color);
        $consulta->bindParam(':idusuario', $idusuario);

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
