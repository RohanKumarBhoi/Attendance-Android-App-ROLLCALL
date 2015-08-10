package com.example.attend;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.attend.DatabaseHandler;

public class take extends Activity {
	
	@SuppressLint("NewApi") protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.take);
		Button sub1=(Button)findViewById(R.id.btSub1);
		Button sub2=(Button)findViewById(R.id.btSub2);
		Button sub3=(Button)findViewById(R.id.btSub3);
		Button sub4=(Button)findViewById(R.id.btSub4);
		Button sub5=(Button)findViewById(R.id.btSub5);
		Button sub6=(Button)findViewById(R.id.btSub6);
		Button sub7=(Button)findViewById(R.id.btSub7);
		Button sub8=(Button)findViewById(R.id.btSub8);
		Button sub9=(Button)findViewById(R.id.btSub9);
		Button sub10=(Button)findViewById(R.id.btSub10);
		final TextView tk=(TextView)findViewById(R.id.tkstr);
		final LinearLayout ll=(LinearLayout)findViewById(R.id.tklayout);
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
			tk.setTextColor(Color.BLACK);
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
			tk.setTextColor(Color.WHITE);
			ll.setBackground(this.getResources().getDrawable(R.drawable.backdark));
		}
		sub1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),take2.class);
				i.putExtra("param", db.getUserDetails().get("sub1"));
				startActivity(i);
				
			}
		});
		sub2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),take2.class);
				i.putExtra("param", db.getUserDetails().get("sub2"));
				startActivity(i);
				
			}
		});
		sub3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),take2.class);
				i.putExtra("param", db.getUserDetails().get("sub3"));
				startActivity(i);
				
			}
		});
		sub4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),take2.class);
				i.putExtra("param", db.getUserDetails().get("sub4"));
				startActivity(i);
				
			}
		});
		sub5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),take2.class);
				i.putExtra("param", db.getUserDetails().get("sub5"));
				startActivity(i);
				
			}
		});
		sub6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),take2.class);
				i.putExtra("param", db.getUserDetails().get("sub6"));
				startActivity(i);
				
			}
		});
		sub7.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),take2.class);
				i.putExtra("param", db.getUserDetails().get("sub7"));
				startActivity(i);
				
			}
		});
		sub8.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),take2.class);
				i.putExtra("param", db.getUserDetails().get("sub8"));
				startActivity(i);
				
			}
		});
		sub9.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),take2.class);
				i.putExtra("param", db.getUserDetails().get("sub9"));
				startActivity(i);
				
			}
		});
		sub10.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getBaseContext(),take2.class);
				i.putExtra("param", db.getUserDetails().get("sub10"));
				startActivity(i);
				
			}
		});
		
	}
}
