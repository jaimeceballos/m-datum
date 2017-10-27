package mdatum.udc.com.m_datum.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 26/10/17.
 */

@Entity
public class EncuestaAgroquimicoUsado {

    @Id(autoincrement = true)
    private Long id;
    private Long idEncuesta;
    private Long idAgroquimicoUsado;
    @Generated(hash = 1066888789)
    public EncuestaAgroquimicoUsado(Long id, Long idEncuesta,
            Long idAgroquimicoUsado) {
        this.id = id;
        this.idEncuesta = idEncuesta;
        this.idAgroquimicoUsado = idAgroquimicoUsado;
    }
    @Generated(hash = 1139616615)
    public EncuestaAgroquimicoUsado() {
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
    public Long getIdAgroquimicoUsado() {
        return this.idAgroquimicoUsado;
    }
    public void setIdAgroquimicoUsado(Long idAgroquimicoUsado) {
        this.idAgroquimicoUsado = idAgroquimicoUsado;
    }
    
}
