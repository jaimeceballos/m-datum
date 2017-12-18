package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 01/07/17.
 */

@Entity
public class Invernaculo {

    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("cantidadModulos")
    private int cantidadModulos;
    @SerializedName("superficieUnitaria")
    private int superficieUnitaria;
    @SerializedName("materialEstructura")
    private int materialEstructuraId;
    @SerializedName("anioConstruccion")
    private int anioConstruccionId;
    @SerializedName("encuesta")
    private Long encuestaId;
    @Expose(serialize = false,deserialize = false)
    private int idRemote;
    @Expose(serialize = false, deserialize = false)
    private boolean isSinchronized;
    @Generated(hash = 471239125)
    public Invernaculo(Long id, int cantidadModulos, int superficieUnitaria,
            int materialEstructuraId, int anioConstruccionId, Long encuestaId,
            int idRemote, boolean isSinchronized) {
        this.id = id;
        this.cantidadModulos = cantidadModulos;
        this.superficieUnitaria = superficieUnitaria;
        this.materialEstructuraId = materialEstructuraId;
        this.anioConstruccionId = anioConstruccionId;
        this.encuestaId = encuestaId;
        this.idRemote = idRemote;
        this.isSinchronized = isSinchronized;
    }
    @Generated(hash = 1458552915)
    public Invernaculo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCantidadModulos() {
        return this.cantidadModulos;
    }
    public void setCantidadModulos(int cantidadModulos) {
        this.cantidadModulos = cantidadModulos;
    }
    public int getSuperficieUnitaria() {
        return this.superficieUnitaria;
    }
    public void setSuperficieUnitaria(int superficieUnitaria) {
        this.superficieUnitaria = superficieUnitaria;
    }
    public int getMaterialEstructuraId() {
        return this.materialEstructuraId;
    }
    public void setMaterialEstructuraId(int materialEstructuraId) {
        this.materialEstructuraId = materialEstructuraId;
    }
    public int getAnioConstruccionId() {
        return this.anioConstruccionId;
    }
    public void setAnioConstruccionId(int anioConstruccionId) {
        this.anioConstruccionId = anioConstruccionId;
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
