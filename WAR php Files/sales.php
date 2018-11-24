<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");

$sname=$_GET['Sale_prdname'];
$sprice=$_GET['Sale_prdprice'];
$scat=$_GET['Sale_cat'];
$scustname=$_GET['Sale_custname'];
$sphone=$_GET['Sale_custphn'];
$saddrs=$_GET['Sale_custadds'];
$ssdate=$_GET['Sale_date'];
$swdate=$_GET['Sale_warr'];
$sedate=$_GET['Sale_ext'];
$srdate=$_GET['Sale_remanid'];
$sf_ID=$_GET['Sf_ID'];
$cmp_ID=$_GET['Cmp_ID'];
$br_ID=$_GET['Br_ID'];
$pphone=(int)$pphone;
$Cmp_ID=(int)$Cmp_ID;
$Br_ID=(int)$Br_ID;
$Sf_ID=(int)$Sf_ID;


$query = "insert into sales (Sale_prdname, Sale_prdprice, Sale_cat, Sale_custname, Sale_custadds, Sale_custphn, Sale_date, Sale_warr, Sale_ext, Sale_remanid, Cmp_ID, Br_ID, Sf_ID) values ('".$sname."',".$sprice.",'".$scat."','".$scustname."','".$saddrs."',".$sphone.",'".$ssdate."','".$Sale_warr."','".$Sale_ext."','".$Sale_remanid."',".$cmp_ID.",".$Br_ID.",".$sf_ID.")";

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