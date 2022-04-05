<?php 
    header("Content-Type: application/json");
    $username = $_GET["username"];
    $password = $_GET["password"];
    $conn = mysqli_connect("localhost","root","","equi");
    
    $response = array();
    
     $sqlR = "select * from clients where clientEmail = '$username' and passwd = '$password'";
     $resultClientLogin = mysqli_query($conn, $sqlR);
     $rowClient = mysqli_fetch_assoc($resultClientLogin);
     if(!$rowClient){
        $sqlR = "select * from user where userEmail = '$username' and userPasswd = '$password'";
        $resultUserLogin = mysqli_query($conn, $sqlR);
        $rowUser = mysqli_fetch_assoc($resultUserLogin);
        if(!$rowUser){
            $response = array(
                'status' => false,
                'error' => 'your authentification does not exist in the data base'
            );
        }
        else{
            $userId = $rowUser["userID"];
            $sqlR = "select * from tasks where user_Fk = '$userId'";
            $tasks = mysqli_query($conn, $sqlR);
            $resultTasks = mysqli_fetch_all($tasks,MYSQLI_ASSOC);;
            $userType = "";
            $userType = $rowUser["userType"];
           
            if(strcmp($userType,"MONITOR")){    
               
                $response = array(
                    'message' => 'Success',
                    'isClient' => false,
                    'user' => $rowUser ,
                    'tasks'=> $resultTasks  );
            }else{
                
                $sqlR = "select * from seances where monitorID = '$userId'";
                $seances = mysqli_query($conn, $sqlR);
                $resultSeances = mysqli_fetch_all($seances,MYSQLI_ASSOC);
                $response = array(
                    'message' => 'Success',
                    'isClient' => false,
                    'user' => $rowUser,
                    'seances'=> $resultSeances ,
                    'tasks'=> $resultTasks );
            }
         }
     }else{
          $clientId = $rowClient["clientID"];
          $sqlR = "select * from seances where clientID = '$clientId'";
          $seances = mysqli_query($conn, $sqlR);
          $resultSeances = mysqli_fetch_all($seances,MYSQLI_ASSOC);
          $response = array(
            'message' => 'Success',
            'isClient' => true,
            'user' => $rowClient,
            'seances'=> $resultSeances );  
     }
     echo json_encode($response);
  ?>

