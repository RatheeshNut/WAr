<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");

// $cmpname=$_GET['Cmp_name'];
$cmpname='y';

$records = mysqli_query($conn,"select Cmp_ID from company where Cmp_Uname='$cmpname'");

$data = $records;


echo json_encode($data);

mysqli_close($conn);

?>