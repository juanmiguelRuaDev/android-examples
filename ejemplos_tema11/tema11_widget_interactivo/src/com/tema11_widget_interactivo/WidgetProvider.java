package com.tema11_widget_interactivo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class WidgetProvider extends AppWidgetProvider {

	private static final String ACTION_cambiar_icono = "cambiamos icono";
	static int pantalla=0; 
	public String str_estado="estado"; 
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		
		  if (intent.getAction().equals(ACTION_cambiar_icono)) {
		         //Leemos el valor almacenado
				 SharedPreferences preferencias = context.getSharedPreferences(str_estado, Context.MODE_PRIVATE);
				 String mensaje = preferencias.getString(str_estado, "error");
				 SharedPreferences.Editor editor = preferencias.edit();
		 
				 String nuevo_estado="";
				 //Cambiamos el valor actual
		         if (mensaje.equals("activado")){
		        	 editor.putString(str_estado , "desactivado");
		             editor.commit();
		             nuevo_estado= "desactivado";		 
		         }
		         else if (mensaje.equals("desactivado")){
		        	 editor.putString(str_estado , "activado");
		             editor.commit();
		             nuevo_estado= "activado";		 
		        	 }
		         //Ponemos la pantalla adecuada
		         AppWidgetManager widgetManager =AppWidgetManager.getInstance(context);
		         actualizarWidget( context,widgetManager ,nuevo_estado);
		   }
		 
		   super.onReceive(context, intent);
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		 //Leemos el estado actual
		 SharedPreferences preferencias =context.getSharedPreferences(str_estado, Context.MODE_PRIVATE);
		 String mensaje = preferencias.getString(str_estado, "no_hay");

		 if (mensaje=="no_hay"){ 
			 //Si es la primera vez no habrá valor de preferencias, le ponemos "desactivado"
			 SharedPreferences.Editor editor = preferencias.edit();
			 editor.putString(str_estado, "desactivado"); 
			 editor.commit();

			 mensaje="desactivado";
		 }

		 //Ponemos la imagen que le toque
		 actualizarWidget(context, appWidgetManager, mensaje); 

		   }
	
	 public static void actualizarWidget(Context context,AppWidgetManager appWidgetManager, String value)
		{	
	       RemoteViews vista ; 
	       ComponentName este_Widget;
	 
	       int pantalla=0;
	 
	       //Ponemos el icono con las gafas
	       if (value.equals("activado")){
	    	    pantalla= R.layout.widget_activado;
	       }
	       //Ponemos el icono sin las gafas
	       if (value.equals("desactivado")){
	    	    pantalla= R.layout.widget_desactivado;
	       }

	       //COgemos una referencia de las pantalla
           vista = new RemoteViews(context.getPackageName(), pantalla);
           //identifica a nuestro widget
		   este_Widget = new ComponentName(context, WidgetProvider.class);
	 
		   //Creamos un intent a nuestro widget. No hay que llamar al main!
		   Intent intent = new Intent(context, WidgetProvider.class);
		   //seleccionamos la accion ACTION_cambiarlayout
           intent.setAction(ACTION_cambiar_icono); 
 
           //En espera de interactuar con el usuario
           PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);           
           vista.setOnClickPendingIntent(R.id.widget_root, pendingIntent);
 
           //actualizamos el widget
           appWidgetManager.updateAppWidget(este_Widget, vista);
           
           //Para verlo mejor, ponemos un toast
           Toast.makeText(context,value,Toast.LENGTH_SHORT).show();
		}
}