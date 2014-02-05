package com.juanmi.timeanddateandlists;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class DateTimeActivity extends Activity {

	private TimePicker tp_controlHora;
	private static final int TIMER_DIALOG_ID = 0;
	private static final int DATE_DIALOG_ID = 1;
	
	

	
	private DatePicker dp_controlFecha;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_time);		
		initView();
		setCurrentTime();
		//set 24h format and hide button that say AM/PM
		tp_controlHora.setIs24HourView(true);
	}	
	
	/**
	 * Init all views defined in layout
	 */
	private void initView()
	{
		this.tp_controlHora = (TimePicker) findViewById(R.id.timePicker1);
		this.dp_controlFecha = (DatePicker) findViewById(R.id.datePicker1);
	}
	
	/**
	 * set the current time in TimePicker 
	 */
	private void setCurrentTime(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		tp_controlHora.setCurrentHour(date.getHours());
		tp_controlHora.setCurrentMinute(date.getMinutes());
		dp_controlFecha.updateDate(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * Method invoke from button that show the current time and date
	 * @param v
	 */
	public void showCurrentDate(View v){
		StringBuffer bfCurrent = new StringBuffer("The current time is: ");
		switch(v.getId()){
		case R.id.btn_showTime:
			bfCurrent.append(tp_controlHora.getCurrentHour());
			bfCurrent.append(":");
			bfCurrent.append(tp_controlHora.getCurrentMinute());
			bfCurrent.append("\n");
			bfCurrent.append("And the current date is:");
			bfCurrent.append(dp_controlFecha.getDayOfMonth());
			bfCurrent.append("/");
			bfCurrent.append(dp_controlFecha.getMonth()+1);
			bfCurrent.append("/");
			bfCurrent.append(dp_controlFecha.getYear());
			
			Toast.makeText(this, bfCurrent.toString(), Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_timedialog:
			this.showDialog(TIMER_DIALOG_ID);
			break;
		case R.id.btn_dateDialog:
			this.showDialog(DATE_DIALOG_ID);
			break;
		}
		
	}
	
	/**
	 * Method override and invoked when 
	 */
	@Override
	protected Dialog onCreateDialog(int id){
		
		switch(id){
		case TIMER_DIALOG_ID:
			Date date = new Date();	
			return new TimePickerDialog(this,timeListener,date.getHours(),date.getMinutes(),false);
		case DATE_DIALOG_ID:
			Calendar calendar = Calendar.getInstance();
			return new DatePickerDialog(this, dateListener, calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH)+1,
					calendar.get(Calendar.DAY_OF_MONTH));
		}
		return null;
		
	}
	
	/**
	 * Timer dialog listener that will be invoked from dialog 
	 */
	private TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener(){

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {			
			StringBuffer bfCurrent = new StringBuffer("The current time is: ");
			bfCurrent.append(hourOfDay);
			bfCurrent.append(":");
			bfCurrent.append(minute);
			Toast.makeText(getBaseContext(), bfCurrent.toString(), Toast.LENGTH_LONG).show();
		}		
	};	
	
	/**
	 * Timer dialog listener that will be invoked from dialog 
	 */
	private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener(){

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			StringBuffer bfCurrent = new StringBuffer("The current date is: ");
			bfCurrent.append(dayOfMonth);
			bfCurrent.append("/");
			bfCurrent.append(monthOfYear);
			bfCurrent.append("/");
			bfCurrent.append(year);
			Toast.makeText(getBaseContext(), bfCurrent.toString(), Toast.LENGTH_LONG).show();
			
		}
		
	};
	
	/**
	 * Each time that activity is restored, set the current time in TimePicker
	 */
	@Override
	public void onRestoreInstanceState(Bundle estado){
		setCurrentTime();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date_time, menu);
		return true;
	}

}
