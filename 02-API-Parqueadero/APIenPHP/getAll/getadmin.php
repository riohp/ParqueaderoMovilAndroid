<?php 
    header("Access-Control-Allow-Origin: *"); // Permite el acceso desde cualquier origen, o usa "http://localhost" si solo quieres permitirlo desde localhost.
    header("Access-Control-Allow-Methods: GET, POST");
    header("Access-Control-Allow-Headers: Content-Type");

    include './../Conexion.php';
    //SUMIR UNA API DENTRO DE OTRA API UNA BENDICION
    $consultarVehiculosAyerURL = "http://localhost/APIenPHP/getAll/obtenerAll.php";
    $respuesta_vehiculos_ayer = file_get_contents($consultarVehiculosAyerURL);

    $datos_vehiculos_ayer = json_decode($respuesta_vehiculos_ayer, true);

    $consulta = $base_de_datos->query("SELECT 
        p.idparqueadero AS idpark, 
        p.nombreparqueadero AS NombreParqueadero,
        t.idtickets AS idticket,
        r.placa AS Placa,
        r.tipovehiculo AS TipoVehiculo,
        t.horaentrada AS HoraEntrada,
        t.estado AS Estado,
        t.horasalida AS HoraSalida,
        t.costototal AS TotalPago
        FROM parqueaderos p
        LEFT JOIN tickets t ON p.idparqueadero = t.idparqueadero
        LEFT JOIN registrarvehiculos r ON t.idvehiculo = r.idvehiculo");

if ($consulta) {
    $datos = $consulta->fetchAll();
    if (sizeof($datos) > 0) {
        // Incluye el número de vehículos que hubo ayer en la respuesta
        $respuesta = [
            'status' => true,
            'message' => "OK##DATOS##GET",
            'data' => $datos,
            'vehiculos_ayer' => $datos_vehiculos_ayer['vehiculos_ayer']  
        ];
    } else {
        $respuesta = [
            'status' => false,
            'message' => "ERROR PARQUEADEROS NO ENCONTRADOS",
            'data' => $datos
        ];
    }
} else {
    $respuesta = [
        'status' => false,
        'message' => "ERROR EN LA CONSULTA",
        'data' => null
    ];
}

echo json_encode($respuesta);
header('Content-Type: application/json');
?>
