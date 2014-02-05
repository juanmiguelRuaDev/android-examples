package com.tema2_servicios;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Bundle;

import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Listener de primer botón
        Button boton_inciar = (Button) findViewById(R.id.boton_inciar);
        boton_inciar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//Iniciamos el servicio
            	startService(new Intent(getBaseContext(), Servicio_ejemplo.class));
            }
        });
        //Listener del segundo botón
        Button boton_terminar = (Button) findViewById(R.id.boton_detener);
        boton_terminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//Finalizamos el servicio
            	stopService(new Intent(getBaseContext(), Servicio_ejemplo.class));            	
            }
        });
    }    
    
   
}