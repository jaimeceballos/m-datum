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
 * DAO for table "ENCUESTA_CULTIVO".
*/
public class EncuestaCultivoDao extends AbstractDao<EncuestaCultivo, Long> {

    public static final String TABLENAME = "ENCUESTA_CULTIVO";

    /**
     * Properties of entity EncuestaCultivo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property IdEncuesta = new Property(1, Long.class, "idEncuesta", false, "ID_ENCUESTA");
        public final static Property IdCultivo = new Property(2, Long.class, "idCultivo", false, "ID_CULTIVO");
    }


    public EncuestaCultivoDao(DaoConfig config) {
        super(config);
    }
    
    public EncuestaCultivoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ENCUESTA_CULTIVO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ID_ENCUESTA\" INTEGER," + // 1: idEncuesta
                "\"ID_CULTIVO\" INTEGER);"); // 2: idCultivo
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ENCUESTA_CULTIVO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, EncuestaCultivo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long idEncuesta = entity.getIdEncuesta();
        if (idEncuesta != null) {
            stmt.bindLong(2, idEncuesta);
        }
 
        Long idCultivo = entity.getIdCultivo();
        if (idCultivo != null) {
            stmt.bindLong(3, idCultivo);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, EncuestaCultivo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long idEncuesta = entity.getIdEncuesta();
        if (idEncuesta != null) {
            stmt.bindLong(2, idEncuesta);
        }
 
        Long idCultivo = entity.getIdCultivo();
        if (idCultivo != null) {
            stmt.bindLong(3, idCultivo);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public EncuestaCultivo readEntity(Cursor cursor, int offset) {
        EncuestaCultivo entity = new EncuestaCultivo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // idEncuesta
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // idCultivo
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, EncuestaCultivo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIdEncuesta(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setIdCultivo(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(EncuestaCultivo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(EncuestaCultivo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(EncuestaCultivo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
