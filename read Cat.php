<?php

$conn = mysqli_connect("localhost","root", "", "sampledb");


$records = mysqli_query($conn,"select * from category);

$data = array();

while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);

mysqli_close($conn);

?>