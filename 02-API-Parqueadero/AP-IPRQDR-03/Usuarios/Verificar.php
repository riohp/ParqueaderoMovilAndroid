<?php 
    // Establecer cabeceras para permitir solicitudes desde cualquier origen
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: GET, POST");
    header("Access-Control-Allow-Headers: Content-Type");

    
    include '../Conexion.php';

  
    if (isset($_GET['identificacion'])) {
    
        $usuario = $_GET['identificacion'];

        $consulta = $base_de_datos->prepare("SELECT * FROM usuarios WHERE identificacion = ?");
        $consulta->execute([$usuario]);
        $datos = $consulta->fetchAll();

   
        if (count($datos) > 0) {
           
            $respuesta = [
                "estado" => "existe",
                "mensaje" => "El usuario ya existe",
                "datos" => $datos[0]
            ];
        } else {
            
            $respuesta = [
                "estado" => "no_existe",
                "mensaje" => "El usuario no existe",
                "datos" => null
            ];
        }
    } else {
        
        $respuesta = [
            "estado" => "error",
            "mensaje" => "No se proporcion el parametro 'usuario'"
        ];
    }

    echo json_encode($respuesta);
?>
