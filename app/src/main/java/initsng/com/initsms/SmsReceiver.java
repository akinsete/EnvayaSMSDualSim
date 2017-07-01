package initsng.com.initsms;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;


public class SmsReceiver extends BroadcastReceiver {
	String sender,message = "",date,service_center;
	SharedPreferences sharedPreferences;
	Context context;
	String TAG = "SmsReceiver";
	final SmsManager sms = SmsManager.getDefault();
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//---get the SMS message passed in---
        Bundle bundle = intent.getExtras();   
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;

		//sms.getSubscriptionId();


		int simSlot = intent.getIntExtra("simSlot", -1);
		int simId = intent.getIntExtra("simId", -1);


		Log.i(TAG,simSlot + " ::Slot ---> 1");
		Log.i(TAG,simId + " ::Slot ---> 2");

		//Toast.makeText(context,"New Message Received",Toast.LENGTH_LONG).show();
		//Toast.makeText(context,simSlot + " ::Slot ---> 1",Toast.LENGTH_LONG).show();
		//Toast.makeText(context,simId + " ::Slot ---> 2",Toast.LENGTH_LONG).show();


		if (bundle != null)
        {
            Object[] smsExtra = (Object[]) bundle.get("pdus");
                        
            for ( int i = 0; i < smsExtra.length; ++i )
            {
            	SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])smsExtra[i]);
            	message += smsMessage.getMessageBody().toString();
            	sender = smsMessage.getOriginatingAddress();
            	date = String.valueOf( smsMessage.getTimestampMillis());
            	service_center = smsMessage.getServiceCenterAddress();
            	
             //   Utilities.log("Firstmessage  From Network",  smsMessage.getMessageBody());

            }
            
            
            
//           if(sender.toString().toLowerCase(Locale.getDefault()).equals(Utilities.VERIFICATION_ADDRESS.toString().toLowerCase(Locale.getDefault()))){
//               String[] message_token = message.split(":");
//               String s = message_token[1];
//               String code = s.substring(0, s.length() - 1);
//
//        	   localDatabase.verifyPhoneNumber("sims", "verified", "1", "code", code);
//        	   localDatabase.close();
//
//           }else{
//
//               	Cursor cur = null;
//    			Uri personUri = Uri.withAppendedPath( PhoneLookup.CONTENT_FILTER_URI, sender);
//
//    			cur = context.getContentResolver().query(personUri, new String[] { PhoneLookup.DISPLAY_NAME, PhoneLookup._ID }, null, null, null );
//
//    			if( cur.moveToFirst() ) {
//    	             int nameIndex = cur.getColumnIndex(PhoneLookup.DISPLAY_NAME);
//    	             display_name = cur.getString(nameIndex);
//    	             int nameIndexs = cur.getColumnIndex(PhoneLookup._ID);
//    	             contact_id = cur.getString(nameIndexs);
//    			}
//    			cur.close();
//
//    	        //Utilities.toast(context,display_name);
//
//    			if(display_name == ""){
//    				display_name = sender;
//    			}
//    			bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
//
//    			if(contact_id == ""){
//    				bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
//    			}else{
//    		        try {
//    					bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Utilities.getContactPhotoUri(Long.parseLong(contact_id)));
//    				} catch (NumberFormatException e) {
//    					// TODO Auto-generated catch block
//    					e.printStackTrace();
//    				} catch (FileNotFoundException e) {
//    					// TODO Auto-generated catch block
//    					e.printStackTrace();
//    				} catch (IOException e) {
//    					// TODO Auto-generated catch block
//    					e.printStackTrace();
//    				}
//    			}
//
//           }

        }      			
        
       
        
	}
	

	

}
