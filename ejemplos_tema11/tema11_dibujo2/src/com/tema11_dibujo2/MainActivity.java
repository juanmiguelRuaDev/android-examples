package com.tema11_dibujo2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LinearLayout vista= (LinearLayout) findViewById(R.id.vista_principal);
        Lienzo fondo=new Lienzo(this);        
        vista.addView(fondo);     
    }
    
        class Lienzo extends View {
            public Lienzo(Context context) {
                super(context);
            }            
            protected void onDraw(Canvas canvas) {
                Paint pincel=new Paint();
                pincel.setColor(Color.MAGENTA);
                for (int i=1;i<300;i=i+10){
                   canvas.drawLine(i, 30,i,100, pincel);
                }
            }
        }    
}