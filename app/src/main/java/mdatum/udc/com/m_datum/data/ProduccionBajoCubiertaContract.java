package mdatum.udc.com.m_datum.data;

import android.provider.BaseColumns;

/**
 * Created by jaime on 01/07/17.
 */

public class ProduccionBajoCubiertaContract {

    public static abstract class ProduccionBajoCubiertaEntry implements BaseColumns{
        public static final String TABLE_NAME = "produccionBajoCubierta";

        public static final String CANTIDAD_MODULOS = "cantidadModulos";
        public static final String SUPERFICIE_UNITARIA  = "superficieUnitaria";
        public static final String MATERIAL_ESTRUCTURA_ID = "materialEstructuraId";
        public static final String ANIO_CONSTRUCCION_ID = "anioConstruccionId";

    }
}
