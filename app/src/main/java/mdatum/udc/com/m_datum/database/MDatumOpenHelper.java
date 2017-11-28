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

       /* ///INSERCION DE DATOS INICIALES
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

        //Carga Inicial de referencias material estructura
        db.execSQL("INSERT INTO "+ MaterialEstructuraDao.TABLENAME + " (" +
                MaterialEstructuraDao.Properties.Descripcion.columnName +
                ") VALUES('Madera')");
        db.execSQL("INSERT INTO "+ MaterialEstructuraDao.TABLENAME + " (" +
                MaterialEstructuraDao.Properties.Descripcion.columnName +
                ") VALUES('Hierro')");

        //Carga Inicial de referencias anio de estructura
        db.execSQL("INSERT INTO "+ AnioEstructuraDao.TABLENAME + " (" +
                AnioEstructuraDao.Properties.Descripcion.columnName +
                ") VALUES('mas de 5 años')");
        db.execSQL("INSERT INTO "+ AnioEstructuraDao.TABLENAME + " (" +
                AnioEstructuraDao.Properties.Descripcion.columnName +
                ") VALUES('Menos de 5 años, mas de 3 años')");
        db.execSQL("INSERT INTO "+ AnioEstructuraDao.TABLENAME + " (" +
                AnioEstructuraDao.Properties.Descripcion.columnName +
                ") VALUES('Menos de 3 años, mas de 1 año')");
        db.execSQL("INSERT INTO "+ AnioEstructuraDao.TABLENAME + " (" +
                AnioEstructuraDao.Properties.Descripcion.columnName +
                ") VALUES('Menos de 1 año')");

        //carga inicial tipo cultivo
        db.execSQL("INSERT INTO "+ TipoCultivoDao.TABLENAME + " (" +
                TipoCultivoDao.Properties.Descripcion.columnName +
                ") VALUES('Campo')");
        db.execSQL("INSERT INTO "+ TipoCultivoDao.TABLENAME + " (" +
                TipoCultivoDao.Properties.Descripcion.columnName +
                ") VALUES('Bajo Cubierta')");

        //carga inicial Especie
        db.execSQL("INSERT INTO "+ EspecieDao.TABLENAME + " (" +
                EspecieDao.Properties.Descripcion.columnName +
                ") VALUES('Lechuga')");
        db.execSQL("INSERT INTO "+ EspecieDao.TABLENAME + " (" +
                EspecieDao.Properties.Descripcion.columnName +
                ") VALUES('Espinaca')");
        db.execSQL("INSERT INTO "+ EspecieDao.TABLENAME + " (" +
                EspecieDao.Properties.Descripcion.columnName +
                ") VALUES('Acelga')");
        db.execSQL("INSERT INTO "+ EspecieDao.TABLENAME + " (" +
                EspecieDao.Properties.Descripcion.columnName +
                ") VALUES('Radicheta')");
        db.execSQL("INSERT INTO "+ EspecieDao.TABLENAME + " (" +
                EspecieDao.Properties.Descripcion.columnName +
                ") VALUES('Albahaca')");
        db.execSQL("INSERT INTO "+ EspecieDao.TABLENAME + " (" +
                EspecieDao.Properties.Descripcion.columnName +
                ") VALUES('Perejil')");
        db.execSQL("INSERT INTO "+ EspecieDao.TABLENAME + " (" +
                EspecieDao.Properties.Descripcion.columnName +
                ") VALUES('Cebolla')");
        db.execSQL("INSERT INTO "+ EspecieDao.TABLENAME + " (" +
                EspecieDao.Properties.Descripcion.columnName +
                ") VALUES('Apio')");
        db.execSQL("INSERT INTO "+ EspecieDao.TABLENAME + " (" +
                EspecieDao.Properties.Descripcion.columnName +
                ") VALUES('Escarola')");

        //carga inicial tipo de produccion
        db.execSQL("INSERT INTO "+ TipoProduccionDao.TABLENAME + " (" +
                TipoProduccionDao.Properties.Descripcion.columnName +
                ") VALUES('Atado')");
        db.execSQL("INSERT INTO "+ TipoProduccionDao.TABLENAME + " (" +
                TipoProduccionDao.Properties.Descripcion.columnName +
                ") VALUES('Bolsa')");
        db.execSQL("INSERT INTO "+ TipoProduccionDao.TABLENAME + " (" +
                TipoProduccionDao.Properties.Descripcion.columnName +
                ") VALUES('Jaula')");
        db.execSQL("INSERT INTO "+ TipoProduccionDao.TABLENAME + " (" +
                TipoProduccionDao.Properties.Descripcion.columnName +
                ") VALUES('Otras')");

        //carga inicial Eleccion Cultivo
        db.execSQL("INSERT INTO "+ EleccionCultivoDao.TABLENAME + " (" +
                EleccionCultivoDao.Properties.Descripcion.columnName +
                ") VALUES('Rotacion')");
        db.execSQL("INSERT INTO "+ EleccionCultivoDao.TABLENAME + " (" +
                EleccionCultivoDao.Properties.Descripcion.columnName +
                ") VALUES('Habitualidad')");
        db.execSQL("INSERT INTO "+ EleccionCultivoDao.TABLENAME + " (" +
                EleccionCultivoDao.Properties.Descripcion.columnName +
                ") VALUES('Seguridad de venta')");
        db.execSQL("INSERT INTO "+ EleccionCultivoDao.TABLENAME + " (" +
                EleccionCultivoDao.Properties.Descripcion.columnName +
                ") VALUES('Por Contrato')");
        db.execSQL("INSERT INTO "+ EleccionCultivoDao.TABLENAME + " (" +
                EleccionCultivoDao.Properties.Descripcion.columnName +
                ") VALUES('Precio Anterior')");
        db.execSQL("INSERT INTO "+ EleccionCultivoDao.TABLENAME + " (" +
                EleccionCultivoDao.Properties.Descripcion.columnName +
                ") VALUES('Precio Futuro')");
        db.execSQL("INSERT INTO "+ EleccionCultivoDao.TABLENAME + " (" +
                EleccionCultivoDao.Properties.Descripcion.columnName +
                ") VALUES('Otro')");

        //carga inicial Asesoramiento
        db.execSQL("INSERT INTO "+ AsesoramientoDao.TABLENAME + " (" +
                AsesoramientoDao.Properties.Descripcion.columnName +
                ") VALUES('Agronomo Particular')");
        db.execSQL("INSERT INTO "+ AsesoramientoDao.TABLENAME + " (" +
                AsesoramientoDao.Properties.Descripcion.columnName +
                ") VALUES('CORFO')");
        db.execSQL("INSERT INTO "+ AsesoramientoDao.TABLENAME + " (" +
                AsesoramientoDao.Properties.Descripcion.columnName +
                ") VALUES('INTA')");
        db.execSQL("INSERT INTO "+ AsesoramientoDao.TABLENAME + " (" +
                AsesoramientoDao.Properties.Descripcion.columnName +
                ") VALUES('Un vecino/Conocido')");
        db.execSQL("INSERT INTO "+ AsesoramientoDao.TABLENAME + " (" +
                AsesoramientoDao.Properties.Descripcion.columnName +
                ") VALUES('Nadie')");
        db.execSQL("INSERT INTO "+ AsesoramientoDao.TABLENAME + " (" +
                AsesoramientoDao.Properties.Descripcion.columnName +
                ") VALUES('Otro')");

        //carga Inicial Factor Climatico
        db.execSQL("INSERT INTO "+ FactorClimaticoDao.TABLENAME + " (" +
                FactorClimaticoDao.Properties.Descripcion.columnName +
                ") VALUES('Viento')");
        db.execSQL("INSERT INTO "+ FactorClimaticoDao.TABLENAME + " (" +
                FactorClimaticoDao.Properties.Descripcion.columnName +
                ") VALUES('Luvia')");
        db.execSQL("INSERT INTO "+ FactorClimaticoDao.TABLENAME + " (" +
                FactorClimaticoDao.Properties.Descripcion.columnName +
                ") VALUES('Viento y Lluvia')");
        db.execSQL("INSERT INTO "+ FactorClimaticoDao.TABLENAME + " (" +
                FactorClimaticoDao.Properties.Descripcion.columnName +
                ") VALUES('Ninguno')");

        //Carga Inicial Triple Lavado
        db.execSQL("INSERT INTO "+ TripleLavadoDao.TABLENAME + " (" +
                TripleLavadoDao.Properties.Descripcion.columnName +
                ") VALUES('Lo Conozco y lo uso')");
        db.execSQL("INSERT INTO "+ TripleLavadoDao.TABLENAME + " (" +
                TripleLavadoDao.Properties.Descripcion.columnName +
                ") VALUES('Lo Conozco y no lo uso')");
        db.execSQL("INSERT INTO "+ TripleLavadoDao.TABLENAME + " (" +
                TripleLavadoDao.Properties.Descripcion.columnName +
                ") VALUES('No lo conozco')");*/
    }
}
