package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

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

    @SerializedName("transaccion")
    private String transaccion;

    @Generated(hash = 2085572707)
    public Familia(Long id, boolean esCasado, boolean tieneHijos, int cantVarones,
            int cantMujeres, String transaccion) {
        this.id = id;
        this.esCasado = esCasado;
        this.tieneHijos = tieneHijos;
        this.cantVarones = cantVarones;
        this.cantMujeres = cantMujeres;
        this.transaccion = transaccion;
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

    public String getTransaccion() {
        return this.transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }




}
