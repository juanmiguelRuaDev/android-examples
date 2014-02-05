package com.juanmi.timeanddateandlists;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class ListActivity extends Activity{

	ListView listCiudades;
	String[] ciudades ;
	Spinner spinnerCities;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		initView();
		initListView();
		initSpinnerView();
		
		
	}	
	
	/**
	 * init method to init the view
	 */
	private void initView(){
		listCiudades = (ListView)findViewById(R.id.list_countries);		
		spinnerCities = (Spinner)findViewById(R.id.spinner_cities);
		ciudades = this.getResources().getStringArray(R.array.list_cities);
	}
	
	
	/**
	 * Init functionality of spinner list
	 */
	private void initSpinnerView(){
		//Creating adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ciudades);				
		spinnerCities.setAdapter(adapter);
		
		//Setting selected listener on each item
		spinnerCities.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				StringBuffer text = new StringBuffer("The city selected is:");
				text.append(ciudades[pos]);
				Toast.makeText(getBaseContext(), text.toString(), Toast.LENGTH_SHORT).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	
	/**
	 * Init method to init the list view 
	 */
	private void initListView(){
		//Creating adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, ciudades);
		listCiudades.setAdapter(adapter);
				
		//Setting click listener on each item 
		listCiudades.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int pos,
							long id) {				
					StringBuffer text = new StringBuffer("The city selected is:");
					text.append(ciudades[pos]);
					Toast.makeText(getBaseContext(), text.toString(), Toast.LENGTH_SHORT).show();						
			}					
		});
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
