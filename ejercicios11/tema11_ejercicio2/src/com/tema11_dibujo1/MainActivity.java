package com.tema11_dibujo1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	Dibujar dibujo;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        		//Creamos una instancia de la pantalla de dibujo
        		dibujo= new Dibujar(this);
        		setContentView(dibujo);
    }

    public class Dibujar extends View implements OnTouchListener {
    	List<Punto> puntos= new ArrayList<Punto>();
    	Paint paint = new Paint();

    	public Dibujar(Context context) {
    		super(context);

    		this.setOnTouchListener(this);

    		paint.setColor(Color.WHITE);
    		paint.setAntiAlias(true);
    	}
    	

			@Override
	    	public void onDraw(Canvas canvas) {
				for (Punto point : puntos) {
					//Bucle para pintar los circulos
					canvas.drawCircle(point.x, point.y, 5, paint);
				}
	    	}
	
	    	public boolean onTouch(View view, MotionEvent event) {
	    		//Agregamos un punto a la matriz.
	    		Punto point = new Punto();
	    		point.x = event.getX();
	    		point.y = event.getY();
	    		puntos.add(point);
	    		invalidate();
	    		return true;
	    		}
	    	}

    //Definimos una clase "Punto"
    	class Punto {
    		float x, y;
    		@Override
    			public String toString() {
    			return x + ", " + y;
    			}
    	}
    
}