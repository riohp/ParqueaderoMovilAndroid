<?php 
header("Access-Control-Allow-Origin: * "); // Permite el acceso desde cualquier origen, o usa "http://localhost" si solo quieres permitirlo desde localhost.
header("Access-Control-Allow-Methods: GET, POST");
header("Acess-Control-Allow-Headers: Content-Type");

include '../Conexion.php';


if(!empty($_POST['user'])){
    $consulta = $base_de_datos->query("SELECT * FROM usuarios WHERE correoelectronico = '".$_POST['user']."'");
    $datos = $consulta->fetchAll();
    if($datos != []){
        if($datos[0]['contrasena'] == $_POST['pass']){
            if($datos[0]['rol'] == 'admin'){
                $respuesta = [
                                'status' => true,
                                'rol' => 'administrador',
                                'mesagge' => "OK##DATOS##POST##CORRECTOS",
                                '$_POST' => $_POST
                              ];
            }else{
                $respuesta = [
                                'status' => true,
                                'rol' => 'vendedor',
                                'mesagge' => "OK##DATOS##POST##CORRECTOS",
                                '$_POST' => $_POST
                                ];
            }
            echo json_encode($respuesta);
        }else{
            $respuesta = [
                            'status' => false,
                            'mesagge' => "ERROR##PASS##INCORRECTA",
                            '$_POST' => $_POST
                          ];
            echo json_encode($respuesta);
        }
    }else{
        $respuesta = [
            'status' => false,
            'mesagge' => "ERROR##USER##INCORRECTO",
            '$_POST' => $_POST
          ];
        echo json_encode($respuesta);
    }
}else{
    $respuesta = [
                    'status' => false,
                    'mesagge' => "ERROR##DATOS##POST",
                    '$_POST' => $_POST
                  ];
    echo json_encode($respuesta);
}
?>