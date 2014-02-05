package com.tema2_servicios;

import android.app.Activity;
import android.os.Bundle;
import android.app.NotificationManager;

public class notificaciones extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notificaciones);
		
		//llamamos al servicio de notificaciones
		NotificationManager aviso=(NotificationManager)
				getSystemService (NOTIFICATION_SERVICE);
		
		//Ahora cancelamos la notificacion
		aviso.cancel(getIntent().getExtras().getInt("id_aviso"));
		
	}	
}
