<?php 
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: GET, POST");
    header("Access-Control-Allow-Headers: Content-Type");
    
    include '../Conexion.php';

    if (!empty($_POST['identificacion']) and !empty($_POST['nombreusuario']) and !empty($_POST['correoelectronico']) and !empty($_POST['contrasena'])) {

        $identificacion = $_POST['identificacion'];
        $nombre = $_POST['nombreusuario'];
        $correo = $_POST['correoelectronico'];
        $contresenia = $_POST['contrasena'];
        $rol = $_POST['rol'];
        $estado = $_POST['estado'];

        try {
            $consulta = $base_de_datos->prepare("INSERT INTO usuarios (identificacion, nombreusuario, correoelectronico, contrasena, rol, estado) VALUES(:ide, :nom, :cor, :con, :rol, :est)");

            $consulta->bindParam(':ide', $identificacion);
            $consulta->bindParam(':nom', $nombre);
            $consulta->bindParam(':cor', $correo);
            $consulta->bindParam(':con', $contresenia);
            $consulta->bindParam(':rol', $rol);
            $consulta->bindParam(':est', $estado);

            $proceso = $consulta->execute();

            if( $proceso ){
                $respuesta = [
                                'status' => true,
                                'mesagge' => "OK##CLIENT##INSERT"
                            ];
                echo json_encode($respuesta);
            }else{
                $respuesta = [
                                'status' => false,
                                'mesagge' => "ERROR##CLIENT##INSERT"
                            ];
                echo json_encode($respuesta);
            }
        } catch (Exception $e) {
            $respuesta = [
                            'status' => false,
                            'mesagge' => "ERROR##SQL",
                            'exception' => $e
                        ];
            echo json_encode($respuesta);
        }
    }else{
        $respuesta = [
                        'status' => false,
                        'mesagge' => "ERROR##DATOS##POST"
                    ];
        echo json_encode($respuesta);
    }
?>