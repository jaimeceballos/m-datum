package mdatum.udc.com.m_datum.database;

import android.content.Context;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

/**
 * Created by jaime on 15/10/17.
 */

public class MDatumOpenHelper extends DaoMaster.OpenHelper {

    public MDatumOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion){
        super.onUpgrade(db, oldVersion, newVersion);

        Log.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
        switch (oldVersion) {
            case 1:
            case 2:


        }
    }
    @Override
    public void onCreate(Database db) {
        super.onCreate(db);

        ///INSERCION DE DATOS INICIALES
        ///Regimen de tenencia
        db.execSQL("INSERT INTO "+ RegimenTenenciaDao.TABLENAME + " (" +
                    RegimenTenenciaDao.Properties.Descpripcion.columnName +
                    ") VALUES('Propiedad')");
        db.execSQL("INSERT INTO "+ RegimenTenenciaDao.TABLENAME + " (" +
                RegimenTenenciaDao.Properties.Descpripcion.columnName +
                ") VALUES('Sucesion Indivisa')");
        db.execSQL("INSERT INTO "+ RegimenTenenciaDao.TABLENAME + " (" +
                RegimenTenenciaDao.Properties.Descpripcion.columnName +
                ") VALUES('Arrendatario')");
        db.execSQL("INSERT INTO "+ RegimenTenenciaDao.TABLENAME + " (" +
                RegimenTenenciaDao.Properties.Descpripcion.columnName +
                ") VALUES('Med. % Producto')");
        db.execSQL("INSERT INTO "+ RegimenTenenciaDao.TABLENAME + " (" +
                RegimenTenenciaDao.Properties.Descpripcion.columnName +
                ") VALUES('Med. % Dinero')");
        db.execSQL("INSERT INTO "+ RegimenTenenciaDao.TABLENAME + " (" +
                RegimenTenenciaDao.Properties.Descpripcion.columnName +
                ") VALUES('Ocupacion')");
        db.execSQL("INSERT INTO "+ RegimenTenenciaDao.TABLENAME + " (" +
                RegimenTenenciaDao.Properties.Descpripcion.columnName +
                ") VALUES('Otro')");
        ///Nacionalidad
        db.execSQL("INSERT INTO "+ NacionalidadDao.TABLENAME + " (" +
                NacionalidadDao.Properties.Descripcion.columnName +
                ") VALUES('Argentina')");
        db.execSQL("INSERT INTO "+ NacionalidadDao.TABLENAME + " (" +
                NacionalidadDao.Properties.Descripcion.columnName +
                ") VALUES('Chile')");
        db.execSQL("INSERT INTO "+ NacionalidadDao.TABLENAME + " (" +
                NacionalidadDao.Properties.Descripcion.columnName +
                ") VALUES('Bolivia')");
        db.execSQL("INSERT INTO "+ NacionalidadDao.TABLENAME + " (" +
                NacionalidadDao.Properties.Descripcion.columnName +
                ") VALUES('Paraguay')");
        db.execSQL("INSERT INTO "+ NacionalidadDao.TABLENAME + " (" +
                NacionalidadDao.Properties.Descripcion.columnName +
                ") VALUES('Peru')");
        ///Nivel de Instruccion
        db.execSQL("INSERT INTO "+ NivelInstruccionDao.TABLENAME + " (" +
                NivelInstruccionDao.Properties.Descripcion.columnName +
                ") VALUES('Primario')");
        db.execSQL("INSERT INTO "+ NivelInstruccionDao.TABLENAME + " (" +
                NivelInstruccionDao.Properties.Descripcion.columnName +
                ") VALUES('Secundario')");
        db.execSQL("INSERT INTO "+ NivelInstruccionDao.TABLENAME + " (" +
                NivelInstruccionDao.Properties.Descripcion.columnName +
                ") VALUES('Terciario')");
        db.execSQL("INSERT INTO "+ NivelInstruccionDao.TABLENAME + " (" +
                NivelInstruccionDao.Properties.Descripcion.columnName +
                ") VALUES('Universitario')");

    }
}
