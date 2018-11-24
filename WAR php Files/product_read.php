<?php

$conn =  mysqli_connect("localhost","16mca021", "1186", "16mca021");
$cmp_ID=$_GET['Cmp_ID'];
$cmp_ID=(int)$cmp_ID;


$records = mysqli_query($conn,"SELECT * FROM product where Cmp_ID=".$cmp_ID);

$data = array();

while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);

mysqli_close($conn);

?>
	