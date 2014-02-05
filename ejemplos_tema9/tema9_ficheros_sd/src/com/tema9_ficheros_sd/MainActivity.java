package com.tema9_ficheros_sd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
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
               
              
        textBox = (EditText) findViewById(R.id.txtText1);
        Button saveBtn = (Button) findViewById(R.id.btnGuardar);
        Button loadBtn = (Button) findViewById(R.id.btnCargar);        
        
        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {         
            	
            	String str = textBox.getText().toString();
                try 
                {                 	
                	//Cargamos los datos del dispositivo externo
                    File sdCard = Environment.getExternalStorageDirectory();
                	File directory = new File (sdCard.getAbsolutePath() + "/MisArchivos");
                	directory.mkdirs();
                	 //Creamos el flujo con el fichero asociado en la SD
                	File file = new File(directory, "textfile.txt"); 
                    FileOutputStream fOut = new FileOutputStream(file);
                    
                    OutputStreamWriter osw = new 
                    OutputStreamWriter(fOut);
                    //Grabamos el contenido y cerramos
                    osw.write(str);              
                    osw.flush(); 
                    osw.close();
                  //Todo Ok
                    Toast.makeText(getBaseContext(), 
                        "Fichero guardado correctamente", 
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
                	//Cargamos los datos del almacenamiento externo 
                	File sdCard = Environment.getExternalStorageDirectory();
                	File directory = new File (sdCard.getAbsolutePath() + "/MyFiles");
                	//Definimso un flujo para el fichero con ese nombre
                	File file = new File(directory, "textfile.txt");
                	FileInputStream fIn = new FileInputStream(file);                	
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
                        "File loaded successfully!", 
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