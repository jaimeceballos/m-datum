package mdatum.udc.com.m_datum.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static mdatum.udc.com.m_datum.data.EstablecimientoContract.EstablecimientoEntry;

/**
 * Created by jaime on 12/06/17.
 */

public class EstablecimientoDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mdatum.db";
    public static final int DATABASE_VERSION = 1;

    public EstablecimientoDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+ EstablecimientoEntry.TABLE_NAME + "("
                +EstablecimientoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +EstablecimientoEntry.NOMBRE + " TEXT NOT NULL,"
                +EstablecimientoEntry.NRO + " TEXT NOT NULL,"
                +EstablecimientoEntry.POS_LATITUD + " TEXT NOT NULL,"
                +EstablecimientoEntry.POS_LONGITUD + " TEXT NOT NULL,"
                +EstablecimientoEntry.FOTO + " TEXT,"
                +EstablecimientoEntry.REGIMEN_TENENCIA + " TEXT NOT NULL,"
                +EstablecimientoEntry.REGIMEN_OTROS + " TEXT)";

        sqLiteDatabase.execSQL(sql);

    }

    public Cursor getAllEstablecimientos(){
        return getReadableDatabase()
                .query(
                  EstablecimientoEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getEstablecimientoById(int id){
        Cursor c = getReadableDatabase().query(
          EstablecimientoEntry.TABLE_NAME,
                null,
                EstablecimientoEntry._ID + " = ?",
                new String[]{Integer.toString(id)},
                null,
                null,
                null);
        return c;
    }

    public long saveEstablecimiento(Establecimiento establecimiento){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(EstablecimientoEntry.TABLE_NAME,
                null,
                establecimiento.toContentValues());
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
