package mdatum.udc.com.m_datum.database;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 04/12/17.
 */


@Entity
public class UpdatesToServer {

    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("entidad")
    private String entidad;
    @SerializedName("valor")
    private String valor;
    @SerializedName("usuario")
    private int usuario;
    private boolean sincronized;
    @Generated(hash = 1325983177)
    public UpdatesToServer(Long id, String entidad, String valor, int usuario,
            boolean sincronized) {
        this.id = id;
        this.entidad = entidad;
        this.valor = valor;
        this.usuario = usuario;
        this.sincronized = sincronized;
    }
    @Generated(hash = 1051109421)
    public UpdatesToServer() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEntidad() {
        return this.entidad;
    }
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }
    public String getValor() {
        return this.valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }
    public int getUsuario() {
        return this.usuario;
    }
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    public boolean getSincronized() {
        return this.sincronized;
    }
    public void setSincronized(boolean sincronized) {
        this.sincronized = sincronized;
    }
}
