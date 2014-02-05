package com.example.holamundo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	Button btnWrite;
	ToggleButton btnChangeSize;
	TextView textView;
	EditText inputText;
	CheckBox checkTexto;
	RadioGroup radiogroup;
	Button btnOpenDialogActivity;
	private static final String LOGTAG = "holamundo";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		Log.d(LOGTAG, "Estoy ahora mismoe en onCreate()");
		findViews();
		setOnclickListeners();
		
		
		
		
	}
	
	private void findViews(){
		btnWrite = (Button)this.findViewById(R.id.btnWriteText);
		btnChangeSize = (ToggleButton)this.findViewById(R.id.btnZise);
		textView = (TextView)this.findViewById(R.id.Texto);
		inputText = (EditText)this.findViewById(R.id.inputText);
		checkTexto = (CheckBox)this.findViewById(R.id.check_texto);
		radiogroup = (RadioGroup)this.findViewById(R.id.radioGroup1);
		btnOpenDialogActivity = (Button)this.findViewById(R.id.btnOpenDialogActiviy);
	}
	
	
	
	private void setOnclickListeners(){
		
		btnChangeSize.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(btnChangeSize.isChecked()){
					textView.setTextSize(40);
				}else{
					textView.setTextSize(20);
				}				
			}			
		});
		
		btnOpenDialogActivity.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,OpenDialogActivity.class);
				startActivity(intent);
			}			
		});
		
		btnWrite.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				textView.setText(inputText.getText().toString());				
			}			
		});
		
		checkTexto.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean checked) {				
				if(checked)textView.setTextSize(30);
				else textView.setTextSize(20);				
			}			
		});
		
		radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup arg0, int idRadio) {				
				Log.d(LOGTAG, "idRadio :"+idRadio);
				RadioButton radio = (RadioButton)findViewById(idRadio);
				String tag = (String) radio.getTag();
				if("50".equals(tag))textView.setTextSize(50);
				else if("60".equals(tag))textView.setTextSize(60);
				else if("70".equals(tag))textView.setTextSize(70);
			}			
		});
		
		
		
	}

	

}
