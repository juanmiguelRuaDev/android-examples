package com.tema10_contenidos;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        //Definimos el objeto URI para acceder al primer elemento
        //de la agenda de contactos
        Uri todos_Contactos = Uri.parse("content://contacts/people/1");
               
        //Creamos un cursor con la consulta de la URI definida antes
        Cursor c = managedQuery(todos_Contactos, null, null, null, null);
        
        //Creamos una matriz de dos dimensaiones
        String[] columnas = new String[] 
        		{ContactsContract.Contacts.DISPLAY_NAME, 
        		ContactsContract.Contacts._ID};
        //Creamos una matriz con lo retornaría de un listener
        int[] views = new int[] {R.id.txt_contacto, R.id.txt_ID};
        
        //Creamos el adaptador
        SimpleCursorAdapter adapter = 
            new SimpleCursorAdapter(this, R.layout.main, c, columnas, views);
        this.setListAdapter(adapter);
    }	
}        
        