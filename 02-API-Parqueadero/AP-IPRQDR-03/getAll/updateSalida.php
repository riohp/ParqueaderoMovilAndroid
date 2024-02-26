<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST");
header("Access-Control-Allow-Headers: Content-Type");

include '../Conexion.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['id_ticket']) && isset($_POST['precio_tipo_vehiculo'])) {
        $id_ticket = $_POST['id_ticket'];
        $precio_vehiculo = $_POST['precio_tipo_vehiculo'];
        // Verificar si el estado actual no es "inactivo"
        $consultaVerificar = "SELECT estado FROM tickets WHERE idtickets = :id_ticket";
        $stmtVerificar = $base_de_datos->prepare($consultaVerificar);
        $stmtVerificar->bindParam(':id_ticket', $id_ticket, PDO::PARAM_INT);
        $stmtVerificar->execute();
        $estadoActual = $stmtVerificar->fetchColumn();

        if ($estadoActual != 'inactivo') {
            $consulta = "UPDATE tickets 
            SET estado = 'inactivo', 
                horasalida = NOW(), 
                costototal = CEIL(TIMESTAMPDIFF(MINUTE, horaentrada, NOW()) / 60) * :precio_vehiculo 
                WHERE idtickets = :id_ticket";

            $stmt = $base_de_datos->prepare($consulta);
            $stmt->bindParam(':id_ticket', $id_ticket, PDO::PARAM_INT);
            $stmt->bindParam(':precio_vehiculo', $precio_vehiculo, PDO::PARAM_INT);

            $resultado = $stmt->execute();

            if ($resultado) {
                $respuesta = [
                    'status' => true,
                    'message' => 'Actualización exitosa.'
                ];
            } else {
                $respuesta = [
                    'status' => false,
                    'message' => 'Error al actualizar.'
                ];
            }
        } else {
            $respuesta = [
                'status' => false,
                'message' => 'El estado ya es inactivo. No se realizó la actualización.'
            ];
        }

        echo json_encode($respuesta);
    } else {
        $respuesta = [
            'status' => false,
            'message' => 'ID del ticket o multiplicador no proporcionados.'
        ];

        echo json_encode($respuesta);
    }
} else {
    $respuesta = [
        'status' => false,
        'message' => 'Método no permitido.'
    ];

    echo json_encode($respuesta);
}
