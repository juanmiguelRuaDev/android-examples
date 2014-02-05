package com.tema11_dibujo3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LinearLayout vista= (LinearLayout) findViewById(R.id.vista_principal);
        //anadimos una "bola" en esas coordenadas:
        vista.addView(new Bola (this,40,40,20));
        
        vista.setOnTouchListener(new View.OnTouchListener() {
        	@Override
            public boolean onTouch(View v, MotionEvent e) {
                float x = e.getX();
                float y = e.getY();
                
                LinearLayout pantalla = (LinearLayout) v;
                pantalla.addView(new Bola(pantalla.getContext(),x,y,25));
			return false;
            }
        });
        
    }
}