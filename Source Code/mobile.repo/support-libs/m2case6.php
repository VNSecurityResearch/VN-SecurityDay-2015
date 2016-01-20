<?php
$host = "127.0.0.1";
$user = "apiuser";
$pass = "apipass";
$dbname = "your_db";
$conn = mysql_connect($host, $user, $pass);
$db_selected = mysql_select_db($dbname, $conn);
if (!$db_selected) {
    die ('Can\'t use db : ' . mysql_error());
}

$json = file_get_contents('php://input');
$POST = json_decode($json, true);

$username = addslashes(mysql_real_escape_string($POST['username']));
$password = addslashes(mysql_real_escape_string($POST['password']));    

$row = mysql_query("SELECT * FROM users WHERE username = '$username' AND password = '$password'");
if(mysql_num_rows($row) > 0){
    $token = createRandomKey();
    mysql_query("UPDATE users SET session = $token WHERE username = '$username' AND password = '$password'");
    echo json_encode(array("sessionToken"=>$token));
}

function createRandomKey(){
    return sha1(rand());
}
?>
