<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");


$records = mysqli_query($conn,"SELECT log_Username FROM login");

$data = array();

while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);

mysqli_close($conn);

?>