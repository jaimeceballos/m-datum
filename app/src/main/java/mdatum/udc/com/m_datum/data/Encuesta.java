package mdatum.udc.com.m_datum.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaime on 27/06/17.
 */

public class Encuesta implements Serializable {
    private int id;
    private int establecimientoId;
    private int encuestadoId;
    private int familiaId;

    public ArrayList<Integer> getInvernaderoId() {
        return invernaderoId;
    }

    public void setInvernaderoId(int invernaderoId) {
        this.invernaderoId.add(invernaderoId);
    }

    private ArrayList<Integer> invernaderoId;



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
}
