<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

// Verificar si se están enviando datos por GET o POST
if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $idusuario = $_GET['idusuario'];
    $identificacion = $_GET['identificacion'];
    $nombreusuario = $_GET['nombreusuario'];
    $correoelectronico = $_GET['correoelectronico'];
    $contrasena = $_GET['contrasena'];
    $rol = $_GET['rol'];
    $estado = $_GET['estado'];
} elseif ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $idusuario = $_POST['idusuario'];
    $identificacion = $_POST['identificacion'];
    $nombreusuario = $_POST['nombreusuario'];
    $correoelectronico = $_POST['correoelectronico'];
    $contrasena = $_POST['contrasena'];
    $rol = $_POST['rol'];
    $estado = $_POST['estado'];
} else {
    // Método de solicitud no soportado
    $respuesta = [
        'status' => false,
        'message' => "ERROR##METHOD_NOT_SUPPORTED"
    ];
    echo json_encode($respuesta);
    exit();
}

try {
    $consulta = $base_de_datos->prepare("UPDATE usuarios SET identificacion = :ide, nombreusuario = :nom, correoelectronico = :cor, contrasena = :con, rol = :rol, estado = :est WHERE idusuario = :id");

    $consulta->bindParam(':id', $idusuario);
    $consulta->bindParam(':ide', $identificacion);
    $consulta->bindParam(':nom', $nombreusuario);
    $consulta->bindParam(':cor', $correoelectronico);
    $consulta->bindParam(':con', $contrasena);
    $consulta->bindParam(':rol', $rol);
    $consulta->bindParam(':est', $estado);

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
?>
