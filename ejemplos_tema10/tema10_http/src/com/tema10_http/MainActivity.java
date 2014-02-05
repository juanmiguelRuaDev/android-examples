package com.tema10_http;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.widget.ImageView;
import android.widget.Toast;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element; 
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MainActivity extends Activity {
	ImageView img;
	
	private InputStream OpenHttpConnection(String urlString) 
    throws IOException
    {
        InputStream in = null;
        int response = -1;
               
        URL url = new URL(urlString); 
        URLConnection conn = url.openConnection();
                 
        if (!(conn instanceof HttpURLConnection))                     
            throw new IOException("No hay conexión HTTP");        
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();                 
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();                                 
            }                     
        }
        catch (Exception ex)
        {
            throw new IOException("Error conectando");            
        }
        return in;     
    }	
    
	 private Bitmap DownloadImage(String URL)
	    {        
	        Bitmap bitmap = null;
	        InputStream in = null;        
	        try {
	            in = OpenHttpConnection(URL);
	            bitmap = BitmapFactory.decodeStream(in);
	            in.close();
	        } catch (IOException e1) {
	        	Toast.makeText(this, e1.getLocalizedMessage(), 
	        			Toast.LENGTH_LONG).show();
	            e1.printStackTrace();
	        }
	        return bitmap;                
	    }

	
	 private String DownloadText(String URL)
	    {
	        int BUFFER_SIZE = 2000;
	        InputStream in = null;
	        try {
	            in = OpenHttpConnection(URL);
	        } catch (IOException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	            return "";
	        }
	        
	        InputStreamReader isr = new InputStreamReader(in);
	        int charRead;
	        String str = "";
	        char[] inputBuffer = new char[BUFFER_SIZE];          
	        try {
	            while ((charRead = isr.read(inputBuffer))>0)
	            {                    
	                //Convertimos los caractres a String
	                String readString = 
	                    String.copyValueOf(inputBuffer, 0, charRead);                    
	                str += readString;
	                inputBuffer = new char[BUFFER_SIZE];
	            }
	            in.close();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return "";
	        }    
	        return str;        
	    }
	 
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Descargar una imagen
        Bitmap bitmap=DownloadImage("http://www.adrformacion.com/img/logoadr.gif");
        img=(ImageView) findViewById (R.id.img);
        img.setImageBitmap (bitmap);
        
        //Descarga de una noticia RSS
        String str=DownloadText("http://appleinsider.com/appleinsider.rss");
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }
    
}



