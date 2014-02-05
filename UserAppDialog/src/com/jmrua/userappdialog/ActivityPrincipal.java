package com.jmrua.userappdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ActivityPrincipal extends Activity {

	EditText edit_result;
	ImageButton btn_button1;
	private final String LOGTAG = "UserAppDialog";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		initViews();
		
	}
	
	private void initViews(){
		edit_result = (EditText)findViewById(R.id.et_adultResut);
		btn_button1 = (ImageButton)findViewById(R.id.button1);
		
	}
	
	public void openDialog(View v){
		if(v.getId() == R.id.button1)
			this.showDialog(0);
	}	
	
	protected Dialog onCreateDialog(int i){
		final int  SOLTERO=0,CASADO=1,OTROS=2;
		final CharSequence[] items = {getString(R.string.txt_single), 
				getString(R.string.txt_married), 
				getString(R.string.txt_other)};
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle(getString(R.string.txt_DialogTitle));
		dialog.setSingleChoiceItems(items, SOLTERO, new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {				
				switch(which){
				case SOLTERO:
					edit_result.setText(getString(R.string.txt_marital_status)
							+items[SOLTERO].toString());
					break;
				case CASADO:
					edit_result.setText(getString(R.string.txt_marital_status)
							+items[CASADO].toString());
					break;
				case OTROS:
					edit_result.setText(getString(R.string.txt_marital_status)
							+items[OTROS].toString());
					break;
				}				
			}
		});
		dialog.setPositiveButton(getString(R.string.txt_acept), new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {				
			}
		});
		
		dialog.setNegativeButton(getString(R.string.txt_cancel), new DialogInterface.OnClickListener(){			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				edit_result.setText("");
				Toast.makeText(getBaseContext(),getString(R.string.txt_canceled), Toast.LENGTH_LONG).show();
				
			}
		});
		
		return dialog.create();
		
	}

	

}
