<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if (!empty($_POST['idparqueadero']) && !empty($_POST['idusuario'])) {

    $idparqueadero = $_POST['idparqueadero'];
    $idusuario = $_POST['idusuario'];

    try {
        $consulta = $base_de_datos->prepare("INSERT INTO asignacionvendedorparqueadero (Idparqueadero, idusuario) VALUES (:idparqueadero, :idusuario)");

        $consulta->bindParam(':idparqueadero', $idparqueadero);
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
