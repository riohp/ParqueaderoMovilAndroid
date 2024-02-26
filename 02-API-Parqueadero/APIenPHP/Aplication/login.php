<?php 

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
                                'identificacion' => $datos[0]['identificacion'],
                                'idusuario' => $datos[0]['idusuario'],
                                'nombreusuario' => $datos[0]['nombreusuario'],
                                'mesagge' => "OK##DATOS##POST##CORRECTOS",
                                '$_POST' => $_POST
                              ];
            }else if($datos[0]['rol'] == 'vendedor' && $datos[0]['estado'] == 'activo'){
                        $respuesta = [
                                'status' => true,
                                'rol' => 'administrador',
                                'estado' => 'activo',
                                'identificacion' => $datos[0]['identificacion'],
                                'idusuario' => $datos[0]['idusuario'],
                                'nombreusuario' => $datos[0]['nombreusuario'],
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