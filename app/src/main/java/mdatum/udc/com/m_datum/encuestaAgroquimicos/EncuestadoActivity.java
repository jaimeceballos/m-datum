package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.data.Encuesta;
import mdatum.udc.com.m_datum.data.Encuestado;
import mdatum.udc.com.m_datum.data.MDatumDbHelper;

public class EncuestadoActivity extends AppCompatActivity {

    private Encuesta encuesta;
    private Encuestado encuestado;
    private Spinner spNivInstruccion;
    private Button btnEncuestadoSiguiente;
    private RadioButton rbHabita;
    private RadioButton rbNoHabita;
    private EditText etApellido;
    private EditText etNombre;
    private EditText etEdad;
    private Spinner spNacionalidad;
    private RadioButton rbCompleto;
    private RadioButton rbIncompleto;
    private MDatumDbHelper mDatumDbHelper;
    private Boolean isHabitaChecked = false;
    private ArrayList<String> opcionesInstruccion;
    private ArrayList<String> opcionesNacionalidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuestado);
        encuesta = (Encuesta) getIntent().getExtras().getSerializable("encuesta");
        rbHabita = (RadioButton) findViewById(R.id.rb_habita);
        rbNoHabita = (RadioButton) findViewById(R.id.rb_no_habita);
        etApellido = (EditText) findViewById(R.id.et_apellido_encuestado);
        etNombre = (EditText) findViewById(R.id.et_nombre_encuestado);
        etEdad = (EditText) findViewById(R.id.et_edad_encuestado);
        spNacionalidad = (Spinner) findViewById(R.id.sp_nacionalidad);
        rbCompleto = (RadioButton) findViewById(R.id.rb_completo);
        rbIncompleto = (RadioButton) findViewById(R.id.rb_incompleto);
        spNivInstruccion = (Spinner) findViewById(R.id.sp_niv_instruccion);
        mDatumDbHelper = new MDatumDbHelper(this);
        opcionesInstruccion = mDatumDbHelper.getAllNivelInstruccion();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesInstruccion);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNivInstruccion.setAdapter(adapter);

        opcionesNacionalidad = mDatumDbHelper.getAllNacionalidad();
        ArrayAdapter<String> adapterNacionalidad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesNacionalidad);
        adapterNacionalidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNacionalidad.setAdapter(adapterNacionalidad);



        mDatumDbHelper = new MDatumDbHelper(this);
        btnEncuestadoSiguiente = (Button) findViewById(R.id.btn_encuestado_siguiente);

        btnEncuestadoSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encuestado = new Encuestado();
                encuestado.setNombre(etNombre.getText().toString());
                encuestado.setApellido(etApellido.getText().toString());
                encuestado.setEdad(Integer.parseInt(etEdad.getText().toString()));
                encuestado.setNacionalidad(Integer.parseInt(opcionesNacionalidad.get((int)spNacionalidad.getSelectedItemId())));
                encuestado.setNivelInstruccion(Integer.parseInt(opcionesInstruccion.get((int)spNivInstruccion.getSelectedItemId())));
                if (rbHabita.isChecked()) {
                    encuestado.setViveEstablecimiento(true);
                    isHabitaChecked = true;
                } else if (rbNoHabita.isChecked()) {
                    encuestado.setViveEstablecimiento(false);
                    isHabitaChecked = true;
                }
                if (rbCompleto.isChecked()) {
                    encuestado.setNivelCompleto(true);
                } else if (rbIncompleto.isChecked()) {
                    encuestado.setNivelCompleto(false);
                }
                if (isHabitaChecked) {
                    Toast savingToast = Toast.makeText(getApplicationContext(), "Guardando los datos.", Toast.LENGTH_SHORT);

                    savingToast.show();
                    new AddEncuestadoTask().execute(encuestado);
                } else {
                    Toast errorToast = Toast.makeText(getApplicationContext(), "Debe indicar si vive en el establecimiento o no.", Toast.LENGTH_SHORT);

                    errorToast.show();
                }

            }
        });

    }

    private class AddEncuestadoTask extends AsyncTask<Encuestado, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Encuestado... encuestado) {
            long result = mDatumDbHelper.saveEncuestado(encuestado[0]);
            encuestado[0].setId((int) result);
            encuesta.setEncuestadoId((int) result);
            return result > 0;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            Intent intent;
            if (encuestado.getViveEstablecimiento()) {
                intent = new Intent(getApplicationContext(), FamiliaActivity.class);

            } else {
                intent = new Intent(getApplicationContext(), ProduccionActivity.class);

            }
            intent.putExtra("encuesta", encuesta);
            startActivity(intent);
        }

    }
}
