<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");

$cmpname=$_GET['Cmp_Uname'];


$records = mysqli_query($conn,"select * from company where Cmp_Uname='$cmpname'");

$data = array();
while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);

mysqli_close($conn);

?>