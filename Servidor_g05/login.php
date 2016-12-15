<html>
<head><title>Servicios Móviles - Pr&aacute;tica 2 by Juan Carlos Cuevas Mart&iacute;nez</title>
<meta name="lang" content="es" />
      <meta name="author" content="Juan Carlos Cuevas Mart&iacute;nez" />
      <meta name="organization" content="Universidad de Ja&eacute;n" />
      <meta name="locality" content="Linares, Espa&ntilde;a" />
      <meta name="keywords" content="Universidad de Ja&eacute;n,EPS Linares, Telecomunicaci&oacute;n,Telem&aacute;tica" />
      <meta name="description" content="Pr&aacute;tica 2 by Juan Carlos Cuevas Mart&iacute;nez" />
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

</head>

<body id="micrositeA">
<h1>Resultado del login</h1>
<?php  

																				 
if($_SERVER['REQUEST_METHOD'] == 'POST') {
	echo '<h2>Par&aacute;metros por post</h2>';
    $username = $_POST['user'];
    $password = $_POST['pass'];
  
  
    echo '<p>Usuario: '.$username.'</p>';
	echo '<p>Clave: '.$password.'</p>';
	$expires = mktime(date("h")+1, date("i"), date("s"), date("m")  , date("d"), date("Y"));
	
    echo 'SESION-ID='.$username.$password.'\r\n';

	
}else if($_SERVER['REQUEST_METHOD'] == 'GET') {
	echo '<h2>Par&aacute;metros por get</h2>';
    $username = $_GET['user'];
    $password = $_GET['pass'];
  
  
    echo '<p>Usuario: '.$username.'</p>';
	echo '<p>Clave: '.$password.'</p>';

    
    echo 'SESION-ID='.$username.$password.'\r\n';

}else
    echo '<p>Par&aacute;metros incorrectos</p>';
    
	

		 
?>
</body></html>
