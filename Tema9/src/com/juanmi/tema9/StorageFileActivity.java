package com.juanmi.tema9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class StorageFileActivity extends Activity {

	EditText etTextToWrite;
	Button btnToSave;
	Button btnToLoad;
	RadioGroup radioGroupMode;
	
	private static final int READ_BLOCK_SIZE = 100;
	private static final String FILE_NAME = "fichero.txt";
	public static  enum STORAGEMODE{INTERAL, EXTERNAL};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_storage_file);
		initView();
	}
	
	/**
	 * Method to init all activity view
	 */
	private void initView(){
		etTextToWrite = (EditText)findViewById(R.id.et_fileStorage);
		btnToSave = (Button)findViewById(R.id.btnSaveFile);
		btnToLoad = (Button)findViewById(R.id.btn_loadFile);
		radioGroupMode = (RadioGroup)findViewById(R.id.rgStorageMode);
	}
	
	private STORAGEMODE getStorageMode(){
		STORAGEMODE mode = STORAGEMODE.INTERAL;
		mode = (radioGroupMode.getCheckedRadioButtonId() == R.id.externalOption)?
				STORAGEMODE.EXTERNAL:mode;		
		return mode;
	}
	
	
	private FileInputStream getFileInputStream(){
		FileInputStream fin = null;
		
		try {
			if(getStorageMode().equals(STORAGEMODE.INTERAL))
				fin = openFileInput(FILE_NAME);
			else{
				File sdCard = Environment.getExternalStorageDirectory();
				Toast.makeText(getApplicationContext(), sdCard.getAbsolutePath(), Toast.LENGTH_SHORT).show();
				Log.i(MainActivity.LOGTAG, sdCard.getAbsolutePath());
				File directory = new File(sdCard.getAbsolutePath()+"/DCIM/MyFiles");
				File file = new File(directory,FILE_NAME);
				fin = new FileInputStream(file);
			}
		} catch (FileNotFoundException e) {
			Log.e(MainActivity.LOGTAG, e.getMessage());	
		}
		
		
		return fin;
	}
	
	/**
	 * Method invoked from button to save in file
	 * @param v
	 */
	public void saveInFile(View v){
		String textToSave = etTextToWrite.getText().toString();
		try {
			//Abrimos fichero o se crea si no existe
			FileOutputStream fout = openFileOutput(FILE_NAME, MODE_WORLD_READABLE);			
			//Abrimos flujo 
			OutputStreamWriter osw = new OutputStreamWriter(fout);
			osw.write(textToSave);
			osw.flush();
			osw.close();			
			//Ok
			Toast.makeText(getBaseContext(), "Todo Ok", Toast.LENGTH_SHORT).show();
			etTextToWrite.setText("");
		} catch ( IOException e) {
			Log.e(MainActivity.LOGTAG, e.getMessage());			
		}
	}
	
	
	/**
	 * Method invoked from button to load the file stored
	 * @param v
	 */
	public void loadFromFile(View v){
		try {
			FileInputStream fin = getFileInputStream();
			InputStreamReader isr = new InputStreamReader(fin);
			
			//declaramos un buffer para cargar los datos
			char[] inputbuffer = new char[READ_BLOCK_SIZE];
			String s = "";
			
			int charRead;
			while((charRead = isr.read(inputbuffer))> 0){
				String readString = String.copyValueOf(inputbuffer,0,charRead);
				s += readString;
				inputbuffer = new char[READ_BLOCK_SIZE];
			}
			etTextToWrite.setText(s);
			isr.close();
			//Ok
			Toast.makeText(getBaseContext(), "Fichero cargado correctamente", Toast.LENGTH_SHORT).show();
			
			
		} catch (IOException e) {
			Log.e(MainActivity.LOGTAG, e.getMessage());	
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.storage_file, menu);
		return true;
	}

}
