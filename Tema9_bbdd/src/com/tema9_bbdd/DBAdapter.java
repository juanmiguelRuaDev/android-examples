package com.tema9_bbdd;

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
    public static final String CLAVE_APELLIDOS= "apellidos";
    public static final String CLAVE_EMAIL= "email";
    private static final String TAG = "DBAdapter";
    
    private static final String BBDD_NOMBRE = "MiBBDD";
    private static final String BBDD_TABLA = "empleados";
    private static final int BBDD_VERSION = 1;

    private static final String BBDD_CREAR =
        "create table empleados (id integer primary key autoincrement, "
        + "dni text not null, nombre text not null, apellidos text not null, email text not null );";
        
    private final Context context;    

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, BBDD_NOMBRE, null, BBDD_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
        	try {
        		db.execSQL(BBDD_CREAR);	
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
            Log.w(TAG, "Actualizado versión de " + oldVersion + " a "
                    + newVersion + ", se borrarán todos los datos");
            db.execSQL("DROP TABLE IF EXISTS empleados");
            onCreate(db);
        }
    }    


    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    
    public void close() 
    {
        DBHelper.close();
    }
    

    public long inserta_empleado(String dni, String nombre, String apellidos, String email) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(CLAVE_DNI, dni);
        initialValues.put(CLAVE_NOMBRE, nombre);
        initialValues.put(CLAVE_APELLIDOS, apellidos);
        initialValues.put(CLAVE_EMAIL, email);
        return db.insert(BBDD_TABLA, null, initialValues);
    }

    public boolean borra_empleado(long id_fila) 
    {
        return db.delete(BBDD_TABLA, CLAVE_ID + "=" + id_fila, null) > 0;
    }

    public Cursor consulta_todos() 
    {
        return db.query(BBDD_TABLA, new String[] {CLAVE_ID, CLAVE_DNI, CLAVE_NOMBRE,
        		CLAVE_APELLIDOS,CLAVE_EMAIL}, null, null, null, null, null);
    }

    public Cursor consulta(long id_fila) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, BBDD_TABLA, new String[] {CLAVE_ID, CLAVE_DNI,
                CLAVE_NOMBRE, CLAVE_APELLIDOS,CLAVE_EMAIL}, CLAVE_ID + "=" + id_fila, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean updateContact(long id_fila, String dni, String nombre, String apellidos, String email) 
    {
        ContentValues args = new ContentValues();
        args.put(CLAVE_DNI, dni);
        args.put(CLAVE_NOMBRE, nombre);
        args.put(CLAVE_APELLIDOS, apellidos);
        args.put(CLAVE_EMAIL, email);

        return db.update(BBDD_TABLA, args, CLAVE_ID + "=" + id_fila, null) > 0;
    }
}
