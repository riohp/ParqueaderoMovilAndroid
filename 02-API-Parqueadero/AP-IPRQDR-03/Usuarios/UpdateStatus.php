<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['idusuario'])) {
    $idusuario = $_POST['idusuario'];

    try {
        $consulta = $base_de_datos->prepare("UPDATE usuarios SET estado = 'inactivo' WHERE idusuario = :id");
        $consulta->bindParam(':id', $idusuario);

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
            'exception' => $e,
        ];
        echo json_encode($respuesta);
    }
} else {

    $respuesta = [
        'status' => false,
        'message' => "ERROR##ID_NOT_PROVIDED"
    ];
    echo json_encode($respuesta);
}
?>
