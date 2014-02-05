package com.juanmi.keyboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		
		switch(keyCode){
		case KeyEvent.KEYCODE_DPAD_CENTER:
			Toast.makeText(	getBaseContext(), "Tecla central DPAD",Toast.LENGTH_LONG).show();
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			Toast.makeText(	getBaseContext(), "Tecla abajo DPAD",Toast.LENGTH_LONG).show();
			break;
		case KeyEvent.KEYCODE_HOME:
			Toast.makeText(	getBaseContext(), "Tecla HOME",Toast.LENGTH_LONG).show();
			break;
		}
		
		return false;
	}

}
