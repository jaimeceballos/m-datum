package mdatum.udc.com.m_datum.data;

import android.content.ContentValues;

/**
 * Created by jaime on 29/06/17.
 */

public class Familia {

    private int id;
    private int esCasado;
    private int tieneHijos;
    private int cantVarones;
    private int cantMujeres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEsCasado() {
        return esCasado;
    }

    public void setEsCasado(int esCasado) {
        this.esCasado = esCasado;
    }

    public int getTieneHijos() {
        return tieneHijos;
    }

    public void setTieneHijos(int tieneHijos) {
        this.tieneHijos = tieneHijos;
    }

    public int getCantVarones() {
        return cantVarones;
    }

    public void setCantVarones(int cantVarones) {
        this.cantVarones = cantVarones;
    }

    public int getCantMujeres() {
        return cantMujeres;
    }

    public void setCantMujeres(int cantMujeres) {
        this.cantMujeres = cantMujeres;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(FamiliaContract.FamiliaEntry.ES_CASADO,esCasado);
        values.put(FamiliaContract.FamiliaEntry.TIENE_HIJOS,tieneHijos);
        values.put(FamiliaContract.FamiliaEntry.CANTIDAD_VARONES,cantVarones);
        values.put(FamiliaContract.FamiliaEntry.CANTIDAD_MUJERES,cantMujeres);
        return values;
    }
}
