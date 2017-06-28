package mdatum.udc.com.m_datum.data;

import android.content.ContentValues;

/**
 * Created by jaime on 28/06/17.
 */

public class RegimenTenencia {
    private int id;
    private String descpripcion;

    public RegimenTenencia(String descpripcion) {
        this.descpripcion = descpripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescpripcion() {
        return descpripcion;
    }

    public void setDescpripcion(String descpripcion) {
        this.descpripcion = descpripcion;
    }

    public ContentValues toContentValues(){

        ContentValues values = new ContentValues();

        values.put(RegimenTenenciaContract.RegimenTenenciaEntry.DESCRIPCION,descpripcion);
        return values;
    }
}
