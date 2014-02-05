package com.tema10_sms_acuserecibo;

import android.app.Activity;
import android.os.Bundle;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    	String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";
 
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
            new Intent(SENT), 0);
 
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
            new Intent(DELIVERED), 0);
 
        //---Resultado de SMS enviado
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
            	//Escribimos el resultado según "getResultCode"
            	switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS enviado", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Fallo general", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "Sin servicio", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "PDU vacío", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio apagada", 
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));
 
        //---Resultado de SMS entregado
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
            	//Escribimos el resultado según "getResultCode"
            	switch (getResultCode())
            	{
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS entregado", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS no entregado", 
                                Toast.LENGTH_SHORT).show();
                        break;
            	}
            }
        }, new IntentFilter(DELIVERED));        
 
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(num_telefono, null, mensaje, sentPI, deliveredPI);        
    }   
}
