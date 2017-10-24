package mdatum.udc.com.m_datum.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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




}
