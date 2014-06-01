package com.example.messagefilter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsListener extends BroadcastReceiver{

    private SharedPreferences preferences;
    MySQLiteHelper db=null;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
    	db=new MySQLiteHelper(context);
    	
    	StatusCheker objStatusCheker=new StatusCheker();
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from = null;
            String msgBody=null;
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++)
                    {
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        msgBody = msgs[i].getMessageBody();
                        System.out.println("msgBody>>>>>>>"+msgBody);
                        System.out.println("msg_from>>>>>>>"+msg_from);
                        
                        
                        
                    }
                }catch(Exception e){
//                            Log.d("Exception caught",e.getMessage());
                }
            }
            String status=objStatusCheker.getStaus(msg_from);
            System.out.println("status---------"+status);
            
            db.insertIntoMessageDetails(msg_from,msgBody,status);
        }
    }
}
