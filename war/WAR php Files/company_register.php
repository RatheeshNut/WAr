<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");

$cname=$_GET['Cmp_Name'];
$cphone1=$_GET['Cmp_Phn1'];
$cphone2=$_GET['Cmp_Phn2'];
$cemail=$_GET['Cmp_Email'];
$caddress=$_GET['Cmp_Addrs'];
$ccategory=$_GET['Cmp_Cat'];
$cuname=$_GET['Cmp_Uname'];
$cpwd=$_GET['Cmp_Pwd'];
$cphone1=(int)$cphone1;
$cphone2=(int)$cphone2;


$query = "insert into company (Cmp_Name, Cmp_Phn1, Cmp_Phn2, Cmp_Email, Cmp_Addrs, Cmp_Cat, Cmp_Uname, Cmp_Pwd) values ('".$cname."',".$cphone1.",".$cphone2.",'".$cemail."','".$caddress."','".$ccategory."','".$cuname."','".$cpwd."')";

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