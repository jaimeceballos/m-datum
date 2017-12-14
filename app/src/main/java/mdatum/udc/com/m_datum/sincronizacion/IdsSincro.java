package mdatum.udc.com.m_datum.sincronizacion;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaime on 12/12/17.
 */

public class IdsSincro {

    @SerializedName("establecimiento")
    private long establecimiento;

    @SerializedName("encuestado")
    private long encuestado;

    @SerializedName("familia")
    private long familia;

    @SerializedName("agroquimico")
    private long agroquimico;


    public long getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(long establecimiento) {
        this.establecimiento = establecimiento;
    }

    public long getEncuestado() {
        return encuestado;
    }

    public void setEncuestado(long encuestado) {
        this.encuestado = encuestado;
    }

    public long getFamilia() {
        return familia;
    }

    public void setFamilia(long familia) {
        this.familia = familia;
    }

    public long getAgroquimico() {
        return agroquimico;
    }

    public void setAgroquimico(long agroquimico) {
        this.agroquimico = agroquimico;
    }
}
