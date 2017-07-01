package mdatum.udc.com.m_datum.data;

import android.content.ContentValues;

/**
 * Created by jaime on 01/07/17.
 */

public class ProduccionBajoCubierta {
    private int id;
    private int cantidadModulos;
    private int superficieUnitaria;
    private int materialEstructuraId;
    private int anioConstruccionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidadModulos() {
        return cantidadModulos;
    }

    public void setCantidadModulos(int cantidadModulos) {
        this.cantidadModulos = cantidadModulos;
    }

    public int getSuperficieUnitaria() {
        return superficieUnitaria;
    }

    public void setSuperficieUnitaria(int superficieUnitaria) {
        this.superficieUnitaria = superficieUnitaria;
    }

    public int getMaterialEstructuraId() {
        return materialEstructuraId;
    }

    public void setMaterialEstructuraId(int materialEstructuraId) {
        this.materialEstructuraId = materialEstructuraId;
    }

    public int getAnioConstruccionId() {
        return anioConstruccionId;
    }

    public void setAnioConstruccionId(int anioConstruccionId) {
        this.anioConstruccionId = anioConstruccionId;
    }

   public ContentValues toContentValues(){
       ContentValues values = new ContentValues();
       values.put(ProduccionBajoCubiertaContract.ProduccionBajoCubiertaEntry.CANTIDAD_MODULOS,cantidadModulos);
       values.put(ProduccionBajoCubiertaContract.ProduccionBajoCubiertaEntry.SUPERFICIE_UNITARIA,superficieUnitaria);
       values.put(ProduccionBajoCubiertaContract.ProduccionBajoCubiertaEntry.MATERIAL_ESTRUCTURA_ID,materialEstructuraId);
       values.put(ProduccionBajoCubiertaContract.ProduccionBajoCubiertaEntry.ANIO_CONSTRUCCION_ID,anioConstruccionId);
       return values;
   }

}
