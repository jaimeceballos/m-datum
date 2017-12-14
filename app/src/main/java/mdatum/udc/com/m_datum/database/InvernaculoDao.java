package mdatum.udc.com.m_datum.database;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "INVERNACULO".
*/
public class InvernaculoDao extends AbstractDao<Invernaculo, Long> {

    public static final String TABLENAME = "INVERNACULO";

    /**
     * Properties of entity Invernaculo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CantidadModulos = new Property(1, int.class, "cantidadModulos", false, "CANTIDAD_MODULOS");
        public final static Property SuperficieUnitaria = new Property(2, int.class, "superficieUnitaria", false, "SUPERFICIE_UNITARIA");
        public final static Property MaterialEstructuraId = new Property(3, int.class, "materialEstructuraId", false, "MATERIAL_ESTRUCTURA_ID");
        public final static Property AnioConstruccionId = new Property(4, int.class, "anioConstruccionId", false, "ANIO_CONSTRUCCION_ID");
        public final static Property EncuestaId = new Property(5, Long.class, "encuestaId", false, "ENCUESTA_ID");
    }

    private Query<Invernaculo> encuesta_InvernaculosQuery;

    public InvernaculoDao(DaoConfig config) {
        super(config);
    }
    
    public InvernaculoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"INVERNACULO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CANTIDAD_MODULOS\" INTEGER NOT NULL ," + // 1: cantidadModulos
                "\"SUPERFICIE_UNITARIA\" INTEGER NOT NULL ," + // 2: superficieUnitaria
                "\"MATERIAL_ESTRUCTURA_ID\" INTEGER NOT NULL ," + // 3: materialEstructuraId
                "\"ANIO_CONSTRUCCION_ID\" INTEGER NOT NULL ," + // 4: anioConstruccionId
                "\"ENCUESTA_ID\" INTEGER);"); // 5: encuestaId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"INVERNACULO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Invernaculo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCantidadModulos());
        stmt.bindLong(3, entity.getSuperficieUnitaria());
        stmt.bindLong(4, entity.getMaterialEstructuraId());
        stmt.bindLong(5, entity.getAnioConstruccionId());
 
        Long encuestaId = entity.getEncuestaId();
        if (encuestaId != null) {
            stmt.bindLong(6, encuestaId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Invernaculo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCantidadModulos());
        stmt.bindLong(3, entity.getSuperficieUnitaria());
        stmt.bindLong(4, entity.getMaterialEstructuraId());
        stmt.bindLong(5, entity.getAnioConstruccionId());
 
        Long encuestaId = entity.getEncuestaId();
        if (encuestaId != null) {
            stmt.bindLong(6, encuestaId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Invernaculo readEntity(Cursor cursor, int offset) {
        Invernaculo entity = new Invernaculo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // cantidadModulos
            cursor.getInt(offset + 2), // superficieUnitaria
            cursor.getInt(offset + 3), // materialEstructuraId
            cursor.getInt(offset + 4), // anioConstruccionId
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5) // encuestaId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Invernaculo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCantidadModulos(cursor.getInt(offset + 1));
        entity.setSuperficieUnitaria(cursor.getInt(offset + 2));
        entity.setMaterialEstructuraId(cursor.getInt(offset + 3));
        entity.setAnioConstruccionId(cursor.getInt(offset + 4));
        entity.setEncuestaId(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Invernaculo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Invernaculo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Invernaculo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "invernaculos" to-many relationship of Encuesta. */
    public List<Invernaculo> _queryEncuesta_Invernaculos(Long encuestaId) {
        synchronized (this) {
            if (encuesta_InvernaculosQuery == null) {
                QueryBuilder<Invernaculo> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.EncuestaId.eq(null));
                queryBuilder.orderRaw("T.'_id' ASC");
                encuesta_InvernaculosQuery = queryBuilder.build();
            }
        }
        Query<Invernaculo> query = encuesta_InvernaculosQuery.forCurrentThread();
        query.setParameter(0, encuestaId);
        return query.list();
    }

}
