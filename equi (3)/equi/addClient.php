<?php 
    header("Content-Type: application/json");
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
    echo $birthdate. $inscdate. $ensdate. $licedate. $fname. $lname. $inumber. $icode. $email. $password. $phone. $pricerate. $notes;
    
    $conn = mysqli_connect("localhost","root","","equi");
    $sqlR = " INSERT INTO clients (photo, birthDate, inscriptionDate, ensurenceValidity, licenceValidity, fName, lName, identityNumber, identityDoc, clientEmail, passwd, clientPhone, priceRate, notes) VALUES ('client-0001.png', '$birthdate', '$inscdate', '$ensdate', '$licedate', '$fname', '$lname', '$inumber', '$icode', '$email', '$password', '$phone', '$pricerate', '$notes') ";
    $response = array();
    $insert = mysqli_query($conn, $sqlR)  or die(mysqli_error($conn));
    if($insert){
        $response = array(
            'status' => true
        );
    } else{
        $response = array(
            'status' => false
        );
    }

     echo json_encode($response);
     mysqli_close($conn);
 ?>