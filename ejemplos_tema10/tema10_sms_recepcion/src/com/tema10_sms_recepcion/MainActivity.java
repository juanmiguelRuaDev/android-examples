package com.tema10_sms_recepcion;

import android.app.Activity;
import android.os.Bundle;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button btnSendSMS;		
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);        
        btnSendSMS.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {                
                sendSMS("5556", "Hola. Este es mi mensaje SMS");
            }
        });
    }
        
    private void sendSMS(String phoneNumber, String message)
    {     
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
}