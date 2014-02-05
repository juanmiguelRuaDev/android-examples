package com.jmrua.orientacion;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.widget.Toast;

public class ActivityPrincipal extends Activity {
	private static final String LOGTAG = "Orientacion";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		Display d = this.getWindowManager().getDefaultDisplay();
		String mensaje = (d.getWidth()>d.getHeight())?"Horizontal":"Vertical";
		Toast.makeText(this, "Orientation "+mensaje, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onSaveInstanceState(Bundle estado){
		estado.putString("id", "12345678");
	}
	
	public void onRestoreInstanceState(Bundle estado){
		String id = estado.getString("id");
		Log.i(LOGTAG, id + " Restored");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_principal, menu);
		return true;
	}

}
