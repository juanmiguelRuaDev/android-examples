package com.juanmi.tema8;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class GridActivity extends Activity {

	private String[] gridValues;
	private GridView gridTechnologies;
	private TextView txtSelected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		intViews();
		loadGrid();
		
	}
	
	/**
	 * inicializamos las VIEWS
	 */
	private void intViews(){
		gridValues = getResources().getStringArray(R.array.gridList);
		gridTechnologies = (GridView) findViewById(R.id.gridTechnos);
		txtSelected = (TextView)findViewById(R.id.textSelected);
	}
	
	/**
	 * Inicializamos los valores del GRID y le asignamos un listener
	 */
	private void loadGrid(){
		gridTechnologies.setAdapter(new ArrayAdapter<String>(this,R.layout.cell,gridValues));
		gridTechnologies.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				txtSelected.setText(gridValues[pos]);
				
			}			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid, menu);
		return true;
	}

}
