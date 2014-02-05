package com.tema11_dibujo1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new prueba_view (this));
    }
    
    public class prueba_view extends View{
    	public prueba_view(Context contexto){
    		super(contexto);
    	}
    	
    	@Override
    	protected void onDraw(Canvas canvas){
    		super.onDraw(canvas);
    		Paint pincel=new Paint();
    		pincel.setColor (Color.BLUE);
    		canvas.drawRect(20,20, 200, 200, pincel);
    	}
    }
}