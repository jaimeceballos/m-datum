package mdatum.udc.com.m_datum.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 25/10/17.
 */

@Entity
public class TipoProduccion {

    @Id(autoincrement = true)
    private Long id;
    private String descripcion;
    @Generated(hash = 547305952)
    public TipoProduccion(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    @Generated(hash = 597019469)
    public TipoProduccion() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
