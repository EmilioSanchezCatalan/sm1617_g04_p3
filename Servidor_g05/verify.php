<?php
error_reporting(0);
$auth = false;
$temp = mktime(date("H"), date("i"), date("s"), date("m")  , date("d"), date("Y"));
$startdate = new DateTime(date("Y:m:d H:i:s",$temp));
$endate = null;
if($_SERVER['REQUEST_METHOD'] == 'GET') {
	$sesion_id = $_GET['SESION-ID'];
	$expires = $_GET['EXPIRES'];
	try{
		$endate = new DateTime($expires);
	}catch (Exception $ex){
	}
}else
	echo 'ER';

$link = mysqli_connect('localhost:3306', 'root', '') or die('No se puede conectar con el servidor');
if (!$link) {
	die('Could not connect to MySQL: ' . mysql_error());
}

mysqli_select_db($link,'restaurante') or die('No se puede conectar con la base de datos');

$sql = "SELECT * FROM mesa";
$resultado = mysqli_query($link, $sql);
while ($row = mysqli_fetch_assoc($resultado)) {

	try {
		if($row["cod_mesa"].base64_encode($row["num_sesion"])==$sesion_id and $startdate < $endate)
		{
			$auth = true;
		}
	}catch (Exception $ex){
		
	}
}
if ($auth == true){
	echo "OK";
}else
	echo "ER"
?>