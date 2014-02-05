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

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends MapActivity {
    /** Called when the activity is first created. */
	
	MapView mapview;
	MapController mc;
	GeoPoint p;
	
	private LocationManager loc;
	private LocationListener location_listener;
	
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
		
		@Override
		public boolean onTouchEvent(MotionEvent evento, MapView mapview){
			if (evento.getAction()==1) {
					//Tenemos las coordenadas de la seleccion de la pantalla en "evento"
					//hay que traducir de pixeles a geocordenada
					GeoPoint p=mapview.getProjection().fromPixels (
							(int) evento.getX(),(int) evento.getY());
					
					Toast.makeText (getBaseContext(),"Ubicación:" +
					p.getLatitudeE6()/1E6 + "," +
					p.getLongitudeE6()/1E6,Toast.LENGTH_SHORT).show();
					
					//Vamos a buscar esa direccion
					Geocoder geoCoder=new Geocoder(
							getBaseContext(),Locale.getDefault());
					try {
						List<Address> direcciones=geoCoder.getFromLocation (
								p.getLatitudeE6()/1E6,p.getLongitudeE6()/1E6,1);
						
						String add="";
						if (direcciones.size()>0) {
							for (int i=0;i<direcciones.get(0).getMaxAddressLineIndex();i++)
								add+=direcciones.get(0).getAddressLine (i) + "\n";
						}
						Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
					}
					catch (IOException e){
						e.printStackTrace();
					}
					return true;
						
					}
			return false;
			
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
        
        //obtenemos la ubicacion
        loc=(LocationManager)
        		getSystemService (Context.LOCATION_SERVICE);
        location_listener=new leer_Ubicacion();        
        loc.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0, location_listener);
    }
    
    private class leer_Ubicacion implements LocationListener {
	
	@Override
	public void onLocationChanged(Location loc) {
		// TODO Auto-generated method stub
		 if (loc!= null) {
			 Toast.makeText(getBaseContext(),"Ubicacion cambiada. Lat:" + loc.getLatitude() +
					 " Lon: " + loc.getLongitude(),Toast.LENGTH_SHORT).show();
		 }
		 
        //Definimos el punto
        p=new GeoPoint ((int) (loc.getLatitude()* 1E6),(int) (loc.getLongitude() * 1E6));
        
        mc.animateTo(p);
        mc.setZoom(18);
	}
	@Override
	 public void onProviderDisabled (String provider){
	}
	@Override
	 public void onProviderEnabled (String provider){
	}
	
	@Override
	 public void onStatusChanged (String provider, int status, Bundle extras){
	}
	

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