package com.tema12_services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btnStartService;
	Button btnDestroyService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
		//Setting the listener in btnStartService button
		btnStartService.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				startService(new Intent(getBaseContext(),MyFirstService.class));				
			}			
		});
		
		//Setting the listener in btnDestroyService
		btnDestroyService.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				stopService(new Intent(getBaseContext(),MyFirstService.class));				
			}			
		});
	}
	
	private void init(){
		btnStartService = (Button)findViewById(R.id.btnStartService);
		btnDestroyService = (Button)findViewById(R.id.btnStopService);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
