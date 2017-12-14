package mdatum.udc.com.m_datum.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

/**
 * Created by jaime on 27/06/17.
 */

@Entity
public class Encuesta implements Serializable {

    private static final long serialVersionUID = 42L;

    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;

    @SerializedName("establecimiento")
    private Long establecimientoId;

    @ToOne(joinProperty = "establecimientoId")
    private Establecimiento establecimientoRelated;

    @SerializedName("encuestado")
    private Long encuestadoId;

    @ToOne(joinProperty = "encuestadoId")
    private Encuestado encuestadoRelated;

    @SerializedName("familia")
    private Long familiaId;

    @ToOne(joinProperty = "familiaId")
    private Familia familiaRelated;

    @SerializedName("agroquimico")
    private Long agroquimicoId;

    @ToOne(joinProperty = "agroquimicoId")
    private Agroquimicos agroquimicoRelated;

    @ToMany(referencedJoinProperty = "encuestaId")
    @OrderBy("id ASC")
    private List<Invernaculo> invernaculos;

    @Expose(serialize = false,deserialize = false)
    @ToMany(referencedJoinProperty = "encuestaId")
    @OrderBy("id ASC")
    private List<Cultivo> cultivos;

    @Expose(serialize = false,deserialize = false)
    @ToMany(referencedJoinProperty = "encuestaId")
    @OrderBy("id ASC")
    private List<AgroquimicoUsado> agroquimicoUsados;

    @SerializedName("creado")
    private String fecha;

    @SerializedName("usuario")
    private int usuario;

    @Expose(serialize = false,deserialize = false)
    private boolean isSincronized;

    @Expose(serialize = false,deserialize = false)
    private int remote_id;

    @Expose(serialize = false,deserialize = false)
    private boolean is_finished;

