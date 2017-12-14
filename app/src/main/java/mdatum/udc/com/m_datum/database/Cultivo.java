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
public class Cultivo {

    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("especie")
    private int especieId;
    @SerializedName("tipo")
    private int tipoId;
    @SerializedName("nroSiembra")
    private int nroSiembra;
    @SerializedName("mesSiembra")
    private int mesSiembra;
    @SerializedName("surcos")
    private int surcos;
    @SerializedName("distancias")
    private int distancias;
    @SerializedName("largo")
    private int largo;
    @SerializedName("tipoProduccion")
    private int tipoProduccionId;
    @SerializedName("eleccionCultivo")
    private int eleccionCultivoId;
    @SerializedName("eleccionEspecificar")
    private String eleccionEspecificar;
    @SerializedName("encuesta")
    private Long encuestaId;
    @SerializedName("especieNueva")
    private String nueva_especie;
    @Generated(hash = 1044248098)
    public Cultivo(Long id, int especieId, int tipoId, int nroSiembra,
            int mesSiembra, int surcos, int distancias, int largo,
            int tipoProduccionId, int eleccionCultivoId, String eleccionEspecificar,
            Long encuestaId, String nueva_especie) {
        this.id = id;
        this.especieId = especieId;
        this.tipoId = tipoId;
        this.nroSiembra = nroSiembra;
        this.mesSiembra = mesSiembra;
        this.surcos = surcos;
        this.distancias = distancias;
        this.largo = largo;
        this.tipoProduccionId = tipoProduccionId;
        this.eleccionCultivoId = eleccionCultivoId;
        this.eleccionEspecificar = eleccionEspecificar;
        this.encuestaId = encuestaId;
        this.nueva_especie = nueva_especie;
    }
    @Generated(hash = 1860368354)
    public Cultivo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getEspecieId() {
        return this.especieId;
    }
    public void setEspecieId(int especieId) {
        this.especieId = especieId;
    }
    public int getTipoId() {
        return this.tipoId;
    }
    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }
    public int getNroSiembra() {
        return this.nroSiembra;
    }
    public void setNroSiembra(int nroSiembra) {
        this.nroSiembra = nroSiembra;
    }
    public int getMesSiembra() {
        return this.mesSiembra;
    }
    public void setMesSiembra(int mesSiembra) {
        this.mesSiembra = mesSiembra;
    }
    public int getSurcos() {
        return this.surcos;
    }
    public void setSurcos(int surcos) {
        this.surcos = surcos;
    }
    public int getDistancias() {
        return this.distancias;
    }
    public void setDistancias(int distancias) {
        this.distancias = distancias;
    }
    public int getLargo() {
        return this.largo;
    }
    public void setLargo(int largo) {
        this.largo = largo;
    }
    public int getTipoProduccionId() {
        return this.tipoProduccionId;
    }
    public void setTipoProduccionId(int tipoProduccionId) {
        this.tipoProduccionId = tipoProduccionId;
    }
    public int getEleccionCultivoId() {
        return this.eleccionCultivoId;
    }
    public void setEleccionCultivoId(int eleccionCultivoId) {
        this.eleccionCultivoId = eleccionCultivoId;
    }
    public String getEleccionEspecificar() {
        return this.eleccionEspecificar;
    }
    public void setEleccionEspecificar(String eleccionEspecificar) {
        this.eleccionEspecificar = eleccionEspecificar;
    }
    public Long getEncuestaId() {
        return this.encuestaId;
    }
    public void setEncuestaId(Long encuestaId) {
        this.encuestaId = encuestaId;
    }
    public String getNueva_especie() {
        return this.nueva_especie;
    }
    public void setNueva_especie(String nueva_especie) {
        this.nueva_especie = nueva_especie;
    }
    
}
