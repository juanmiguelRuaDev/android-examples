package com.tema11_sensores;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager = null;
    private Sensor sensorDeTemperatura = null;
    private Sensor sensorDeProximidad = null;
    private Sensor sensorDeLuz = null;
    private Sensor sensorAcelerometro = null;
    private Sensor sensorDeOrientacion = null;
    private TextView textViewAcelerometro = null;
    private TextView textViewOrientacion = null;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        

        textViewAcelerometro = (TextView) findViewById(R.id.sensorDeMovimiento);
        textViewAcelerometro.setTextSize(30);

        textViewOrientacion = (TextView) findViewById(R.id.sensorDeOrientacion);
        textViewOrientacion.setTextSize(30);
        
        
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorDeProximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorDeTemperatura = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        sensorDeLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorDeOrientacion = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        
        
        if(sensorAcelerometro == null){
            Toast.makeText(getApplicationContext(), "No hay Sensor movimiento", Toast.LENGTH_SHORT).show();
    }
    else{
           	Toast.makeText(getApplicationContext(), "Hay Sensor de movimiento", Toast.LENGTH_SHORT).show();
           	sensorManager.registerListener(this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }
     
    if(sensorDeProximidad == null){
           	Toast.makeText(getApplicationContext(), "No hay Sensor de Proximidad", Toast.LENGTH_SHORT).show();
    }
    else{
           	Toast.makeText(getApplicationContext(), "Hay Sensor de Proximidad", Toast.LENGTH_SHORT).show();
           	sensorManager.registerListener(this, sensorDeProximidad, SensorManager.SENSOR_DELAY_NORMAL);
    }
     
    if(sensorDeLuz == null){
          	Toast.makeText(getApplicationContext(), "No hay Sensor de Luz", Toast.LENGTH_SHORT).show();
    }
    else{
           	Toast.makeText(getApplicationContext(), "Hay Sensor de Luz", Toast.LENGTH_SHORT).show();
           	sensorManager.registerListener(this, sensorDeLuz, SensorManager.SENSOR_DELAY_NORMAL);
    }
     
    if(sensorDeTemperatura == null){
          	Toast.makeText(getApplicationContext(), "No hay sensor de Temperatura", Toast.LENGTH_SHORT).show();
    }
    else{
           	Toast.makeText(getApplicationContext(), "Hay sensor de Temperatura", Toast.LENGTH_SHORT).show();
          	sensorManager.registerListener(this, sensorDeTemperatura, SensorManager.SENSOR_DELAY_NORMAL);
    }
     
    if(sensorDeOrientacion == null){
           	Toast.makeText(getApplicationContext(), "No hay sensor de Orientacion", Toast.LENGTH_SHORT).show();
    }
    else{
           	Toast.makeText(getApplicationContext(), "Hay sensor de Orientacion", Toast.LENGTH_SHORT).show();
           	sensorManager.registerListener(this, sensorDeOrientacion, SensorManager.SENSOR_DELAY_NORMAL);
    } }


    
    
    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }
     
    @Override
    public void onSensorChanged(SensorEvent arg0) {
    	synchronized (this){
    		float[] masData;
    		float x;
    		float y;
    		float z;
    		// TODO Auto-generated method stub
    		switch(arg0.sensor.getType()){
    			case Sensor.TYPE_PROXIMITY:
    				masData = arg0.values;
    				if(masData[0]==0){
    					textViewAcelerometro.setTextSize(textViewAcelerometro.getTextSize()+10);
    				}
    				else{
    					textViewAcelerometro.setTextSize(textViewAcelerometro.getTextSize()-10);
    				}
    				break;
                            case Sensor.TYPE_ACCELEROMETER:
                             	masData = arg0.values;
    		                x = masData[0];
    				y = masData[1];
                                    z = masData[2];
                                    textViewAcelerometro.setText("x: " + x + "\ny: "+y + "\nz: "+z);
    				break;
                   		case Sensor.TYPE_ORIENTATION:
    				masData = arg0.values;
    				x = masData[0];
    				y = masData[1];
    				textViewOrientacion.setText("x: " + x + "\ny: "+y);
    				break;
    			default:
    				break;
    			}
    		}
    	}
}