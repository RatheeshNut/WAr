<?php

$conn = mysqli_connect("localhost","16mca021", "1186", "16mca021");

$cname=$_GET['cat_Name'];



$query = "insert into category (cat_Name) values ('".$cname."')";

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