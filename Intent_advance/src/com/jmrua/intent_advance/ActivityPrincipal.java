package com.jmrua.intent_advance;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class ActivityPrincipal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
	}
	
	public void llamada_intent(View v){
		Intent intent = null;
		switch(v.getId()){
		case R.id.btnUrl: 
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.es"));
			startActivity(intent);
			break;
		case R.id.btnDial: 
			intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+34)123456789"));
			startActivity(intent);
			break;
		case R.id.btnCall: 
			intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)123456789"));
			startActivity(intent);
			break;
		case R.id.btnGeo: 
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:50.123,7.1342?z=19"));
				startActivity(intent);
				break;
		case R.id.btnImage: 
			intent = new Intent("android.media.action.IMAGE_CAPTURE");
			startActivityForResult(intent,0);
			break;
		case R.id.btnContacts: 
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
			startActivity(intent);
			break;
		
		}
	}
	
	//Llamada mediante la action definida en el itent-filter
	public void llamada_otherActivity(View v){
		Intent intent = new Intent("com.jmrua.activityDos");
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_principal, menu);
		return true;
	}
	
	@SuppressLint("ShowToast")
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(resultCode == Activity.RESULT_OK && requestCode == 0){
			String result = data.toUri(0);
			Toast.makeText(this, result, Toast.LENGTH_LONG);
		}
	}

}
