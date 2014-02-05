package com.jmrua.dynamiclayout;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class ActivityPrincipal extends Activity {
	int id_aviso = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		LayoutParams layout_params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 
				LayoutParams.WRAP_CONTENT);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		
		
		Button button = new Button(this);
		button.setText("Click me to open notification");
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				startNotification();				
			}			
		});
		button.setLayoutParams(layout_params);
		layout.addView(button);
		
		this.addContentView(layout, layout_params);
	} 

	
	private void startNotification(){
		Intent i = new Intent(this,Notificaciones.class);
		i.putExtra("id_aviso", id_aviso);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);
		NotificationManager nm = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
		Notification notifica = new Notification(R.drawable.ic_launcher,
				"Aviso, open the activity Notification",
				System.currentTimeMillis());
		CharSequence msgFrom = "jmrua";
		CharSequence msgBody = "Este es el cuerpo de la notificación";
		
		notifica.setLatestEventInfo(this, msgFrom, msgBody, pendingIntent);
		//espera 100ms, Vibra 250ms, espera 100ms, vibra otros 500ms
		notifica.vibrate = new long[]{100,250,100,500};
		notifica.flags |= Notification.FLAG_AUTO_CANCEL;
		notifica.defaults |= Notification.DEFAULT_SOUND;
		notifica.defaults |= Notification.DEFAULT_LIGHTS;
		
		nm.notify(id_aviso,notifica);
		
	}

}
