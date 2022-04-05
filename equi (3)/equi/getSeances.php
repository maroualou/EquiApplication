<?php 
    header("Content-Type: application/json");
    $clientId = $_GET["clientId"];
    $conn = mysqli_connect("localhost","root","","equi");
    $response = array();
    $sqlR = "select seanceID,startDate,durationMinut,userFName,userLName from seances,user where clientID = '$clientId' and seances.monitorID = user.userID and startDate > CURRENT_TIMESTAMP order by startDate";
    $seances = mysqli_query($conn, $sqlR);
    $resultSeances = mysqli_fetch_all($seances,MYSQLI_ASSOC);
    if(!$resultSeances){
        $response = array(
            'status' => false,
            'error' => 'client does\'t exiSt in the database'
        );
    }
    else{   
        $response = array(
            'message' => 'Success',
            'seances'=> $resultSeances );  
        }
     echo json_encode($response);
  ?>

