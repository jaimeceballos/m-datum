package mdatum.udc.com.m_datum.data;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by jaime on 28/06/17.
 */

public class RegimenTenenciaDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mdatum.db";
    public static final int DATABASE_VERSION = 1;


    public RegimenTenenciaDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE "+ RegimenTenenciaContract.RegimenTenenciaEntry.TABLE_NAME + "("
                + RegimenTenenciaContract.RegimenTenenciaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + RegimenTenenciaContract.RegimenTenenciaEntry.DESCRIPCION + " TEXT NOT NULL, "
                + "UNIQUE ("+ RegimenTenenciaContract.RegimenTenenciaEntry.DESCRIPCION+"))";
        sqLiteDatabase.execSQL(sql);

        cargaDatos(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private  void cargaDatos(SQLiteDatabase db){
        insertarDatos(db,new RegimenTenencia("Propiedad"));
        insertarDatos(db,new RegimenTenencia("Sucesión indivisa"));
        insertarDatos(db,new RegimenTenencia("Arrendatario"));
        insertarDatos(db,new RegimenTenencia("Med. % producto"));
        insertarDatos(db,new RegimenTenencia("Med. % dinero"));
        insertarDatos(db,new RegimenTenencia("Ocupación"));
        insertarDatos(db,new RegimenTenencia("Otro"));

    }

    public long insertarDatos(SQLiteDatabase db, RegimenTenencia regimen){
        return db.insert(
                RegimenTenenciaContract.RegimenTenenciaEntry.TABLE_NAME,
                null,
                regimen.toContentValues()
        );
    }

    public ArrayList<String> getAllRegTenencia(){
        ArrayList<String> opciones = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        String selectQuery = "SELECT * FROM " + RegimenTenenciaContract.RegimenTenenciaEntry.TABLE_NAME;
        Cursor c = db.rawQuery(selectQuery, null);
        try {
            if (c.getCount() < 0) {
                while (c.moveToNext()) {
                    String regDescripcion = c.getString(c.getColumnIndex("descripcion"));
                    opciones.add(regDescripcion);
                }
            }
            db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return opciones;
    }


}
