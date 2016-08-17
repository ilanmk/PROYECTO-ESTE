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
	$query = "INSERT INTO rutinas (Nombre1,Nombre2,Nombre3,Nombre4,Nombre5,Nombre6) values ('".$_GET["Nombre1"]."','".$_GET["Nombre2"]."','".$_GET["Nombre3"]."','".$_GET["Nombre4"]."','".$_GET["Nombre5"]."','".$_GET["Nombre6"]."')";
	$stmt = $con->prepare($query);
	var_dump($query);
	// $stmt->bind_param('s', $ejercicios["Nombre"]);
	$stmt->execute();
	$res = $stmt->get_result();
}
mysqli_close($con);
?>