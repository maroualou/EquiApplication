<?php 
    header("Content-Type: application/json");
    $clientId = $_POST["clientID"];
    $fname = $_POST["fName"];
    $lname = $_POST["lName"];
    $inumber = $_POST["identityNumber"];
    $icode = $_POST["identityDoc"];
    $email = $_POST["clientEmail"];
    $password = $_POST["passwd"];
    $phone = $_POST["clientPhone"];
    $birthdate = $_POST["birthDate"];
    $inscdate = $_POST["inscriptionDate"];
    $ensdate = $_POST["ensurenceValidity"];
    $licedate = $_POST["licenceValidity"];
    $pricerate = $_POST["priceRate"];
    $notes = $_POST["notes"];
    
    $conn = mysqli_connect("localhost","root","","equi");
    $response = array();
    $sqlR = " UPDATE clients SET birthDate='$birthdate', inscriptionDate='$inscdate', ensurenceValidity='$ensdate', licenceValidity='$licedate', fName= '$fname', lName='$lname', identityNumber='$inumber', identityDoc='$icode', clientEmail='$email', passwd='$password', clientPhone='$phone', priceRate='$pricerate', notes='$notes' WHERE clientID='$clientId' ";
    $update = mysqli_query($conn, $sqlR)  or die(mysqli_error($conn));
     if(mysqli_affected_rows($conn) ){ 
            
        $response = array(
            'status' => true
        );
    }
    else{   
            $response = array(
                'status' => false
            );
        }
     echo json_encode($response);
  ?>