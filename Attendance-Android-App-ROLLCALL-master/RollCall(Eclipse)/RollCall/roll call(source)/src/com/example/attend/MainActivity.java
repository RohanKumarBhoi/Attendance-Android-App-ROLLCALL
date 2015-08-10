package com.example.attend;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.attend.DatabaseHandler;



public class MainActivity extends Activity {

	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button blogin=(Button)findViewById(R.id.bLogin);
		final EditText username=(EditText)findViewById(R.id.uname);
		final EditText password=(EditText)findViewById(R.id.password);
		blogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String email=username.getText().toString();
				String pass=password.getText().toString();
				ArrayList<NameValuePair> NVpair=new ArrayList<NameValuePair>();
				NVpair.add(new BasicNameValuePair("email",email));
				//Toast.makeText(getApplicationContext(), "email="+email, Toast.LENGTH_LONG).show();
				NVpair.add(new BasicNameValuePair("pass",pass));
				//Toast.makeText(getApplicationContext(), "password="+pass, Toast.LENGTH_LONG).show();
				InputStream is=null;
				String result=null;
				StrictMode.setThreadPolicy(policy);
				try{
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/login2.php");
					httppost.setEntity(new UrlEncodedFormEntity(NVpair));
					HttpResponse response=httpclient.execute(httppost);
					HttpEntity entity=response.getEntity();
					is=entity.getContent();
					Log.e("log_tag", "connection success "+new UrlEncodedFormEntity(NVpair).toString());
					//Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.e("log_tag", "Error in http connection "+ex.toString());
        	        //Toast.makeText(getApplicationContext(), "Failed to Connect to Internet.", Toast.LENGTH_SHORT).show();
				}
				try
				{
					BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
					StringBuilder sb=new StringBuilder();
					String line=null;
					while((line=reader.readLine())!=null)
					{
						sb.append(line + "\n");
					}
					is.close();
					result=sb.toString();
					Log.e("log_tag", "converted- "+result);
					
				}
				catch(Exception e)
	        	{
	        	    Log.e("log_tag", "Error converting result "+e.toString());
	           	}

	   
	        	try
	        	{
	        		
	        		JSONObject json_data = new JSONObject(result);
	        		String msg= json_data.getString("msg");
	        		if(msg.equals("success"))
	        		{
	        			String name,sub1,sub2,sub3,sub4,sub5,sub6,sub7,sub8,sub9,sub10;
	        			name=json_data.getString("name");
	        			sub1=json_data.getString("sub1");
	        			sub2=json_data.getString("sub2");
	        			sub3=json_data.getString("sub3");
	        			sub4=json_data.getString("sub4");
	        			sub5=json_data.getString("sub5");
	        			sub6=json_data.getString("sub6");
	        			sub7=json_data.getString("sub7");
	        			sub8=json_data.getString("sub8");
	        			sub9=json_data.getString("sub9");
	        			sub10=json_data.getString("sub10");
	        			int count=0;
	        			if(sub1!="null" && sub1!="")
	        			{
	        				ArrayList<NameValuePair> tpair=new ArrayList<NameValuePair>();
	        				tpair.add(new BasicNameValuePair("table",sub1));
	        				StrictMode.setThreadPolicy(policy);
	        				try{
	        					HttpClient httpclient=new DefaultHttpClient();
	        					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/fetch.php");
	        					httppost.setEntity(new UrlEncodedFormEntity(tpair));
	        					HttpResponse response=httpclient.execute(httppost);
	        					HttpEntity entity=response.getEntity();
	        					is=entity.getContent();
	        					Log.e("log_tag", "table connection success "+new UrlEncodedFormEntity(tpair).toString());
	        					Toast.makeText(getApplicationContext(), sub1+" data fetched.", Toast.LENGTH_SHORT).show();
	        				}
	        				catch(Exception ex)
	        				{
	        					Log.e("log_tag", "Error in http connection "+ex.toString());
	                	        Toast.makeText(getApplicationContext(), sub1+" data fetch failure", Toast.LENGTH_SHORT).show();
	        				}
	        				
	        				try
	        				{
	        					BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        					StringBuilder sb=new StringBuilder();
	        					String line=null;
	        					while((line=reader.readLine())!=null)
	        					{
	        						sb.append(line + "\n");
	        					}
	        					is.close();
	        					result=sb.toString();
	        					Log.e("log_tag", "converted- "+result);
	        					
	        				}
	        				catch(Exception e)
	        	        	{
	        	        	    Log.e("log_tag", "Error converting result "+e.toString());
	        	           	}
	        				try
	        	        	{
	        	        		
	        	        		JSONObject json_tdata = new JSONObject(result);
	        	        		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        	        		db.resetTablewname(sub1);
		        				db.init(sub1);
		        				int rows=json_tdata.getInt("rows");
	        	        		for(int i=0;i<rows;++i)
	        	        		{
	        	        			String namei=json_tdata.getString("name"+String.valueOf(i));
	        	        			String rolli=json_tdata.getString("roll"+String.valueOf(i));
	        	        			db.ins_table(sub1,namei,rolli);
	        	        		}
	        	        		/*String temp=db.getStudents(sub1)[0];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
        	        			temp=db.getStudents(sub1)[1];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();*/
	        	        		//Cursor temp=db.getStudents(sub1);
	        	        		//while(temp.moveToNext())
	        	        		//Toast.makeText(getApplicationContext(), temp.getString(0), Toast.LENGTH_SHORT).show();
	        	        	}
	        				catch(JSONException e)
	        	        	{
	        	        	    Log.e("log_tag", "Error parsing data "+e.toString());
	        	        	    Toast.makeText(getApplicationContext(), "Parsing failure", Toast.LENGTH_SHORT).show();
	        	        	}
	        				
	        				count++;
	        			}
	        			if(sub2!="null" && sub2!="")
	        			{
	        				ArrayList<NameValuePair> tpair=new ArrayList<NameValuePair>();
	        				tpair.add(new BasicNameValuePair("table",sub2));
	        				StrictMode.setThreadPolicy(policy);
	        				try{
	        					HttpClient httpclient=new DefaultHttpClient();
	        					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/fetch.php");
	        					httppost.setEntity(new UrlEncodedFormEntity(tpair));
	        					HttpResponse response=httpclient.execute(httppost);
	        					HttpEntity entity=response.getEntity();
	        					is=entity.getContent();
	        					Log.e("log_tag", "table connection success "+new UrlEncodedFormEntity(tpair).toString());
	        					Toast.makeText(getApplicationContext(), sub2+" data fetched.", Toast.LENGTH_SHORT).show();
	        				}
	        				catch(Exception ex)
	        				{
	        					Log.e("log_tag", "Error in http connection "+ex.toString());
	                	        Toast.makeText(getApplicationContext(), sub2+" data fetch failure", Toast.LENGTH_SHORT).show();
	        				}
	        				
	        				try
	        				{
	        					BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        					StringBuilder sb=new StringBuilder();
	        					String line=null;
	        					while((line=reader.readLine())!=null)
	        					{
	        						sb.append(line + "\n");
	        					}
	        					is.close();
	        					result=sb.toString();
	        					Log.e("log_tag", "converted- "+result);
	        					
	        				}
	        				catch(Exception e)
	        	        	{
	        	        	    Log.e("log_tag", "Error converting result "+e.toString());
	        	           	}
	        				try
	        	        	{
	        	        		
	        	        		JSONObject json_tdata = new JSONObject(result);
	        	        		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        	        		db.resetTablewname(sub2);
		        				db.init(sub2);
		        				int rows=json_tdata.getInt("rows");
	        	        		for(int i=0;i<rows;++i)
	        	        		{
	        	        			String namei=json_tdata.getString("name"+String.valueOf(i));
	        	        			String rolli=json_tdata.getString("roll"+String.valueOf(i));
	        	        			db.ins_table(sub2,namei,rolli);
	        	        		}
	        	        		/*String temp=db.getStudents(sub1)[0];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
        	        			temp=db.getStudents(sub1)[1];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();*/
	        	        		//Cursor temp=db.getStudents(sub1);
	        	        		//while(temp.moveToNext())
	        	        		//Toast.makeText(getApplicationContext(), temp.getString(0), Toast.LENGTH_SHORT).show();
	        	        	}
	        				catch(JSONException e)
	        	        	{
	        	        	    Log.e("log_tag", "Error parsing data "+e.toString());
	        	        	    Toast.makeText(getApplicationContext(), "Parsing failure", Toast.LENGTH_SHORT).show();
	        	        	}
	        				
	        				count++;
	        			}
	        			if(sub3!="null" && sub3!="")
	        			{
	        				ArrayList<NameValuePair> tpair=new ArrayList<NameValuePair>();
	        				tpair.add(new BasicNameValuePair("table",sub3));
	        				StrictMode.setThreadPolicy(policy);
	        				try{
	        					HttpClient httpclient=new DefaultHttpClient();
	        					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/fetch.php");
	        					httppost.setEntity(new UrlEncodedFormEntity(tpair));
	        					HttpResponse response=httpclient.execute(httppost);
	        					HttpEntity entity=response.getEntity();
	        					is=entity.getContent();
	        					Log.e("log_tag", "table connection success "+new UrlEncodedFormEntity(tpair).toString());
	        					Toast.makeText(getApplicationContext(), sub3+" data fetched.", Toast.LENGTH_SHORT).show();
	        				}
	        				catch(Exception ex)
	        				{
	        					Log.e("log_tag", "Error in http connection "+ex.toString());
	                	        Toast.makeText(getApplicationContext(), sub3+" data fetch failure", Toast.LENGTH_SHORT).show();
	        				}
	        				
	        				try
	        				{
	        					BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        					StringBuilder sb=new StringBuilder();
	        					String line=null;
	        					while((line=reader.readLine())!=null)
	        					{
	        						sb.append(line + "\n");
	        					}
	        					is.close();
	        					result=sb.toString();
	        					Log.e("log_tag", "converted- "+result);
	        					
	        				}
	        				catch(Exception e)
	        	        	{
	        	        	    Log.e("log_tag", "Error converting result "+e.toString());
	        	           	}
	        				try
	        	        	{
	        	        		
	        	        		JSONObject json_tdata = new JSONObject(result);
	        	        		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        	        		db.resetTablewname(sub3);
		        				db.init(sub3);
		        				int rows=json_tdata.getInt("rows");
	        	        		for(int i=0;i<rows;++i)
	        	        		{
	        	        			String namei=json_tdata.getString("name"+String.valueOf(i));
	        	        			String rolli=json_tdata.getString("roll"+String.valueOf(i));
	        	        			db.ins_table(sub3,namei,rolli);
	        	        		}
	        	        		/*String temp=db.getStudents(sub1)[0];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
        	        			temp=db.getStudents(sub1)[1];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();*/
	        	        		//Cursor temp=db.getStudents(sub1);
	        	        		//while(temp.moveToNext())
	        	        		//Toast.makeText(getApplicationContext(), temp.getString(0), Toast.LENGTH_SHORT).show();
	        	        	}
	        				catch(JSONException e)
	        	        	{
	        	        	    Log.e("log_tag", "Error parsing data "+e.toString());
	        	        	    Toast.makeText(getApplicationContext(), "Parsing failure", Toast.LENGTH_SHORT).show();
	        	        	}
	        				
	        				count++;
	        			}
	        			if(sub4!="null" && sub4!="")
	        			{
	        				ArrayList<NameValuePair> tpair=new ArrayList<NameValuePair>();
	        				tpair.add(new BasicNameValuePair("table",sub4));
	        				StrictMode.setThreadPolicy(policy);
	        				try{
	        					HttpClient httpclient=new DefaultHttpClient();
	        					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/fetch.php");
	        					httppost.setEntity(new UrlEncodedFormEntity(tpair));
	        					HttpResponse response=httpclient.execute(httppost);
	        					HttpEntity entity=response.getEntity();
	        					is=entity.getContent();
	        					Log.e("log_tag", "table connection success "+new UrlEncodedFormEntity(tpair).toString());
	        					Toast.makeText(getApplicationContext(), sub4+" data fetched.", Toast.LENGTH_SHORT).show();
	        				}
	        				catch(Exception ex)
	        				{
	        					Log.e("log_tag", "Error in http connection "+ex.toString());
	                	        Toast.makeText(getApplicationContext(), sub4+" data fetch failure", Toast.LENGTH_SHORT).show();
	        				}
	        				
	        				try
	        				{
	        					BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        					StringBuilder sb=new StringBuilder();
	        					String line=null;
	        					while((line=reader.readLine())!=null)
	        					{
	        						sb.append(line + "\n");
	        					}
	        					is.close();
	        					result=sb.toString();
	        					Log.e("log_tag", "converted- "+result);
	        					
	        				}
	        				catch(Exception e)
	        	        	{
	        	        	    Log.e("log_tag", "Error converting result "+e.toString());
	        	           	}
	        				try
	        	        	{
	        	        		
	        	        		JSONObject json_tdata = new JSONObject(result);
	        	        		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        	        		db.resetTablewname(sub4);
		        				db.init(sub4);
		        				int rows=json_tdata.getInt("rows");
	        	        		for(int i=0;i<rows;++i)
	        	        		{
	        	        			String namei=json_tdata.getString("name"+String.valueOf(i));
	        	        			String rolli=json_tdata.getString("roll"+String.valueOf(i));
	        	        			db.ins_table(sub4,namei,rolli);
	        	        		}
	        	        		/*String temp=db.getStudents(sub1)[0];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
        	        			temp=db.getStudents(sub1)[1];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();*/
	        	        		//Cursor temp=db.getStudents(sub1);
	        	        		//while(temp.moveToNext())
	        	        		//Toast.makeText(getApplicationContext(), temp.getString(0), Toast.LENGTH_SHORT).show();
	        	        	}
	        				catch(JSONException e)
	        	        	{
	        	        	    Log.e("log_tag", "Error parsing data "+e.toString());
	        	        	    Toast.makeText(getApplicationContext(), "Parsing failure", Toast.LENGTH_SHORT).show();
	        	        	}
	        				
	        				count++;
	        			}
	        			if(sub5!="null" && sub5!="")
	        			{
	        				ArrayList<NameValuePair> tpair=new ArrayList<NameValuePair>();
	        				tpair.add(new BasicNameValuePair("table",sub5));
	        				StrictMode.setThreadPolicy(policy);
	        				try{
	        					HttpClient httpclient=new DefaultHttpClient();
	        					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/fetch.php");
	        					httppost.setEntity(new UrlEncodedFormEntity(tpair));
	        					HttpResponse response=httpclient.execute(httppost);
	        					HttpEntity entity=response.getEntity();
	        					is=entity.getContent();
	        					Log.e("log_tag", "table connection success "+new UrlEncodedFormEntity(tpair).toString());
	        					Toast.makeText(getApplicationContext(), sub5+" data fetched.", Toast.LENGTH_SHORT).show();
	        				}
	        				catch(Exception ex)
	        				{
	        					Log.e("log_tag", "Error in http connection "+ex.toString());
	                	        Toast.makeText(getApplicationContext(), sub5+" data fetch failure", Toast.LENGTH_SHORT).show();
	        				}
	        				
	        				try
	        				{
	        					BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        					StringBuilder sb=new StringBuilder();
	        					String line=null;
	        					while((line=reader.readLine())!=null)
	        					{
	        						sb.append(line + "\n");
	        					}
	        					is.close();
	        					result=sb.toString();
	        					Log.e("log_tag", "converted- "+result);
	        					
	        				}
	        				catch(Exception e)
	        	        	{
	        	        	    Log.e("log_tag", "Error converting result "+e.toString());
	        	           	}
	        				try
	        	        	{
	        	        		
	        	        		JSONObject json_tdata = new JSONObject(result);
	        	        		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        	        		db.resetTablewname(sub5);
		        				db.init(sub5);
		        				int rows=json_tdata.getInt("rows");
	        	        		for(int i=0;i<rows;++i)
	        	        		{
	        	        			String namei=json_tdata.getString("name"+String.valueOf(i));
	        	        			String rolli=json_tdata.getString("roll"+String.valueOf(i));
	        	        			db.ins_table(sub5,namei,rolli);
	        	        		}
	        	        		/*String temp=db.getStudents(sub1)[0];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
        	        			temp=db.getStudents(sub1)[1];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();*/
	        	        		//Cursor temp=db.getStudents(sub1);
	        	        		//while(temp.moveToNext())
	        	        		//Toast.makeText(getApplicationContext(), temp.getString(0), Toast.LENGTH_SHORT).show();
	        	        	}
	        				catch(JSONException e)
	        	        	{
	        	        	    Log.e("log_tag", "Error parsing data "+e.toString());
	        	        	    Toast.makeText(getApplicationContext(), "Parsing failure", Toast.LENGTH_SHORT).show();
	        	        	}
	        				
	        				count++;
	        			}
	        			if(sub6!="null" && sub6!="")
	        			{
	        				ArrayList<NameValuePair> tpair=new ArrayList<NameValuePair>();
	        				tpair.add(new BasicNameValuePair("table",sub6));
	        				StrictMode.setThreadPolicy(policy);
	        				try{
	        					HttpClient httpclient=new DefaultHttpClient();
	        					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/fetch.php");
	        					httppost.setEntity(new UrlEncodedFormEntity(tpair));
	        					HttpResponse response=httpclient.execute(httppost);
	        					HttpEntity entity=response.getEntity();
	        					is=entity.getContent();
	        					Log.e("log_tag", "table connection success "+new UrlEncodedFormEntity(tpair).toString());
	        					Toast.makeText(getApplicationContext(), sub6+" data fetched.", Toast.LENGTH_SHORT).show();
	        				}
	        				catch(Exception ex)
	        				{
	        					Log.e("log_tag", "Error in http connection "+ex.toString());
	                	        Toast.makeText(getApplicationContext(), sub6+" data fetch failure", Toast.LENGTH_SHORT).show();
	        				}
	        				
	        				try
	        				{
	        					BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        					StringBuilder sb=new StringBuilder();
	        					String line=null;
	        					while((line=reader.readLine())!=null)
	        					{
	        						sb.append(line + "\n");
	        					}
	        					is.close();
	        					result=sb.toString();
	        					Log.e("log_tag", "converted- "+result);
	        					
	        				}
	        				catch(Exception e)
	        	        	{
	        	        	    Log.e("log_tag", "Error converting result "+e.toString());
	        	           	}
	        				try
	        	        	{
	        	        		
	        	        		JSONObject json_tdata = new JSONObject(result);
	        	        		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        	        		db.resetTablewname(sub6);
		        				db.init(sub6);
		        				int rows=json_tdata.getInt("rows");
	        	        		for(int i=0;i<rows;++i)
	        	        		{
	        	        			String namei=json_tdata.getString("name"+String.valueOf(i));
	        	        			String rolli=json_tdata.getString("roll"+String.valueOf(i));
	        	        			db.ins_table(sub6,namei,rolli);
	        	        		}
	        	        		/*String temp=db.getStudents(sub1)[0];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
        	        			temp=db.getStudents(sub1)[1];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();*/
	        	        		//Cursor temp=db.getStudents(sub1);
	        	        		//while(temp.moveToNext())
	        	        		//Toast.makeText(getApplicationContext(), temp.getString(0), Toast.LENGTH_SHORT).show();
	        	        	}
	        				catch(JSONException e)
	        	        	{
	        	        	    Log.e("log_tag", "Error parsing data "+e.toString());
	        	        	    Toast.makeText(getApplicationContext(), "Parsing failure", Toast.LENGTH_SHORT).show();
	        	        	}
	        				
	        				count++;
	        			}
	        			if(sub7!="null" && sub7!="")
	        			{
	        				ArrayList<NameValuePair> tpair=new ArrayList<NameValuePair>();
	        				tpair.add(new BasicNameValuePair("table",sub7));
	        				StrictMode.setThreadPolicy(policy);
	        				try{
	        					HttpClient httpclient=new DefaultHttpClient();
	        					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/fetch.php");
	        					httppost.setEntity(new UrlEncodedFormEntity(tpair));
	        					HttpResponse response=httpclient.execute(httppost);
	        					HttpEntity entity=response.getEntity();
	        					is=entity.getContent();
	        					Log.e("log_tag", "table connection success "+new UrlEncodedFormEntity(tpair).toString());
	        					Toast.makeText(getApplicationContext(), sub7+" data fetched.", Toast.LENGTH_SHORT).show();
	        				}
	        				catch(Exception ex)
	        				{
	        					Log.e("log_tag", "Error in http connection "+ex.toString());
	                	        Toast.makeText(getApplicationContext(), sub7+" data fetch failure", Toast.LENGTH_SHORT).show();
	        				}
	        				
	        				try
	        				{
	        					BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        					StringBuilder sb=new StringBuilder();
	        					String line=null;
	        					while((line=reader.readLine())!=null)
	        					{
	        						sb.append(line + "\n");
	        					}
	        					is.close();
	        					result=sb.toString();
	        					Log.e("log_tag", "converted- "+result);
	        					
	        				}
	        				catch(Exception e)
	        	        	{
	        	        	    Log.e("log_tag", "Error converting result "+e.toString());
	        	           	}
	        				try
	        	        	{
	        	        		
	        	        		JSONObject json_tdata = new JSONObject(result);
	        	        		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        	        		db.resetTablewname(sub7);
		        				db.init(sub7);
		        				int rows=json_tdata.getInt("rows");
	        	        		for(int i=0;i<rows;++i)
	        	        		{
	        	        			String namei=json_tdata.getString("name"+String.valueOf(i));
	        	        			String rolli=json_tdata.getString("roll"+String.valueOf(i));
	        	        			db.ins_table(sub7,namei,rolli);
	        	        		}
	        	        		/*String temp=db.getStudents(sub1)[0];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
        	        			temp=db.getStudents(sub1)[1];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();*/
	        	        		//Cursor temp=db.getStudents(sub1);
	        	        		//while(temp.moveToNext())
	        	        		//Toast.makeText(getApplicationContext(), temp.getString(0), Toast.LENGTH_SHORT).show();
	        	        	}
	        				catch(JSONException e)
	        	        	{
	        	        	    Log.e("log_tag", "Error parsing data "+e.toString());
	        	        	    Toast.makeText(getApplicationContext(), "Parsing failure", Toast.LENGTH_SHORT).show();
	        	        	}
	        				
	        				count++;
	        			}
	        			if(sub8!="null" && sub8!="")
	        			{
	        				ArrayList<NameValuePair> tpair=new ArrayList<NameValuePair>();
	        				tpair.add(new BasicNameValuePair("table",sub8));
	        				StrictMode.setThreadPolicy(policy);
	        				try{
	        					HttpClient httpclient=new DefaultHttpClient();
	        					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/fetch.php");
	        					httppost.setEntity(new UrlEncodedFormEntity(tpair));
	        					HttpResponse response=httpclient.execute(httppost);
	        					HttpEntity entity=response.getEntity();
	        					is=entity.getContent();
	        					Log.e("log_tag", "table connection success "+new UrlEncodedFormEntity(tpair).toString());
	        					Toast.makeText(getApplicationContext(), sub8+" data fetched.", Toast.LENGTH_SHORT).show();
	        				}
	        				catch(Exception ex)
	        				{
	        					Log.e("log_tag", "Error in http connection "+ex.toString());
	                	        Toast.makeText(getApplicationContext(), sub8+" data fetch failure", Toast.LENGTH_SHORT).show();
	        				}
	        				
	        				try
	        				{
	        					BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        					StringBuilder sb=new StringBuilder();
	        					String line=null;
	        					while((line=reader.readLine())!=null)
	        					{
	        						sb.append(line + "\n");
	        					}
	        					is.close();
	        					result=sb.toString();
	        					Log.e("log_tag", "converted- "+result);
	        					
	        				}
	        				catch(Exception e)
	        	        	{
	        	        	    Log.e("log_tag", "Error converting result "+e.toString());
	        	           	}
	        				try
	        	        	{
	        	        		
	        	        		JSONObject json_tdata = new JSONObject(result);
	        	        		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        	        		db.resetTablewname(sub8);
		        				db.init(sub8);
		        				int rows=json_tdata.getInt("rows");
	        	        		for(int i=0;i<rows;++i)
	        	        		{
	        	        			String namei=json_tdata.getString("name"+String.valueOf(i));
	        	        			String rolli=json_tdata.getString("roll"+String.valueOf(i));
	        	        			db.ins_table(sub8,namei,rolli);
	        	        		}
	        	        		/*String temp=db.getStudents(sub1)[0];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
        	        			temp=db.getStudents(sub1)[1];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();*/
	        	        		//Cursor temp=db.getStudents(sub1);
	        	        		//while(temp.moveToNext())
	        	        		//Toast.makeText(getApplicationContext(), temp.getString(0), Toast.LENGTH_SHORT).show();
	        	        	}
	        				catch(JSONException e)
	        	        	{
	        	        	    Log.e("log_tag", "Error parsing data "+e.toString());
	        	        	    Toast.makeText(getApplicationContext(), "Parsing failure", Toast.LENGTH_SHORT).show();
	        	        	}
	        				
	        				count++;
	        			}
	        			if(sub9!="null" && sub9!="")
	        			{
	        				ArrayList<NameValuePair> tpair=new ArrayList<NameValuePair>();
	        				tpair.add(new BasicNameValuePair("table",sub9));
	        				StrictMode.setThreadPolicy(policy);
	        				try{
	        					HttpClient httpclient=new DefaultHttpClient();
	        					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/fetch.php");
	        					httppost.setEntity(new UrlEncodedFormEntity(tpair));
	        					HttpResponse response=httpclient.execute(httppost);
	        					HttpEntity entity=response.getEntity();
	        					is=entity.getContent();
	        					Log.e("log_tag", "table connection success "+new UrlEncodedFormEntity(tpair).toString());
	        					Toast.makeText(getApplicationContext(), sub9+" data fetched.", Toast.LENGTH_SHORT).show();
	        				}
	        				catch(Exception ex)
	        				{
	        					Log.e("log_tag", "Error in http connection "+ex.toString());
	                	        Toast.makeText(getApplicationContext(), sub9+" data fetch failure", Toast.LENGTH_SHORT).show();
	        				}
	        				
	        				try
	        				{
	        					BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        					StringBuilder sb=new StringBuilder();
	        					String line=null;
	        					while((line=reader.readLine())!=null)
	        					{
	        						sb.append(line + "\n");
	        					}
	        					is.close();
	        					result=sb.toString();
	        					Log.e("log_tag", "converted- "+result);
	        					
	        				}
	        				catch(Exception e)
	        	        	{
	        	        	    Log.e("log_tag", "Error converting result "+e.toString());
	        	           	}
	        				try
	        	        	{
	        	        		
	        	        		JSONObject json_tdata = new JSONObject(result);
	        	        		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        	        		db.resetTablewname(sub9);
		        				db.init(sub9);
		        				int rows=json_tdata.getInt("rows");
	        	        		for(int i=0;i<rows;++i)
	        	        		{
	        	        			String namei=json_tdata.getString("name"+String.valueOf(i));
	        	        			String rolli=json_tdata.getString("roll"+String.valueOf(i));
	        	        			db.ins_table(sub9,namei,rolli);
	        	        		}
	        	        		/*String temp=db.getStudents(sub1)[0];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
        	        			temp=db.getStudents(sub1)[1];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();*/
	        	        		//Cursor temp=db.getStudents(sub1);
	        	        		//while(temp.moveToNext())
	        	        		//Toast.makeText(getApplicationContext(), temp.getString(0), Toast.LENGTH_SHORT).show();
	        	        	}
	        				catch(JSONException e)
	        	        	{
	        	        	    Log.e("log_tag", "Error parsing data "+e.toString());
	        	        	    Toast.makeText(getApplicationContext(), "Parsing failure", Toast.LENGTH_SHORT).show();
	        	        	}
	        				
	        				count++;
	        			}
	        			if(sub10!="null" && sub10!="")
	        			{
	        				ArrayList<NameValuePair> tpair=new ArrayList<NameValuePair>();
	        				tpair.add(new BasicNameValuePair("table",sub10));
	        				StrictMode.setThreadPolicy(policy);
	        				try{
	        					HttpClient httpclient=new DefaultHttpClient();
	        					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/fetch.php");
	        					httppost.setEntity(new UrlEncodedFormEntity(tpair));
	        					HttpResponse response=httpclient.execute(httppost);
	        					HttpEntity entity=response.getEntity();
	        					is=entity.getContent();
	        					Log.e("log_tag", "table connection success "+new UrlEncodedFormEntity(tpair).toString());
	        					Toast.makeText(getApplicationContext(), sub10+" data fetched.", Toast.LENGTH_SHORT).show();
	        				}
	        				catch(Exception ex)
	        				{
	        					Log.e("log_tag", "Error in http connection "+ex.toString());
	                	        Toast.makeText(getApplicationContext(),sub10+" data fetch failure", Toast.LENGTH_SHORT).show();
	        				}
	        				
	        				try
	        				{
	        					BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        					StringBuilder sb=new StringBuilder();
	        					String line=null;
	        					while((line=reader.readLine())!=null)
	        					{
	        						sb.append(line + "\n");
	        					}
	        					is.close();
	        					result=sb.toString();
	        					Log.e("log_tag", "converted- "+result);
	        					
	        				}
	        				catch(Exception e)
	        	        	{
	        	        	    Log.e("log_tag", "Error converting result "+e.toString());
	        	           	}
	        				try
	        	        	{
	        	        		
	        	        		JSONObject json_tdata = new JSONObject(result);
	        	        		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        	        		db.resetTablewname(sub10);
		        				db.init(sub10);
		        				int rows=json_tdata.getInt("rows");
	        	        		for(int i=0;i<rows;++i)
	        	        		{
	        	        			String namei=json_tdata.getString("name"+String.valueOf(i));
	        	        			String rolli=json_tdata.getString("roll"+String.valueOf(i));
	        	        			db.ins_table(sub10,namei,rolli);
	        	        		}
	        	        		/*String temp=db.getStudents(sub1)[0];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
        	        			temp=db.getStudents(sub1)[1];
        	        			Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();*/
	        	        		//Cursor temp=db.getStudents(sub1);
	        	        		//while(temp.moveToNext())
	        	        		//Toast.makeText(getApplicationContext(), temp.getString(0), Toast.LENGTH_SHORT).show();
	        	        	}
	        				catch(JSONException e)
	        	        	{
	        	        	    Log.e("log_tag", "Error parsing data "+e.toString());
	        	        	    Toast.makeText(getApplicationContext(), "Parsing failure", Toast.LENGTH_SHORT).show();
	        	        	}
	        				
	        				count++;
	        			}
	        			DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	        			db.resetTables();
	        			db.addUser(name,sub1,sub2,sub3,sub4,sub5,sub6,sub7,sub8,sub9,sub10);
	        			Toast.makeText(getApplicationContext(), "Hello "+db.getUserDetails().get("name"), Toast.LENGTH_SHORT).show();
	        			Intent i=new Intent(getBaseContext(),dashboard.class);
	    				startActivity(i);
	    				finish();
	        			
	        		}
	        		else if(msg.equals("wrong password"))
	        		{
	        			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	        		}
	        		else if(msg.equals("nonexistant user"))
	        		{
	        			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	        		}
	        		else if(msg.equals("empty field"))
	        		{
	        			Toast.makeText(getApplicationContext(), msg+" for username or password", Toast.LENGTH_LONG).show();
	        		}
	        		//Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	        	    /*DatabaseHandler db=new DatabaseHandler(getApplicationContext());
	        	    if(db.getRowCount()>0)
	        	    	Toast.makeText(getApplicationContext(), String.valueOf(db.getRowCount()), Toast.LENGTH_LONG).show();*/
	        	}
	        	catch(JSONException e)
	        	{
	        	    Log.e("log_tag", "Error parsing data "+e.toString());
	        	    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
	        	}
			}
		});
	}
	
}
