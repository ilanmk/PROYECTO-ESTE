<?php
$con=mysqli_connect("localhost","root","","mydb");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
 else
{ 
$query = 'SELECT Calificacion,Nombre,Descripcion FROM mydb.rutinas';
$result = mysqli_query($con, $query);
$rutinas = array();

while($row = mysqli_fetch_array($result)) 
{ 
	$Calificacion=$row['Calificacion'];
	$Nombre=$row['Nombre'];
	$Descripcion=$row['Descripcion'];
	$objeto = array('Calificacion'=> $Calificacion,'Nombre'=>$Nombre,'Descripcion'=>$Descripcion);
    $rutinas[] = $objeto;
	
}
header("Content-Type: application/json");
	$json = json_encode($rutinas, JSON_PRETTY_PRINT);
	echo($json);
}
mysqli_close($con);
?>