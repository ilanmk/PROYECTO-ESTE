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
	$query = "UPDATE ejercicios SET Nombre=('".$_GET["Nombre"]."') WHERE Nombre=('".$_GET["Nombre1"]."')";
	$stmt = $con->prepare($query);
	//$stmt->bind_param('s', $ejercicios["Nombre"]);
	$stmt->execute();
	$res = $stmt->get_result();
}
mysqli_close($con);
?>