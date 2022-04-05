<?php 
    header("Content-Type: application/json");
    $username = $_POST["username"];
    $password = $_POST["password"];
    $conn = mysqli_connect("localhost","root","","equi");
    
    $response = array();
    
     $sqlR = "select * from user where userEmail = '$username' and userPasswd = '$password' and userType = 'ADMIN'";
     $resultClientLogin = mysqli_query($conn, $sqlR);
     $rowClient = mysqli_fetch_assoc($resultClientLogin);
     if(!$rowClient){
            $response = array(
                'status' => false,
                'error' => 'your authentification does not exist in the data base'
            );
        }
        else{
          $response = array(
            'message' => 'Success',
            'user' => $rowClient );  
     }
     echo json_encode($response);
  ?>

