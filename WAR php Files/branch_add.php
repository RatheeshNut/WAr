<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");

$bname=$_GET['Br_Name'];
$bphone1=$_GET['Br_Phn1'];
$bphone2=$_GET['Br_Phn2'];
$bemail=$_GET['Br_Email'];
$baddress=$_GET['Br_Adrs'];
$buname=$_GET['Br_Uname'];
$bpwd=$_GET['Br_Pwd'];
$cmp_id=$_GET['Cmp_ID'];
$bphone1=(int)$bphone1;
$bphone2=(int)$bphone2;
$cmp_id=(int)$cmp_id;


$query = "insert into branch (Br_Name, Br_Phn1, Br_Phn2, Br_Email, Br_Adrs, Br_Uname, Br_Pwd , Cmp_ID) values ('".$bname."',".$bphone1.",".$bphone2.",'".$bemail."','".$baddress."','".$buname."','".$bpwd."',".$cmp_id.")";

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