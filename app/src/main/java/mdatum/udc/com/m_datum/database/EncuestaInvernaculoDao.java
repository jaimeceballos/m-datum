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
 * DAO for table "ENCUESTA_INVERNACULO".
*/
public class EncuestaInvernaculoDao extends AbstractDao<EncuestaInvernaculo, Long> {

    public static final String TABLENAME = "ENCUESTA_INVERNACULO";

    /**
     * Properties of entity EncuestaInvernaculo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property EncuestaId = new Property(1, Long.class, "encuestaId", false, "ENCUESTA_ID");
        public final static Property InvernaculoId = new Property(2, Long.class, "invernaculoId", false, "INVERNACULO_ID");
    }


    public EncuestaInvernaculoDao(DaoConfig config) {
        super(config);
    }
    
    public EncuestaInvernaculoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ENCUESTA_INVERNACULO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ENCUESTA_ID\" INTEGER," + // 1: encuestaId
                "\"INVERNACULO_ID\" INTEGER);"); // 2: invernaculoId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ENCUESTA_INVERNACULO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, EncuestaInvernaculo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long encuestaId = entity.getEncuestaId();
        if (encuestaId != null) {
            stmt.bindLong(2, encuestaId);
        }
 
        Long invernaculoId = entity.getInvernaculoId();
        if (invernaculoId != null) {
            stmt.bindLong(3, invernaculoId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, EncuestaInvernaculo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long encuestaId = entity.getEncuestaId();
        if (encuestaId != null) {
            stmt.bindLong(2, encuestaId);
        }
 
        Long invernaculoId = entity.getInvernaculoId();
        if (invernaculoId != null) {
            stmt.bindLong(3, invernaculoId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public EncuestaInvernaculo readEntity(Cursor cursor, int offset) {
        EncuestaInvernaculo entity = new EncuestaInvernaculo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // encuestaId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // invernaculoId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, EncuestaInvernaculo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEncuestaId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setInvernaculoId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(EncuestaInvernaculo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(EncuestaInvernaculo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(EncuestaInvernaculo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
