package com.tema10_mensajes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btnSendEmail;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnSendEmail = (Button) findViewById(R.id.btnEnviarEmail);        
        btnSendEmail.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {     
            	String[] to = {"jose@josemari.net", "ana@josemari.net"};   
                String[] cc = {"concopia@josemari.net"};           
            	sendEmail(to, cc, "Hola", "Hola, Mensaje para mis amigos");
            }
        });
    }
    
    //Enviar mensajes 
    private void sendEmail(String[] emailAddresses, String[] carbonCopies, 
    String subject, String message)
    {     
        //DEfinimos la acción
    	Intent emailIntent = new Intent(Intent.ACTION_SEND);
    	//declaramos y ponemos los parámetros de mensajes:
        emailIntent.setData(Uri.parse("mailto:")); 
        String[] to = emailAddresses;   
        String[] cc = carbonCopies; 
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);   
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);        
        emailIntent.setType("message/rfc822");   
        startActivity(Intent.createChooser(emailIntent, "Email"));
    }
}