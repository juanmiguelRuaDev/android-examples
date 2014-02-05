package com.tema10_sms_intents;


import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button btn_enviarSMS;		
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        
        btn_enviarSMS = (Button) findViewById(R.id.btnSendSMS);        
        btn_enviarSMS.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {                
                // Creamos un objeto Intent, le ponemos los parámetros necesarios
            	// y lo ejecutamos
            	Intent i=new Intent(android.content.Intent.ACTION_VIEW);
            	i.putExtra("address", "5556; 5558; 5560");
            	i.putExtra("sms_body","Hola, este es un mensaje desde Intents...");
            	
            	i.setType("vnd.android-dir/mms-sms");
            	startActivity(i);
            }
        });
    } 
}
