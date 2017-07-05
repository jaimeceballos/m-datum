package mdatum.udc.com.m_datum.data;

import android.provider.BaseColumns;

/**
 * Created by jaime on 05/07/17.
 */

public class EncuestaContract {

    public static abstract  class EncuestaEntry implements BaseColumns{
        public static final String TABLE_NAME = "encuesta";

        public static final String ESTABLECIMIENTO_ID = "establecimientoId";
        public static final String ENCUESTADO_ID = "encuestadoId";
        public static final String FAMILIA_ID = "familiaID";
    }
}
