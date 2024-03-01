<?php 
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: GET, POST");
    header("Access-Control-Allow-Headers: Content-Type");

    include '../Conexion.php';

    if(isset($_GET['nombreparqueadero']) && isset($_GET['direccion'])){
        $nombreparqueadero = $_GET['nombreparqueadero'];
        $direccion = $_GET['direccion'];

        $consulta = $base_de_datos->prepare("SELECT * FROM parqueaderos WHERE nombreparqueadero = ? AND direccion = ?");
        $consulta->execute([$nombreparqueadero, $direccion]);
        $datos = $consulta->fetchAll();

        if(count($datos) > 0){
            $respuesta = [
                "estado" => "existe",
                "mensaje" => "El parqueadero ya existe",
                "datos" => $datos[0]
            ];
        } else {
            $respuesta = [
                "estado" => "no_existe",
                "mensaje" => "El parqueadero no existe",
                "datos" => null
            ];
        }
    } else {
        $respuesta = [
            "estado" => "error",
            "mensaje" => "No se proporcionaron los parámetros 'nombreparqueadero' y 'direccion'"
        ];
    }

    echo json_encode($respuesta);
?>
