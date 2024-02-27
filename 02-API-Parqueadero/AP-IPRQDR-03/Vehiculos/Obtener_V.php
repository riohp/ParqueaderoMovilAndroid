<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

// Función para responder con JSON
function respondWithJson($status, $message, $data = null) {
    $response = array(
        'status' => $status,
        'message' => $message,
        'data' => $data
    );
    echo json_encode($response);
    exit(); // Terminar la ejecución del script después de enviar la respuesta
}

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    // Si es una solicitud GET, verificar si se envió un idvehiculo
    if (!empty($_GET['idvehiculo'])) {
        $idvehiculo = $_GET['idvehiculo'];

        try {
            $consulta = $base_de_datos->prepare("SELECT * FROM registrarvehiculos WHERE idvehiculo = :idvehiculo");
            $consulta->bindParam(':idvehiculo', $idvehiculo);
            $consulta->execute();
            $datos = $consulta->fetch(PDO::FETCH_ASSOC);

            if ($datos) {
                respondWithJson(true, "OK##CLIENT##SELECT", $datos);
            } else {
                respondWithJson(false, "ERROR##CLIENT##SELECT");
            }
        } catch (Exception $e) {
            respondWithJson(false, "ERROR##SQL", $e->getMessage());
        }
    } else {
        respondWithJson(false, "ERROR##DATOS##GET");
    }
} elseif ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Si es una solicitud POST, verificar si se envió un idvehiculo
    if (!empty($_POST['idvehiculo'])) {
        $idvehiculo = $_POST['idvehiculo'];

        try {
            $consulta = $base_de_datos->prepare("SELECT * FROM registrarvehiculos WHERE idvehiculo = :idvehiculo");
            $consulta->bindParam(':idvehiculo', $idvehiculo);
            $consulta->execute();
            $datos = $consulta->fetch(PDO::FETCH_ASSOC);

            if ($datos) {
                respondWithJson(true, "OK##CLIENT##SELECT", $datos);
            } else {
                respondWithJson(false, "ERROR##CLIENT##SELECT");
            }
        } catch (Exception $e) {
            respondWithJson(false, "ERROR##SQL", $e->getMessage());
        }
    } else {
        respondWithJson(false, "ERROR##DATOS##POST");
    }
} else {
    respondWithJson(false, "ERROR##METODO_NO_PERMITIDO");
}
?>
