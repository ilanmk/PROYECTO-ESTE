<?php
$con=mysqli_connect("localhost","root","","mydb");
if (mysqli_connect_errno()) 
{
	echo "Failed to connect to MySQL" . mysqli_connect_error();
}
else
{
	$string = file_get_contents('php://input'); 
	$objeto = json_decode($string, true);
	$query = "INSERT INTO instruido (Nombre,Apellido,Mail,Contrasena,Fecha,Peso,Altura,Sexo,Complicaciones,FueAlGym) values ('".$_GET["Nombre"]."','".$_GET["Apellido"]."','".$_GET["Mail"]."','".$_GET["Contrasena"]."','".$_GET["Fecha"]."','".$_GET["Peso"]."','".$_GET["Altura"]."','".$_GET["Sexo"]."','".$_GET["Complicaciones"]."','".$_GET["FueAlGym"]."')";
	var_dump($query);
	$stmt = $con->prepare($query);
	//$stmt->bind_param('ssssssssss',$objeto["Nombre"],$objeto["Apellido"],$objeto["Mail"],$objeto["Contraseña"],$objeto["Fecha"],$objeto["Peso"],$objeto["Altura"],$objeto["Sexo"],$objeto["Complicaciones"],$objeto["FueAlGym"]);
	$stmt->execute();
	$res = $stmt->get_result();
}
mysqli_close($con);
?>