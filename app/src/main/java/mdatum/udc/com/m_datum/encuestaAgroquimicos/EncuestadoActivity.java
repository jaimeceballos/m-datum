package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import mdatum.udc.com.m_datum.R;

public class EncuestadoActivity extends AppCompatActivity {

    private Spinner spNivInstruccion;
    private Button btnAtras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuestado);

        spNivInstruccion = (Spinner) findViewById(R.id.sp_niv_instruccion);
        final String []opciones = new String[]{"Primario", "Secundario", "Terciario", "Universitario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNivInstruccion.setAdapter(adapter);




    }
}
