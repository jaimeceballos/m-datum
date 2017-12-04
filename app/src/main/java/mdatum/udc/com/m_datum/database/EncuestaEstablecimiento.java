package mdatum.udc.com.m_datum.database;

import java.util.Date;

/**
 * Created by jaime on 01/12/17.
 */

public class EncuestaEstablecimiento {
    private Long id_encuesta;
    private Date fecha;
    private String establecimiento;

    public EncuestaEstablecimiento(Long id_encuesta, Date fecha, String establecimiento) {
        this.id_encuesta = id_encuesta;
        this.fecha = fecha;
        this.establecimiento = establecimiento;
    }


    public Long getId_encuesta() {
        return id_encuesta;
    }

    public void setId_encuesta(Long id_encuesta) {
        this.id_encuesta = id_encuesta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }
}
