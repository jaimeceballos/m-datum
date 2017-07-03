package mdatum.udc.com.m_datum.data;

import android.content.ContentValues;

/**
 * Created by jaime on 01/07/17.
 */

public class Invernaculo {
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
       values.put(InvernaculoContract.InvernaculoEntry.CANTIDAD_MODULOS,cantidadModulos);
       values.put(InvernaculoContract.InvernaculoEntry.SUPERFICIE_UNITARIA,superficieUnitaria);
       values.put(InvernaculoContract.InvernaculoEntry.MATERIAL_ESTRUCTURA_ID,materialEstructuraId);
       values.put(InvernaculoContract.InvernaculoEntry.ANIO_CONSTRUCCION_ID,anioConstruccionId);
       return values;
   }

}
