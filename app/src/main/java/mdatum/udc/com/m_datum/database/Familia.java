package mdatum.udc.com.m_datum.database;

import android.content.ContentValues;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaime on 29/06/17.
 */
@Entity
public class Familia {

    @Id(autoincrement = true)
    private Long id;
    private int esCasado;
    private int tieneHijos;
    private int cantVarones;
    private int cantMujeres;

    @Generated(hash = 268209188)
    public Familia(Long id, int esCasado, int tieneHijos, int cantVarones,
            int cantMujeres) {
        this.id = id;
        this.esCasado = esCasado;
        this.tieneHijos = tieneHijos;
        this.cantVarones = cantVarones;
        this.cantMujeres = cantMujeres;
    }

    @Generated(hash = 2042212105)
    public Familia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEsCasado() {
        return esCasado;
    }

    public void setEsCasado(int esCasado) {
        this.esCasado = esCasado;
    }

    public int getTieneHijos() {
        return tieneHijos;
    }

    public void setTieneHijos(int tieneHijos) {
        this.tieneHijos = tieneHijos;
    }

    public int getCantVarones() {
        return cantVarones;
    }

    public void setCantVarones(int cantVarones) {
        this.cantVarones = cantVarones;
    }

    public int getCantMujeres() {
        return cantMujeres;
    }

    public void setCantMujeres(int cantMujeres) {
        this.cantMujeres = cantMujeres;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(FamiliaContract.FamiliaEntry.ES_CASADO,esCasado);
        values.put(FamiliaContract.FamiliaEntry.TIENE_HIJOS,tieneHijos);
        values.put(FamiliaContract.FamiliaEntry.CANTIDAD_VARONES,cantVarones);
        values.put(FamiliaContract.FamiliaEntry.CANTIDAD_MUJERES,cantMujeres);
        return values;
    }
}
