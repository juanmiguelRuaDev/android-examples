package com.tema9_preferencias;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	
    private SharedPreferences prefs;
    private String prefName = "MisPreferencias";
    private EditText editText;
    private SeekBar barra;
    private Button btn;
    
    //Delcaramos dos constantes para almacenar estas preferencias de usuario
    private static final String FONT_SIZE_KEY = "fonttam";
    private static final String TEXT_VALUE_KEY = "valortexto";
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
        //Recogemos los controles de los recursos
        editText = (EditText) findViewById(R.id.EditText1);
        barra= (SeekBar) findViewById(R.id.barra);
        btn = (Button) findViewById(R.id.btnguardar);
        
        //en el evenbto clic grabamos las preferencias
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//Recogemos una referencia para las preferencias
            	prefs = getPreferences(MODE_PRIVATE);
            	
            	//Activamos la edicion
                SharedPreferences.Editor editor = prefs.edit();
                
                //Escribimos un valor float y otro string
                editor.putFloat(FONT_SIZE_KEY, editText.getTextSize());  
                editor.putString(TEXT_VALUE_KEY, editText.getText().toString());
                
                //Volcamos los datos
                editor.commit();       
                
                Toast.makeText(getBaseContext(), 
                    "¡Datos almacenados!", 
                    Toast.LENGTH_SHORT).show();
            }
        });        
        
        //Al iniciarse el programa cogemos las preferencias de los usuarios
        prefs = getPreferences(MODE_PRIVATE);
        
        //ponemos valores a los tamaños de las letras y contenido
        float fontSize = prefs.getFloat(FONT_SIZE_KEY, 12);
        
        barra.setProgress((int) fontSize);
        editText.setText(prefs.getString(TEXT_VALUE_KEY, ""));
        editText.setTextSize(barra.getProgress());
        
        
        //Listener para cambiar dinamicamente el tamaño de las letras
        barra.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {								
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				//Le ponemos el valor de la barra
				editText.setTextSize(progress);
			}
		}); 
    }
}