    @SerializedName("transaccion")
    private String transaccion;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 245791029)
    private transient EncuestaDao myDao;

    @Generated(hash = 351400041)
    public Encuesta(Long id, Long establecimientoId, Long encuestadoId,
            Long familiaId, Long agroquimicoId, String fecha, int usuario,
            boolean isSincronized, int remote_id, boolean is_finished,
            String transaccion) {
        this.id = id;
        this.establecimientoId = establecimientoId;
        this.encuestadoId = encuestadoId;
        this.familiaId = familiaId;
        this.agroquimicoId = agroquimicoId;
        this.fecha = fecha;
        this.usuario = usuario;
        this.isSincronized = isSincronized;
        this.remote_id = remote_id;
        this.is_finished = is_finished;
        this.transaccion = transaccion;
    }

    @Generated(hash = 483204667)
    public Encuesta() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstablecimientoId() {
        return this.establecimientoId;
    }

    public void setEstablecimientoId(Long establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public Long getEncuestadoId() {
        return this.encuestadoId;
    }

    public void setEncuestadoId(Long encuestadoId) {
        this.encuestadoId = encuestadoId;
    }

    public Long getFamiliaId() {
        return this.familiaId;
    }

    public void setFamiliaId(Long familiaId) {
        this.familiaId = familiaId;
    }

    public Long getAgroquimicoId() {
        return this.agroquimicoId;
    }

    public void setAgroquimicoId(Long agroquimicoId) {
        this.agroquimicoId = agroquimicoId;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getUsuario() {
        return this.usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public boolean getIsSincronized() {
        return this.isSincronized;
    }

    public void setIsSincronized(boolean isSincronized) {
        this.isSincronized = isSincronized;
    }

    public int getRemote_id() {
        return this.remote_id;
    }

    public void setRemote_id(int remote_id) {
        this.remote_id = remote_id;
    }

    public boolean getIs_finished() {
        return this.is_finished;
    }

    public void setIs_finished(boolean is_finished) {
        this.is_finished = is_finished;
    }

    public String getTransaccion() {
        return this.transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    @Generated(hash = 1989921088)
    private transient Long establecimientoRelated__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1698050870)
    public Establecimiento getEstablecimientoRelated() {
        Long __key = this.establecimientoId;
        if (establecimientoRelated__resolvedKey == null
                || !establecimientoRelated__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EstablecimientoDao targetDao = daoSession.getEstablecimientoDao();
            Establecimiento establecimientoRelatedNew = targetDao.load(__key);
            synchronized (this) {
                establecimientoRelated = establecimientoRelatedNew;
                establecimientoRelated__resolvedKey = __key;
            }
        }
        return establecimientoRelated;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 433761928)
    public void setEstablecimientoRelated(Establecimiento establecimientoRelated) {
        synchronized (this) {
            this.establecimientoRelated = establecimientoRelated;
            establecimientoId = establecimientoRelated == null ? null
                    : establecimientoRelated.getId();
            establecimientoRelated__resolvedKey = establecimientoId;
        }
    }

    @Generated(hash = 2085671147)
    private transient Long encuestadoRelated__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1634197041)
    public Encuestado getEncuestadoRelated() {
        Long __key = this.encuestadoId;
        if (encuestadoRelated__resolvedKey == null
                || !encuestadoRelated__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EncuestadoDao targetDao = daoSession.getEncuestadoDao();
            Encuestado encuestadoRelatedNew = targetDao.load(__key);
            synchronized (this) {
                encuestadoRelated = encuestadoRelatedNew;
                encuestadoRelated__resolvedKey = __key;
            }
        }
        return encuestadoRelated;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2046802682)
    public void setEncuestadoRelated(Encuestado encuestadoRelated) {
        synchronized (this) {
            this.encuestadoRelated = encuestadoRelated;
            encuestadoId = encuestadoRelated == null ? null
                    : encuestadoRelated.getId();
            encuestadoRelated__resolvedKey = encuestadoId;
        }
    }

    @Generated(hash = 1100154527)
    private transient Long familiaRelated__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1354839704)
    public Familia getFamiliaRelated() {
        Long __key = this.familiaId;
        if (familiaRelated__resolvedKey == null
                || !familiaRelated__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FamiliaDao targetDao = daoSession.getFamiliaDao();
            Familia familiaRelatedNew = targetDao.load(__key);
            synchronized (this) {
                familiaRelated = familiaRelatedNew;
                familiaRelated__resolvedKey = __key;
            }
        }
        return familiaRelated;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2097263250)
    public void setFamiliaRelated(Familia familiaRelated) {
        synchronized (this) {
            this.familiaRelated = familiaRelated;
            familiaId = familiaRelated == null ? null : familiaRelated.getId();
            familiaRelated__resolvedKey = familiaId;
        }
    }

    @Generated(hash = 183711360)
    private transient Long agroquimicoRelated__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1988386163)
    public Agroquimicos getAgroquimicoRelated() {
        Long __key = this.agroquimicoId;
        if (agroquimicoRelated__resolvedKey == null
                || !agroquimicoRelated__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AgroquimicosDao targetDao = daoSession.getAgroquimicosDao();
            Agroquimicos agroquimicoRelatedNew = targetDao.load(__key);
            synchronized (this) {
                agroquimicoRelated = agroquimicoRelatedNew;
                agroquimicoRelated__resolvedKey = __key;
            }
        }
        return agroquimicoRelated;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1380668656)
    public void setAgroquimicoRelated(Agroquimicos agroquimicoRelated) {
        synchronized (this) {
            this.agroquimicoRelated = agroquimicoRelated;
            agroquimicoId = agroquimicoRelated == null ? null
                    : agroquimicoRelated.getId();
            agroquimicoRelated__resolvedKey = agroquimicoId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 298458283)
    public List<Invernaculo> getInvernaculos() {
        if (invernaculos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            InvernaculoDao targetDao = daoSession.getInvernaculoDao();
            List<Invernaculo> invernaculosNew = targetDao
                    ._queryEncuesta_Invernaculos(id);
            synchronized (this) {
                if (invernaculos == null) {
                    invernaculos = invernaculosNew;
                }
            }
        }
        return invernaculos;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1479945653)
    public synchronized void resetInvernaculos() {
        invernaculos = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1322016946)
    public List<Cultivo> getCultivos() {
        if (cultivos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CultivoDao targetDao = daoSession.getCultivoDao();
            List<Cultivo> cultivosNew = targetDao._queryEncuesta_Cultivos(id);
            synchronized (this) {
                if (cultivos == null) {
                    cultivos = cultivosNew;
                }
            }
        }
        return cultivos;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1044076171)
    public synchronized void resetCultivos() {
        cultivos = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1083681509)
    public List<AgroquimicoUsado> getAgroquimicoUsados() {
        if (agroquimicoUsados == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AgroquimicoUsadoDao targetDao = daoSession.getAgroquimicoUsadoDao();
            List<AgroquimicoUsado> agroquimicoUsadosNew = targetDao
                    ._queryEncuesta_AgroquimicoUsados(id);
            synchronized (this) {
                if (agroquimicoUsados == null) {
                    agroquimicoUsados = agroquimicoUsadosNew;
                }
            }
        }
        return agroquimicoUsados;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1410594084)
    public synchronized void resetAgroquimicoUsados() {
        agroquimicoUsados = null;
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
    @Generated(hash = 288983806)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEncuestaDao() : null;
    }

     
    
}
