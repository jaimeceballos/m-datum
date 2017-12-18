package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 29/06/17.
 */
@Entity
public class Familia {

    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("esCasado")
    private boolean esCasado;
    @SerializedName("tieneHijos")
    private boolean tieneHijos;
    @SerializedName("cantVarones")
    private int cantVarones;
    @SerializedName("cantMujeres")
    private int cantMujeres;
    @Expose(serialize = false,deserialize = false)
    private int idRemote;
    @Expose(serialize = false, deserialize = false)
    private boolean isSinchronized;
    @Generated(hash = 440285608)
    public Familia(Long id, boolean esCasado, boolean tieneHijos, int cantVarones,
            int cantMujeres, int idRemote, boolean isSinchronized) {
        this.id = id;
        this.esCasado = esCasado;
        this.tieneHijos = tieneHijos;
        this.cantVarones = cantVarones;
        this.cantMujeres = cantMujeres;
        this.idRemote = idRemote;
        this.isSinchronized = isSinchronized;
    }
    @Generated(hash = 2042212105)
    public Familia() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getEsCasado() {
        return this.esCasado;
    }
    public void setEsCasado(boolean esCasado) {
        this.esCasado = esCasado;
    }
    public boolean getTieneHijos() {
        return this.tieneHijos;
    }
    public void setTieneHijos(boolean tieneHijos) {
        this.tieneHijos = tieneHijos;
    }
    public int getCantVarones() {
        return this.cantVarones;
    }
    public void setCantVarones(int cantVarones) {
        this.cantVarones = cantVarones;
    }
    public int getCantMujeres() {
        return this.cantMujeres;
    }
    public void setCantMujeres(int cantMujeres) {
        this.cantMujeres = cantMujeres;
    }
    public int getIdRemote() {
        return this.idRemote;
    }
    public void setIdRemote(int idRemote) {
        this.idRemote = idRemote;
    }
    public boolean getIsSinchronized() {
        return this.isSinchronized;
    }
    public void setIsSinchronized(boolean isSinchronized) {
        this.isSinchronized = isSinchronized;
    }

}
