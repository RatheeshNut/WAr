<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");

$sname=$_GET['Sf_Name'];
$sphone=$_GET['Sf_phone'];
$semail=$_GET['Sf_email'];
$suname=$_GET['Sf_uname'];
$spwd=$_GET['Sf_pass'];
$cmp_ID=$_GET['Cmp_ID'];
$br_ID=$_GET['Br_ID'];
$sphone=(int)$bphone;
$Cmp_ID=(int)$Cmp_ID;
$Br_ID=(int)$Br_ID;


$query = "insert into staff (Sf_Name, Sf_phone, Sf_email, Sf_uname, Sf_pass, Cmp_ID, Br_ID) values ('".$sname."',".$sphone.",'".$semail."','".$suname."','".$spwd."',".$cmp_ID.",".$Br_ID.")";

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