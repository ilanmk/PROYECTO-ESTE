<?php
$con=mysqli_connect("localhost","root","","mydb");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
 else
{ 
$query = 'SELECT Nombre,Apellido FROM mydb.instruido';
$result = mysqli_query($con, $query);
$instruido = array();
while($row = mysqli_fetch_array($result)) 
{ 
	$Nombre=$row['Nombre'];
	$Apellido=$row['Apellido'];
	
	$objeto = array('Nombre'=> $Nombre, 'Apellido'=> $Apellido);
	
    $instruido[] = $objeto;
	
}
header("Content-Type: application/json");
	$json = json_encode($instruido, JSON_PRETTY_PRINT);
	echo($json);
}
mysqli_close($con);
?>