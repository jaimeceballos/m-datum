package mdatum.udc.com.m_datum.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 26/10/17.
 */

@Entity
public class Agroquimicos {

    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("usa")
    private boolean usa;
    @SerializedName("factorClimatico")
    private Long factor_climatico;
    @SerializedName("tripleLavado")
    private Long triple_lavado;
    @SerializedName("asesoramiento")
    private Long asesoramiento;
    @SerializedName("asesoramientoOtro")
    private String asesoramiento_otro;
    @Expose(deserialize = false,serialize = false)
    private int remoteId;
    @Expose(deserialize = false,serialize = false)
    private boolean isSincronized;
    @Generated(hash = 2002469106)
    public Agroquimicos(Long id, boolean usa, Long factor_climatico,
            Long triple_lavado, Long asesoramiento, String asesoramiento_otro,
            int remoteId, boolean isSincronized) {
        this.id = id;
        this.usa = usa;
        this.factor_climatico = factor_climatico;
        this.triple_lavado = triple_lavado;
        this.asesoramiento = asesoramiento;
        this.asesoramiento_otro = asesoramiento_otro;
        this.remoteId = remoteId;
        this.isSincronized = isSincronized;
    }
    @Generated(hash = 1769417060)
    public Agroquimicos() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getUsa() {
        return this.usa;
    }
    public void setUsa(boolean usa) {
        this.usa = usa;
    }
    public Long getFactor_climatico() {
        return this.factor_climatico;
    }
    public void setFactor_climatico(Long factor_climatico) {
        this.factor_climatico = factor_climatico;
    }
    public Long getTriple_lavado() {
        return this.triple_lavado;
    }
    public void setTriple_lavado(Long triple_lavado) {
        this.triple_lavado = triple_lavado;
    }
    public Long getAsesoramiento() {
        return this.asesoramiento;
    }
    public void setAsesoramiento(Long asesoramiento) {
        this.asesoramiento = asesoramiento;
    }
    public String getAsesoramiento_otro() {
        return this.asesoramiento_otro;
    }
    public void setAsesoramiento_otro(String asesoramiento_otro) {
        this.asesoramiento_otro = asesoramiento_otro;
    }
    public int getRemoteId() {
        return this.remoteId;
    }
    public void setRemoteId(int remoteId) {
        this.remoteId = remoteId;
    }
    public boolean getIsSincronized() {
        return this.isSincronized;
    }
    public void setIsSincronized(boolean isSincronized) {
        this.isSincronized = isSincronized;
    }



}
