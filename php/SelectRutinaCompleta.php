<?php
$con=mysqli_connect("localhost","root","","mydb");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
 else
{ 
$query = 'SELECT Nombre1,Nombre2,Nombre3,Nombre4,Nombre5,Nombre6 FROM mydb.rutinas';
$result = mysqli_query($con, $query);
$rutinas = array();

while($row = mysqli_fetch_array($result)) 
{
	$id=$row['Nombre1'];
	$id=$row['Nombre2'];
	$id=$row['Nombre3'];
	$id=$row['Nombre4'];
	$id=$row['Nombre5'];
	$id=$row['Nombre6'];
	
	
	$objeto = array('Nombre1'=> $Nombre1,'Nombre2'=> $Nombre2,'Nombre3'=> $Nombre3,'Nombre4'=> $Nombre4,'Nombre5'=> $Nombre5,'Nombre6'=> $Nombre6);
	
    $rutinas[] = $objeto;
	
}
header("Content-Type: application/json");

	$json = json_encode($rutinas, JSON_PRETTY_PRINT);
	echo($json);
	
}
mysqli_close($con);
?>