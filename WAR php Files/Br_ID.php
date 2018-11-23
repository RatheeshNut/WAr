<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");
$brname=$_GET['Br_Uname'];


$records = mysqli_query($conn,"select * from branch where Cmp_Uname='$brname'");

$data = array();
while($row = mysqli_fetch_assoc($records))
{
    $data = $row; 
}

echo json_encode($data);

mysqli_close($conn);

?>