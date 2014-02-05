package com.jmrua.userappintents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityPrincipal extends Activity {

	Button btnOpenNav;
	Button btnCall;
	Button btnShowContacts;
	EditText etIntentValue;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		initViews();
	}

	private void initViews(){
		btnOpenNav = (Button)findViewById(R.id.btnOpenNav);
		btnCall = (Button)findViewById(R.id.btnCall);
		btnShowContacts = (Button)findViewById(R.id.btnShowContacts);
		etIntentValue = (EditText)findViewById(R.id.et_value);
	}
	
	
	public void invokeIntent(View v){			
		Intent intent = null;
			switch(v.getId()){			
			case R.id.btnShowContacts:
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
				startActivity(intent);
				break;
			default:
				startCallOrViewIntent(v.getId(),intent);
				break;
				
			}		
	}	
	
	private void startCallOrViewIntent(int id, Intent intent){
		if(hasEditTextValue()){
			switch (id){
			case R.id.btnOpenNav:
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse(etIntentValue.getText().toString()));
				startActivity(intent);
				break;
			case R.id.btnCall:
				intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+etIntentValue.getText().toString()));
				startActivity(intent);
				break;
			}
		}else Toast.makeText(this, getString(R.string.txt_alert), Toast.LENGTH_LONG).show();
			
			
	}
	
	private boolean hasEditTextValue(){
		return !etIntentValue.getText().toString().isEmpty();
	}

}
