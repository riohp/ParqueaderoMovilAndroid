<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST' && !empty($_POST)) {
    // Si es una solicitud POST, accede a los datos a través de $_POST
    $idvehiculo = $_POST['idvehiculo'];
    $placa = $_POST['placa'];
    $tipovehiculo = $_POST['tipovehiculo'];
    $marca = $_POST['marca'];
    $modelo = $_POST['modelo'];
    $color = $_POST['color'];
    $idusuario = $_POST['idusuario'];
} elseif ($_SERVER['REQUEST_METHOD'] === 'GET' && !empty($_GET)) {
    // Si es una solicitud GET, accede a los datos a través de $_GET
    $idvehiculo = $_GET['idvehiculo'];
    $placa = $_GET['placa'];
    $tipovehiculo = $_GET['tipovehiculo'];
    $marca = $_GET['marca'];
    $modelo = $_GET['modelo'];
    $color = $_GET['color'];
    $idusuario = $_GET['idusuario'];
} else {
    // Si no se proporcionaron datos válidos, responde con un error
    $respuesta = [
        'status' => false,
        'message' => "ERROR##DATOS"
    ];
    echo json_encode($respuesta);
    exit();
}

try {
    $consulta = $base_de_datos->prepare("UPDATE registrarvehiculos SET placa = :placa, tipovehiculo = :tipovehiculo, marca = :marca, modelo = :modelo, color = :color, idusuario = :idusuario WHERE idvehiculo = :idvehiculo");

    $consulta->bindParam(':idvehiculo', $idvehiculo);
    $consulta->bindParam(':placa', $placa);
    $consulta->bindParam(':tipovehiculo', $tipovehiculo);
    $consulta->bindParam(':marca', $marca);
    $consulta->bindParam(':modelo', $modelo);
    $consulta->bindParam(':color', $color);
    $consulta->bindParam(':idusuario', $idusuario);

    $proceso = $consulta->execute();

    if ($proceso) {
        // Obtener los datos actualizados
        $consultaDatos = $base_de_datos->prepare("SELECT * FROM registrarvehiculos WHERE idvehiculo = :idvehiculo");
        $consultaDatos->bindParam(':idvehiculo', $idvehiculo);
        $consultaDatos->execute();
        $datosActualizados = $consultaDatos->fetch(PDO::FETCH_ASSOC);

        $respuesta = [
            'status' => true,
            'message' => "OK##CLIENT##UPDATE",
            'data' => $datosActualizados // Incluir los datos actualizados en la respuesta
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
?>
