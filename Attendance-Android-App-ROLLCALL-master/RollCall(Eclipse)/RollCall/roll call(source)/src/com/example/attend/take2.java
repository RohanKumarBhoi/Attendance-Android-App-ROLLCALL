package com.example.attend;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attend.DatabaseHandler;

import android.util.Log;
public class take2 extends Activity {
	
		int ctr=0;
		Button roll1,roll2,roll3,roll4,roll5,up,down,done;
		TextView TVsubcode;
		String roll[],v[];
		int val[];
		int num=0;
		String subcode=null;
		@SuppressLint("NewApi") protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.take2);
		Bundle extras=getIntent().getExtras();
		if(extras!=null)
		{
			subcode=extras.getString("param");
		}
		final DatabaseHandler db=new DatabaseHandler(getApplicationContext());
		roll1=(Button)findViewById(R.id.broll1);
		roll2=(Button)findViewById(R.id.broll2);
		roll3=(Button)findViewById(R.id.broll3);
		roll4=(Button)findViewById(R.id.broll4);
		roll5=(Button)findViewById(R.id.broll5);
		up=(Button)findViewById(R.id.bup);
		down=(Button)findViewById(R.id.bdown);
		done=(Button)findViewById(R.id.bdone);
		final TextView TVsubcode=(TextView)findViewById(R.id.subcode);
		TVsubcode.setText(subcode);
		final Drawable green=this.getResources().getDrawable(R.drawable.green);
		final Drawable red=this.getResources().getDrawable(R.drawable.red);
		LinearLayout ll=(LinearLayout)findViewById(R.id.tk2layout);
		if(db.get_theme()==0)
		{
			roll1.setTextColor(Color.BLACK);
			roll2.setTextColor(Color.BLACK);
			roll3.setTextColor(Color.BLACK);
			roll4.setTextColor(Color.BLACK);
			roll5.setTextColor(Color.BLACK);
			done.setTextColor(Color.BLACK);
			TVsubcode.setTextColor(Color.BLACK);
			ll.setBackground(this.getResources().getDrawable(R.drawable.backlight));
		}
		else
		{
			roll1.setTextColor(Color.WHITE);
			roll2.setTextColor(Color.WHITE);
			roll3.setTextColor(Color.WHITE);
			roll4.setTextColor(Color.WHITE);
			roll5.setTextColor(Color.WHITE);
			done.setTextColor(Color.WHITE);
			TVsubcode.setTextColor(Color.WHITE);
			ll.setBackground(this.getResources().getDrawable(R.drawable.backdark));
		}
			num=db.getRowCountwtn(subcode);
			
			roll=new String[num];
			val=new int[num];
			roll=db.getStudents(subcode);
			v=new String[num];
			v=db.getAttendance(subcode);
			for(int i=0;i<num;++i)
				val[i]=Integer.parseInt(v[i]);
			
			if(ctr+0<num)
			{
				roll1.setText(roll[0+ctr]);
				if(val[ctr+0]==1)
					roll1.setBackground(green);
					else
						roll1.setBackground(red);
			}
			else
				roll1.setText("roll1");
			if(ctr+1<num)
			{
				roll2.setText(roll[1+ctr]);
				if(val[ctr+1]==1)
					roll2.setBackground(green);
					else
						roll2.setBackground(red);
			}
			else
				roll2.setText("roll2");
			if(ctr+2<num)
			{
				roll3.setText(roll[2+ctr]);
				if(val[ctr+2]==1)
					roll3.setBackground(green);
					else
						roll3.setBackground(red);
			}
			else
				roll3.setText("roll3");
			if(ctr+3<num)
			{
				roll4.setText(roll[3+ctr]);
				if(val[ctr+3]==1)
					roll4.setBackground(green);
					else
						roll4.setBackground(red);
			}
			else
				roll4.setText("roll4");
			if(ctr+4<num)
			{
				roll5.setText(roll[4+ctr]);
				if(val[ctr+4]==1)
					roll5.setBackground(green);
					else
						roll5.setBackground(red);
			}
			else
				roll5.setText("roll5");
			if(roll1.getText().equals("roll1"))
			roll1.setVisibility(4);
			else
				roll1.setVisibility(0);
			if(roll2.getText().equals("roll2"))
			roll2.setVisibility(4);
			else
				roll2.setVisibility(0);
			if(roll3.getText().equals("roll3"))
			roll3.setVisibility(4);
			else
				roll3.setVisibility(0);
			if(roll4.getText().equals("roll4"))
			roll4.setVisibility(4);
			else
				roll4.setVisibility(0);
			if(roll5.getText().equals("roll5"))
			roll5.setVisibility(4);
			else
				roll5.setVisibility(0);
			
		roll1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(val[ctr+0]==0)
					val[ctr+0]=1;
				else
					val[ctr+0]=0;
				if(val[ctr+0]==1)
					roll1.setBackground(green);
				else
					roll1.setBackground(red);
				Log.e("log_tag", "roll1= "+val[ctr+0]);
				
			}
		});
		roll2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(val[ctr+1]==0)
					val[ctr+1]=1;
				else
					val[ctr+1]=0;
				if(val[ctr+1]==1)
					roll2.setBackground(green);
				else
					roll2.setBackground(red);
				Log.e("log_tag", "roll2= "+val[ctr+1]);
				
			}
		});
		roll3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(val[ctr+2]==0)
					val[ctr+2]=1;
				else
					val[ctr+2]=0;
				if(val[ctr+2]==1)
					roll3.setBackground(green);
				else
					roll3.setBackground(red);
				Log.e("log_tag", "roll3= "+val[ctr+2]);
				
				
			}
		});
		roll4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(val[ctr+3]==0)
					val[ctr+3]=1;
				else
					val[ctr+3]=0;
				if(val[ctr+3]==1)
					roll4.setBackground(green);
				else
					roll4.setBackground(red);
				Log.e("log_tag", "roll4= "+val[ctr+3]);
				
			}
		});
		roll5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(val[ctr+4]==0)
					val[ctr+4]=1;
				else
					val[ctr+4]=0;
				if(val[ctr+4]==1)
					roll5.setBackground(green);
				else
					roll5.setBackground(red);
				Log.e("log_tag", "roll5= "+val[ctr+4]);
				
			}
		});
		down.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ctr+5<num)
				{
					ctr=ctr+5;
					if(ctr+0<num)
						roll1.setText(roll[0+ctr]);
					else
						roll1.setText("roll1");
					if(ctr+1<num)
						roll2.setText(roll[1+ctr]);
					else
						roll2.setText("roll2");
					if(ctr+2<num)
						roll3.setText(roll[2+ctr]);
					else
						roll3.setText("roll3");
					if(ctr+3<num)
						roll4.setText(roll[3+ctr]);
					else
						roll4.setText("roll4");
					if(ctr+4<num)
						roll5.setText(roll[4+ctr]);
					else
						roll5.setText("roll5");
					if(ctr+0<num)
					{
					if(val[ctr+0]==1)
						roll1.setBackground(green);
					else
						roll1.setBackground(red);
					}
					if(ctr+1<num)
					{
					if(val[ctr+1]==1)
						roll2.setBackground(green);
					else
						roll2.setBackground(red);
					}
					if(ctr+2<num)
					{
					if(val[ctr+2]==1)
						roll3.setBackground(green);
					else
						roll3.setBackground(red);
					}
					if(ctr+3<num)
					{
					if(val[ctr+3]==1)
						roll4.setBackground(green);
					else
						roll4.setBackground(red);
					}
					if(ctr+4<num)
					{
					if(val[ctr+4]==1)
						roll5.setBackground(green);
					else
						roll5.setBackground(red);
					}
					
					if(roll1.getText().equals("roll1"))
					roll1.setVisibility(4);
					else
						roll1.setVisibility(0);
					if(roll2.getText().equals("roll2"))
					roll2.setVisibility(4);
					else
						roll2.setVisibility(0);
					if(roll3.getText().equals("roll3"))
					roll3.setVisibility(4);
					else
						roll3.setVisibility(0);
					if(roll4.getText().equals("roll4"))
					roll4.setVisibility(4);
					else
						roll4.setVisibility(0);
					if(roll5.getText().equals("roll5"))
					roll5.setVisibility(4);
					else
						roll5.setVisibility(0);
					
				}
				
			}
		});
		up.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ctr-5>=0)
				{
					ctr=ctr-5;
					if(ctr+0<num)
						roll1.setText(roll[0+ctr]);
					else
						roll1.setText("roll1");
					if(ctr+1<num)
						roll2.setText(roll[1+ctr]);
					else
						roll2.setText("roll2");
					if(ctr+2<num)
						roll3.setText(roll[2+ctr]);
					else
						roll3.setText("roll3");
					if(ctr+3<num)
						roll4.setText(roll[3+ctr]);
					else
						roll4.setText("roll4");
					if(ctr+4<num)
						roll5.setText(roll[4+ctr]);
					else
						roll5.setText("roll5");
					if(ctr+0<num)
					{
					if(val[ctr+0]==1)
						roll1.setBackground(green);
					else
						roll1.setBackground(red);
					}
					if(ctr+1<num)
					{
					if(val[ctr+1]==1)
						roll2.setBackground(green);
					else
						roll2.setBackground(red);
					}
					if(ctr+2<num)
					{
					if(val[ctr+2]==1)
						roll3.setBackground(green);
					else
						roll3.setBackground(red);
					}
					if(ctr+3<num)
					{
					if(val[ctr+3]==1)
						roll4.setBackground(green);
					else
						roll4.setBackground(red);
					}
					if(ctr+4<num)
					{
					if(val[ctr+4]==1)
						roll5.setBackground(green);
					else
						roll5.setBackground(red);
					}
					if(roll1.getText().equals("roll1"))
					roll1.setVisibility(4);
					else
						roll1.setVisibility(0);
					if(roll2.getText().equals("roll2"))
					roll2.setVisibility(4);
					else
						roll2.setVisibility(0);
					if(roll3.getText().equals("roll3"))
					roll3.setVisibility(4);
					else
						roll3.setVisibility(0);
					if(roll4.getText().equals("roll4"))
					roll4.setVisibility(4);
					else
						roll4.setVisibility(0);
					if(roll5.getText().equals("roll5"))
					roll5.setVisibility(4);
					else
						roll5.setVisibility(0);
				}
				
			}
		});
		done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.ins_att(subcode, roll, val);
				Toast.makeText(getApplicationContext(), "Attendance Saved.", Toast.LENGTH_SHORT).show();
				finish();
		
			}
		});
		}
		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			finish();
		}
		

}
