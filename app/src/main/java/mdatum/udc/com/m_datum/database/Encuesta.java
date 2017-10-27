package mdatum.udc.com.m_datum.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 27/06/17.
 */

@Entity
public class Encuesta implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id(autoincrement = true)
    private Long id;
    private Long establecimientoId;
    private Long encuestadoId;
    private Long familiaId;
    private Long agroquimicoId;
    @Generated(hash = 637315002)
    public Encuesta(Long id, Long establecimientoId, Long encuestadoId,
            Long familiaId, Long agroquimicoId) {
        this.id = id;
        this.establecimientoId = establecimientoId;
        this.encuestadoId = encuestadoId;
        this.familiaId = familiaId;
        this.agroquimicoId = agroquimicoId;
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



}
