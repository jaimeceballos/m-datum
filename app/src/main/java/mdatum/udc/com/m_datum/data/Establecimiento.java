package mdatum.udc.com.m_datum.data;

import android.content.ContentValues;

/**
 * Created by jaime on 12/06/17.
 */

public class Establecimiento {

    private int id;
    private String nombre;
    private String nro;
    private String posLatitud;
    private String posLongitud;
    private String foto;
    private int regimenTenencia;
    private String regimenOtros;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getRegimenTenencia() {
        return regimenTenencia;
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

    public void setRegimenTenencia(int regimenTenencia) {
        this.regimenTenencia = regimenTenencia;
    }

    public void setRegimenOtros(String regimenOtros) {
        this.regimenOtros = regimenOtros;
    }


    public ContentValues toContentValues() {

        ContentValues values = new ContentValues();
        values.put(EstablecimientoContract.EstablecimientoEntry.NOMBRE, nombre);
        values.put(EstablecimientoContract.EstablecimientoEntry.NRO,nro);
        values.put(EstablecimientoContract.EstablecimientoEntry.POS_LATITUD,posLatitud);
        values.put(EstablecimientoContract.EstablecimientoEntry.POS_LONGITUD,posLongitud);
        values.put(EstablecimientoContract.EstablecimientoEntry.FOTO,foto);
        values.put(EstablecimientoContract.EstablecimientoEntry.REGIMEN_TENENCIA,regimenTenencia);
        values.put(EstablecimientoContract.EstablecimientoEntry.REGIMEN_OTROS,regimenOtros);
        return values;
    }
}