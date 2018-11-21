<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");

$cname="hh";
$records = mysqli_query($conn,"SELECT Cmp_ID FROM company where Cmp_Name='hh'");

echo $records;

echo json_encode($records);

mysqli_close($conn);

?>