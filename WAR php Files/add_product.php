<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");

$prname=$_GET['Pr_name'];
$prprice=$_GET['Pr_price'];
$prdesc=$_GET['Pr_desc'];
$prcat=$_GET['Pr_catname'];
$pcmp_ID=$_GET['Cmp_ID'];
$pcmp_ID=(int)$pcmp_ID;
$prprice=(int)$prprice;


$query = "insert into product (Pr_name, Pr_price, Pr_desc, Pr_catname, Cmp_ID) values ('".$prname."',".$prprice.",'".$prdesc."','".$prcat."',".$pcmp_ID.")";

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