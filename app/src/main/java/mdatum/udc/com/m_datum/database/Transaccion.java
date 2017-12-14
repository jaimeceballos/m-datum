package mdatum.udc.com.m_datum.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by jaime on 11/12/17.
 */

@Entity
public class Transaccion {

    @Id(autoincrement = true)
    private long id;

    private long encuestaId;

    @ToOne(joinProperty = "encuestaId")
    private Encuesta encuesta;

    private String transaccion;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1320776634)
    private transient TransaccionDao myDao;

    @Generated(hash = 2075727977)
    public Transaccion(long id, long encuestaId, String transaccion) {
        this.id = id;
        this.encuestaId = encuestaId;
        this.transaccion = transaccion;
    }

    @Generated(hash = 1374811510)
    public Transaccion() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEncuestaId() {
        return this.encuestaId;
    }

    public void setEncuestaId(long encuestaId) {
        this.encuestaId = encuestaId;
    }

    public String getTransaccion() {
        return this.transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    @Generated(hash = 1993739060)
    private transient Long encuesta__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1865667523)
    public Encuesta getEncuesta() {
        long __key = this.encuestaId;
        if (encuesta__resolvedKey == null || !encuesta__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EncuestaDao targetDao = daoSession.getEncuestaDao();
            Encuesta encuestaNew = targetDao.load(__key);
            synchronized (this) {
                encuesta = encuestaNew;
                encuesta__resolvedKey = __key;
            }
        }
        return encuesta;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1928229099)
    public void setEncuesta(@NotNull Encuesta encuesta) {
        if (encuesta == null) {
            throw new DaoException(
                    "To-one property 'encuestaId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.encuesta = encuesta;
            encuestaId = encuesta.getId();
            encuesta__resolvedKey = encuestaId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1729849219)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTransaccionDao() : null;
    }

    
}
