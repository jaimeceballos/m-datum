package mdatum.udc.com.m_datum.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jaime on 29/06/17.
 */

public class MDatumDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mdatum.db";
    public static final int DATABASE_VERSION = 1;


    private String createEstablecimiento = "CREATE TABLE "+ EstablecimientoContract.EstablecimientoEntry.TABLE_NAME + "("
            + EstablecimientoContract.EstablecimientoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EstablecimientoContract.EstablecimientoEntry.NOMBRE + " TEXT NOT NULL,"
            + EstablecimientoContract.EstablecimientoEntry.NRO + " TEXT NOT NULL,"
            + EstablecimientoContract.EstablecimientoEntry.POS_LATITUD + " TEXT NOT NULL,"
            + EstablecimientoContract.EstablecimientoEntry.POS_LONGITUD + " TEXT NOT NULL,"
            + EstablecimientoContract.EstablecimientoEntry.FOTO + " TEXT,"
            + EstablecimientoContract.EstablecimientoEntry.REGIMEN_TENENCIA + " TEXT NOT NULL,"
            + EstablecimientoContract.EstablecimientoEntry.REGIMEN_OTROS + " TEXT)";

    private String createEncuestado = "CREATE TABLE "+ EncuestadoContract.EncuestadoEntry.TABLE_NAME + "("
            + EncuestadoContract.EncuestadoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EncuestadoContract.EncuestadoEntry.NOMBRE + " TEXT NOT NULL,"
            + EncuestadoContract.EncuestadoEntry.APELLIDO + " TEXT NOT NULL,"
            + EncuestadoContract.EncuestadoEntry.EDAD + " INTEGER NOT NULL,"
            + EncuestadoContract.EncuestadoEntry.NACIONALIDAD + " TEXT NOT NULL,"
            + EncuestadoContract.EncuestadoEntry.NIVEL_INSTRUCCION + " TEXT NOT NULL,"
            + EncuestadoContract.EncuestadoEntry.NIVEL_COMPLETO + " INTEGER NOT NULL,"
            + EncuestadoContract.EncuestadoEntry.VIVE_ESTABLECIMIENTO + " INTEGER NOT NULL)";

    private String createRegimenTenencia = "CREATE TABLE "+ RegimenTenenciaContract.RegimenTenenciaEntry.TABLE_NAME + "("
            + RegimenTenenciaContract.RegimenTenenciaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + RegimenTenenciaContract.RegimenTenenciaEntry.DESCRIPCION + " TEXT NOT NULL, "
            + "UNIQUE ("+ RegimenTenenciaContract.RegimenTenenciaEntry.DESCRIPCION+"))";

    private String createFamilia = "CREATE TABLE "+ FamiliaContract.FamiliaEntry.TABLE_NAME +" ("
            + FamiliaContract.FamiliaEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FamiliaContract.FamiliaEntry.ES_CASADO + " INTEGER NOT NULL, "
            + FamiliaContract.FamiliaEntry.TIENE_HIJOS + " INTEGER NOT NULL, "
            + FamiliaContract.FamiliaEntry.CANTIDAD_MUJERES + " INTEGER, "
            + FamiliaContract.FamiliaEntry.CANTIDAD_VARONES + " INTEGER )";

    private String createCultivo = "CREATE TABLE "+ CultivoContract.CultivoEntry.TABLE_NAME+" ("
            + CultivoContract.CultivoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CultivoContract.CultivoEntry.ESPECIE_ID + " INTEGER, "
            + CultivoContract.CultivoEntry.TIPO_ID + " INTEGER, "
            + CultivoContract.CultivoEntry.NRO_SIEMBRA + " INTEGER, "
            + CultivoContract.CultivoEntry.MES_SIEMBRA + " INTEGER, "
            + CultivoContract.CultivoEntry.SURCOS + " INTEGER, "
            + CultivoContract.CultivoEntry.DISTANCIAS + " INTEGER, "
            + CultivoContract.CultivoEntry.LARGO + " INTEGER, "
            + CultivoContract.CultivoEntry.SUPERFICIE_SEMBRADA + " INTEGER, "
            + CultivoContract.CultivoEntry.SUPERFICIE_MEDIDA_ID + " INTEGER, "
            + CultivoContract.CultivoEntry.TIPO_PRODUCCION_ID + " INTEGER, "
            + CultivoContract.CultivoEntry.ELECCION_CULTIVO_ID + " INTEGER, "
            + CultivoContract.CultivoEntry.ELECCION_ESPECIFICAR + " TEXT)";

    public MDatumDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createEstablecimiento);
        sqLiteDatabase.execSQL(createEncuestado);
        sqLiteDatabase.execSQL(createRegimenTenencia);
        sqLiteDatabase.execSQL(createFamilia);
        cargaRegimenTenencia(sqLiteDatabase);
        sqLiteDatabase.execSQL(createCultivo);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getAllEstablecimientos(){
        return getReadableDatabase()
                .query(
                        EstablecimientoContract.EstablecimientoEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getEstablecimientoById(int id){
        Cursor c = getReadableDatabase().query(
                EstablecimientoContract.EstablecimientoEntry.TABLE_NAME,
                null,
                EstablecimientoContract.EstablecimientoEntry._ID + " = ?",
                new String[]{Integer.toString(id)},
                null,
                null,
                null);
        return c;
    }

    public long saveEstablecimiento(Establecimiento establecimiento){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(EstablecimientoContract.EstablecimientoEntry.TABLE_NAME,
                null,
                establecimiento.toContentValues());
    }

    public long saveEncuestado(Encuestado encuestado){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(EncuestadoContract.EncuestadoEntry.TABLE_NAME,
                null,
                encuestado.toContentValues());
    }

    public long saveFamilia(Familia familia){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long l = 0;
        try{
            l = sqLiteDatabase.insertOrThrow(
                    FamiliaContract.FamiliaEntry.TABLE_NAME,
                    null,
                    familia.toContentValues()
            );
        }
        catch (SQLException e){
            Log.e("Exception","SQLException "+ String.valueOf(e.getMessage()));
            e.printStackTrace();
        }

        return l;
    }

    private void cargaRegimenTenencia(SQLiteDatabase db){
        saveRegimen(db,new RegimenTenencia("Propiedad"));
        saveRegimen(db,new RegimenTenencia("Sucesion Indivisa"));
        saveRegimen(db,new RegimenTenencia("Arrendatario"));
        saveRegimen(db,new RegimenTenencia("Med. % Producto"));
        saveRegimen(db,new RegimenTenencia("Med. % Dinero"));
        saveRegimen(db,new RegimenTenencia("Ocupacion"));
        saveRegimen(db,new RegimenTenencia("Otro"));

    }

    public long saveRegimen(SQLiteDatabase db, RegimenTenencia regimen){
        return db.insert(
                RegimenTenenciaContract.RegimenTenenciaEntry.TABLE_NAME,
                null,
                regimen.toContentValues()
        );
    }

    public long saveCultivo(Cultivo cultivo){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long l = 0;
        try{
            l = sqLiteDatabase.insertOrThrow(
                    CultivoContract.CultivoEntry.TABLE_NAME,
                    null,
                    cultivo.toContentValues()
            );
        }catch (SQLException e){
            Log.e("Exception","SQLException "+ String.valueOf(e.getMessage()));
            e.printStackTrace();
        }
        return l;
    }


}
