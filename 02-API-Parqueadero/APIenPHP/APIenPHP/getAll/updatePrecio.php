<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

include '../Conexion.php';

if (!empty($_POST['idparqueadero']) && !empty($_POST['preciomoto']) && !empty($_POST['preciauto']) && !empty($_POST['precicamioneta']) && !empty($_POST['preciocamion'])) {
    // Obtener parámetros
    $idparqueadero = $_POST['idparqueadero'];
    $preciomoto = $_POST['preciomoto'];
    $preciauto = $_POST['preciauto'];
    $precicamioneta = $_POST['precicamioneta'];
    $preciocamion = $_POST['preciocamion'];
    
    // Prepara la consulta y la ejecuta
    $consulta = $base_de_datos->prepare("UPDATE parqueaderos SET costotarifacarro = ?, costotarifamoto = ?, costotarifacamion = ?, costotarifacamioneta = ? WHERE idparqueadero = ?");
    $consulta->bindParam(1, $preciauto);
    $consulta->bindParam(2, $preciomoto);
    $consulta->bindParam(3, $preciocamion);
    $consulta->bindParam(4, $precicamioneta);
    $consulta->bindParam(5, $idparqueadero);
    $resultado = $consulta->execute();
    if ($resultado) {
        $respuesta = [
            'status' => true,
            'message' => "Precios actualizados correctamente",
        ];
    } else {
        $respuesta = [
            'status' => false,
            'message' => "Error al actualizar los precios",
        ];
    }
    echo json_encode($respuesta);
} else {
    $respuesta = [
        'status' => false,
        'message' => "ERROR##DATOS##POST",
        '$_POST' => $_POST,
    ];
    echo json_encode($respuesta);
}
?>


