package mdatum.udc.com.m_datum.database;

import android.provider.BaseColumns;

/**
 * Created by jaime on 21/06/17.
 */

public class EncuestadoContract {

    public static abstract class EncuestadoEntry implements BaseColumns{
        public static final String  TABLE_NAME = "encuestado";

        public static final String NOMBRE               = "nombre";
        public static final String APELLIDO             = "apellido";
        public static final String EDAD                 = "edad";
        public static final String NACIONALIDAD         = "nacionalidad";
        public static final String NIVEL_INSTRUCCION    = "nivelInstruccion";
        public static final String NIVEL_COMPLETO       = "nivelCompleto";
        public static final String VIVE_ESTABLECIMIENTO = "viveEstablecimiento";
    }
}
