<?php 
    header("Content-Type: application/json");
    $id = $_POST["adminId"];
    $conn = mysqli_connect("localhost","root","","equi");
    
    $response = array();
    
     $sqlR = "select * from user where userID = $id and userType = 'ADMIN'; ";
     $resultAdminLogin = mysqli_query($conn, $sqlR);
     $rowAdmin = mysqli_fetch_assoc($resultAdminLogin);
     if(!$rowAdmin){
            $response = array(
                'status' => false,
                'error' => 'your authentification does not exist in the data base'
            );
        }
        else{
            $sqlR = "select * from clients ; ";
            $resultClient = mysqli_query($conn, $sqlR);
            $resultClients = mysqli_fetch_all($resultClient,MYSQLI_ASSOC);
          $response = array(
            'message' => 'Success',
            'clients' => $resultClients );  
     }
     echo json_encode($response);
  ?>