package com.tema2_servicios;

import java.util.Timer;
import java.util.TimerTask;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import android.os.IBinder;

public class Servicio_ejemplo extends Service {
	int contador=0;
	int id_aviso=1;
	
	//60000 milisegundos son 60 segundos -> cada minuto
	static final int INTERVALO_ACTUALIZACION=60000;
	private Timer temporizador = new Timer();
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Servicio iniciado", Toast.LENGTH_LONG).show();        
        Ejecuta_Tarea_Repetitiva();
		return START_STICKY;
	}
	private void Ejecuta_Tarea_Repetitiva() {
		temporizador.scheduleAtFixedRate( new TimerTask() {
			public void run() {
                
                muestra_aviso();
			}
		}, 0, INTERVALO_ACTUALIZACION);
	}
		
	@Override
    public void onDestroy() {
        super.onDestroy();     
        if (temporizador != null){
        	temporizador.cancel();
        }
        Toast.makeText(this, "Servicioe Destruido", Toast.LENGTH_LONG).show();
    }	
	
	
	 public void muestra_aviso(){
			//Creamos y mostramos la segunda actividad
			Intent i=new Intent(this,notificaciones.class);
			i.putExtra("id_aviso", id_aviso);
			
			PendingIntent intent_pendiente=PendingIntent.getActivity(this,0,i,0);
			
			NotificationManager admin_avisos=(NotificationManager)
				getSystemService(NOTIFICATION_SERVICE);
			
			Notification notifica=new Notification(
				R.drawable.ic_launcher,
				"Aviso, la reunión es en 5 minutos",
				System.currentTimeMillis());
			
			CharSequence mensaje_de="Aviso del sistema";
			CharSequence mensaje="Reunión con el cliente a las 17:00";
			
			notifica.setLatestEventInfo(this, mensaje_de,mensaje,intent_pendiente);
			
			//espra 100ms, vibra 250 ms, espera 100 ms y vibra otros 500ms
			notifica.vibrate=new long[] {100,250,100,500};
			
			admin_avisos.notify (id_aviso,notifica);
		}
	    
	
}