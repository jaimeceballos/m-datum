package mdatum.udc.com.m_datum.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 26/10/17.
 */

@Entity
public class AgroquimicoUsado {

    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("producto")
    private String producto;
    @SerializedName("plaga")
    private String plaga;
    @SerializedName("metodo_aplicacion")
    private String metodo_aplicacion;
    @SerializedName("frecuencia_uso")
    private String frecuencia_uso;
    @SerializedName("encuesta")
    private Long encuestaId;
    @Expose(serialize = false,deserialize = false)
    private int idRemote;
    @Expose(serialize = false, deserialize = false)
    private boolean isSinchronized;
    @Generated(hash = 1814494494)
    public AgroquimicoUsado(Long id, String producto, String plaga,
            String metodo_aplicacion, String frecuencia_uso, Long encuestaId,
            int idRemote, boolean isSinchronized) {
        this.id = id;
        this.producto = producto;
        this.plaga = plaga;
        this.metodo_aplicacion = metodo_aplicacion;
        this.frecuencia_uso = frecuencia_uso;
        this.encuestaId = encuestaId;
        this.idRemote = idRemote;
        this.isSinchronized = isSinchronized;
    }
    @Generated(hash = 299707638)
    public AgroquimicoUsado() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProducto() {
        return this.producto;
    }
    public void setProducto(String producto) {
        this.producto = producto;
    }
    public String getPlaga() {
        return this.plaga;
    }
    public void setPlaga(String plaga) {
        this.plaga = plaga;
    }
    public String getMetodo_aplicacion() {
        return this.metodo_aplicacion;
    }
    public void setMetodo_aplicacion(String metodo_aplicacion) {
        this.metodo_aplicacion = metodo_aplicacion;
    }
    public String getFrecuencia_uso() {
        return this.frecuencia_uso;
    }
    public void setFrecuencia_uso(String frecuencia_uso) {
        this.frecuencia_uso = frecuencia_uso;
    }
    public Long getEncuestaId() {
        return this.encuestaId;
    }
    public void setEncuestaId(Long encuestaId) {
        this.encuestaId = encuestaId;
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
