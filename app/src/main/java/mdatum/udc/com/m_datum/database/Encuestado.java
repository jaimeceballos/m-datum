package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by jaime on 21/06/17.
 */

@Entity
public class Encuestado {

    @SerializedName("id")
    @org.greenrobot.greendao.annotation.Id (autoincrement = true)
    private Long id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("apellido")
    private String apellido;
    @SerializedName("edad")
    private int edad;
    @SerializedName("nacionalidad")
    private int nacionalidadId;
    @SerializedName("nivelInstruccion")
    private int nivelInstruccionId;
    @SerializedName("nivelCOmpleto")
    private Boolean nivelCompleto;
    @SerializedName("viveEstablecimiento")
    private Boolean viveEstablecimiento;

    @SerializedName("transaccion")
    private String transaccion;

    @Generated(hash = 2121983181)
    public Encuestado(Long id, String nombre, String apellido, int edad,
            int nacionalidadId, int nivelInstruccionId, Boolean nivelCompleto,
            Boolean viveEstablecimiento, String transaccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.nacionalidadId = nacionalidadId;
        this.nivelInstruccionId = nivelInstruccionId;
        this.nivelCompleto = nivelCompleto;
        this.viveEstablecimiento = viveEstablecimiento;
        this.transaccion = transaccion;
    }

    @Generated(hash = 401734286)
    public Encuestado() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNacionalidadId() {
        return this.nacionalidadId;
    }

    public void setNacionalidadId(int nacionalidadId) {
        this.nacionalidadId = nacionalidadId;
    }

    public int getNivelInstruccionId() {
        return this.nivelInstruccionId;
    }

    public void setNivelInstruccionId(int nivelInstruccionId) {
        this.nivelInstruccionId = nivelInstruccionId;
    }

    public Boolean getNivelCompleto() {
        return this.nivelCompleto;
    }

    public void setNivelCompleto(Boolean nivelCompleto) {
        this.nivelCompleto = nivelCompleto;
    }

    public Boolean getViveEstablecimiento() {
        return this.viveEstablecimiento;
    }

    public void setViveEstablecimiento(Boolean viveEstablecimiento) {
        this.viveEstablecimiento = viveEstablecimiento;
    }

    public String getTransaccion() {
        return this.transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }



    

}
