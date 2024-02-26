<?php 
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: GET, POST");
    header("Access-Control-Allow-Headers: Content-Type");
    
    include '../Conexion.php';

    if (!empty($_POST['nombreparqueadero']) and !empty($_POST['direccion']) and !empty($_POST['costotarifacarro']) and !empty($_POST['costotarifamoto']) and !empty($_POST['costotarifacamion']) and !empty($_POST['costotarifacamioneta'])) {

        $nombreparqueadero = $_POST['nombreparqueadero'];
        $direccion = $_POST['direccion'];
        $costotarifacarro = $_POST['costotarifacarro'];
        $costotarifamoto = $_POST['costotarifamoto'];
        $costotarifacamion = $_POST['costotarifacamion'];
        $costotarifacamioneta = $_POST['costotarifacamioneta'];

        try {
            $consulta = $base_de_datos->prepare("INSERT INTO parqueaderos (nombreparqueadero, direccion, costotarifacarro, costotarifamoto, costotarifacamion, costotarifacamioneta) VALUES(:nom, :dir, :ccr, :cmt, :ccm, :cmt)");

            $consulta->bindParam(':nom', $nombreparqueadero);
            $consulta->bindParam(':dir', $direccion);
            $consulta->bindParam(':ccr', $costotarifacarro);
            $consulta->bindParam(':cmt', $costotarifamoto);
            $consulta->bindParam(':ccm', $costotarifacamion);
            $consulta->bindParam(':cmt', $costotarifacamioneta);

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