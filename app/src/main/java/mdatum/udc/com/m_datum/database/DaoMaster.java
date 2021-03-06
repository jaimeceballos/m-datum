package mdatum.udc.com.m_datum.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * Master of DAO (schema version 1): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        FactorClimaticoDao.createTable(db, ifNotExists);
        EleccionCultivoDao.createTable(db, ifNotExists);
        RegimenTenenciaDao.createTable(db, ifNotExists);
        FamiliaDao.createTable(db, ifNotExists);
        AsesoramientoDao.createTable(db, ifNotExists);
        EspecieDao.createTable(db, ifNotExists);
        UpdatesToServerDao.createTable(db, ifNotExists);
        EncuestadoDao.createTable(db, ifNotExists);
        EncuestaDao.createTable(db, ifNotExists);
        TipoCultivoDao.createTable(db, ifNotExists);
        AnioEstructuraDao.createTable(db, ifNotExists);
        EstablecimientoDao.createTable(db, ifNotExists);
        TripleLavadoDao.createTable(db, ifNotExists);
        InvernaculoDao.createTable(db, ifNotExists);
        CultivoDao.createTable(db, ifNotExists);
        AgroquimicoUsadoDao.createTable(db, ifNotExists);
        MaterialEstructuraDao.createTable(db, ifNotExists);
        NivelInstruccionDao.createTable(db, ifNotExists);
        AgroquimicosDao.createTable(db, ifNotExists);
        TipoProduccionDao.createTable(db, ifNotExists);
        NacionalidadDao.createTable(db, ifNotExists);
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        FactorClimaticoDao.dropTable(db, ifExists);
        EleccionCultivoDao.dropTable(db, ifExists);
        RegimenTenenciaDao.dropTable(db, ifExists);
        FamiliaDao.dropTable(db, ifExists);
        AsesoramientoDao.dropTable(db, ifExists);
        EspecieDao.dropTable(db, ifExists);
        UpdatesToServerDao.dropTable(db, ifExists);
        EncuestadoDao.dropTable(db, ifExists);
        EncuestaDao.dropTable(db, ifExists);
        TipoCultivoDao.dropTable(db, ifExists);
        AnioEstructuraDao.dropTable(db, ifExists);
        EstablecimientoDao.dropTable(db, ifExists);
        TripleLavadoDao.dropTable(db, ifExists);
        InvernaculoDao.dropTable(db, ifExists);
        CultivoDao.dropTable(db, ifExists);
        AgroquimicoUsadoDao.dropTable(db, ifExists);
        MaterialEstructuraDao.dropTable(db, ifExists);
        NivelInstruccionDao.dropTable(db, ifExists);
        AgroquimicosDao.dropTable(db, ifExists);
        TipoProduccionDao.dropTable(db, ifExists);
        NacionalidadDao.dropTable(db, ifExists);
    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database db = new DevOpenHelper(context, name).getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(FactorClimaticoDao.class);
        registerDaoClass(EleccionCultivoDao.class);
        registerDaoClass(RegimenTenenciaDao.class);
        registerDaoClass(FamiliaDao.class);
        registerDaoClass(AsesoramientoDao.class);
        registerDaoClass(EspecieDao.class);
        registerDaoClass(UpdatesToServerDao.class);
        registerDaoClass(EncuestadoDao.class);
        registerDaoClass(EncuestaDao.class);
        registerDaoClass(TipoCultivoDao.class);
        registerDaoClass(AnioEstructuraDao.class);
        registerDaoClass(EstablecimientoDao.class);
        registerDaoClass(TripleLavadoDao.class);
        registerDaoClass(InvernaculoDao.class);
        registerDaoClass(CultivoDao.class);
        registerDaoClass(AgroquimicoUsadoDao.class);
        registerDaoClass(MaterialEstructuraDao.class);
        registerDaoClass(NivelInstruccionDao.class);
        registerDaoClass(AgroquimicosDao.class);
        registerDaoClass(TipoProduccionDao.class);
        registerDaoClass(NacionalidadDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }

    /**
     * Calls {@link #createAllTables(Database, boolean)} in {@link #onCreate(Database)} -
     */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

}
