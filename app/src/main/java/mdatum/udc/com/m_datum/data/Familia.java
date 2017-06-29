package mdatum.udc.com.m_datum.data;

import android.content.ContentValues;

/**
 * Created by jaime on 29/06/17.
 */

public class Familia {

    private int id;
    private Boolean esCasado;
    private Boolean tieneHijos;
    private int cantVarones;
    private int cantMujeres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEsCasado() {
        return esCasado;
    }

    public void setEsCasado(Boolean esCasado) {
        this.esCasado = esCasado;
    }

    public Boolean getTieneHijos() {
        return tieneHijos;
    }

    public void setTieneHijos(Boolean tieneHijos) {
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

        return values;
    }
}
