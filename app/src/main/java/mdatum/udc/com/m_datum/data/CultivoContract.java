package mdatum.udc.com.m_datum.data;

import android.provider.BaseColumns;

/**
 * Created by jaime on 29/06/17.
 */

public class CultivoContract {

    public static abstract class CultivoEntry implements BaseColumns{

        public static final String TABLE_NAME = "cultivo";

        public static final String ESPECIE_ID           = "especieId";
        public static final String TIPO_ID              = "tipoId";
        public static final String NRO_SIEMBRA          = "nroSiembra";
        public static final String MES_SIEMBRA          = "mesSiembra";
        public static final String SURCOS               = "surcos";
        public static final String DISTANCIAS           = "distancias";
        public static final String LARGO                = "largo";
        public static final String SUPERFICIE_SEMBRADA  = "superficieSembrada";
        public static final String SUPERFICIE_MEDIDA_ID = "superficieMedidaId";
        public static final String TIPO_PRODUCCION_ID   = "tipoProduccionId";
        public static final String ELECCION_CULTIVO_ID  = "eleccionCultivoId";
        public static final String ELECCION_ESPECIFICAR = "eleccionEspecificar";
    }
}
