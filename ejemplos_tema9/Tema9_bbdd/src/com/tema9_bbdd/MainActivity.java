package com.tema9_bbdd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Copia una base de datos creada de forma externa
        //try {        	
        //	String destPath = "/data/data/" + getPackageName() + "/databases/mibbdd";
        //	File f = new File(destPath);        	
        //	if (!f.exists()) {        	
		//	    CopyDB( getBaseContext().getAssets().open("mibbdd"), 
		//			new FileOutputStream(destPath));
        //	}
		//} catch (FileNotFoundException e) {			
		//	e.printStackTrace();
		//} catch (IOException e) {
		//	e.printStackTrace();

        
        DBAdapter db = new DBAdapter(this); 

        
        //Añadir
        db.open();        
        long id = db.inserta_empleado("61123456","Jesus", "lopez","mimail@mail.com");        
        id = db.inserta_empleado("61123457","Ana", "martinez","mimail@mail.com");
        db.close();
        
        
        //Consultar todos los contactos
        db.open();
        Cursor c = db.consulta_todos();
        if (c.moveToFirst())
        {
            do {          
            	muestra_empleado(c);
            } while (c.moveToNext());
        }
        db.close();
        
        
        //Consultar un contacto
        //db.open();
        //Cursor c = db.consulta(2);
        //if (c.moveToFirst())        
        //	muestra_empleado(c);
        //else
        //    Toast.makeText(this, "Empleado no encontrado", Toast.LENGTH_LONG).show();
        //db.close();
        
        
        
        //Actualizar un contacto
        //db.open();
        //if (db.updateContact(1, "11111111", "Andres", "Gomez","mail@mail.com"))
        //    Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG).show();
        //else
        //    Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();        
        //db.close();
        
        
        
        //Borrar a contact---
        //db.open();
        //if (db.borra_empleado(1))
        //    Toast.makeText(this, "Registro borrado.", Toast.LENGTH_LONG).show();
        //else
        //    Toast.makeText(this, "No se ha podido borrar.", Toast.LENGTH_LONG).show();            
        //db.close();
        
    }
        
    
        
        
    public void muestra_empleado(Cursor c)
    {
        Toast.makeText(this, 
                "id: " + c.getString(0) + "\n" +
                "DNI: " + c.getString(1) + "\n" +
                "Nombre:  " + c.getString(2)+ "\n" +
                "Apellidos:  " + c.getString(3)+ "\n" +
                "email:  " + c.getString(4)+ "\n" ,
                Toast.LENGTH_LONG).show();        
    } 
}