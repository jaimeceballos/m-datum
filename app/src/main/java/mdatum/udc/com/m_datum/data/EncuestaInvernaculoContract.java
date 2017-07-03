package mdatum.udc.com.m_datum.data;

import android.provider.BaseColumns;

/**
 * Created by jaime on 02/07/17.
 */

public class EncuestaInvernaculoContract
{
    public static abstract class  EncuestaProduccionBajoCubiertaEntry implements BaseColumns{
        public static final String TABLE_NAME = "encuestaInvernaculo";

        public static final String ENCUESTA_ID = "encuestaId";
        public static final String INVERNACULO_ID = "invernaculoId";
    }
}
