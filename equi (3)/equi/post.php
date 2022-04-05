<?php 
  
  header("Content-Type: application/json");
  $response = array();

  if( isset($_POST['username']) && isset($_POST['password']) ) {

        $usernam = $_POST['username'];
        $passwor = $_POST['password'];
        
       
    
        if ($usernam == $passwor) {
          $response = array(
            'mssg' => $usernam." est correct"
             );         
        }else {
          $response = array(
            'error' => 'authentification incorret'
             );  
        }
      }
      else {
        $response = array(
          'error' => 'we didnt receive object'
           ); 
      }
        
   
   echo json_encode($response);
   

  ?>
