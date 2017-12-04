package mdatum.udc.com.m_datum.sincronizacion;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaime on 30/11/17.
 */

public class EstablecimientoBody {

    @SerializedName("id")
    private Long id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("numero")
    private String numero;
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

    public EstablecimientoBody(Long id, String nombre, String numero,
                               String posLatitud, String posLongitud, String foto,
                               int regimenTenenciaId, String regimenOtros) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.posLatitud = posLatitud;
        this.posLongitud = posLongitud;
        this.foto = foto;
        this.regimenTenenciaId = regimenTenenciaId;
        this.regimenOtros = regimenOtros;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPosLatitud() {
        return posLatitud;
    }

    public void setPosLatitud(String posLatitud) {
        this.posLatitud = posLatitud;
    }

    public String getPosLongitud() {
        return posLongitud;
    }

    public void setPosLongitud(String posLongitud) {
        this.posLongitud = posLongitud;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getRegimenTenenciaId() {
        return regimenTenenciaId;
    }

    public void setRegimenTenenciaId(int regimenTenenciaId) {
        this.regimenTenenciaId = regimenTenenciaId;
    }

    public String getRegimenOtros() {
        return regimenOtros;
    }

    public void setRegimenOtros(String regimenOtros) {
        this.regimenOtros = regimenOtros;
    }
}
