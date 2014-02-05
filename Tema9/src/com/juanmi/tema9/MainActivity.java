package com.juanmi.tema9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	Spinner spinnerPoint;
	String[] stringPoint;
	
	private static final int PREFERENCES = 1;
	private static final int STORAGEFILE = 2;
	private static final int SQLITE = 3;
	private static final int MENU = 4;
	
	public static final String LOGTAG = "Tema9";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		loadList();
		
	}
	
	private void initView(){
		spinnerPoint = (Spinner)findViewById(R.id.spinnerPoints);
		stringPoint = getResources().getStringArray(R.array.listPoints);
	}
	
	private void loadList(){
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stringPoint);
		spinnerPoint.setAdapter(adapter);
		spinnerPoint.setOnItemSelectedListener(new OnItemSelectedListener(){
			
			Intent intent = null;			
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				switch(pos){
				case PREFERENCES:
					intent = new Intent(arg1.getContext(),SimpleStorageActivity.class);
					startActivity(intent);
					break;
				case STORAGEFILE:
					intent = new Intent(arg1.getContext(),StorageFileActivity.class);
					startActivity(intent);
					break;
				case SQLITE:
					intent = new Intent(arg1.getContext(),SQLiteActivity.class);
					startActivity(intent);
					break;
				
					
				}
				//Log.i(LOGTAG, "pos:"+pos+", arg3:"+arg3+", arg1:"+arg1);
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}		
		});
	}
	
	
	private void crearMenu(Menu menu){
		menu.setQwertyMode(true);
		MenuItem mn1 = menu.add(0, 0, 0, "Opcion 1");
		{
			mn1.setAlphabeticShortcut('a');
			mn1.setIcon(R.drawable.ic_launcher);
		}
		MenuItem mn2 = menu.add(0, 1, 1, "Opcion 2");
		{
			mn2.setAlphabeticShortcut('b');
			mn2.setIcon(R.drawable.ic_launcher);
		}
		MenuItem mn3 = menu.add(0, 2, 2, "Opcion 3");
		{
			mn3.setAlphabeticShortcut('a');
			mn3.setIcon(R.drawable.ic_launcher);
		}
		MenuItem mn4 = menu.add(0, 3, 3, "Opcion 4");
		{
			mn4.setAlphabeticShortcut('a');
			mn4.setIcon(R.drawable.ic_launcher);
		}
		
		 menu.add(0, 4, 4, "Opcion 5");
		 menu.add(0, 5, 5, "Opcion 5");
		 menu.add(0, 6, 6, "Opcion 6");
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		crearMenu(menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		crearMenu(menu);
	}

}
