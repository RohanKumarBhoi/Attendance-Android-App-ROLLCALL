<?php
	$con=mysql_connect("sql300.byethost.com","b7_15501502","qwerty");
	mysql_select_db("b7_15501502_attendance");
	if(isset($_REQUEST['email']) && isset($_REQUEST['pass']) && $_REQUEST['email']!='' && $_REQUEST['pass']!='')
	{
		$email=$_REQUEST['email'];
		$pass=$_REQUEST['pass'];
		$query="select * from profs where email='$email'";
		$res=mysql_query($query);
		if(mysql_num_rows($res)!=0)
		{
			$query="select * from profs where email='$email' and pass='$pass'";
			$res=mysql_query($query);
			if(mysql_num_rows($res)!=0)
			{
				$col=mysql_num_fields($res);
				$response['name']=mysql_result($res,0,2);
				$response['sub1']=mysql_result($res,0,3);
				$response['sub2']=mysql_result($res,0,4);
				$response['sub3']=mysql_result($res,0,5);
				$response['sub4']=mysql_result($res,0,6);
				$response['sub5']=mysql_result($res,0,7);
				$response['sub6']=mysql_result($res,0,8);
				$response['sub7']=mysql_result($res,0,9);
				$response['sub8']=mysql_result($res,0,10);
				$response['sub9']=mysql_result($res,0,11);
				$response['sub10']=mysql_result($res,0,12);
				$response['msg']="success";
				echo json_encode($response);
			}
			else
			{
				$response['email']='';
				$response['pass']='';
				$response['msg']="wrong password";
				echo json_encode($response);
			}
		}
		else
		{
			$response['email']='';
			$response['pass']='';
			$response['msg']="nonexistant user";
			echo json_encode($response);
		}
	}
	else
	{
		$response['email']='';
		$response['pass']='';
		$response['msg']="empty field";
		echo json_encode($response);
	}
	mysql_close();


?>