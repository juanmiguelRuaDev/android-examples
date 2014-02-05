package com.juanmi.llamadas;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityPrincipal extends Activity {

	
	Button navegar;
	Button llamar;
	Button mapas;
	Button contacts;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_principal);
		
		navegar = (Button)findViewById(R.id.navegar_button);
		llamar = (Button)findViewById(R.id.llamar_button);
		mapas = (Button)findViewById(R.id.mapas_button);
		contacts = (Button)findViewById(R.id.contactos_button);
		
		navegar.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.es"));
				startActivity(intent);
			}			
		});
		
		llamar.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(34)12345678"));
				startActivity(intent);
			}			
		});
		
		mapas.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:50.123,7.134?z=19"));
				startActivity(intent);
			}			
		});
		
		contacts.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
				startActivity(intent);
			}			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_principal, menu);
		return true;
	}

}
