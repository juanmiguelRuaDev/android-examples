package com.tema10exercice2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText etSubject;
	EditText etTo;
	EditText etMessage;
	Button btnSend;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	/*** Init view 	 */
	private void initView(){
		etSubject = (EditText)findViewById(R.id.etSubject);
		etTo = (EditText)findViewById(R.id.etTo);
		etMessage = (EditText)findViewById(R.id.etMessage);
		btnSend = (Button)findViewById(R.id.btnSend);
	}
	
	/*** Method to validate the neccesary fields 
	 * @return <code>true</code> if subject and To contain some value, else <code>false</code> 
	 */
	private boolean validateFields(){
		boolean result = true;
		String messageError;
		//Validate subject
		if(etSubject.getText().toString().isEmpty()){
			messageError = getResources().getString(R.string.lack_subject);
			Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
			result = false;
		}
		//Validate To
		if(etTo.getText().toString().isEmpty()){
			messageError = getResources().getString(R.string.lack_destination);
			Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
			result = false;
		}		
		return result;			
	}
	
	
	/*** Method invoked from button when is pushed and send message
	 * @param v	 */
	public void sendMessage(View v){
		if(validateFields()){
			Intent emailIntent = new Intent(Intent.ACTION_SEND);
			 emailIntent.setData(Uri.parse("mailto:")); 
			 String[] to = etTo.getText().toString().split(",");
			 String subject = etSubject.getText().toString();
			 String message = etMessage.getText().toString();
			 emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
			 emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		     emailIntent.putExtra(Intent.EXTRA_TEXT, message);  
		     emailIntent.setType("message/rfc822");
		     startActivity(Intent.createChooser(emailIntent, "Email"));			 
		}		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
