package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by jaime on 29/06/17.
 */

public class MDatumDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mdatum.db";
    public static final int DATABASE_VERSION = 1;

    /*

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

    private String createInvernaculo = "CREATE TABLE " + InvernaculoContract.InvernaculoEntry.TABLE_NAME + " ( "
            + InvernaculoContract.InvernaculoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + InvernaculoContract.InvernaculoEntry.CANTIDAD_MODULOS + " INTEGER, "
            + InvernaculoContract.InvernaculoEntry.SUPERFICIE_UNITARIA + " INTEGER, "
            + InvernaculoContract.InvernaculoEntry.MATERIAL_ESTRUCTURA_ID + " INTEGER, "
            + InvernaculoContract.InvernaculoEntry.ANIO_CONSTRUCCION_ID + " INTEGER)";

    private String createMaterialEstructura = "CREATE TABLE materialEstructura(id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT NOT NULL)";
    private String createAnioEstructura = "CREATE TABLE anioEstructura(id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT NOT NULL)";
    private String createNivelInstruccion = "CREATE TABLE nivelInstruccion(id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT NOT NULL)";
    private String createNacionalidad = "CREATE TABLE nacionalidad(id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT NOT NULL)";
    private String createEspecie = "CREATE TABLE especie(id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT NOT NULL)";
    private String createTipoCultivo = "CREATE TABLE tipoCultivo(id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT NOT NULL)";
    private String createSuperficieMedida = "CREATE TABLE superficieMedida(id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT NOT NULL)";
    private String createTipoProduccion = "CREATE TABLE tipoProduccion(id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT NOT NULL)";
    private String createEleccionCultivo = "CREATE TABLE eleccionCultivo(id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT NOT NULL)";
    private String createEncuestaInvernaculo = "CREATE TABLE encuestaInvernaculo(id INTEGER PRIMARY KEY AUTOINCREMENT, encuestaId INTEGER NOT NULL, invernaculoId INTEGER NOT NULL)";
    private String createEncuestaCultivo = "CREATE TABLE encuestaCultivo(id INTEGER PRIMARY KEY AUTOINCREMENT, encuestaId INTEGER NOT NULL, cultivoId INTEGER NOT NULL)";
*/
    public MDatumDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       /* sqLiteDatabase.execSQL(createEstablecimiento);
        sqLiteDatabase.execSQL(createEncuestado);
        sqLiteDatabase.execSQL(createRegimenTenencia);
        sqLiteDatabase.execSQL(createFamilia);
        cargaRegimenTenencia(sqLiteDatabase);
        sqLiteDatabase.execSQL(createCultivo);
        sqLiteDatabase.execSQL(createInvernaculo);
        sqLiteDatabase.execSQL(createMaterialEstructura);
        sqLiteDatabase.execSQL(createAnioEstructura);
        cargaMaterialEstrutura(sqLiteDatabase);
        cargaAnioEstructura(sqLiteDatabase);
        sqLiteDatabase.execSQL(createNivelInstruccion);
        cargaNivelInstruccion(sqLiteDatabase);
        sqLiteDatabase.execSQL(createNacionalidad);
        cargaNacionalidad(sqLiteDatabase);
        sqLiteDatabase.execSQL(createEspecie);
        sqLiteDatabase.execSQL(createTipoCultivo);
        sqLiteDatabase.execSQL(createSuperficieMedida);
        sqLiteDatabase.execSQL(createTipoProduccion);
        sqLiteDatabase.execSQL(createEleccionCultivo);
        carga(sqLiteDatabase);
        sqLiteDatabase.execSQL(createEncuestaInvernaculo);
        sqLiteDatabase.execSQL(createEncuestaCultivo);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
/*
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

    public long saveEncuesta(Encuesta encuesta){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long l = 0;
        try {
            l = sqLiteDatabase.insertOrThrow(
                    EncuestaContract.EncuestaEntry.TABLE_NAME,
                    null,
                    encuesta.toContentValues()
            );
        }catch (SQLException e){
            Log.e("Exception","SQLException "+String.valueOf(e.getMessage()));
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

    private void cargaMaterialEstrutura(SQLiteDatabase db){
        saveMaterialEstructura(db,"madera");
        saveMaterialEstructura(db,"hierro");
    }
    private void cargaAnioEstructura(SQLiteDatabase db){
        saveAnioEstructura(db,"mas de 5 años");
        saveAnioEstructura(db,"Menos de 5 años, mas de 3 años");
        saveAnioEstructura(db,"Menos de 3 años, mas de 1 año");
        saveAnioEstructura(db,"Menos de 1 año");
    }

    private void cargaNivelInstruccion(SQLiteDatabase db){
        saveNivelInstruccion(db,"Primario");
        saveNivelInstruccion(db,"Secundario");
        saveNivelInstruccion(db,"Terciario");
        saveNivelInstruccion(db,"Universitario");
    }

    private void cargaNacionalidad(SQLiteDatabase db){
        saveNacionalidad(db,"Argentina");
        saveNacionalidad(db,"Chile");
        saveNacionalidad(db,"Bolivia");
        saveNacionalidad(db,"Paraguay");
        saveNacionalidad(db,"Peru");
    }
    private void carga(SQLiteDatabase db){
        save(db,"Lechuga","especie");
        save(db,"Espinaca","especie");
        save(db,"Acelga","especie");
        save(db,"Radicheta","especie");
        save(db,"Albahaca","especie");
        save(db,"Perejil","especie");
        save(db,"Cebolla Verdeo","especie");
        save(db,"Apio","especie");
        save(db,"Escarola","especie");
        save(db,"Campo","tipoCultivo");
        save(db,"Bajo Cubierta","tipoCultivo");
        save(db,"M2","superficieMedida");
        save(db,"HA","superficieMedida");
        save(db,"Atado","tipoProduccion");
        save(db,"Bolsa","tipoProduccion");
        save(db,"Jaula","tipoProduccion");
        save(db,"Otras","tipoProduccion");
        save(db,"Rotacion","eleccionCultivo");
        save(db,"Habitualidad","eleccionCultivo");
        save(db,"Seguridad de venta","eleccionCultivo");
        save(db,"Por Contrato","eleccionCultivo");
        save(db,"Precio Anterior","eleccionCultivo");
        save(db,"Precio Futuro","eleccionCultivo");
        save(db,"Otro","eleccionCultivo");


    }

    public long saveRegimen(SQLiteDatabase db, RegimenTenencia regimen){
        return db.insert(
                RegimenTenenciaContract.RegimenTenenciaEntry.TABLE_NAME,
                null,
                regimen.toContentValues()
        );
    }

    public long saveMaterialEstructura(SQLiteDatabase db, String material){
        ContentValues values = new ContentValues();
        values.put("descripcion",material);
        return db.insert(
                "materialEstructura",
                null,
                values
        );
    }

    public long saveNivelInstruccion(SQLiteDatabase db, String nivel){
        ContentValues values = new ContentValues();
        values.put("descripcion",nivel);
        return db.insert(
                "nivelInstruccion",
                null,
                values
        );
    }

    public long saveAnioEstructura(SQLiteDatabase db, String anio){
        ContentValues values = new ContentValues();
        values.put("descripcion",anio);
        return db.insert(
                "anioEstructura",
                null,
                values
        );
    }

    public long saveNacionalidad(SQLiteDatabase db, String nacionalidad){
        ContentValues values = new ContentValues();
        values.put("descripcion",nacionalidad);
        return db.insert(
                "nacionalidad",
                null,
                values
        );
    }
    public long save(SQLiteDatabase db, String dato, String tabla){
        ContentValues values = new ContentValues();
        values.put("descripcion",dato);
        return db.insert(
                tabla,
                null,
                values
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

    public long saveInvernaculo(Invernaculo invernaculo){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long l = 0;
        try{
            l = sqLiteDatabase.insertOrThrow(
                    InvernaculoContract.InvernaculoEntry.TABLE_NAME,
                    null,
                    invernaculo.toContentValues()
            );

        }catch (SQLException e){
            Log.e("Exception","SQLException "+ String.valueOf(e.getMessage()));
            e.printStackTrace();
        }
        return l;
    }
    public long saveIntermedio(String tabla, int encuestaId,int idAnexo){

        ContentValues values = new ContentValues();
        values.put("encuestaId",encuestaId);
        if(tabla.equals("encuestaInvernaculo")){
            values.put("invernaculoId",idAnexo);
        }else{
            values.put("cultivoId",idAnexo);
        }
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long l = 0;
        try{
            l = sqLiteDatabase.insertOrThrow(
                    tabla,
                    null,
                    values
            );
        }catch (SQLException e){
            Log.e("Exception","SQLException "+ String.valueOf(e.getMessage()));
            e.printStackTrace();
        }
        return l;

    }
    public ArrayList<String> getAllRegimen(){

                ArrayList<String> lista = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

                db.beginTransaction();
        try {
                String selectQuery = "SELECT * FROM "+ RegimenTenenciaContract.RegimenTenenciaEntry.TABLE_NAME;
                Cursor cursor = db.rawQuery(selectQuery,null);
                if(cursor.getCount() > 0 ){
                        while (cursor.moveToNext()){
                                String regimen = cursor.getString(cursor.getColumnIndex(RegimenTenenciaContract.RegimenTenenciaEntry.DESCRIPCION));
                                lista.add(regimen);
                            }
                    }
                db.setTransactionSuccessful();
            }catch (Exception e){
                e.printStackTrace();
            }
        finally {
                db.endTransaction();
                db.close();
            }
        return lista;
    }

    public ArrayList<String> getAllEstructura(){
        ArrayList<String> lista = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            String selectQuery = "SELECT * FROM materialEstructura";
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() > 0){
                while (cursor.moveToNext()){
                    String material = cursor.getString(cursor.getColumnIndex("descripcion"));
                    lista.add(material);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return  lista;
    }

    public ArrayList<String> getAllAnioEstructura(){
        ArrayList<String> lista = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery = "SELECT * FROM anioEstructura";
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() > 0 ){
                while (cursor.moveToNext()){
                    String anio = cursor.getString(cursor.getColumnIndex("descripcion"));
                    lista.add(anio);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return lista;
    }

    public ArrayList<String> getAllNivelInstruccion(){
        ArrayList<String> lista = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            String selectQuery = "SELECT * FROM nivelInstruccion";
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() > 0){
                while (cursor.moveToNext()){
                    String nivel = cursor.getString(cursor.getColumnIndex("descripcion"));
                    lista.add(nivel);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return lista;
    }
    public ArrayList<String> getAllNacionalidad(){
        ArrayList<String> lista = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery = "SELECT * FROM nacionalidad";
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    String nacionalidad = cursor.getString(cursor.getColumnIndex("descripcion"));
                    lista.add(nacionalidad);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return lista;
    }
    public ArrayList<String> getAllItems(String tabla){
        ArrayList<String> lista = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery = "SELECT * from "+tabla;
            Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() > 0){
                while (cursor.moveToNext()){
                    String elemento = cursor.getString(cursor.getColumnIndex("descripcion"));
                    lista.add(elemento);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return lista;
    }*/
}
