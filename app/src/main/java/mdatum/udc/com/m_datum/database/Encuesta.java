package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jaime on 27/06/17.
 */

public class Encuesta implements Serializable {
    private int id;
    private int establecimientoId;
    private int encuestadoId;
    private int familiaId;
    private ArrayList<Integer> invernaderoId;
    private ArrayList<Integer> cultivoId;

    public ArrayList<Integer> getCultivoId() {
        return cultivoId;
    }

    public void setCultivoId(int cultivoId) {
        if(this.cultivoId == null)
            this.cultivoId = new ArrayList<Integer>();

        this.cultivoId.add(cultivoId);
    }



    public ArrayList<Integer> getInvernaderoId() {
        return invernaderoId;
    }

    public void setInvernaderoId(int invernaderoId) {
        if(this.invernaderoId==null)
            this.invernaderoId = new ArrayList<Integer>();
        this.invernaderoId.add(invernaderoId);
    }





    public int getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(int familiaId) {
        this.familiaId = familiaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public int getEncuestadoId() {
        return encuestadoId;
    }

    public void setEncuestadoId(int encuestadoId) {
        this.encuestadoId = encuestadoId;
    }


    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(EncuestaContract.EncuestaEntry.ESTABLECIMIENTO_ID,"establecimientoId");
        values.put(EncuestaContract.EncuestaEntry.ENCUESTADO_ID,"encuestadoId");
        values.put(EncuestaContract.EncuestaEntry.FAMILIA_ID,"familiaId");
        return values;
    }
}
