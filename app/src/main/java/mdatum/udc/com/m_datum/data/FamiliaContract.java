package mdatum.udc.com.m_datum.data;

import android.provider.BaseColumns;

/**
 * Created by jaime on 29/06/17.
 */

public class FamiliaContract {

    public static abstract class FamiliaEntry implements BaseColumns{
        public static final String TABLE_NAME = "familia";

        public static final String ES_CASADO        = "esCasado";
        public static final String TIENE_HIJOS      = "tieneHijos";
        public static final String CANTIDAD_VARONES = "cantidadVarones";
        public static final String CANTIDAD_MUJERES = "cantidadMujeres";
    }

}
