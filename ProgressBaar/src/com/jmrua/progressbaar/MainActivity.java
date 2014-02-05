package com.jmrua.progressbaar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private static int progreso;
	private ProgressBar progressbar;
	private int estado_progreso = 0;
	AutoCompleteTextView autocomplet;
	private Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		progressbar = (ProgressBar) findViewById(R.id.progressBar1);
		autocomplet = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		
		//implementación de la barra de progreso
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(estado_progreso<10){
					estado_progreso = hacer_algo();
				}				
				handler.post(new Runnable(){
					@Override
					public void run() {
						//0:visible, 4:invisible, 8:hecho
						progressbar.setVisibility(8);						
					}					
				});				
			}			
			private int hacer_algo(){
				try{
					Thread.sleep(1000);					
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				return progreso++;
			}			
		}).start();
		
		//implementación de autocomplet text view
		String[] lista = {"enero","enebrero", "febrero", "marzo", "abril", "mayo", "junio","julio",
				"agosto","septiembre","octubre","noviembre","diciembre"};
		
		//Creamos un adaptador de strings para alimentar el autocompleto
		//(context,tipus view, array datos)
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_gallery_item,lista);
		
		autocomplet.setThreshold(3);
		autocomplet.setAdapter(adaptador);
		
		
		
		
		
		
		
	}

	

}
