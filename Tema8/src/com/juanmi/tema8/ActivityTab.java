package com.juanmi.tema8;

import android.app.TabActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

public class ActivityTab extends TabActivity {
	SeekBar seekBar;
	TextView seekBarLabel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);	
		
		initSeekBar();
		
		
		
		Resources res = getResources();		
		//Referencia al contol tabhost
		TabHost ficha = getTabHost();
		
		//inciamos la configuración
		ficha.setup();
		
		//añadimos una ficha con el nombre tab uno y el contenido "tab1"
		TabHost.TabSpec spec = ficha.newTabSpec("Ficha1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Reloj");
		ficha.addTab(spec);
		
		//añadimos una ficha con el nombre tab uno y el contenido "tab2" con imagen
		spec = ficha.newTabSpec("tag2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Botón",res.getDrawable(R.drawable.ic_launcher));
		ficha.addTab(spec);
		
		//añadimos una ficha con el nombre tab uno y el contenido "tab2"
		spec = ficha.newTabSpec("tag3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Input");
		ficha.addTab(spec);
		
		
	}
	/**
	 * Init seekBar implementation
	 */
	private void initSeekBar(){
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBarLabel=  (TextView)findViewById(R.id.textSeekBarLabel);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				Integer valor = seekBar.getProgress();
				seekBarLabel.setText(valor.toString());
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	/**
	 * Method invoked from button of tab2 and create a new Tab
	 * @param v
	 */
	public void addNewTab(View v){
		TabHost ficha = getTabHost();
		//inciamos la configuración
		ficha.setup();	
		TabHost.TabSpec spec = ficha.newTabSpec("F1");
		spec.setContent(new TabHost.TabContentFactory() {			
			@Override
			public View createTabContent(String arg0) {
				return (new AnalogClock(ActivityTab.this));
			}
		});
		spec.setIndicator("newTab");
		ficha.addTab(spec);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab, menu);
		return true;
	}	

}
