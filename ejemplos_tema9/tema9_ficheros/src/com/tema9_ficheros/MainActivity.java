package com.tema9_ficheros;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
private EditText textBox; 
private static final int READ_BLOCK_SIZE = 100;

/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);       
                   
    //Cargamos las referencias de los controles
    textBox = (EditText) findViewById(R.id.txtText1);
    Button saveBtn = (Button) findViewById(R.id.btnGuardar);
    Button loadBtn = (Button) findViewById(R.id.btnCargar);        
    
    //Listener para el boton
    saveBtn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {         
        	//Cargamos el string a guardar en fichero
        	String str = textBox.getText().toString();
            try 
            {                 	
            	//Creamo sun fichero
                FileOutputStream fOut = 
                		openFileOutput ("fichero.txt",MODE_WORLD_READABLE);
                
                //Creamos el flujo con el fichero asociado
                OutputStreamWriter osw = new 
                OutputStreamWriter(fOut);
                
                //Grabamos el contenido y cerramos
                osw.write(str);              
                osw.flush(); 
                osw.close();
                
                //Todo Ok
                Toast.makeText(getBaseContext(), 
                    "Fichero guardado", 
                    Toast.LENGTH_SHORT).show();
             
                textBox.setText("");
             } 
             catch (IOException ioe) 
             { 
                //Por si se ha producido un error
            	 ioe.printStackTrace(); 
             }  
        }
    });
    
    loadBtn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            try 
            {  
            	//Definimso un flujo para el fichero con ese nombre
            	FileInputStream fIn = openFileInput("fichero.txt");                	
                InputStreamReader isr = new InputStreamReader(fIn);
                
                //declaramos un bufer para cargar los datos
                char[] inputBuffer = new char[READ_BLOCK_SIZE];
                String s = "";
 
                int charRead;
                //Bucle para leer los datos en bloques
                while ((charRead = isr.read(inputBuffer))>0)
                {                    
                    String readString = 
                        String.copyValueOf(inputBuffer, 0, charRead);                    
                    s += readString;

                   inputBuffer = new char[READ_BLOCK_SIZE];
                } 
                textBox.setText(s);
                
                //TOdo Ok
                Toast.makeText(getBaseContext(),
                    "Fichero cargado correctamente", 
                    Toast.LENGTH_SHORT).show();
            } 
            catch (IOException ioe) {
            	//Por si se ha producido un error
                ioe.printStackTrace(); 
            }            	
        }
    });
}    
}