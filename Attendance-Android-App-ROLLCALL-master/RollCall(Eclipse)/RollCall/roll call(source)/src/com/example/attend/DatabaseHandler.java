package com.example.attend;

//public class DatabaseHandler {

//}
 
import java.util.HashMap;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "attend";
 
    // Login table name
    private static final String TABLE_LOGIN = "login";
   // private static final String TABLE_SUB1 = "l";
 
    // Login Table Columns names
    //private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
   // private static final String KEY_EMAIL = "email";
   // private static final String KEY_UID = "uid";
    //private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_SUB1 = "sub1";
    private static final String KEY_SUB2 = "sub2";
    private static final String KEY_SUB3 = "sub3";
    private static final String KEY_SUB4 = "sub4";
    private static final String KEY_SUB5 = "sub5";
    private static final String KEY_SUB6 = "sub6";
    private static final String KEY_SUB7 = "sub7";
    private static final String KEY_SUB8 = "sub8";
    private static final String KEY_SUB9 = "sub9";
    private static final String KEY_SUB10 = "sub10";
    
    private static final String KEY_uname = "name";
    private static final String KEY_roll = "roll";
    private static final String KEY_att = "att";
    
    private static final String TTHEME = "theme";
    
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
                //+ KEY_NAME + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " VARCHAR(20) PRIMARY KEY,"
                + KEY_SUB1 + " VARCHAR(20) ,"
                + KEY_SUB2 + " VARCHAR(20) ,"
                + KEY_SUB3 + " VARCHAR(20) ,"
                + KEY_SUB4 + " VARCHAR(20) ,"
                + KEY_SUB5 + " VARCHAR(20) ,"
                + KEY_SUB6 + " VARCHAR(20) ,"
                + KEY_SUB7 + " VARCHAR(20) ,"
                + KEY_SUB8 + " VARCHAR(20) ,"
                + KEY_SUB9 + " VARCHAR(20) ,"
                + KEY_SUB10 + " VARCHAR(20)" + ")";
               // + KEY_UID + " TEXT,"
               // + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);
        String query="CREATE TABLE "+TTHEME+"(val INT)";
        db.execSQL(query);
        ContentValues values = new ContentValues();
        values.put("val", 1);
        db.insert(TTHEME, null, values);
        
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * Storing user details in database
     * */
   // public void addUser(String name, String email, String uid, String created_at) {
    public void addUser(String name,String sub1,String sub2,String sub3,String sub4,String sub5,String sub6,String sub7,String sub8,String sub9,String sub10) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        //values.put(KEY_NAME, name); // Name
        values.put(KEY_NAME, name); // Email
        values.put(KEY_SUB1, sub1);
        values.put(KEY_SUB2, sub2);
        values.put(KEY_SUB3, sub3);
        values.put(KEY_SUB4, sub4);
        values.put(KEY_SUB5, sub5);
        values.put(KEY_SUB6, sub6);
        values.put(KEY_SUB7, sub7);
        values.put(KEY_SUB8, sub8);
        values.put(KEY_SUB9, sub9);
        values.put(KEY_SUB10, sub10);
        //values.put(KEY_UID, uid); // Email
       // values.put(KEY_CREATED_AT, created_at); // Created At
 
        // Inserting Row
        db.insert(TABLE_LOGIN, null, values);
        
        
        db.close(); // Closing database connection
    }
    public void ins_table(String tname,String name,String roll) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        //values.put(KEY_NAME, name); // Name
        name="`"+name+"`";
        values.put(KEY_uname, name); // Email
        values.put(KEY_roll, roll);
        values.put(KEY_att, 1);
        //values.put(KEY_UID, uid); // Email
       // values.put(KEY_CREATED_AT, created_at); // Created At
 
        // Inserting Row
        db.insert(tname, null, values);  
        db.close(); // Closing database connection
    }
    public void init(String tname) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	String CREATE_t_TABLE = "CREATE TABLE " + tname + "("
                //+ KEY_NAME + " INTEGER PRIMARY KEY,"
                + KEY_uname + " VARCHAR(50),"
                + KEY_roll + " VARCHAR(9) PRIMARY KEY,"
                + KEY_att + " INT " + ")";
               // + KEY_UID + " TEXT,"
               // + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_t_TABLE);
        db.close();
    }
    public void toggle_theme() {
    	SQLiteDatabase db = this.getWritableDatabase();
    	String query = "SELECT  * FROM "+TTHEME;
    	Cursor cursor = db.rawQuery(query, null);
    	cursor.moveToFirst();
    	int temp=Integer.parseInt(cursor.getString(0));
    	if(temp==0)
    	{
    		query="UPDATE theme SET val="+String.valueOf(1)+" WHERE 1";
    		db.execSQL(query);
    		Log.e("log_data_tag","val=1");
    	}
    	if(temp==1)
    	{
    		query="UPDATE theme SET val="+String.valueOf(0)+" WHERE 1";
    		db.execSQL(query);
    		Log.e("log_data_tag","val=0");
    	}
        db.close();
    }
    public int get_theme() {
    	SQLiteDatabase db = this.getReadableDatabase();
    	String query="SELECT * FROM theme";
    	Cursor cursor=db.rawQuery(query, null);
    	cursor.moveToFirst();
    	int ret= Integer.parseInt(cursor.getString(0));
    	return ret;
    }
    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String,String> user = new HashMap<String,String>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;
          
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put("name", cursor.getString(0));
            user.put("sub1", cursor.getString(1));
            user.put("sub2", cursor.getString(2));
            user.put("sub3", cursor.getString(3));
            user.put("sub4", cursor.getString(4));
            user.put("sub5", cursor.getString(5));
            user.put("sub6", cursor.getString(6));
            user.put("sub7", cursor.getString(7));
            user.put("sub8", cursor.getString(8));
            user.put("sub9", cursor.getString(9));
            user.put("sub10", cursor.getString(10));
            //user.put("uid", cursor.getString(3));
            //user.put("created_at", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
        return user;
    }
 
    public String[] getStudents(String tname){
        HashMap<String,String> user = new HashMap<String,String>();
        String ret[]=new String[this.getRowCountwtn(tname)];
        String selectQuery = "SELECT  * FROM " + tname;
          
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        int i=0;
        //if(cursor.getCount() > 0){
        while(!cursor.isAfterLast()){
            //user.put("name"+i, cursor.getString(0));
            //user.put("roll"+i, cursor.getString(1));
        	ret[i++]=cursor.getString(1);
        	cursor.moveToNext();
            //user.put("uid", cursor.getString(3));
            //user.put("created_at", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
        return ret;
    }
    public String[] getAttendance(String tname){
        HashMap<String,String> user = new HashMap<String,String>();
        String ret[]=new String[this.getRowCountwtn(tname)];
        String selectQuery = "SELECT  * FROM " + tname;
          
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        int i=0;
        //if(cursor.getCount() > 0){
        while(!cursor.isAfterLast()){
            //user.put("name"+i, cursor.getString(0));
            //user.put("roll"+i, cursor.getString(1));
        	ret[i++]=cursor.getString(2);
        	cursor.moveToNext();
            //user.put("uid", cursor.getString(3));
            //user.put("created_at", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
        return ret;
    }
    /**
     * Getting user login status
     * return true if rows are there in table
     * */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
         
        // return row count
        return rowCount;
    }
    public int getRowCountwtn(String tname) {
        String countQuery = "SELECT  * FROM " + tname;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
         
        // return row count
        return rowCount;
    }
     
    /**
     * Re crate database
     * Delete all tables and create them again
     * */
    public void resetTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_LOGIN, null, null);
        db.close();
    }
    public void resetTablewname(String tname){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.execSQL("DROP TABLE IF EXISTS " + tname);
        db.close();
    }
    public void ins_att(String tname,String[] roll,int[] val)
    {
    	String countQuery = "SELECT  * FROM " + tname;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int num = cursor.getCount();
        db.close();
        cursor.close();
    	db=this.getWritableDatabase();
    	String name="`"+tname+"`";
    	//int num=2;
    	for(int i=0;i<num;++i)
    	{
    		String query="UPDATE "+name+" SET "+ KEY_att +"="+val[i]+" WHERE "+KEY_roll+"='"+roll[i]+"'";
    		db.execSQL(query);
    	}
    	db.close();
    }
 
}
