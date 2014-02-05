package com.tema10_contenidos3;

import android.app.Activity;
import android.os.Bundle;

import android.app.ListActivity;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Uri todos_contactos = ContactsContract.Contacts.CONTENT_URI;
        
        String[] projection = new String[] 
                {ContactsContract.Contacts._ID, 
                 ContactsContract.Contacts.DISPLAY_NAME,
                 ContactsContract.Contacts.HAS_PHONE_NUMBER};
       
        
        Cursor c = managedQuery(
        		todos_contactos, projection, 
        		ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?", 
        		new String[] {"%Jose"} , 
        		ContactsContract.Contacts.DISPLAY_NAME + " ASC");


                
        String[] columns = new String[] {ContactsContract.Contacts._ID, 
        		ContactsContract.Contacts.DISPLAY_NAME,};
        int[] views = new int[] {R.id.txt_contacto, R.id.txt_ID};
        
        SimpleCursorAdapter adapter = 
            new SimpleCursorAdapter(this, R.layout.main, c, columns, views);
        this.setListAdapter(adapter);
        
        Escribe_contactos(c);  
    }    
    
    private void Escribe_contactos(Cursor c)
    {
    	if (c.moveToFirst()) {
            do{
            	String contactID = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
            	String contactDisplayName = 
            			c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            	
                Log.v("Tema10", contactID + ", " + contactDisplayName);
                //---get phone number---
                int hasPhone = c.getInt(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if (hasPhone == 1) {
                    Cursor phoneCursor = getContentResolver().query(
      		            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, 
      		            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactID, null, null);                     
      	            while (phoneCursor.moveToNext()) {
      	        	    Log.v("Tema10",
                            phoneCursor.getString(phoneCursor.getColumnIndex
                            		(ContactsContract.CommonDataKinds.Phone.NUMBER)));      	        	
      	            } 
      	            phoneCursor.close();
      	        }
            } while (c.moveToNext());
        }
    }
}
