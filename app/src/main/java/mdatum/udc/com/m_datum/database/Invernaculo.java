package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

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

    @Generated(hash = 1553698320)
    public Invernaculo(Long id, int cantidadModulos, int superficieUnitaria, int materialEstructuraId,
            int anioConstruccionId, Long encuestaId) {
        this.id = id;
        this.cantidadModulos = cantidadModulos;
        this.superficieUnitaria = superficieUnitaria;
        this.materialEstructuraId = materialEstructuraId;
        this.anioConstruccionId = anioConstruccionId;
        this.encuestaId = encuestaId;
    }

    @Generated(hash = 1458552915)
    public Invernaculo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidadModulos() {
        return cantidadModulos;
    }

    public void setCantidadModulos(int cantidadModulos) {
        this.cantidadModulos = cantidadModulos;
    }

    public int getSuperficieUnitaria() {
        return superficieUnitaria;
    }

    public void setSuperficieUnitaria(int superficieUnitaria) {
        this.superficieUnitaria = superficieUnitaria;
    }

    public int getMaterialEstructuraId() {
        return materialEstructuraId;
    }

    public void setMaterialEstructuraId(int materialEstructuraId) {
        this.materialEstructuraId = materialEstructuraId;
    }

    public int getAnioConstruccionId() {
        return anioConstruccionId;
    }

    public void setAnioConstruccionId(int anioConstruccionId) {
        this.anioConstruccionId = anioConstruccionId;
    }

    public Long getEncuestaId() {
        return encuestaId;
    }

    public void setEncuestaId(Long encuestaId) {
        this.encuestaId = encuestaId;
    }

}
