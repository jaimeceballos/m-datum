package mdatum.udc.com.m_datum.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 26/10/17.
 */

@Entity
public class Agroquimicos {

    @Id(autoincrement = true)
    private Long id;
    private boolean usa;
    private String razon_no;
    private int tiempo_usa;
    private Long factor_climatico;
    private Long triple_lavado;
    private Long asesoramiento;
    private String asesoramiento_otro;
    @Generated(hash = 303800307)
    public Agroquimicos(Long id, boolean usa, String razon_no, int tiempo_usa,
            Long factor_climatico, Long triple_lavado, Long asesoramiento,
            String asesoramiento_otro) {
        this.id = id;
        this.usa = usa;
        this.razon_no = razon_no;
        this.tiempo_usa = tiempo_usa;
        this.factor_climatico = factor_climatico;
        this.triple_lavado = triple_lavado;
        this.asesoramiento = asesoramiento;
        this.asesoramiento_otro = asesoramiento_otro;
    }
    @Generated(hash = 1769417060)
    public Agroquimicos() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getUsa() {
        return this.usa;
    }
    public void setUsa(boolean usa) {
        this.usa = usa;
    }
    public String getRazon_no() {
        return this.razon_no;
    }
    public void setRazon_no(String razon_no) {
        this.razon_no = razon_no;
    }
    public int getTiempo_usa() {
        return this.tiempo_usa;
    }
    public void setTiempo_usa(int tiempo_usa) {
        this.tiempo_usa = tiempo_usa;
    }
    public Long getFactor_climatico() {
        return this.factor_climatico;
    }
    public void setFactor_climatico(Long factor_climatico) {
        this.factor_climatico = factor_climatico;
    }
    public Long getTriple_lavado() {
        return this.triple_lavado;
    }
    public void setTriple_lavado(Long triple_lavado) {
        this.triple_lavado = triple_lavado;
    }
    public Long getAsesoramiento() {
        return this.asesoramiento;
    }
    public void setAsesoramiento(Long asesoramiento) {
        this.asesoramiento = asesoramiento;
    }
    public String getAsesoramiento_otro() {
        return this.asesoramiento_otro;
    }
    public void setAsesoramiento_otro(String asesoramiento_otro) {
        this.asesoramiento_otro = asesoramiento_otro;
    }
}
