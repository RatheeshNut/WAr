<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");

$prname=$_GET['Pr_name'];
$prprice=$_GET['Pr_price'];
$prdesc=$_GET['Pr_desc'];
$prcat=$_GET['Pr_catname'];
$cmp_ID=$_GET['Cmp_ID'];
$Cmp_ID=(int)$Cmp_ID;
$prprice=(int)$prprice;


$query = "insert into product (Pr_name, Pr_price, Pr_desc, Pr_catname, Cmp_ID) values ('".$prname."',".$prprice.",'".$prdesc."',".$cmp_ID.")";

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