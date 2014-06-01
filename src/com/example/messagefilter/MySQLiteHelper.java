package com.example.messagefilter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {


	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "MessageINDB5";
	static final String PID = "_id";
	static final String NAME = "contact_name";
	static final String NUMBER = "contact_number";
	static final String MESSAGE_BODY = "message_body";
	static final String STATUS = "status";



	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);  
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// SQL statement to create ContactDetails table
		String CREATE_CONTACTS_DETAILS_TABLE = "CREATE TABLE ContactDetails ( " +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"contact_name TEXT, "+
				"contact_number TEXT )";

		// SQL statement to create MessageDetails table
		String CREATE_MESSAGE_DETAILS_TABLE = "CREATE TABLE MessageDetails ( " +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"contact_number TEXT, "+
				"contact_name TEXT, "+
				"message_body TEXT, "+
				"status TEXT DEFAULT 'I' )";

		// create books table
		db.execSQL(CREATE_CONTACTS_DETAILS_TABLE);
		db.execSQL(CREATE_MESSAGE_DETAILS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void insertIntoContactDetails(String id, String name, String number) {
		// TODO Auto-generated method stub
		//try {


		//System.out.println("insertIntoContactDetails>>>>>>>");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		//contentValues.put("contact_pid", id);
		contentValues.put("contact_name", name);
		contentValues.put("contact_number", number);


		db.insert("ContactDetails", null, contentValues);
		db.close();
		//}
		//	    catch (Exception e) {
		//			// TODO: handle exception
		//	    	//Toast.makeText(getApplicationContext(), "Couldn't create or open the database", Toast.LENGTH_LONG).show();
		//	   
		//		}
	}


	public void insertIntoMessageDetails(String number, String messageBody ,String status)
	{

		//System.out.println("insertIntoContactDetails>>>>>>>");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		//contentValues.put("contact_pid", id);
		//		if (status.equals("I"))
		//		{
		//		String name=getContactName(number);
		//		contentValues.put("contact_name", name);
		//		} else
		//		{
		//			contentValues.put("contact_name", number);	
		//		}

		contentValues.put("contact_name", number);	
		contentValues.put("contact_number", number);
		contentValues.put("message_body", messageBody);
		contentValues.put("status", status);


		db.insert("MessageDetails", null, contentValues);
		//   db.close();
	}

	//	private String getContactName(String number) {
	//		// TODO Auto-generated method stub
	//		String name=null;
	//		//number=number.replace( "\\+[0-9]{1,}", "");
	//		number="8097896769";
	//		System.out.println("number>>>>>>>>>>"+number);
	//		SQLiteDatabase db = this.getReadableDatabase();
	//        Cursor c = db.rawQuery("SELECT contact_name  FROM "
	//                + " ContactDetails " + " where " + " contact_number " + " like '%" + number
	//                + "%'", null);
	//      if(c.moveToFirst())
	//      {
	//       
	//       name= c.getString(0);
	//      }
	//        
	//		return name;
	//	}


	public Cursor getInboxMessage()

	{
		Cursor cur=null;
		SQLiteDatabase db = this.getReadableDatabase();
		try
		{
			cur=db.rawQuery("SELECT _id,contact_name,contact_number,message_body,status  FROM "
					+" MessageDetails "+" where " + " status= 'I' " , null);		

			//         cur=db.rawQuery("SELECT _id,contact_name,contact_number,message_body,status  FROM "
			//        			+" MessageDetails ", null);	
		}
		catch(Exception e){

		}


		return cur;

	}
	public Cursor getPromoMessage()

	{
		Cursor cur=null;
		SQLiteDatabase db = this.getReadableDatabase();
		try
		{
			cur=db.rawQuery("SELECT _id,contact_name,contact_number,message_body,status  FROM "
					+" MessageDetails "+" where " + " status= 'P' " , null);		

			//         cur=db.rawQuery("SELECT _id,contact_name,contact_number,message_body,status  FROM "
			//        			+" MessageDetails ", null);	
		}
		catch(Exception e){

		}


		return cur;

	}
	public Cursor getUpdatesMessage()

	{
		Cursor cur=null;
		SQLiteDatabase db = this.getReadableDatabase();
		try
		{
			cur=db.rawQuery("SELECT _id,contact_name,contact_number,message_body,status  FROM "
					+" MessageDetails "+" where " + " status= 'U' " , null);		

			//     cur=db.rawQuery("SELECT _id,contact_name,contact_number,message_body,status  FROM "
			//    			+" MessageDetails ", null);	
		}
		catch(Exception e){

		}


		return cur;

	}

	public String getMessageById(String Id)
	{String messgae=null;
	Cursor cur=null;
	SQLiteDatabase db = this.getReadableDatabase();

	cur=db.rawQuery("SELECT message_body  FROM "
			+" MessageDetails "+" where " + " _id= ' "+Id+"' " , null);

	while(cur.moveToNext())
	{
		messgae=cur.getString(cur.getColumnIndexOrThrow("message_body"));
	}
	System.out.println("messgae>>>>>>"+messgae);
	return messgae;
	}

}
