package com.example.messagefilter;



	
	
	
	
	import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
	 
	public class SplashScreen extends Activity {
	 
	    // Splash screen timer
	    private static int SPLASH_TIME_OUT = 1000;
	    MySQLiteHelper db=null;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.splashscreen);
	 
	         db= new MySQLiteHelper(this);
	         boolean mboolean = false;

	         SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
	         mboolean = settings.getBoolean("FIRST_RUN", false);
	         if (!mboolean) {
	          // do the thing for the first time 
	           settings = getSharedPreferences("PREFS_NAME", 0);
	                             SharedPreferences.Editor editor = settings.edit();
	                             editor.putBoolean("FIRST_RUN", true);
	                             editor.commit();      
	                             new AsyncLoadXMLFeed().execute();
	         } else {
	        	 new Handler().postDelayed(new Runnable() {
	        		 
	                 /*
	                  * Showing splash screen with a timer. This will be useful when you
	                  * want to show case your app logo / company
	                  */
	      
	                 @Override
	                 public void run() {
	                     // This method will be executed once the timer is over
	                     // Start your app main activity
	                     Intent i = new Intent(SplashScreen.this, HomePageTabs.class);
	                     startActivity(i);
	      
	                     // close this activity
	                     finish();
	                 }
	             }, SPLASH_TIME_OUT);
	         }
	      
	    }
	    
	    private class AsyncLoadXMLFeed extends AsyncTask<Void, Void, Void>{
	        @Override
	        protected void onPreExecute(){
	              // show your progress dialog

	        }

	        @Override
	        protected Void doInBackground(Void... voids){
	              // load your xml feed asynchronously
	        	loadContacts();
	        	
	        	return null;
	        }

	        @Override
	        protected void onPostExecute(Void params){
	              // dismiss your dialog
	              // launch your News activity
	              Intent intent = new Intent(SplashScreen.this, HomePageTabs.class);
	              startActivity(intent);

	              // close this activity
	              finish();
	        }
	        
		    //to retrive  contact information stored in Contacts table
		    public void loadContacts()
		    {
		    	
		    	System.out.println("in loadContacts/......");
		    	ContentResolver cr = getContentResolver();
		        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
		                null, null, null, null);
		        
		        if (cur.getCount() > 0) 
		        {
		        	  while (cur.moveToNext())
		        	  {
		        		  String id = cur.getString(
		                          cur.getColumnIndex(ContactsContract.Contacts._ID));
		        		//  System.out.println("id-----------"+id);
		        		  String name = cur.getString(
		                          cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
		        		  
		        		//  System.out.println("name-----------"+name);
		        		  if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
		        		  {
		        			  Cursor pCur = cr.query(
		        			 		    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
		        			 		    null, 
		        			 		    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
		        			 		    new String[]{id}, null);
		        			 	        while (pCur.moveToNext()) {
		        			 		    // Do something with phones
		        			 	        	
		        			 	        	String number=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
		        			 	      //  	System.out.println("number-----------"+number);
		        			 	        	 db.insertIntoContactDetails(id,name,number);
		        			 	        } 
		        			 	        pCur.close();

		        			  
		        		  }
		        		  
		        		 
		        		  
		        	  }
		        	  
		        	  
		        	
		        }
		        
		    }

	     }
	  }
	    
	    
//	    
//	        new Handler().postDelayed(new Runnable() {
//	 
//	            
//	        	
//	        	
//	        	
//	        	/*
//	             * Showing splash screen with a timer. This will be useful when you
//	             * want to show case your app logo / company
//	             */
//	 
//	            @Override
//	            public void run() {
//	                // This method will be executed once the timer is over
//	                // Start your app main activity
//	                Intent i = new Intent(SplashScreen.this, HomePageTabs.class);
//	                startActivity(i);
//	 
//	                // close this activity
//	                finish();
//	            }
//	        }, SPLASH_TIME_OUT);
//	    }
//	   
//	    //to retrive  contact information stored in Contacts table
//	    public void loadContacts()
//	    {
//	    	ContentResolver cr = getContentResolver();
//	        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
//	                null, null, null, null);
//	        
//	        if (cur.getCount() > 0) 
//	        {
//	        	  while (cur.moveToNext())
//	        	  {
//	        		  String id = cur.getString(
//	                          cur.getColumnIndex(ContactsContract.Contacts._ID));
//	        		  System.out.println("id-----------"+id);
//	        		  String name = cur.getString(
//	                          cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//	        		  
//	        		  System.out.println("name-----------"+name);
//	        		  if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
//	        		  {
//	        			  Cursor pCur = cr.query(
//	        			 		    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
//	        			 		    null, 
//	        			 		    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
//	        			 		    new String[]{id}, null);
//	        			 	        while (pCur.moveToNext()) {
//	        			 		    // Do something with phones
//	        			 	        	
//	        			 	        	String number=pCur.getString(pCur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
//	        			 	        	System.out.println("number-----------"+number);
//	        			 	        	
//	        			 	        } 
//	        			 	        pCur.close();
//
//	        			  
//	        		  }
//	        		  
//
//	        	  }
//	        }
//	        
//	    }
	 
//	}

