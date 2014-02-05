package com.tema11exercice1;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {

	
	MapView mapView;
	GeoPoint p;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mapView = (MapView)findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);
		MapController mc = mapView.getController();
		String[] coordenadas = {"42.4700831","-2.449265"};
		Double latitud = Double.parseDouble(coordenadas[0]);
		Double longitud = Double.parseDouble(coordenadas[1]);
		
		//Definimos el punto
		p = new GeoPoint((int)(latitud * 1E6), (int)(longitud * 1E6));
		mc.animateTo(p);
		mc.setZoom(13);
		mapView.invalidate();
		
		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event){
		MapController mc = mapView.getController();
		switch(keyCode){
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
