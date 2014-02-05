package com.tema11_dibujos5;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new pantalla (this));
    }
    
    class pantalla extends View {
        public pantalla(Context context) {
            super(context);
        }
 
        @Override
        public void onDraw(Canvas canvas) {
            //codigo para dibujar 
        	Bitmap grafico = BitmapFactory.decodeResource(getResources(), 
        			R.drawable.logoadr);
            canvas.drawBitmap(grafico, 10, 10, null);
        }
    }
    
}