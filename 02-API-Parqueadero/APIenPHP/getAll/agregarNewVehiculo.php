<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

include '../Conexion.php';

if(!empty($_POST['placa']) && !empty($_POST['tpvehiculo']) && !empty($_POST['marca']) && !empty($_POST['modelo']) && !empty($_POST['color']) && !empty($_POST['idUser'])){
    $consulta = $base_de_datos->prepare("INSERT INTO registrarvehiculos(placa, tipovehiculo, marca, modelo, color, idusuario)
                                        VALUES (?, ?, ?, ?, ? ,?)");
    $consulta->bindParam(1, $_POST['placa']);
    $consulta->bindParam(2, $_POST['tpvehiculo']);
    $consulta->bindParam(3, $_POST['marca']);
    $consulta->bindParam(4, $_POST['modelo']);
    $consulta->bindParam(5, $_POST['color']);
    $consulta->bindParam(6, $_POST['idUser']);
    $resultado = $consulta->execute();
    if($resultado){
        $respuesta = [
            'status' => true,
            'message' => "Vehiculo agregado correctamente",
        ];
    }else{
        $respuesta = [
            'status' => false,
            'message' => "Error al agregar el vehiculo",
        ];
    }
}else{
    $respuesta = [
        'status' => false,
        'message' => "ERROR EN POST"
    ];
}
echo json_encode($respuesta);
?>