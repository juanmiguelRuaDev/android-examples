package com.juanmi.tema9;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class SimpleStorageActivity extends Activity {

	SeekBar seekSimple;
	Button btnSave;
	EditText etSimple;
	private SharedPreferences prefs;
	private static final String PREFERENCESNAME = "myPreferences";
	
	//Constantes de las preferencias
	private static final String FONT_SIZE_KEY = "fonttam";
	private static final String TEXT_VALUE_KEY = "valortexto";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_storage);
		initView();
		setListeners();
	}
	
	/**
	 * Method to init all activity view
	 */
	private void initView(){
		seekSimple = (SeekBar) findViewById(R.id.seekSimple);
		btnSave = (Button)findViewById(R.id.btnSimple);		
		etSimple = (EditText)findViewById(R.id.et_simpleText);
		//cargamos los valores si los tienes almacenados
		prefs = getPreferences(MODE_PRIVATE);
		float size = prefs.getFloat(FONT_SIZE_KEY, 12);
		etSimple.setTextSize(size);
		etSimple.setText(prefs.getString(TEXT_VALUE_KEY, ""));
		seekSimple.setProgress((int)size);
	}
	
	/**
	 * This method is used to set all listeners to necessaries views
	 */
	private void setListeners(){
		seekSimple.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
				etSimple.setTextSize(progress);			
			}
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {}
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {}			
		});
	}
	
	/**
	 * Method invoked when the <code>btnSave</code> is clicked
	 * @param vi
	 */
	public void onClickSave(View vi){
		prefs = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putFloat(FONT_SIZE_KEY, this.etSimple.getTextSize());
		editor.putString(TEXT_VALUE_KEY, etSimple.getText().toString());
		//Volcamos los datos
		editor.commit();		
		Toast.makeText(this, "Datos almacenados!!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simple_storage, menu);
		return true;
	}

}
