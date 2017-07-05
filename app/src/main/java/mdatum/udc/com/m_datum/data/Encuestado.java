package mdatum.udc.com.m_datum.data;

import android.content.ContentValues;

import static mdatum.udc.com.m_datum.data.EncuestadoContract.EncuestadoEntry;
/**
 * Created by jaime on 21/06/17.
 */

public class Encuestado {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private int nacionalidadId;
    private int nivelInstruccionId;
    private Boolean nivelCompleto;
    private Boolean viveEstablecimiento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();

        values.put(EncuestadoEntry.NOMBRE, nombre);
        values.put(EncuestadoEntry.APELLIDO,apellido);
        values.put(EncuestadoEntry.EDAD,edad);
        values.put(EncuestadoEntry.NACIONALIDAD, nacionalidadId);
        values.put(EncuestadoEntry.NIVEL_INSTRUCCION, nivelInstruccionId);
        values.put(EncuestadoEntry.NIVEL_COMPLETO,nivelCompleto);
        values.put(EncuestadoEntry.VIVE_ESTABLECIMIENTO,viveEstablecimiento);
        return values;

    }
}
