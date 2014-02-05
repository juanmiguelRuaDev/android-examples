package com.example.holamundo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class OpenDialogActivity extends Activity {

	CharSequence items[] ={"Madrid", "Logroño", "Barcelona"};
	boolean[] itemschecked = new boolean[items.length];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_open_dialog);
		
		Button btn = (Button)findViewById(R.id.btnOpenDialog);
		btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				//showDialog(0);
				Toast.makeText(getBaseContext(), "Hola que tal", Toast.LENGTH_LONG).show();
			}
			
		});
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.open_dialog, menu);
		return true;
	}
	
	
}
