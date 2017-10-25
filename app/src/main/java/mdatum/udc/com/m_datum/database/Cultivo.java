package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 29/06/17.
 */
@Entity
public class Cultivo {

    @Id(autoincrement = true)
    private Long id;
    private int especieId;
    private int tipoId;
    private int nroSiembra;
    private int mesSiembra;
    private int surcos;
    private int distancias;
    private int largo;
    private int superficieSembrada;
    private int superficieMedidaId;
    private int tipoProduccionId;
    private int eleccionCultivoId;
    private String eleccionEspecificar;

    @Generated(hash = 1772569774)
    public Cultivo(Long id, int especieId, int tipoId, int nroSiembra, int mesSiembra,
            int surcos, int distancias, int largo, int superficieSembrada,
            int superficieMedidaId, int tipoProduccionId, int eleccionCultivoId,
            String eleccionEspecificar) {
        this.id = id;
        this.especieId = especieId;
        this.tipoId = tipoId;
        this.nroSiembra = nroSiembra;
        this.mesSiembra = mesSiembra;
        this.surcos = surcos;
        this.distancias = distancias;
        this.largo = largo;
        this.superficieSembrada = superficieSembrada;
        this.superficieMedidaId = superficieMedidaId;
        this.tipoProduccionId = tipoProduccionId;
        this.eleccionCultivoId = eleccionCultivoId;
        this.eleccionEspecificar = eleccionEspecificar;
    }

    @Generated(hash = 1860368354)
    public Cultivo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEspecieId() {
        return especieId;
    }

    public void setEspecieId(int especieId) {
        this.especieId = especieId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public int getNroSiembra() {
        return nroSiembra;
    }

    public void setNroSiembra(int nroSiembra) {
        this.nroSiembra = nroSiembra;
    }

    public int getMesSiembra() {
        return mesSiembra;
    }

    public void setMesSiembra(int mesSiembra) {
        this.mesSiembra = mesSiembra;
    }

    public int getSurcos() {
        return surcos;
    }

    public void setSurcos(int surcos) {
        this.surcos = surcos;
    }

    public int getDistancias() {
        return distancias;
    }

    public void setDistancias(int distancias) {
        this.distancias = distancias;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getSuperficieSembrada() {
        return superficieSembrada;
    }

    public void setSuperficieSembrada(int superficieSembrada) {
        this.superficieSembrada = superficieSembrada;
    }

    public int getSuperficieMedidaId() {
        return superficieMedidaId;
    }

    public void setSuperficieMedidaId(int superficieMedidaId) {
        this.superficieMedidaId = superficieMedidaId;
    }

    public int getTipoProduccionId() {
        return tipoProduccionId;
    }

    public void setTipoProduccionId(int tipoProduccionId) {
        this.tipoProduccionId = tipoProduccionId;
    }

    public int getEleccionCultivoId() {
        return eleccionCultivoId;
    }

    public void setEleccionCultivoId(int eleccionCultivoId) {
        this.eleccionCultivoId = eleccionCultivoId;
    }

    public String getEleccionEspecificar() {
        return eleccionEspecificar;
    }

    public void setEleccionEspecificar(String eleccionEspecificar) {
        this.eleccionEspecificar = eleccionEspecificar;
    }


}
