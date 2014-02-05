package com.jmrua.userapplication;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

public class ActivityPrincipal extends Activity {

	private static final String LOGTAG = "UserApplication";
	ImageButton btn = null;
	CheckBox cb_adult = null;
	RadioGroup rg_maritalStatus = null;
	EditText et_maritalResult = null;
	EditText et_adultResult = null;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		initViews();
	}
	
	private void initViews()
	{
		
		btn = (ImageButton)findViewById(R.id.button1);
		cb_adult = (CheckBox)findViewById(R.id.check_adult);
		rg_maritalStatus = (RadioGroup)findViewById(R.id.radiogroup_marital);
		et_maritalResult = (EditText)findViewById(R.id.et_maritalResult);
		et_adultResult = (EditText)findViewById(R.id.et_adultResut);
	}
	
	public void setTextViewResults(View v){
		if(v.getId() == R.id.button1){
			Log.i(LOGTAG, "Setting editText values");
			setAdultResult();
			setMaritalResult();
						
		}	
	}
	
	private void setAdultResult(){
		Log.i(LOGTAG, "\t Adult Result");
		//Adult Value
		String adultResult = " mayor de edad";			
		adultResult = (cb_adult.isChecked())?
				"Es"+adultResult:"No es"+adultResult;
		et_adultResult.setText(adultResult);
	}
	
	private void setMaritalResult(){
		Log.i(LOGTAG, "\t Marital Result");
		int radioId = rg_maritalStatus.getCheckedRadioButtonId();
		switch(radioId){
		case R.id.radio_single:
			et_maritalResult.setText("Está soltero");
			break;
		case R.id.radio_marrided:
			et_maritalResult.setText("Está casado");
			break;
		case R.id.radio_other:
			et_maritalResult.setText("Otro");
			break;
			
		}
		
	}
	
	

	

}
