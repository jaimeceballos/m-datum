package mdatum.udc.com.m_datum.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 24/10/17.
 */
@Entity
public class AnioEstructura {

    @Id (autoincrement = true)
    private Long id;
    private String descripcion;
    @Generated(hash = 433266489)
    public AnioEstructura(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    @Generated(hash = 199460522)
    public AnioEstructura() {
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
