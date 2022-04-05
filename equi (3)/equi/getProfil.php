<?php 
    header("Content-Type: application/json");
    $id = $_GET["clientId"];
    $conn = mysqli_connect("localhost","root","","equi");
    
    $response = array();
    
     $sqlR = "select * from clients where clientID = $id ; ";
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