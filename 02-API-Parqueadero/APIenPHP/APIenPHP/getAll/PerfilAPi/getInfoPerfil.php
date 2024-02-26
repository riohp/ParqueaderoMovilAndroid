<?php 
	header("Access-Control-Allow-Origin: * "); // Permite el acceso desde cualquier origen, o usa "http://localhost" si solo quieres permitirlo desde localhost.
	header("Access-Control-Allow-Methods: GET, POST");
	header("Access-Control-Allow-Headers: Content-Type");

    include '../../Conexion.php';

    if (!empty($_GET['documento'])) {
	    $consulta = $base_de_datos->query("SELECT usere.idusuario, usere.nombreusuario AS NombreVendedor, p.nombreparqueadero AS NombreParqueadero
                                            FROM usuarios usere
                                            INNER JOIN asignacionvendedorparqueadero avp ON usere.idusuario = avp.idusuario
                                            INNER JOIN parqueaderos p ON avp.idparqueadero = p.idparqueadero
                                            WHERE usere.identificacion = ".$_GET['documento']);
	    $datos = $consulta->fetchAll();
        if(sizeof($datos)>0){
            $respuesta = [
                'status' => true,
                'mesagge' => "OK##DATOS##GET",
                'data' => $datos
            ];
        }else{
            $respuesta = [
                'status' => false,
                'mesagge' => "ERROR USUARIO NO ENCONTRADO",
                'data' => $datos
            ];
        }
	}else if(!empty($_GET['user'])){
        $consulta = $base_de_datos->query("SELECT usere.idusuario, p.idparqueadero AS idpark, usere.nombreusuario AS NombreVendedor, p.nombreparqueadero AS NombreParqueadero
                                            FROM usuarios usere
                                            INNER JOIN asignacionvendedorparqueadero avp ON usere.idusuario = avp.idusuario
                                            INNER JOIN parqueaderos p ON avp.idparqueadero = p.idparqueadero
                                            WHERE usere.correoelectronico = '".$_GET['user']."'");
        $datos = $consulta->fetchAll();
        if(sizeof($datos)>0){
            $respuesta = [
                'status' => true,
                'mesagge' => "OK##DATOS##GET",
                'data' => $datos
            ];
        }else{
            $respuesta = [
                'status' => false,
                'mesagge' => "ERROR USUARIO NO ENCONTRADO",
                'data' => $datos
            ];
        }
    }else{
        $respuesta = [
                        'status' => false,
                        'mesagge' => "ERROR##DATOS##GET",
                        '$_GET' => $_GET,
                      ];
    }


    echo json_encode($respuesta);
    header('Content-Type: application/json');
?>

