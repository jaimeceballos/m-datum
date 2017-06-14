package mdatum.udc.com.m_datum.data;

import android.provider.BaseColumns;

/**
 * Created by jaime on 12/06/17.
 */

public class EstablecimientoContract {

    public static abstract class EstablecimientoEntry implements BaseColumns{
        public static final String TABLE_NAME = "establecimiento";

        public static final String NOMBRE           = "nombre";
        public static final String NRO              = "nro";
        public static final String POS_LATITUD      = "posLatitud";
        public static final String POS_LONGITUD     = "posLongitud";
        public static final String FOTO             = "foto";
        public static final String REGIMEN_TENENCIA = "regimenTenencia";
        public static final String REGIMEN_OTROS    = "regimenOtros";
    }

}
