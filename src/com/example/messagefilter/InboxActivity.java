package com.example.messagefilter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class InboxActivity extends Activity {
	private ListView messageListView ; 
	private CustomCursorAdapter customAdapter;
	private ArrayAdapter<String> listAdapter ;
	MySQLiteHelper databaseHelper;
	private MyCursorAdapter dataAdapter;
	final Context context = this;
	 private static final String TAG = InboxActivity.class.getSimpleName();
	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.homepage);
	        databaseHelper=new MySQLiteHelper(this);
	        
	        
	        
	        
	        Cursor cursor = databaseHelper.getInboxMessage();
	        
	        // The desired columns to be bound
	        String[] columns = new String[] {
	        		MySQLiteHelper.NUMBER
//	        		MySQLiteHelper.name
	        		
	        };
	        
	        int[] to = new int[] { 
	        	          	    R.id.name,
	//        	    R.id.number
	        	
	        	  };
	        
	        dataAdapter = new MyCursorAdapter(
	        	    this, R.layout.message_row, 
	        	    cursor, 
	        	    columns, 
	        	    to,
	        	    0);
	        
	        messageListView = (ListView) findViewById(R.id.listView);
	        messageListView.setAdapter(dataAdapter);
	        
	        
	        messageListView.setOnItemClickListener(new OnItemClickListener() {
	        	   @Override
	        	   public void onItemClick(AdapterView<?> listView, View view, 
	        	     int position, long id) {
	        	   // Get the cursor, positioned to the corresponding row in the result set
	        		   
	        		   System.out.println("id......."+id);
	        		   String Id=String.valueOf(id);
	        		   String message=databaseHelper.getMessageById(Id);
	        		   AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	        					context);
	        		   alertDialogBuilder.setMessage(message);
	        		   AlertDialog alertDialog = alertDialogBuilder.create();
	        		   alertDialog.show();
//	        	   Cursor cursor = (Cursor) listView.getItemAtPosition(position);
//	        	 
//	        	   // Get the state's capital from this row in the database.
//	        	   String countryCode = 
//	        	    cursor.getString(cursor.getColumnIndexOrThrow("code"));
//	        	   Toast.makeText(getApplicationContext(),
//	        	     countryCode, Toast.LENGTH_SHORT).show();
//	        	 
	        	   }
	        	  });
//	        messageListView.setOnItemClickListener(new OnItemClickListener() {
//	        	 
//	            @Override
//	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//	                Log.d(TAG, "clicked on item: " + position);
//	            }
//	        });
//	        new Handler().post(new Runnable() {
//	            @Override
//	            public void run() {
//	                customAdapter = new CustomCursorAdapter(InboxActivity.this, databaseHelper.getMessage("I"));
//	                messageListView.setAdapter(customAdapter);
//	            }
//	        });
	       
	        }

	    


	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	 
}
 
  
