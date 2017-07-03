package mdatum.udc.com.m_datum.data;

import android.content.ContentValues;

/**
 * Created by jaime on 02/07/17.
 */

public class EncuestaInvernaculo {
    private int id;
    private int encuestaId;
    private int invernaculoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEncuestaId() {
        return encuestaId;
    }

    public void setEncuestaId(int encuestaId) {
        this.encuestaId = encuestaId;
    }

    public int getInvernaculoId() {
        return invernaculoId;
    }

    public void setInvernaculoId(int invernaculoId) {
        this.invernaculoId = invernaculoId;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(EncuestaInvernaculoContract.EncuestaProduccionBajoCubiertaEntry.ENCUESTA_ID,"encuestaId");
        values.put(EncuestaInvernaculoContract.EncuestaProduccionBajoCubiertaEntry.INVERNACULO_ID,"invernaculoId");
        return values;
    }
}
