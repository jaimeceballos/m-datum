package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

/**
 * Created by jaime on 29/06/17.
 */

public class Cultivo {

    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();

        values.put(CultivoContract.CultivoEntry.ESPECIE_ID,especieId);
        values.put(CultivoContract.CultivoEntry.TIPO_ID,tipoId);
        values.put(CultivoContract.CultivoEntry.NRO_SIEMBRA,nroSiembra);
        values.put(CultivoContract.CultivoEntry.MES_SIEMBRA,mesSiembra);
        values.put(CultivoContract.CultivoEntry.SURCOS,surcos);
        values.put(CultivoContract.CultivoEntry.DISTANCIAS,distancias);
        values.put(CultivoContract.CultivoEntry.LARGO,largo);
        values.put(CultivoContract.CultivoEntry.SUPERFICIE_SEMBRADA,superficieSembrada);
        values.put(CultivoContract.CultivoEntry.SUPERFICIE_MEDIDA_ID,superficieMedidaId);
        values.put(CultivoContract.CultivoEntry.TIPO_PRODUCCION_ID,tipoProduccionId);
        values.put(CultivoContract.CultivoEntry.ELECCION_CULTIVO_ID,eleccionCultivoId);
        values.put(CultivoContract.CultivoEntry.ELECCION_ESPECIFICAR,eleccionEspecificar);
        return values;
    }
}
