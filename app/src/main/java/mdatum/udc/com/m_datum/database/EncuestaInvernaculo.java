package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 02/07/17.
 */


@Entity
public class EncuestaInvernaculo {

    @Id(autoincrement = true)
    private Long id;
    private Long encuestaId;
    private Long invernaculoId;
    @Generated(hash = 613301895)
    public EncuestaInvernaculo(Long id, Long encuestaId, Long invernaculoId) {
        this.id = id;
        this.encuestaId = encuestaId;
        this.invernaculoId = invernaculoId;
    }
    @Generated(hash = 375668003)
    public EncuestaInvernaculo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getEncuestaId() {
        return this.encuestaId;
    }
    public void setEncuestaId(Long encuestaId) {
        this.encuestaId = encuestaId;
    }
    public Long getInvernaculoId() {
        return this.invernaculoId;
    }
    public void setInvernaculoId(Long invernaculoId) {
        this.invernaculoId = invernaculoId;
    }







}
