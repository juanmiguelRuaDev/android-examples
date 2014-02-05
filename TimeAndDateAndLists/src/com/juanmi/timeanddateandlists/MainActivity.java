package com.juanmi.timeanddateandlists;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void openActivity(View v){
		Intent i = null;
		switch(v.getId()){
		case R.id.btn_viewDateTime:
			i = new Intent(this,DateTimeActivity.class);
			break;
		case R.id.btn_viewList:
			i = new Intent(this,ListActivity.class);
			break;
		}
		this.startActivity(i);
		
	}

}
