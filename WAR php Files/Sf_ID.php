<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");
$br_ID=$_GET['Br_ID'];
$br_ID=(int)$br_ID;
$cmp_Id=$_GET['Cmp_ID'];
$cmp_Id=(int)$cmp_Id;


$records = mysqli_query($conn,"select * from staff where (Cmp_ID='$cmp_Id' AND Br_ID='$br_ID'");

$data = array();
while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);

mysqli_close($conn);

?>