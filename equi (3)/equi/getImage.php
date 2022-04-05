<?php 
    header("Content-Type: application/json");
    $id = $_GET["clientId"];
    $conn = mysqli_connect("localhost","root","","equi");
    
    $response = array();
    
     $sqlR = "select photo from clients where clientID = $id ; ";
     $resultClientLogin = mysqli_query($conn, $sqlR);
     $rowClient = mysqli_fetch_assoc($resultClientLogin);
     if(!$rowClient){
            $response = array(
                'status' => false,
                'error' => 'id client does not exist in the data base'
            );
            echo json_encode($response);
        }
        else{
            $photo = file_get_contents('imageProfils/'. $rowClient["photo"]);
            header('content-type: image/png');
            echo $photo; 
     }
     
  ?>