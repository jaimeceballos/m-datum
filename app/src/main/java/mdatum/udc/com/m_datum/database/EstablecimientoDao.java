package mdatum.udc.com.m_datum.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ESTABLECIMIENTO".
*/
public class EstablecimientoDao extends AbstractDao<Establecimiento, Long> {

    public static final String TABLENAME = "ESTABLECIMIENTO";

    /**
     * Properties of entity Establecimiento.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Nombre = new Property(1, String.class, "nombre", false, "NOMBRE");
        public final static Property Nro = new Property(2, String.class, "nro", false, "NRO");
        public final static Property PosLatitud = new Property(3, String.class, "posLatitud", false, "POS_LATITUD");
        public final static Property PosLongitud = new Property(4, String.class, "posLongitud", false, "POS_LONGITUD");
        public final static Property Foto = new Property(5, String.class, "foto", false, "FOTO");
        public final static Property RegimenTenenciaId = new Property(6, int.class, "regimenTenenciaId", false, "REGIMEN_TENENCIA_ID");
        public final static Property RegimenOtros = new Property(7, String.class, "regimenOtros", false, "REGIMEN_OTROS");
        public final static Property RemoteId = new Property(8, int.class, "remoteId", false, "REMOTE_ID");
        public final static Property IsSinchronized = new Property(9, boolean.class, "isSinchronized", false, "IS_SINCHRONIZED");
    }


    public EstablecimientoDao(DaoConfig config) {
        super(config);
    }
    
    public EstablecimientoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ESTABLECIMIENTO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NOMBRE\" TEXT," + // 1: nombre
                "\"NRO\" TEXT," + // 2: nro
                "\"POS_LATITUD\" TEXT," + // 3: posLatitud
                "\"POS_LONGITUD\" TEXT," + // 4: posLongitud
                "\"FOTO\" TEXT," + // 5: foto
                "\"REGIMEN_TENENCIA_ID\" INTEGER NOT NULL ," + // 6: regimenTenenciaId
                "\"REGIMEN_OTROS\" TEXT," + // 7: regimenOtros
                "\"REMOTE_ID\" INTEGER NOT NULL ," + // 8: remoteId
                "\"IS_SINCHRONIZED\" INTEGER NOT NULL );"); // 9: isSinchronized
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_ESTABLECIMIENTO_NOMBRE_NRO ON ESTABLECIMIENTO" +
                " (\"NOMBRE\" ASC,\"NRO\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ESTABLECIMIENTO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Establecimiento entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String nombre = entity.getNombre();
        if (nombre != null) {
            stmt.bindString(2, nombre);
        }
 
        String nro = entity.getNro();
        if (nro != null) {
            stmt.bindString(3, nro);
        }
 
        String posLatitud = entity.getPosLatitud();
        if (posLatitud != null) {
            stmt.bindString(4, posLatitud);
        }
 
        String posLongitud = entity.getPosLongitud();
        if (posLongitud != null) {
            stmt.bindString(5, posLongitud);
        }
 
        String foto = entity.getFoto();
        if (foto != null) {
            stmt.bindString(6, foto);
        }
        stmt.bindLong(7, entity.getRegimenTenenciaId());
 
        String regimenOtros = entity.getRegimenOtros();
        if (regimenOtros != null) {
            stmt.bindString(8, regimenOtros);
        }
        stmt.bindLong(9, entity.getRemoteId());
        stmt.bindLong(10, entity.getIsSinchronized() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Establecimiento entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String nombre = entity.getNombre();
        if (nombre != null) {
            stmt.bindString(2, nombre);
        }
 
        String nro = entity.getNro();
        if (nro != null) {
            stmt.bindString(3, nro);
        }
 
        String posLatitud = entity.getPosLatitud();
        if (posLatitud != null) {
            stmt.bindString(4, posLatitud);
        }
 
        String posLongitud = entity.getPosLongitud();
        if (posLongitud != null) {
            stmt.bindString(5, posLongitud);
        }
 
        String foto = entity.getFoto();
        if (foto != null) {
            stmt.bindString(6, foto);
        }
        stmt.bindLong(7, entity.getRegimenTenenciaId());
 
        String regimenOtros = entity.getRegimenOtros();
        if (regimenOtros != null) {
            stmt.bindString(8, regimenOtros);
        }
        stmt.bindLong(9, entity.getRemoteId());
        stmt.bindLong(10, entity.getIsSinchronized() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Establecimiento readEntity(Cursor cursor, int offset) {
        Establecimiento entity = new Establecimiento( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // nombre
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // nro
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // posLatitud
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // posLongitud
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // foto
            cursor.getInt(offset + 6), // regimenTenenciaId
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // regimenOtros
            cursor.getInt(offset + 8), // remoteId
            cursor.getShort(offset + 9) != 0 // isSinchronized
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Establecimiento entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNombre(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNro(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPosLatitud(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPosLongitud(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFoto(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setRegimenTenenciaId(cursor.getInt(offset + 6));
        entity.setRegimenOtros(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setRemoteId(cursor.getInt(offset + 8));
        entity.setIsSinchronized(cursor.getShort(offset + 9) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Establecimiento entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Establecimiento entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Establecimiento entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
