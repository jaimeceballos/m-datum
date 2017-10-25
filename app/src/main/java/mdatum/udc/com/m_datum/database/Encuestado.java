package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import org.greenrobot.greendao.annotation.Generated;
/**
 * Created by jaime on 21/06/17.
 */

@Entity
public class Encuestado {

    @org.greenrobot.greendao.annotation.Id (autoincrement = true)
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private int nacionalidadId;
    private int nivelInstruccionId;
    private Boolean nivelCompleto;
    private Boolean viveEstablecimiento;

    @Generated(hash = 1327549907)
    public Encuestado(Long id, String nombre, String apellido, int edad,
            int nacionalidadId, int nivelInstruccionId, Boolean nivelCompleto,
            Boolean viveEstablecimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.nacionalidadId = nacionalidadId;
        this.nivelInstruccionId = nivelInstruccionId;
        this.nivelCompleto = nivelCompleto;
        this.viveEstablecimiento = viveEstablecimiento;
    }

    @Generated(hash = 401734286)
    public Encuestado() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNacionalidadId() {
        return nacionalidadId;
    }

    public void setNacionalidadId(int nacionalidadId) {
        this.nacionalidadId = nacionalidadId;
    }

    public int getNivelInstruccionId() {
        return nivelInstruccionId;
    }

    public void setNivelInstruccionId(int nivelInstruccionId) {
        this.nivelInstruccionId = nivelInstruccionId;
    }

    public Boolean getNivelCompleto() {
        return nivelCompleto;
    }

    public void setNivelCompleto(Boolean nivelCompleto) {
        this.nivelCompleto = nivelCompleto;
    }

    public Boolean getViveEstablecimiento() {
        return viveEstablecimiento;
    }

    public void setViveEstablecimiento(Boolean viveEstablecimiento) {
        this.viveEstablecimiento = viveEstablecimiento;
    }


}
