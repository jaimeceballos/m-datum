package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 12/06/17.
 */

@Entity
public class Establecimiento {

    @SerializedName("id")
    @org.greenrobot.greendao.annotation.Id (autoincrement = true)
    private Long id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("numero")
    private String nro;
    @SerializedName("posLatitud")
    private String posLatitud;
    @SerializedName("posLongitud")
    private String posLongitud;
    @SerializedName("foto")
    private String foto;
    @SerializedName("regimenTenencia")
    private int regimenTenenciaId;
    @SerializedName("regimenOtros")
    private String regimenOtros;

    @Generated(hash = 541625268)
    public Establecimiento(Long id, String nombre, String nro, String posLatitud, String posLongitud, String foto, int regimenTenenciaId, String regimenOtros) {
        this.id = id;
        this.nombre = nombre;
        this.nro = nro;
        this.posLatitud = posLatitud;
        this.posLongitud = posLongitud;
        this.foto = foto;
        this.regimenTenenciaId = regimenTenenciaId;
        this.regimenOtros = regimenOtros;
    }

    @Generated(hash = 1125154551)
    public Establecimiento() {
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

    public String getNro() {
        return nro;
    }

    public String getPosLatitud() {
        return posLatitud;
    }

    public String getPosLongitud() {
        return posLongitud;
    }

    public String getFoto() {
        return foto;
    }

    public int getRegimenTenenciaId() {
        return regimenTenenciaId;
    }

    public String getRegimenOtros() {
        return regimenOtros;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public void setPosLatitud(String posLatitud) {
        this.posLatitud = posLatitud;
    }

    public void setPosLongitud(String posLongitud) {
        this.posLongitud = posLongitud;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setRegimenTenenciaId(int regimenTenenciaId) {
        this.regimenTenenciaId = regimenTenenciaId;
    }

    public void setRegimenOtros(String regimenOtros) {
        this.regimenOtros = regimenOtros;
    }


    

    public Boolean validar(){
        if(!this.getNombre().isEmpty() && !this.getNro().isEmpty() && this.getRegimenTenenciaId() != 6 || (this.getRegimenTenenciaId() == 6 && !this.getRegimenOtros().isEmpty())){
            return true;
        }
        return false;
    }
}
