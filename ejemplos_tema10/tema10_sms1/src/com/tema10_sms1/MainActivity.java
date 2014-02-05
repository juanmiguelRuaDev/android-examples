package com.tema10_sms1;

import android.app.Activity;
import android.os.Bundle;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button btn_enviar_SMS;		
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        
        //recogemos el boton y creamos un listener.
        btn_enviar_SMS= (Button) findViewById(R.id.btnSendSMS);        
        btn_enviar_SMS.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {                
            	//Llamamos al metodo para enviar el mensaje
                envia_SMS("5556", "Hola. Este es mi mensaje SMS");
            }
        });
    }
        
    private void envia_SMS(String num_telefono, String mensaje)
    {     
    	//Creamos una referencia al gestor de mensajes y lo enviamos.
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(num_telefono, null, mensaje, null, null);
    }
}