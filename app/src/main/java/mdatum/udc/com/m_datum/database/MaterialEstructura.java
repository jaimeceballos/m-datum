package mdatum.udc.com.m_datum.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 24/10/17.
 */
@Entity
public class MaterialEstructura {

    @Id(autoincrement = true)
    private Long id;
    private String descripcion;
    @Generated(hash = 700231104)
    public MaterialEstructura(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    @Generated(hash = 765363012)
    public MaterialEstructura() {
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
