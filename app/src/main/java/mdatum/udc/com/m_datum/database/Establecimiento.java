package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by jaime on 12/06/17.
 */

@Entity(
        indexes = {@Index(value = "nombre,nro", unique = true) }
)
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

    @SerializedName("transaccion")
    private String transaccion;

@Generated(hash = 1529512107)
public Establecimiento(Long id, String nombre, String nro, String posLatitud,
        String posLongitud, String foto, int regimenTenenciaId,
        String regimenOtros, String transaccion) {
    this.id = id;
    this.nombre = nombre;
    this.nro = nro;
    this.posLatitud = posLatitud;
    this.posLongitud = posLongitud;
    this.foto = foto;
    this.regimenTenenciaId = regimenTenenciaId;
    this.regimenOtros = regimenOtros;
    this.transaccion = transaccion;
}

@Generated(hash = 1125154551)
public Establecimiento() {
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

public String getNro() {
    return this.nro;
}

public void setNro(String nro) {
    this.nro = nro;
}

public String getPosLatitud() {
    return this.posLatitud;
}

public void setPosLatitud(String posLatitud) {
    this.posLatitud = posLatitud;
}

public String getPosLongitud() {
    return this.posLongitud;
}

public void setPosLongitud(String posLongitud) {
    this.posLongitud = posLongitud;
}

public String getFoto() {
    return this.foto;
}

public void setFoto(String foto) {
    this.foto = foto;
}

public int getRegimenTenenciaId() {
    return this.regimenTenenciaId;
}

public void setRegimenTenenciaId(int regimenTenenciaId) {
    this.regimenTenenciaId = regimenTenenciaId;
}

public String getRegimenOtros() {
    return this.regimenOtros;
}

public void setRegimenOtros(String regimenOtros) {
    this.regimenOtros = regimenOtros;
}

public String getTransaccion() {
    return this.transaccion;
}

public void setTransaccion(String transaccion) {
    this.transaccion = transaccion;
}


}
