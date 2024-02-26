<?php

header("Access-Control-Allow-Origin: * "); // Permite el acceso desde cualquier origen, o usa "http://localhost" si solo quieres permitirlo desde localhost.
header("Access-Control-Allow-Methods: GET, POST");
header("Acess-Control-Allow-Headers: Content-Type");

$servidor = 'localhost';
$usuario = 'root';
$contrasena = '';
$nombre_de_base = 'parqueadero_deec';

try{
   $base_de_datos = new PDO("mysql:host=$servidor;dbname=$nombre_de_base", $usuario, $contrasena);
}catch(Exception $e){
   echo 'Ha surgido un error y no se puede conectar a la base de datos. Detalle: ' . $e->getMessage();
   exit;
}

