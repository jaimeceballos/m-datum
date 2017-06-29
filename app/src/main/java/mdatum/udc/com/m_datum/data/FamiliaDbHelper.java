package mdatum.udc.com.m_datum.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jaime on 29/06/17.
 */

public class FamiliaDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mdatum.db";
    public static final int DATABASE_VERSION = 1;

    public FamiliaDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+ FamiliaContract.FamiliaEntry.TABLE_NAME +" ("
                     + FamiliaContract.FamiliaEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                     + FamiliaContract.FamiliaEntry.ES_CASADO + " INTEGER NOT NULL, "
                     + FamiliaContract.FamiliaEntry.TIENE_HIJOS + " INTEGER NOT NULL, "
                     + FamiliaContract.FamiliaEntry.CANTIDAD_MUJERES + " INTEGER, "
                     + FamiliaContract.FamiliaEntry.CANTIDAD_VARONES + " INTEGER )";

        sqLiteDatabase.execSQL(sql);
    }

    public long saveFamilia(Familia familia){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                FamiliaContract.FamiliaEntry.TABLE_NAME,
                null,
                familia.toContentValues()
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
