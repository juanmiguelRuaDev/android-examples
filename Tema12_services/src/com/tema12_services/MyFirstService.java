package com.tema12_services;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
/***
 * This service simulate a asynchronous task
 * @author juanmi
 *
 */
public class MyFirstService extends Service {
	private static final String LOGTAG = "Tema12Service";
	
	public MyFirstService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Servicio iniciado", Toast.LENGTH_LONG).show();
		
		try{
			new TareaDeFondo().execute(
					new URL("http://www.google.es"),
					new URL("http://www.lacaixa.es"),
					new URL("http://www.roca.es"),
					new URL("http://www.elcolombiando.com.co"));
		}catch(MalformedURLException e){
			e.printStackTrace();
		}
		
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		Toast.makeText(this, "Servicio parado", Toast.LENGTH_LONG).show();
		super.onDestroy();
	}
	
	private int Descarga(URL url){
		try{
			Thread.sleep(5000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		return 100;
	}
	
	
	
	/**
	 * Private class that implements the asynchronous task	 *
	 */
	private class TareaDeFondo extends AsyncTask<URL,Integer,Long>{

		@Override
		protected Long doInBackground(URL... urls) {
			int total = urls.length;
			long totalBytesDowloaded = 0;
			for(int i = 0; i < total; i++){
				totalBytesDowloaded += Descarga(urls[i]);
				publishProgress((int)((i+1)/(float)total)*100);
			}			
			return totalBytesDowloaded;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {			
			super.onProgressUpdate(values);
			Log.d(LOGTAG, String.valueOf(values[0])+ " % descargados");
			Toast.makeText(getApplicationContext(), 
					String.valueOf(values[0])+ " % descargados", 
					Toast.LENGTH_LONG).show();
		}
		@Override
		protected void onPostExecute(Long result) {			
			super.onPostExecute(result);
			Toast.makeText(getApplicationContext(), 
					"Descargado "+result+ "bytes", 
					Toast.LENGTH_LONG).show();			
			stopSelf(); // Stop the service it self
		}
		
	}
}
