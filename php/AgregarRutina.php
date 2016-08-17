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
	$query = "INSERT INTO rutinas (Calificacion,Nombre,Descripcion) values ('".$_GET["Calificacion"]."','".$_GET["Nombre"]."','".$_GET["Descripcion"]."')";
	$stmt = $con->prepare($query);
	$stmt->execute();
	$res = $stmt->get_result();
}
mysqli_close($con);
?>