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
            if($datos[0]['rol'] == 'administrador' && $datos[0]['estado'] == 'activo'){
                $respuesta = [
                                'status' => true,
                                'rol' => 'administrador',
                                'estado' => 'activo',
                                'mesagge' => "OK##DATOS##POST##CORRECTOS",
                                '$_POST' => $_POST
                              ];
            }else if($datos[0]['rol'] == 'vendedor' && $datos[0]['estado'] == 'activo'){
                $respuesta = [
                                'status' => true,
                                'rol' => 'vendedor',
                                'mesagge' => "OK##DATOS##POST##CORRECTOS",
                                '$_POST' => $_POST
                                ];
            }else{
                $respuesta = [
                                'status' => false,
                                'mesagge' => "USUARIO INACTIVO",
                                '$_POST' => $_POST
                              ];
            }
            echo json_encode($respuesta);
        }else{
            $respuesta = [
                            'status' => false,
                            'mesagge' => "CONTRASEÑA INCORRECTA",
                            '$_POST' => $_POST
                          ];
            echo json_encode($respuesta);
        }
    }else{
        $respuesta = [
            'status' => false,
            'mesagge' => "ERROR USER INCORRECTO",
            '$_POST' => $_POST
          ];
        echo json_encode($respuesta);
    }
}else{
    $respuesta = [
                    'status' => false,
                    'mesaggeDEV' => "ERROR##DATOS##POST",
                    'mesagge' => "INGRESE DATOS",
                    '$_POST' => $_POST
                  ];
    echo json_encode($respuesta);
}
?>