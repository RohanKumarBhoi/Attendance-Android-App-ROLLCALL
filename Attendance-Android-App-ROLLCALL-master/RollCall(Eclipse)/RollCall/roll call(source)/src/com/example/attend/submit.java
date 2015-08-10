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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attend.DatabaseHandler;

public class submit extends Activity{

	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit);
		Button sub1=(Button)findViewById(R.id.bsSub1);
		Button sub2=(Button)findViewById(R.id.bsSub2);
		Button sub3=(Button)findViewById(R.id.bsSub3);
		Button sub4=(Button)findViewById(R.id.bsSub4);
		Button sub5=(Button)findViewById(R.id.bsSub5);
		Button sub6=(Button)findViewById(R.id.bsSub6);
		Button sub7=(Button)findViewById(R.id.bsSub7);
		Button sub8=(Button)findViewById(R.id.bsSub8);
		Button sub9=(Button)findViewById(R.id.bsSub9);
		Button sub10=(Button)findViewById(R.id.bsSub10);
		final EditText date=(EditText)findViewById(R.id.bdate);
		final TextView sub=(TextView)findViewById(R.id.submstr);
		final LinearLayout ll=(LinearLayout)findViewById(R.id.sublayout);
		final DatabaseHandler db=new DatabaseHandler(getApplicationContext());
		if(!db.getUserDetails().get("sub1").equals("null"))
		{
			sub1.setText(db.getUserDetails().get("sub1"));
			sub1.setVisibility(0);
		}
		else
			sub1.setVisibility(8);
		if(!db.getUserDetails().get("sub2").equals("null"))
		{
			sub2.setText(db.getUserDetails().get("sub2"));
			sub2.setVisibility(0);
		}
		else
			sub2.setVisibility(8);
		if(!db.getUserDetails().get("sub3").equals("null"))
		{
			sub3.setText(db.getUserDetails().get("sub3"));
			sub3.setVisibility(0);
		}
		else
			sub3.setVisibility(8);
		if(!db.getUserDetails().get("sub4").equals("null"))
		{
			sub4.setText(db.getUserDetails().get("sub4"));
			sub4.setVisibility(0);
		}
		else
			sub4.setVisibility(8);
		if(!db.getUserDetails().get("sub5").equals("null"))
		{
			sub5.setText(db.getUserDetails().get("sub5"));
			sub5.setVisibility(0);
		}
		else
			sub5.setVisibility(8);
		if(!db.getUserDetails().get("sub6").equals("null"))
		{
			sub6.setText(db.getUserDetails().get("sub6"));
			sub6.setVisibility(0);
		}
		else
			sub6.setVisibility(8);
		if(!db.getUserDetails().get("sub7").equals("null"))
		{
			sub7.setText(db.getUserDetails().get("sub7"));
			sub7.setVisibility(0);
		}
		else
			sub7.setVisibility(8);
		if(!db.getUserDetails().get("sub8").equals("null"))
		{
			sub8.setText(db.getUserDetails().get("sub8"));
			sub8.setVisibility(0);
		}
		else
			sub8.setVisibility(8);
		if(!db.getUserDetails().get("sub9").equals("null"))
		{
			sub9.setText(db.getUserDetails().get("sub9"));
			sub9.setVisibility(0);
		}
		else
			sub9.setVisibility(8);
		if(!db.getUserDetails().get("sub10").equals("null"))
		{
			sub10.setText(db.getUserDetails().get("sub10"));
			sub10.setVisibility(0);
		}
		else
			sub10.setVisibility(8);
		if(db.get_theme()==0)
		{
			sub1.setTextColor(Color.BLACK);
			sub2.setTextColor(Color.BLACK);
			sub3.setTextColor(Color.BLACK);
			sub4.setTextColor(Color.BLACK);
			sub5.setTextColor(Color.BLACK);
			sub6.setTextColor(Color.BLACK);
			sub7.setTextColor(Color.BLACK);
			sub8.setTextColor(Color.BLACK);
			sub9.setTextColor(Color.BLACK);
			sub10.setTextColor(Color.BLACK);
			date.setTextColor(Color.BLACK);
			sub.setTextColor(Color.BLACK);
			ll.setBackground(this.getResources().getDrawable(R.drawable.backlight));
		}
		else
		{
			sub1.setTextColor(Color.WHITE);
			sub2.setTextColor(Color.WHITE);
			sub3.setTextColor(Color.WHITE);
			sub4.setTextColor(Color.WHITE);
			sub5.setTextColor(Color.WHITE);
			sub6.setTextColor(Color.WHITE);
			sub7.setTextColor(Color.WHITE);
			sub8.setTextColor(Color.WHITE);
			sub9.setTextColor(Color.WHITE);
			sub10.setTextColor(Color.WHITE);
			date.setTextColor(Color.WHITE);
			sub.setTextColor(Color.WHITE);
			ll.setBackground(this.getResources().getDrawable(R.drawable.backdark));
		}
		sub1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subcode=db.getUserDetails().get("sub1");
				int num=db.getRowCountwtn(subcode);
				String roll[]=new String[num];
				roll=db.getStudents(subcode);
				String val[]=new String[num];
				val=db.getAttendance(subcode);
				ArrayList<NameValuePair> NVpair=new ArrayList<NameValuePair>();
				NVpair.add(new BasicNameValuePair("date",date.getText().toString()));
				NVpair.add(new BasicNameValuePair("subcode",subcode));
				NVpair.add(new BasicNameValuePair("num",String.valueOf(num)));
				for(int i=0;i<num;++i)
				{
					NVpair.add(new BasicNameValuePair("roll"+i,roll[i]));
					NVpair.add(new BasicNameValuePair("val"+i,val[i]));
				}
				InputStream is=null;
				String result=null;
				StrictMode.setThreadPolicy(policy);
				try{
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/submit.php");
					httppost.setEntity(new UrlEncodedFormEntity(NVpair));
					HttpResponse response=httpclient.execute(httppost);
					HttpEntity entity=response.getEntity();
					is=entity.getContent();
					//Log.e("log_tag", "connection success "+new UrlEncodedFormEntity(NVpair).toString());
					Toast.makeText(getApplicationContext(), "Connection Established.", Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.e("log_tag", "Error in http connection "+ex.toString());
        	        Toast.makeText(getApplicationContext(), "Cannot connect to server.", Toast.LENGTH_SHORT).show();
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
					Log.e("log_tag", "converted submit msg- "+result);
					
				}
				catch(Exception e)
	        	{
	        	    Log.e("log_tag", "Error converting submit msg "+e.toString());
	           	}
				try
	        	{
	        		
	        		JSONObject json_data = new JSONObject(result);
	        		if(json_data.get("msg").equals("success"))
	        		{
	        			Log.e("log_tag", "submit data successful");
		        	    Toast.makeText(getApplicationContext(), "Submited successfully", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("Duplicate Date Entry"))
	        		{
	        			Log.e("log_tag", "duplicate date");
		        	    Toast.makeText(getApplicationContext(), "Duplicate Date Entry", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("missing field"))
	        		{
	        			Log.e("log_tag", "missing field");
		        	    Toast.makeText(getApplicationContext(), "Missing Date", Toast.LENGTH_SHORT).show();
	        		}
	        		else
	        		{
	        			Log.e("log_tag", "submit data failed");
		        	    Toast.makeText(getApplicationContext(), "Submission Failed", Toast.LENGTH_SHORT).show();
	        		}
	        	}
				catch(JSONException e)
	        	{
	        	    Log.e("log_tag", "Error parsing submit data "+e.toString());
	        	    Toast.makeText(getApplicationContext(), "Submit Error", Toast.LENGTH_SHORT).show();
	        	}
			}
		});
		sub2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subcode=db.getUserDetails().get("sub2");
				int num=db.getRowCountwtn(subcode);
				String roll[]=new String[num];
				roll=db.getStudents(subcode);
				String val[]=new String[num];
				val=db.getAttendance(subcode);
				ArrayList<NameValuePair> NVpair=new ArrayList<NameValuePair>();
				NVpair.add(new BasicNameValuePair("date",date.getText().toString()));
				NVpair.add(new BasicNameValuePair("subcode",subcode));
				NVpair.add(new BasicNameValuePair("num",String.valueOf(num)));
				for(int i=0;i<num;++i)
				{
					NVpair.add(new BasicNameValuePair("roll"+i,roll[i]));
					NVpair.add(new BasicNameValuePair("val"+i,val[i]));
				}
				InputStream is=null;
				String result=null;
				StrictMode.setThreadPolicy(policy);
				try{
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/submit.php");
					httppost.setEntity(new UrlEncodedFormEntity(NVpair));
					HttpResponse response=httpclient.execute(httppost);
					HttpEntity entity=response.getEntity();
					is=entity.getContent();
					//Log.e("log_tag", "connection success "+new UrlEncodedFormEntity(NVpair).toString());
					Toast.makeText(getApplicationContext(), "Connection Established", Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.e("log_tag", "Error in http connection "+ex.toString());
        	        Toast.makeText(getApplicationContext(), "Cannot connect to server.", Toast.LENGTH_SHORT).show();
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
					Log.e("log_tag", "converted submit msg- "+result);
					
				}
				catch(Exception e)
	        	{
	        	    Log.e("log_tag", "Error converting submit msg "+e.toString());
	           	}
				try
	        	{
	        		
	        		JSONObject json_data = new JSONObject(result);
	        		if(json_data.get("msg").equals("success"))
	        		{
	        			Log.e("log_tag", "submit data successful");
		        	    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("Duplicate Date Entry"))
	        		{
	        			Log.e("log_tag", "duplicate date");
		        	    Toast.makeText(getApplicationContext(), "Duplicate Date Entry", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("missing field"))
	        		{
	        			Log.e("log_tag", "missing field");
		        	    Toast.makeText(getApplicationContext(), "Missing Date", Toast.LENGTH_SHORT).show();
	        		}
	        		else
	        		{
	        			Log.e("log_tag", "submit data failed");
		        	    Toast.makeText(getApplicationContext(), "Submission Failed", Toast.LENGTH_SHORT).show();
	        		}
	        		
	        	}
				catch(JSONException e)
	        	{
	        	    Log.e("log_tag", "Error parsing submit data "+e.toString());
	        	    Toast.makeText(getApplicationContext(), "Submit Error", Toast.LENGTH_SHORT).show();
	        	}
			}
		});
		sub3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subcode=db.getUserDetails().get("sub3");
				int num=db.getRowCountwtn(subcode);
				String roll[]=new String[num];
				roll=db.getStudents(subcode);
				String val[]=new String[num];
				val=db.getAttendance(subcode);
				ArrayList<NameValuePair> NVpair=new ArrayList<NameValuePair>();
				NVpair.add(new BasicNameValuePair("date",date.getText().toString()));
				NVpair.add(new BasicNameValuePair("subcode",subcode));
				NVpair.add(new BasicNameValuePair("num",String.valueOf(num)));
				for(int i=0;i<num;++i)
				{
					NVpair.add(new BasicNameValuePair("roll"+i,roll[i]));
					NVpair.add(new BasicNameValuePair("val"+i,val[i]));
				}
				InputStream is=null;
				String result=null;
				StrictMode.setThreadPolicy(policy);
				try{
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/submit.php");
					httppost.setEntity(new UrlEncodedFormEntity(NVpair));
					HttpResponse response=httpclient.execute(httppost);
					HttpEntity entity=response.getEntity();
					is=entity.getContent();
					//Log.e("log_tag", "connection success "+new UrlEncodedFormEntity(NVpair).toString());
					Toast.makeText(getApplicationContext(), "Connection Established", Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.e("log_tag", "Error in http connection "+ex.toString());
        	        Toast.makeText(getApplicationContext(), "Cannot connect to server.", Toast.LENGTH_SHORT).show();
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
					Log.e("log_tag", "converted submit msg- "+result);
					
				}
				catch(Exception e)
	        	{
	        	    Log.e("log_tag", "Error converting submit msg "+e.toString());
	           	}
				try
	        	{
	        		
	        		JSONObject json_data = new JSONObject(result);
	        		if(json_data.get("msg").equals("success"))
	        		{
	        			Log.e("log_tag", "submit data successful");
		        	    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("Duplicate Date Entry"))
	        		{
	        			Log.e("log_tag", "duplicate date");
		        	    Toast.makeText(getApplicationContext(), "Duplicate Date Entry", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("missing field"))
	        		{
	        			Log.e("log_tag", "missing field");
		        	    Toast.makeText(getApplicationContext(), "Missing Date", Toast.LENGTH_SHORT).show();
	        		}
	        		else
	        		{
	        			Log.e("log_tag", "submit data failed");
		        	    Toast.makeText(getApplicationContext(), "Submission Failed", Toast.LENGTH_SHORT).show();
	        		}
	        	}
				catch(JSONException e)
	        	{
	        	    Log.e("log_tag", "Error parsing submit data "+e.toString());
	        	    Toast.makeText(getApplicationContext(), "Submit Error", Toast.LENGTH_SHORT).show();
	        	}
			}
		});
		sub4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subcode=db.getUserDetails().get("sub4");
				int num=db.getRowCountwtn(subcode);
				String roll[]=new String[num];
				roll=db.getStudents(subcode);
				String val[]=new String[num];
				val=db.getAttendance(subcode);
				ArrayList<NameValuePair> NVpair=new ArrayList<NameValuePair>();
				NVpair.add(new BasicNameValuePair("date",date.getText().toString()));
				NVpair.add(new BasicNameValuePair("subcode",subcode));
				NVpair.add(new BasicNameValuePair("num",String.valueOf(num)));
				for(int i=0;i<num;++i)
				{
					NVpair.add(new BasicNameValuePair("roll"+i,roll[i]));
					NVpair.add(new BasicNameValuePair("val"+i,val[i]));
				}
				InputStream is=null;
				String result=null;
				StrictMode.setThreadPolicy(policy);
				try{
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/submit.php");
					httppost.setEntity(new UrlEncodedFormEntity(NVpair));
					HttpResponse response=httpclient.execute(httppost);
					HttpEntity entity=response.getEntity();
					is=entity.getContent();
					//Log.e("log_tag", "connection success "+new UrlEncodedFormEntity(NVpair).toString());
					Toast.makeText(getApplicationContext(), "Connection Established", Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.e("log_tag", "Error in http connection "+ex.toString());
        	        Toast.makeText(getApplicationContext(), "Cannot connect to server.", Toast.LENGTH_SHORT).show();
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
					Log.e("log_tag", "converted submit msg- "+result);
					
				}
				catch(Exception e)
	        	{
	        	    Log.e("log_tag", "Error converting submit msg "+e.toString());
	           	}
				try
	        	{
	        		
	        		JSONObject json_data = new JSONObject(result);
	        		if(json_data.get("msg").equals("success"))
	        		{
	        			Log.e("log_tag", "submit data successful");
		        	    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("Duplicate Date Entry"))
	        		{
	        			Log.e("log_tag", "duplicate date");
		        	    Toast.makeText(getApplicationContext(), "Duplicate Date Entry", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("missing field"))
	        		{
	        			Log.e("log_tag", "missing field");
		        	    Toast.makeText(getApplicationContext(), "Missing Date", Toast.LENGTH_SHORT).show();
	        		}
	        		else
	        		{
	        			Log.e("log_tag", "submit data failed");
		        	    Toast.makeText(getApplicationContext(), "Submission Failed", Toast.LENGTH_SHORT).show();
	        		}
	        	}
				catch(JSONException e)
	        	{
	        	    Log.e("log_tag", "Error parsing submit data "+e.toString());
	        	    Toast.makeText(getApplicationContext(), "Submit Error", Toast.LENGTH_SHORT).show();
	        	}
			}
		});
		sub5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subcode=db.getUserDetails().get("sub5");
				int num=db.getRowCountwtn(subcode);
				String roll[]=new String[num];
				roll=db.getStudents(subcode);
				String val[]=new String[num];
				val=db.getAttendance(subcode);
				ArrayList<NameValuePair> NVpair=new ArrayList<NameValuePair>();
				NVpair.add(new BasicNameValuePair("date",date.getText().toString()));
				NVpair.add(new BasicNameValuePair("subcode",subcode));
				NVpair.add(new BasicNameValuePair("num",String.valueOf(num)));
				for(int i=0;i<num;++i)
				{
					NVpair.add(new BasicNameValuePair("roll"+i,roll[i]));
					NVpair.add(new BasicNameValuePair("val"+i,val[i]));
				}
				InputStream is=null;
				String result=null;
				StrictMode.setThreadPolicy(policy);
				try{
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/submit.php");
					httppost.setEntity(new UrlEncodedFormEntity(NVpair));
					HttpResponse response=httpclient.execute(httppost);
					HttpEntity entity=response.getEntity();
					is=entity.getContent();
					//Log.e("log_tag", "connection success "+new UrlEncodedFormEntity(NVpair).toString());
					Toast.makeText(getApplicationContext(), "Connection Established", Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.e("log_tag", "Error in http connection "+ex.toString());
        	        Toast.makeText(getApplicationContext(), "Cannot connect to server.", Toast.LENGTH_SHORT).show();
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
					Log.e("log_tag", "converted submit msg- "+result);
					
				}
				catch(Exception e)
	        	{
	        	    Log.e("log_tag", "Error converting submit msg "+e.toString());
	           	}
				try
	        	{
	        		
	        		JSONObject json_data = new JSONObject(result);
	        		if(json_data.get("msg").equals("success"))
	        		{
	        			Log.e("log_tag", "submit data successful");
		        	    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("Duplicate Date Entry"))
	        		{
	        			Log.e("log_tag", "duplicate date");
		        	    Toast.makeText(getApplicationContext(), "Duplicate Date Entry", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("missing field"))
	        		{
	        			Log.e("log_tag", "missing field");
		        	    Toast.makeText(getApplicationContext(), "Missing Date", Toast.LENGTH_SHORT).show();
	        		}
	        		else
	        		{
	        			Log.e("log_tag", "submit data failed");
		        	    Toast.makeText(getApplicationContext(), "Submission Failed", Toast.LENGTH_SHORT).show();
	        		}
	        	}
				catch(JSONException e)
	        	{
	        	    Log.e("log_tag", "Error parsing submit data "+e.toString());
	        	    Toast.makeText(getApplicationContext(), "Submit Error", Toast.LENGTH_SHORT).show();
	        	}
			}
		});
		sub6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subcode=db.getUserDetails().get("sub6");
				int num=db.getRowCountwtn(subcode);
				String roll[]=new String[num];
				roll=db.getStudents(subcode);
				String val[]=new String[num];
				val=db.getAttendance(subcode);
				ArrayList<NameValuePair> NVpair=new ArrayList<NameValuePair>();
				NVpair.add(new BasicNameValuePair("date",date.getText().toString()));
				NVpair.add(new BasicNameValuePair("subcode",subcode));
				NVpair.add(new BasicNameValuePair("num",String.valueOf(num)));
				for(int i=0;i<num;++i)
				{
					NVpair.add(new BasicNameValuePair("roll"+i,roll[i]));
					NVpair.add(new BasicNameValuePair("val"+i,val[i]));
				}
				InputStream is=null;
				String result=null;
				StrictMode.setThreadPolicy(policy);
				try{
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/submit.php");
					httppost.setEntity(new UrlEncodedFormEntity(NVpair));
					HttpResponse response=httpclient.execute(httppost);
					HttpEntity entity=response.getEntity();
					is=entity.getContent();
					//Log.e("log_tag", "connection success "+new UrlEncodedFormEntity(NVpair).toString());
					Toast.makeText(getApplicationContext(), "Connection Established", Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.e("log_tag", "Error in http connection "+ex.toString());
        	        Toast.makeText(getApplicationContext(), "Cannot connect to server.", Toast.LENGTH_SHORT).show();
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
					Log.e("log_tag", "converted submit msg- "+result);
					
				}
				catch(Exception e)
	        	{
	        	    Log.e("log_tag", "Error converting submit msg "+e.toString());
	           	}
				try
	        	{
	        		
	        		JSONObject json_data = new JSONObject(result);
	        		if(json_data.get("msg").equals("success"))
	        		{
	        			Log.e("log_tag", "submit data successful");
		        	    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("Duplicate Date Entry"))
	        		{
	        			Log.e("log_tag", "duplicate date");
		        	    Toast.makeText(getApplicationContext(), "Duplicate Date Entry", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("missing field"))
	        		{
	        			Log.e("log_tag", "missing field");
		        	    Toast.makeText(getApplicationContext(), "Missing Date", Toast.LENGTH_SHORT).show();
	        		}
	        		else
	        		{
	        			Log.e("log_tag", "submit data failed");
		        	    Toast.makeText(getApplicationContext(), "Submission Failed", Toast.LENGTH_SHORT).show();
	        		}
	        	}
				catch(JSONException e)
	        	{
	        	    Log.e("log_tag", "Error parsing submit data "+e.toString());
	        	    Toast.makeText(getApplicationContext(), "Submit Error", Toast.LENGTH_SHORT).show();
	        	}
			}
		});
		sub7.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subcode=db.getUserDetails().get("sub7");
				int num=db.getRowCountwtn(subcode);
				String roll[]=new String[num];
				roll=db.getStudents(subcode);
				String val[]=new String[num];
				val=db.getAttendance(subcode);
				ArrayList<NameValuePair> NVpair=new ArrayList<NameValuePair>();
				NVpair.add(new BasicNameValuePair("date",date.getText().toString()));
				NVpair.add(new BasicNameValuePair("subcode",subcode));
				NVpair.add(new BasicNameValuePair("num",String.valueOf(num)));
				for(int i=0;i<num;++i)
				{
					NVpair.add(new BasicNameValuePair("roll"+i,roll[i]));
					NVpair.add(new BasicNameValuePair("val"+i,val[i]));
				}
				InputStream is=null;
				String result=null;
				StrictMode.setThreadPolicy(policy);
				try{
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/submit.php");
					httppost.setEntity(new UrlEncodedFormEntity(NVpair));
					HttpResponse response=httpclient.execute(httppost);
					HttpEntity entity=response.getEntity();
					is=entity.getContent();
					//Log.e("log_tag", "connection success "+new UrlEncodedFormEntity(NVpair).toString());
					Toast.makeText(getApplicationContext(), "Connection Established", Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.e("log_tag", "Error in http connection "+ex.toString());
        	        Toast.makeText(getApplicationContext(), "Cannot connect to server.", Toast.LENGTH_SHORT).show();
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
					Log.e("log_tag", "converted submit msg- "+result);
					
				}
				catch(Exception e)
	        	{
	        	    Log.e("log_tag", "Error converting submit msg "+e.toString());
	           	}
				try
	        	{
	        		
	        		JSONObject json_data = new JSONObject(result);
	        		if(json_data.get("msg").equals("success"))
	        		{
	        			Log.e("log_tag", "submit data successful");
		        	    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("Duplicate Date Entry"))
	        		{
	        			Log.e("log_tag", "duplicate date");
		        	    Toast.makeText(getApplicationContext(), "Duplicate Date Entry", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("missing field"))
	        		{
	        			Log.e("log_tag", "missing field");
		        	    Toast.makeText(getApplicationContext(), "Missing Date", Toast.LENGTH_SHORT).show();
	        		}
	        		else
	        		{
	        			Log.e("log_tag", "submit data failed");
		        	    Toast.makeText(getApplicationContext(), "Submission Failed", Toast.LENGTH_SHORT).show();
	        		}
	        	}
				catch(JSONException e)
	        	{
	        	    Log.e("log_tag", "Error parsing submit data "+e.toString());
	        	    Toast.makeText(getApplicationContext(), "Submit Error", Toast.LENGTH_SHORT).show();
	        	}
			}
		});
		sub8.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subcode=db.getUserDetails().get("sub8");
				int num=db.getRowCountwtn(subcode);
				String roll[]=new String[num];
				roll=db.getStudents(subcode);
				String val[]=new String[num];
				val=db.getAttendance(subcode);
				ArrayList<NameValuePair> NVpair=new ArrayList<NameValuePair>();
				NVpair.add(new BasicNameValuePair("date",date.getText().toString()));
				NVpair.add(new BasicNameValuePair("subcode",subcode));
				NVpair.add(new BasicNameValuePair("num",String.valueOf(num)));
				for(int i=0;i<num;++i)
				{
					NVpair.add(new BasicNameValuePair("roll"+i,roll[i]));
					NVpair.add(new BasicNameValuePair("val"+i,val[i]));
				}
				InputStream is=null;
				String result=null;
				StrictMode.setThreadPolicy(policy);
				try{
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/submit.php");
					httppost.setEntity(new UrlEncodedFormEntity(NVpair));
					HttpResponse response=httpclient.execute(httppost);
					HttpEntity entity=response.getEntity();
					is=entity.getContent();
					//Log.e("log_tag", "connection success "+new UrlEncodedFormEntity(NVpair).toString());
					Toast.makeText(getApplicationContext(), "Connection Established", Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.e("log_tag", "Error in http connection "+ex.toString());
        	        Toast.makeText(getApplicationContext(), "Cannot connect to server.", Toast.LENGTH_SHORT).show();
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
					Log.e("log_tag", "converted submit msg- "+result);
					
				}
				catch(Exception e)
	        	{
	        	    Log.e("log_tag", "Error converting submit msg "+e.toString());
	           	}
				try
	        	{
	        		
	        		JSONObject json_data = new JSONObject(result);
	        		if(json_data.get("msg").equals("success"))
	        		{
	        			Log.e("log_tag", "submit data successful");
		        	    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("Duplicate Date Entry"))
	        		{
	        			Log.e("log_tag", "duplicate date");
		        	    Toast.makeText(getApplicationContext(), "Duplicate Date Entry", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("missing field"))
	        		{
	        			Log.e("log_tag", "missing field");
		        	    Toast.makeText(getApplicationContext(), "Missing Date", Toast.LENGTH_SHORT).show();
	        		}
	        		else
	        		{
	        			Log.e("log_tag", "submit data failed");
		        	    Toast.makeText(getApplicationContext(), "Submission Failed", Toast.LENGTH_SHORT).show();
	        		}
	        	}
				catch(JSONException e)
	        	{
	        	    Log.e("log_tag", "Error parsing submit data "+e.toString());
	        	    Toast.makeText(getApplicationContext(), "Submit Error", Toast.LENGTH_SHORT).show();
	        	}
			}
		});
		sub9.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subcode=db.getUserDetails().get("sub9");
				int num=db.getRowCountwtn(subcode);
				String roll[]=new String[num];
				roll=db.getStudents(subcode);
				String val[]=new String[num];
				val=db.getAttendance(subcode);
				ArrayList<NameValuePair> NVpair=new ArrayList<NameValuePair>();
				NVpair.add(new BasicNameValuePair("date",date.getText().toString()));
				NVpair.add(new BasicNameValuePair("subcode",subcode));
				NVpair.add(new BasicNameValuePair("num",String.valueOf(num)));
				for(int i=0;i<num;++i)
				{
					NVpair.add(new BasicNameValuePair("roll"+i,roll[i]));
					NVpair.add(new BasicNameValuePair("val"+i,val[i]));
				}
				InputStream is=null;
				String result=null;
				StrictMode.setThreadPolicy(policy);
				try{
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/submit.php");
					httppost.setEntity(new UrlEncodedFormEntity(NVpair));
					HttpResponse response=httpclient.execute(httppost);
					HttpEntity entity=response.getEntity();
					is=entity.getContent();
					//Log.e("log_tag", "connection success "+new UrlEncodedFormEntity(NVpair).toString());
					Toast.makeText(getApplicationContext(), "Connection Established", Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.e("log_tag", "Error in http connection "+ex.toString());
        	        Toast.makeText(getApplicationContext(), "Cannot connect to server.", Toast.LENGTH_SHORT).show();
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
					Log.e("log_tag", "converted submit msg- "+result);
					
				}
				catch(Exception e)
	        	{
	        	    Log.e("log_tag", "Error converting submit msg "+e.toString());
	           	}
				try
	        	{
	        		
	        		JSONObject json_data = new JSONObject(result);
	        		if(json_data.get("msg").equals("success"))
	        		{
	        			Log.e("log_tag", "submit data successful");
		        	    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("Duplicate Date Entry"))
	        		{
	        			Log.e("log_tag", "duplicate date");
		        	    Toast.makeText(getApplicationContext(), "Duplicate Date Entry", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("missing field"))
	        		{
	        			Log.e("log_tag", "missing field");
		        	    Toast.makeText(getApplicationContext(), "Missing Date", Toast.LENGTH_SHORT).show();
	        		}
	        		else
	        		{
	        			Log.e("log_tag", "submit data failed");
		        	    Toast.makeText(getApplicationContext(), "Submission Failed", Toast.LENGTH_SHORT).show();
	        		}
	        	}
				catch(JSONException e)
	        	{
	        	    Log.e("log_tag", "Error parsing submit data "+e.toString());
	        	    Toast.makeText(getApplicationContext(), "Submit Error", Toast.LENGTH_SHORT).show();
	        	}
			}
		});
		sub10.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subcode=db.getUserDetails().get("sub10");
				int num=db.getRowCountwtn(subcode);
				String roll[]=new String[num];
				roll=db.getStudents(subcode);
				String val[]=new String[num];
				val=db.getAttendance(subcode);
				ArrayList<NameValuePair> NVpair=new ArrayList<NameValuePair>();
				NVpair.add(new BasicNameValuePair("date",date.getText().toString()));
				NVpair.add(new BasicNameValuePair("subcode",subcode));
				NVpair.add(new BasicNameValuePair("num",String.valueOf(num)));
				for(int i=0;i<num;++i)
				{
					NVpair.add(new BasicNameValuePair("roll"+i,roll[i]));
					NVpair.add(new BasicNameValuePair("val"+i,val[i]));
				}
				InputStream is=null;
				String result=null;
				StrictMode.setThreadPolicy(policy);
				try{
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httppost=new HttpPost("http://zxcvbn.byethost7.com/attendance/submit.php");
					httppost.setEntity(new UrlEncodedFormEntity(NVpair));
					HttpResponse response=httpclient.execute(httppost);
					HttpEntity entity=response.getEntity();
					is=entity.getContent();
					//Log.e("log_tag", "connection success "+new UrlEncodedFormEntity(NVpair).toString());
					Toast.makeText(getApplicationContext(), "Connection Established", Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.e("log_tag", "Error in http connection "+ex.toString());
        	        Toast.makeText(getApplicationContext(), "Cannot connect to server.", Toast.LENGTH_SHORT).show();
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
					Log.e("log_tag", "converted submit msg- "+result);
					
				}
				catch(Exception e)
	        	{
	        	    Log.e("log_tag", "Error converting submit msg "+e.toString());
	           	}
				try
	        	{
	        		
	        		JSONObject json_data = new JSONObject(result);
	        		if(json_data.get("msg").equals("success"))
	        		{
	        			Log.e("log_tag", "submit data successful");
		        	    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("Duplicate Date Entry"))
	        		{
	        			Log.e("log_tag", "duplicate date");
		        	    Toast.makeText(getApplicationContext(), "Duplicate Date Entry", Toast.LENGTH_SHORT).show();
	        		}
	        		else if(json_data.get("msg").equals("missing field"))
	        		{
	        			Log.e("log_tag", "missing field");
		        	    Toast.makeText(getApplicationContext(), "Missing Date", Toast.LENGTH_SHORT).show();
	        		}
	        		else
	        		{
	        			Log.e("log_tag", "submit data failed");
		        	    Toast.makeText(getApplicationContext(), "Submission Failed", Toast.LENGTH_SHORT).show();
	        		}
	        	}
				catch(JSONException e)
	        	{
	        	    Log.e("log_tag", "Error parsing submit data "+e.toString());
	        	    Toast.makeText(getApplicationContext(), "Submit Error", Toast.LENGTH_SHORT).show();
	        	}
			}
		});

	}

}
