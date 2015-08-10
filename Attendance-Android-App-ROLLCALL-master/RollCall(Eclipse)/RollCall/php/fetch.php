<?php
	$con=mysql_connect("sql300.byethost.com","b7_15501502","qwerty");
	mysql_select_db("b7_15501502_attendance");
	if(isset($_REQUEST['table']) && $_REQUEST['table']!='')
	{
		$table=$_REQUEST['table'];
		$query="SELECT * FROM `$table` where 1";
		$res=mysql_query($query);
		$i=0;
		$row=mysql_num_rows($res);
		if($row==0)
		{
			$response['msg']="inexistant table";
			echo json_encode($response);
		}
		else
		{
			$response['msg']="success";
			$response['rows']=$row;
			for($i=0;$i<$row;++$i)
			{
				$response["name$i"]=mysql_result($res,$i,0);
				$response["roll$i"]=mysql_result($res,$i,1);
			}
			echo json_encode($response);
		}
	}
	else
	{
		$response['msg']="empty field";
		echo json_encode($response);
	}
?>
			
			