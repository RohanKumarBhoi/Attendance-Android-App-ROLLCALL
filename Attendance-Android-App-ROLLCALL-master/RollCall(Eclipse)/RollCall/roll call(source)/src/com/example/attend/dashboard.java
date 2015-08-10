package com.example.attend;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.attend.DatabaseHandler;

@SuppressLint("NewApi") public class dashboard extends Activity{

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.dashboard);
			final DatabaseHandler db = new DatabaseHandler(getApplicationContext());
			if(db.getRowCount()==0)
			{
				Intent i=new Intent(getBaseContext(),MainActivity.class);
				startActivity(i);
				finish();
			}
			Button logout=(Button)findViewById(R.id.bLogout);
			Button tattendance=(Button)findViewById(R.id.bTAttendance);
			Button sattendance=(Button)findViewById(R.id.bSAttendance);
			Button bswitch=(Button)findViewById(R.id.bswitch);
			TextView uname=(TextView)findViewById(R.id.uname);
			TextView welcome=(TextView)findViewById(R.id.welcome);
			uname.setText(db.getUserDetails().get("name"));
			LinearLayout ll=(LinearLayout)findViewById(R.id.dashlayout);
			if(db.get_theme()==0)
			{
				logout.setTextColor(Color.BLACK);
				tattendance.setTextColor(Color.BLACK);
				sattendance.setTextColor(Color.BLACK);
				bswitch.setTextColor(Color.BLACK);
				bswitch.setText("-Switch to DARK Theme-");
				uname.setTextColor(Color.BLACK);
				welcome.setTextColor(Color.BLACK);
				ll.setBackground(this.getResources().getDrawable(R.drawable.backlight));
			}
			else
			{
				logout.setTextColor(Color.WHITE);
				tattendance.setTextColor(Color.WHITE);
				sattendance.setTextColor(Color.WHITE);
				bswitch.setTextColor(Color.WHITE);
				bswitch.setText("-Switch to LIGHT Theme-");
				uname.setTextColor(Color.WHITE);
				welcome.setTextColor(Color.WHITE);
				ll.setBackground(this.getResources().getDrawable(R.drawable.backdark));
			}
			logout.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					db.resetTables();
					db.close();
					Intent i=new Intent(getBaseContext(),MainActivity.class);
					startActivity(i);
					finish();
				}
			});
			tattendance.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					/*String email=db.getUserDetails().get("name");
					String sub1=db.getUserDetails().get("sub1");
					String sub2=db.getUserDetails().get("sub2");
					String sub3=db.getUserDetails().get("sub3");
					String sub4=db.getUserDetails().get("sub4");
					String sub5=db.getUserDetails().get("sub5");
					String sub6=db.getUserDetails().get("sub6");
					String sub7=db.getUserDetails().get("sub7");
					String sub8=db.getUserDetails().get("sub8");
					String sub9=db.getUserDetails().get("sub9");
					String sub10=db.getUserDetails().get("sub10");*/
					Intent i=new Intent(getBaseContext(),take.class);
					startActivity(i);
				}
			});
			sattendance.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(getBaseContext(),submit.class);
					startActivity(i);
				}
			});
			bswitch.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					db.toggle_theme();
					Intent i=new Intent(getBaseContext(),dashboard.class);
					startActivity(i);
					finish();
				}
			});
		}
}
