<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Obtiene los datos del POST
    $idparqueadero = isset($_POST['idparqueadero']) ? $_POST['idparqueadero'] : null;
    $idusuario = isset($_POST['idusuario']) ? $_POST['idusuario'] : null;

    if ($idparqueadero !== null && $idusuario !== null) {
        // Verifica si el vendedor ya está asignado al parqueadero
        $consulta = $base_de_datos->prepare("SELECT idusuario FROM asignacionvendedorparqueadero WHERE idparqueadero = ? AND idusuario = ?");
        $consulta->bindParam(1, $idparqueadero);
        $consulta->bindParam(2, $idusuario);
        $consulta->execute();

        if ($consulta->rowCount() > 0) {
            // El vendedor ya está asignado a este parqueadero
            $respuesta = array("error" => "El vendedor ya está asignado a este parqueadero.");
        } else {
            // Realiza la inserción en la tabla asignacionvendedorparqueadero
            $insercion = $base_de_datos->prepare("INSERT INTO asignacionvendedorparqueadero (idparqueadero, idusuario) VALUES (?, ?)");

            if ($insercion) {
                $insercion->bindParam(1, $idparqueadero);
                $insercion->bindParam(2, $idusuario);

                if ($insercion->execute()) {
                    $respuesta = array("mensaje" => "Vendedor asignado al parqueadero correctamente.");
                } else {
                    $respuesta = array("error" => "No se pudo insertar en la base de datos.");
                }
            } else {
                $respuesta = array("error" => "Error en la preparación de la consulta SQL.");
            }
        }
    } else {
        $respuesta = array("error" => "Los campos están vacíos o no se enviaron.");
    }

    echo json_encode($respuesta);
} else {
    echo json_encode(array("error" => "Método no permitido. Utiliza POST para asignar vendedores."));
}
?>
