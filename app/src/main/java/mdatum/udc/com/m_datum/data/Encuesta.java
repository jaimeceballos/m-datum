package mdatum.udc.com.m_datum.data;

import java.io.Serializable;

/**
 * Created by jaime on 27/06/17.
 */

public class Encuesta implements Serializable {
    private int id;
    private int establecimientoId;
    private int encuestadoId;

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
}
