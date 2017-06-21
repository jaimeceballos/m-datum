package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import mdatum.udc.com.m_datum.R;

public class EncuestadoActivity extends AppCompatActivity {

    private Spinner spNivInstruccion;
    private Button btnEncuestadoSiguiente;
    private RadioButton rbHabita;
    private RadioButton rbNoHabita;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuestado);

        rbHabita = (RadioButton) findViewById(R.id.rb_habita);
        rbNoHabita = (RadioButton) findViewById(R.id.rb_no_habita);

        spNivInstruccion = (Spinner) findViewById(R.id.sp_niv_instruccion);
        final String []opciones = new String[]{"Primario", "Secundario", "Terciario", "Universitario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNivInstruccion.setAdapter(adapter);

        btnEncuestadoSiguiente = (Button) findViewById(R.id.btn_encuestado_siguiente);

        btnEncuestadoSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbHabita.isChecked() ) {
                    Intent familia = new Intent(view.getContext(), FamiliaActivity.class);
                    startActivity(familia);
                }else
                if(rbNoHabita.isChecked()){
                    Intent produccion = new Intent(view.getContext(), ProduccionActivity.class);
                    startActivity(produccion);
                }
            }
        });

    }
}
