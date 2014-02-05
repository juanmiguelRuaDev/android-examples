package com.juanmi.tema9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class SQLiteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
		
		//Verify if existe data base,else copy
		try{
			String desPath = "/data/data/"+getPackageName()+"/databases/mibbdd";
			File f = new File(desPath);
			if(!f.exists()){
				copyDB(this.getBaseContext().getAssets().open("mibbdd"),new FileOutputStream(desPath));
			}
		}catch(FileNotFoundException e){
			
		}catch(IOException e){
			
		}		
		
		
		DBAdapter db = new DBAdapter(this);
		
		//inserta empleados
		db.open();
		long id = db.insertEmpleado("61123456", "Jesus", "Granada","jegra@gmail.com");
		id = db.insertEmpleado("12345673","Ana", "Martinez","ama@nada.com");
		db.close();
		
		//muestra todos los empleados
		db.open();
		Cursor c = db.consulta_todos();		
		if(c.moveToFirst()){
			do{
				muestra_empleado(c);
			}while(c.moveToNext());
		}
		db.close();
		
	}
	
	/**
	 * Copy BBDD from assets to data/data/...
	 * @param inputStream
	 * @param outputStream
	 * @throws IOException
	 */
	public void copyDB(InputStream inputStream, OutputStream outputStream)throws IOException{
		byte[] buffer = new byte[1024];
		int length;
		while((length = inputStream.read(buffer))> 0){
			outputStream.write(buffer,0,length);
		}
		inputStream.close();
		outputStream.close();
		
	}
	
	public void muestra_empleado(Cursor c){
		Toast.makeText(this, "id: "+c.getString(0)+"\n" +
				"dni: "+c.getString(1)+"\n" +
				"nombre: "+c.getString(2)+"\n" +
				"Apellidos: "+c.getString(0)+"\n" +
				"email: "+c.getString(0)+"\n" , Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite, menu);
		return true;
	}

}
