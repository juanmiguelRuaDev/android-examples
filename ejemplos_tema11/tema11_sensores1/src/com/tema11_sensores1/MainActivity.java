package com.tema11_sensores1;

import java.util.List;

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
    private Sensor sensor_temperatura = null;
    private Sensor sensor_proximidad = null;
    private Sensor sensor_luz = null;
    private Sensor sensor_acelerometro = null;
    private Sensor sensor_orientacion = null;
    private TextView txt_acelerometro = null;
    private TextView txt_orientacion = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        
        txt_acelerometro = (TextView) findViewById(R.id.txt_sensormovimiento);    
        txt_orientacion  = (TextView) findViewById(R.id.txt_sensororientacion);
        
        //Cargamos los sensores
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_proximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensor_temperatura = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        sensor_luz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensor_acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor_orientacion = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        //Comprobamos si hay instalados
        comprueba_sensores();
       
 }

    void comprueba_sensores (){        
        String cadena="hola";
    	
    	//Vamos comprobando y creamos los listener de cada sensor
    	if(sensor_acelerometro == null)
            cadena ="No hay Sensor movimiento" + "\n";
    	else{
    		cadena ="No hay Sensor movimiento" + "\n";
              sensorManager.registerListener(this, sensor_acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    		}
     
    	if(sensor_proximidad == null)
    		cadena +="No hay Sensor de Proximidad" + "\n";
    	else{
    		cadena +="Hay Sensor de Proximidad" + "\n";
              sensorManager.registerListener(this, sensor_proximidad, SensorManager.SENSOR_DELAY_NORMAL);
    	}
     
    	if(sensor_luz == null)
    		cadena +="No hay Sensor de Luz" + "\n";
    	else{
    		cadena +="Hay Sensor de Luz" + "\n";
              sensorManager.registerListener( this, sensor_luz, SensorManager.SENSOR_DELAY_NORMAL);
    	}
     
    	if(sensor_temperatura == null)
    		cadena +="No hay sensor de Temperatura" + "\n";
    	else{
    		cadena +="Hay sensor de Temperatura" + "\n";
            sensorManager.registerListener( this, sensor_temperatura, SensorManager.SENSOR_DELAY_NORMAL);
    	}
     
    	if(sensor_orientacion == null)
    		cadena +="No hay sensor de Orientacion" + "\n";
    	else{
    		cadena +="Hay sensor de Orientacion" + "\n";
             sensorManager.registerListener( this, sensor_orientacion, SensorManager.SENSOR_DELAY_NORMAL);
    	}
    	txt_acelerometro.setText(cadena);
    }
    
    
    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }
     
    @Override
    public void onSensorChanged(SensorEvent arg0) {
    	synchronized (this){
    		float[] datos;
    		float x;
    		float y;
    		float z;
    		// TODO Auto-generated method stub
    		switch(arg0.sensor.getType()){
    			//Leemos los datos de los sensores, según se haya activado
    			case Sensor.TYPE_PROXIMITY:
    				//Recogemos los datos
    				datos = arg0.values;
    				//Vemos si hay cambios en la proximidad 
    				if(datos[0]==0){
    					 txt_acelerometro.setTextSize( txt_acelerometro.getTextSize()+10);
    				}
    				else{
    					 txt_acelerometro.setTextSize( txt_acelerometro.getTextSize()-10);
    				}
    				break;
                case Sensor.TYPE_ACCELEROMETER:
    				//Recogemos los datos                	
                	datos= arg0.values;
    		        x = datos[0];
    				y = datos[1];
                    z = datos[2];
                    //Tenemos los tres datos: x, y z
                    txt_acelerometro.setText("x: " + x + "\ny: "+y + "\nz: "+z);
    				break;
                 case Sensor.TYPE_ORIENTATION:
     				//Recogemos los datos                	 
                 	datos= arg0.values;
    				x = datos[0];
    				y = datos[1];
    				txt_orientacion.setText("x: " + x + "\ny: "+y);
    				break;
    			default:
    				break;
    			}
    		}
    	}
   
}