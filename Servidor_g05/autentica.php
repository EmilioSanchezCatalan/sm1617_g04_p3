<?php
error_reporting(0);
$auth = false;
$expires = mktime(date("H")+1, date("i"), date("s"), date("m")  , date("d"), date("Y"));
if($_SERVER['REQUEST_METHOD'] == 'GET') {
    $cod_mesa = $_GET['cod_mesa'];
    $numero_session = $_GET['num_session'];
}else
    echo 'ER';
$link = mysqli_connect('localhost:3306', 'root', '');
if (!$link) {
	die('Could not connect to MySQL: ' . mysql_error());
}

mysqli_select_db($link,'restaurante');

$sql = "SELECT * FROM mesa";
$resultado = mysqli_query($link, $sql);
while ($row = mysqli_fetch_assoc($resultado)) {

	if($row["cod_mesa"]==$cod_mesa and $row["num_sesion"]==$numero_session)
	{
		$auth = true;
	}
}
if ($auth == true){
	echo 'OK SESION-ID::'.$cod_mesa.base64_encode($numero_session).'&EXPIRES::'.date('Y:m:d H:i:s',$expires);
}else 
	echo "ER";
?>

