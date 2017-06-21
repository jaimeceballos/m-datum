package mdatum.udc.com.m_datum.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static mdatum.udc.com.m_datum.data.EncuestadoContract.EncuestadoEntry;
/**
 * Created by jaime on 21/06/17.
 */

public class EncuestadoDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mdatum.db";
    public static final int DATABASE_VERSION = 1;


    public EncuestadoDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+ EncuestadoEntry.TABLE_NAME + "("
                +EncuestadoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +EncuestadoEntry.NOMBRE + " TEXT NOT NULL,"
                +EncuestadoEntry.APELLIDO + " TEXT NOT NULL,"
                +EncuestadoEntry.EDAD + " INTEGER NOT NULL,"
                +EncuestadoEntry.NACIONALIDAD + " TEXT NOT NULL,"
                +EncuestadoEntry.NIVEL_INSTRUCCION + " TEXT NOT NULL,"
                +EncuestadoEntry.NIVEL_COMPLETO + " INTEGER NOT NULL,"
                +EncuestadoEntry.VIVE_ESTABLECIMIENTO + " INTEGER NOT NULL)";

        sqLiteDatabase.execSQL(sql);
    }


    public long saveEncuestado(Encuestado encuestado){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(EncuestadoEntry.TABLE_NAME,
                null,
                encuestado.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
