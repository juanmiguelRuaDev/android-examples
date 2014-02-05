package com.juanmi.tema9;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	public static final String CLAVE_ID = "id";
	public static final String CLAVE_DNI = "dni";
	public static final String CLAVE_NOMBRE = "nombre";
	public static final String CLAVE_APELLIDO = "apellidos";
	public static final String CLAVE_EMAIL = "email";
	
	private static final String BBDD_NAME = "MiBBDD";
	private static final String BBDD_TABLA  = "empleados";
	private static final int BBDD_VERSION = 1;
	
	private static final String BBDD_CREAR = 
			"create table empleados (id integer primary key autoincrement, "
			+ "dni text not null, nombre text not null, apellidos text not null, "
					+"email text not null);";
	
	private final Context context;
	private final DataBaseHelper dataBaseHelper;	
	private SQLiteDatabase db;
	
	public DBAdapter(Context ctx) {
		this.context = ctx;
		dataBaseHelper = new DataBaseHelper(ctx);
	}
	
	/**
	 * Open database
	 * @return
	 * @throws SQLException
	 */
	public DBAdapter open()throws SQLException{
		db = dataBaseHelper.getWritableDatabase();
		return this;
	}
	
	/**
	 * close database
	 */
	public void close(){
		dataBaseHelper.close();
	}
	
	public long insertEmpleado(String dni,String name,String apellido,String email){
		ContentValues initialValues = new ContentValues();
		initialValues.put(CLAVE_DNI, dni);
		initialValues.put(CLAVE_NOMBRE, name);
		initialValues.put(CLAVE_APELLIDO, apellido);
		initialValues.put(CLAVE_EMAIL, email);
		return db.insert(BBDD_TABLA, null, initialValues);
	}
	
	/**
	 * delete registre
	 * @param id_fila
	 * @return
	 */
	public boolean borrarEmpleado(long id_fila){
		return db.delete(BBDD_TABLA, CLAVE_ID +"="+id_fila, null)>0;
	}
	
	/**
	 * consulta all registers
	 * @return
	 */
	public Cursor consulta_todos(){
		return db.query(BBDD_TABLA,new String[]{CLAVE_ID,CLAVE_DNI,CLAVE_NOMBRE,CLAVE_APELLIDO,CLAVE_EMAIL}, 
				null, null, null, null, null);
	}
	
	public Cursor consulta(long id_fila){
		Cursor c =  db.query(true, BBDD_TABLA,new String[]{CLAVE_ID,CLAVE_DNI,CLAVE_NOMBRE,CLAVE_APELLIDO,CLAVE_EMAIL}, 
				CLAVE_ID+"="+id_fila, null, null, null, null,null);
		if(c != null)
			c.moveToFirst();		
		return c;		
	}
	
	public boolean updateContact(long id_fila,String dni,String nombre,String apellido,String email){
		ContentValues initialValues = new ContentValues();
		initialValues.put(CLAVE_DNI, dni);
		initialValues.put(CLAVE_NOMBRE, nombre);
		initialValues.put(CLAVE_APELLIDO, apellido);
		initialValues.put(CLAVE_EMAIL, email);
		return db.update(BBDD_TABLA, initialValues, CLAVE_ID+"="+id_fila,null)>0;
	}
	
	/**
	 * SqlOpenHelper implementing in android
	 * @author juanmi
	 *
	 */
	private static class DataBaseHelper extends SQLiteOpenHelper{

		public DataBaseHelper(Context context) {
			super(context, BBDD_NAME, null, BBDD_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try{
				db.execSQL(BBDD_CREAR);
			}catch(SQLException e){
				Log.e(MainActivity.LOGTAG, e.getMessage());
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(MainActivity.LOGTAG,"Actualizacion version "+oldVersion+" a "+
					newVersion+", se borraran todos los datos");
			db.execSQL("DROP TABLE IF EXIST empleados");			
		}		
	}
}
