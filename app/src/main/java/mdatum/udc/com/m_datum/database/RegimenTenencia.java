package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 28/06/17.
 */

@Entity
public class RegimenTenencia {

    @org.greenrobot.greendao.annotation.Id (autoincrement = true)
    private Long id;
    private String descpripcion;

    public RegimenTenencia(String descpripcion) {
        this.descpripcion = descpripcion;
    }

    @Generated(hash = 661241241)
    public RegimenTenencia(Long id, String descpripcion) {
        this.id = id;
        this.descpripcion = descpripcion;
    }

    @Generated(hash = 421682395)
    public RegimenTenencia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescpripcion() {
        return descpripcion;
    }

    public void setDescpripcion(String descpripcion) {
        this.descpripcion = descpripcion;
    }


}
