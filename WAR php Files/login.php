<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");


$uname=$_GET['log_Username'];
$pwd=$_GET['log_Pass'];
$logtype=$_GET['Log_Type'];


$query = "insert into login (log_Username, log_Pass, Log_Type) values ('".$uname."','".$pwd."','".$logtype."')";

if(mysqli_query($conn, $query))
{
     echo "success";
}
else
{
     echo "failed";
}

mysqli_close($conn);

?>