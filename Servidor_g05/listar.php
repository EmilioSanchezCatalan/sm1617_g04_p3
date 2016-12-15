<?php

$lJson = "[";
$link = mysqli_connect('localhost:3306', 'root', '');
if (!$link) {
	die('Could not connect to MySQL: ' . mysql_error());
}

mysqli_select_db($link,'restaurante');

$sql = "SELECT * FROM mesa";

$sqljson = "SELECT * FROM menu";
$resultadojson = mysqli_query($link, $sqljson);
while ($row = mysqli_fetch_assoc($resultadojson)) {
	$lJson = $lJson."{\"cod_producto\":\"".$row["cod_producto"]."\",\"nombre\":\"". $row["nombre"]."\",\"imagen\":\"".$row["imagen"]."\",\"precio\":\"".$row["precio"]."\"},";
}
$lJson = substr($lJson, 0, strlen($lJson)-1);
$lJson = $lJson."]";
echo $lJson;
?>