package com.tema11_mapas1;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends MapActivity {
    /** Called when the activity is first created. */
	
	MapView mapview;
	MapController mc;
	GeoPoint p;
	
	class MapOverlay extends com.google.android.maps.Overlay
	{
		@Override
		public boolean draw(Canvas canvas, MapView mapview,
				boolean shadow, long when)
		{
			super.draw (canvas,mapview,shadow);
			
			//Traducimos el punto geográfico en pixeles
			Point puntos=new Point();
			mapview.getProjection().toPixels(p, puntos);
			
			//ponemos el marcador
			Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.drawable.chincheta);
			canvas.drawBitmap(bmp, puntos.x,puntos.y-50,null);
			return true;
		}
	}
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mapview=(MapView) findViewById (R.id.mapView);
        //Vista con los controles de Zoom
        mapview.setBuiltInZoomControls(true);
     
        //Cogemos una referencia de los objetos de geolocalización para indicarle uno:
        mc=mapview.getController();
        String coordenadas[]={"42.470831","-2.449265"};
        
        Double latitud=Double.parseDouble(coordenadas [0]);
        Double longitud=Double.parseDouble(coordenadas [1]);
        
        //DEfinimos el punto
        p=new GeoPoint ((int) (latitud * 1E6),(int) (longitud * 1E6));
        
        mc.animateTo(p);
        mc.setZoom(13);
        mapview.invalidate();
        
        //Añadimos un marcador:
        MapOverlay mapoverlay=new MapOverlay();
        List<Overlay> lista_Overlays = mapview.getOverlays();
        lista_Overlays.clear();
        lista_Overlays.add(mapoverlay);
        
    }
    

    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
        MapController mc = mapview.getController(); 
        switch (keyCode) 
        {
            case KeyEvent.KEYCODE_3:
                mc.zoomIn();
                break;
            case KeyEvent.KEYCODE_1:
                mc.zoomOut();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}