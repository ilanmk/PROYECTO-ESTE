<?php
$con=mysqli_connect("localhost","root","","mydb");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
 else
{ 
$query = 'SELECT Nombre FROM mydb.ejercicios';
$result = mysqli_query($con, $query);
$Ejercicios = array();

while($row = mysqli_fetch_array($result)) 
{
	$id=$row['Nombre'];
	
	
	$objeto = array('Nombre'=> $id);
	
    $Ejercicios[] = $objeto;
	
}
header("Content-Type: application/json");

	$json = json_encode($Ejercicios, JSON_PRETTY_PRINT);
	echo($json);
	
}
mysqli_close($con);
?>