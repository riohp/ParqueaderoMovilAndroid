<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST' || ($_SERVER['REQUEST_METHOD'] === 'GET' && !empty($_REQUEST))) {
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $idparqueadero = $_POST['idparqueadero'];
        $direccion = $_POST['direccion'];
        $costotarifacarro = $_POST['costotarifacarro'];
        $costotarifamoto = $_POST['costotarifamoto'];
        $costotarifacamion = $_POST['costotarifacamion'];
        $costotarifacamioneta = $_POST['costotarifacamioneta'];
    } else {
        $idparqueadero = $_REQUEST['idparqueadero'];
        $direccion = $_REQUEST['direccion'];
        $costotarifacarro = $_REQUEST['costotarifacarro'];
        $costotarifamoto = $_REQUEST['costotarifamoto'];
        $costotarifacamion = $_REQUEST['costotarifacamion'];
        $costotarifacamioneta = $_REQUEST['costotarifacamioneta'];
    }

    try {
        $consulta = $base_de_datos->prepare("UPDATE parqueaderos SET direccion = :direccion, costotarifacarro = :costotarifacarro, costotarifamoto = :costotarifamoto, costotarifacamion = :costotarifacamion, costotarifacamioneta = :costotarifacamioneta WHERE idparqueadero = :idparqueadero");

        $consulta->bindParam(':idparqueadero', $idparqueadero);
        $consulta->bindParam(':direccion', $direccion);
        $consulta->bindParam(':costotarifacarro', $costotarifacarro);
        $consulta->bindParam(':costotarifamoto', $costotarifamoto);
        $consulta->bindParam(':costotarifacamion', $costotarifacamion);
        $consulta->bindParam(':costotarifacamioneta', $costotarifacamioneta);

        $proceso = $consulta->execute();

        if ($proceso) {
            $respuesta = [
                'status' => true,
                'message' => "OK##PARQUEADERO##UPDATE"
            ];
            echo json_encode($respuesta);
        } else {
            $respuesta = [
                'status' => false,
                'message' => "ERROR##PARQUEADERO##UPDATE"
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
