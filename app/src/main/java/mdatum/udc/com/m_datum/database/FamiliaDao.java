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
 * DAO for table "FAMILIA".
*/
public class FamiliaDao extends AbstractDao<Familia, Long> {

    public static final String TABLENAME = "FAMILIA";

    /**
     * Properties of entity Familia.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property EsCasado = new Property(1, boolean.class, "esCasado", false, "ES_CASADO");
        public final static Property TieneHijos = new Property(2, boolean.class, "tieneHijos", false, "TIENE_HIJOS");
        public final static Property CantVarones = new Property(3, int.class, "cantVarones", false, "CANT_VARONES");
        public final static Property CantMujeres = new Property(4, int.class, "cantMujeres", false, "CANT_MUJERES");
        public final static Property IdRemote = new Property(5, int.class, "idRemote", false, "ID_REMOTE");
        public final static Property IsSinchronized = new Property(6, boolean.class, "isSinchronized", false, "IS_SINCHRONIZED");
    }


    public FamiliaDao(DaoConfig config) {
        super(config);
    }
    
    public FamiliaDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FAMILIA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ES_CASADO\" INTEGER NOT NULL ," + // 1: esCasado
                "\"TIENE_HIJOS\" INTEGER NOT NULL ," + // 2: tieneHijos
                "\"CANT_VARONES\" INTEGER NOT NULL ," + // 3: cantVarones
                "\"CANT_MUJERES\" INTEGER NOT NULL ," + // 4: cantMujeres
                "\"ID_REMOTE\" INTEGER NOT NULL ," + // 5: idRemote
                "\"IS_SINCHRONIZED\" INTEGER NOT NULL );"); // 6: isSinchronized
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FAMILIA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Familia entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getEsCasado() ? 1L: 0L);
        stmt.bindLong(3, entity.getTieneHijos() ? 1L: 0L);
        stmt.bindLong(4, entity.getCantVarones());
        stmt.bindLong(5, entity.getCantMujeres());
        stmt.bindLong(6, entity.getIdRemote());
        stmt.bindLong(7, entity.getIsSinchronized() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Familia entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getEsCasado() ? 1L: 0L);
        stmt.bindLong(3, entity.getTieneHijos() ? 1L: 0L);
        stmt.bindLong(4, entity.getCantVarones());
        stmt.bindLong(5, entity.getCantMujeres());
        stmt.bindLong(6, entity.getIdRemote());
        stmt.bindLong(7, entity.getIsSinchronized() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Familia readEntity(Cursor cursor, int offset) {
        Familia entity = new Familia( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getShort(offset + 1) != 0, // esCasado
            cursor.getShort(offset + 2) != 0, // tieneHijos
            cursor.getInt(offset + 3), // cantVarones
            cursor.getInt(offset + 4), // cantMujeres
            cursor.getInt(offset + 5), // idRemote
            cursor.getShort(offset + 6) != 0 // isSinchronized
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Familia entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEsCasado(cursor.getShort(offset + 1) != 0);
        entity.setTieneHijos(cursor.getShort(offset + 2) != 0);
        entity.setCantVarones(cursor.getInt(offset + 3));
        entity.setCantMujeres(cursor.getInt(offset + 4));
        entity.setIdRemote(cursor.getInt(offset + 5));
        entity.setIsSinchronized(cursor.getShort(offset + 6) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Familia entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Familia entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Familia entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
