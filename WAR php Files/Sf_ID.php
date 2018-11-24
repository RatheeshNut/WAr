<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");
$sfname=$_GET['Sf_uname'];


$records = mysqli_query($conn,"select * from staff where Sf_uname='$sfname'");

$data = array();
while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);

mysqli_close($conn);

?>