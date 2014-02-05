package com.jmrua.unitsixexone;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	protected static final String LOGTAG = "UnitSixExOne";
	protected static final String NAME_KEY = "name";
	protected static final String REPS_KEY = "reps";
	Button btnOpenActivity;
	Button btnSendNotification;
	EditText et_name;
	EditText et_replay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
	}
	
	
	
	private void initViews()
	{		
		btnOpenActivity = (Button)findViewById(R.id.btn_send);
		btnSendNotification = (Button)findViewById(R.id.btn_notification);
		et_name = (EditText)findViewById(R.id.et_name);
		et_replay = (EditText)findViewById(R.id.et_replay);
	}
	
	public void openActivityReplay(View v){
		if(this.hasValues()){
			Intent i = new Intent(this,ActivityReplay.class);
			Bundle b = new Bundle();
			b.putString(NAME_KEY, et_name.getText().toString());
			b.putInt(REPS_KEY, Integer.parseInt(et_replay.getText().toString()));
			i.putExtras(b);
			this.startActivity(i);
		}else
			Toast.makeText(this, "Insert some text please", Toast.LENGTH_LONG).show();
	}
	
	
	private boolean hasValues(){
		return (!et_name.getText().toString().isEmpty() &&
				!et_replay.getText().toString().isEmpty());
	}
	
	public void sendNotification(View v){
		
	}



}
