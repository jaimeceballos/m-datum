package mdatum.udc.com.m_datum.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 26/10/17.
 */

@Entity
public class EncuestaCultivo {

    @Id(autoincrement = true)
    private Long id;
    private Long idEncuesta;
    private Long idCultivo;
    @Generated(hash = 807734013)
    public EncuestaCultivo(Long id, Long idEncuesta, Long idCultivo) {
        this.id = id;
        this.idEncuesta = idEncuesta;
        this.idCultivo = idCultivo;
    }
    @Generated(hash = 374189070)
    public EncuestaCultivo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdEncuesta() {
        return this.idEncuesta;
    }
    public void setIdEncuesta(Long idEncuesta) {
        this.idEncuesta = idEncuesta;
    }
    public Long getIdCultivo() {
        return this.idCultivo;
    }
    public void setIdCultivo(Long idCultivo) {
        this.idCultivo = idCultivo;
    }
}
