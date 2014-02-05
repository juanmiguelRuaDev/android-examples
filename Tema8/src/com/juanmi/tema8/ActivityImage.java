package com.juanmi.tema8;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;


public class ActivityImage extends Activity {
	private static final String LOGTAG = "Tema8";
	Integer[] id_images = {R.drawable.retronovembre,R.drawable.scream,R.drawable.tastatura};
	Gallery galeria;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		Log.i(LOGTAG, "Starting ActivityImage");
		galeria = (Gallery)findViewById(R.id.gallery1);
		
		galeria.setAdapter(new ImageAdapter(this));
		galeria.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getBaseContext(), "image "+(arg2 +1)+" selected" ,Toast.LENGTH_SHORT).show();
				ImageView imagen = (ImageView) findViewById(R.id.imagePrincipal);
				imagen.setImageResource(id_images[arg2]);
				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_image, menu);
		return true;
	}
	
	
	
	
	public class ImageAdapter extends BaseAdapter {
		
		private Context contexto;
		private int img_fondo;
		
		

		public ImageAdapter(Context contexto) {
			super();
			this.contexto = contexto;
			
			
			//--setting styles
			TypedArray a = 	contexto.obtainStyledAttributes(R.styleable.galeria);
			img_fondo = a.getResourceId(R.styleable.galeria_android_galleryItemBackground, 0);
			a.recycle();
		}

		@Override
		public int getCount() {
			return id_images.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(contexto);
			imageView.setImageResource(id_images[position]);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setLayoutParams(new Gallery.LayoutParams(150,20));
			imageView.setBackgroundResource(img_fondo);
			return null;
		}

	}

}







