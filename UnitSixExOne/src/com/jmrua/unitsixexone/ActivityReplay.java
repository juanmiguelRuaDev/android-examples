package com.jmrua.unitsixexone;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class ActivityReplay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle b = this.getIntent().getExtras();
		String name = b.getString(MainActivity.NAME_KEY);
		int reps = b.getInt(MainActivity.REPS_KEY);
		
		//scroll
		ScrollView scroll = new ScrollView(this);
		ScrollView.LayoutParams scroll_params = new ScrollView.LayoutParams(
				ScrollView.LayoutParams.FILL_PARENT,
				ScrollView.LayoutParams.FILL_PARENT);
		scroll.setLayoutParams(scroll_params);
		//linearLayout
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		for(int i= 0; i < reps; i++){
			EditText edit = new EditText(this);
			edit.setText(name);
			edit.setEnabled(false);
			layout.addView(edit);
		}
		
		scroll.addView(layout);
		addContentView(scroll, scroll_params);
	}
}
