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
	$query = "UPDATE rutinas SET Calificacion=('".$_GET["Calificacion"]."'),Nombre=('".$_GET["Nombre"]."'),Descripcion=('".$_GET["Descripcion"]."') WHERE Nombre=('".$_GET["Nombre1"]."')";
	var_dump($query);
	$stmt = $con->prepare($query);
	$stmt->execute();
	$res = $stmt->get_result();
}
mysqli_close($con);
?>