<?php
	$con=mysql_connect("sql300.byethost.com","b7_15501502","qwerty");
	mysql_select_db("b7_15501502_attendance");
	if(isset($_REQUEST['subcode']) && isset($_REQUEST['date']) && isset($_REQUEST['num']) && $_REQUEST['subcode']!='' && $_REQUEST['date']!="" && $_REQUEST['num']!='')
	{
		$subcode=$_REQUEST['subcode'];
		$date=$_REQUEST['date'];
		$query="alter table `$subcode` add `$date` integer";
		$res=mysql_query($query);
                if($res==1)
                {
		$n=$_REQUEST['num'];
		for($i=0;$i<$n;++$i)
		{
			$roll=$_REQUEST["roll$i"];
			$val=$_REQUEST["val$i"];
			$query="update `$subcode` set `$date`=$val where `roll`='$roll'";
			mysql_query($query);
		}
		$response['msg']="success";
		echo json_encode($response);
                }
                else
                {
                        $response['msg']="Duplicate Date Entry";
                        echo json_encode($response);
                }
	}
	else
	{
		$response['msg']="missing field";
		echo json_encode($response);
	}
	mysql_close();
?>			