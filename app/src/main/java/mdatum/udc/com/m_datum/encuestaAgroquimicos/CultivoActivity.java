package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import mdatum.udc.com.m_datum.MainActivity;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.data.Cultivo;
import mdatum.udc.com.m_datum.data.Encuesta;
import mdatum.udc.com.m_datum.data.MDatumDbHelper;

public class CultivoActivity extends AppCompatActivity {

    private Encuesta encuesta;
    private Spinner spEspecie;
    private Spinner spTipoCultivo;
    private EditText etNroSiembra;
    private EditText etMesSiembra;
    private EditText etSurcos;
    private EditText etDistancias;
    private EditText etLargo;
    private EditText etSuperficieSembrada;
    private Spinner spSuperficieMedida;
    private Spinner spTipoProduccion;
    private Spinner spEleccionCultivo;
    private EditText etEleccionEspecificar;
    private MDatumDbHelper mDatumDbHelper;
    private ArrayList<String> opcionesEspecie;
    private ArrayList<String> opcionesTipoCultivo;
    private ArrayList<String> opcionesSuperficieMedida;
    private ArrayList<String> opcionesTipoProduccion;
    private ArrayList<String> opcionesEleccionCultivo;
    private Button btnGuardar;
    private Cultivo cultivo;
    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivo);

        encuesta = (Encuesta) getIntent().getExtras().getSerializable("encuesta");
        spEspecie = (Spinner) findViewById(R.id.sp_especie);
        spTipoCultivo = (Spinner) findViewById(R.id.sp_tipo_cultivo);
        etNroSiembra = (EditText) findViewById(R.id.et_nro_siembra);
        etMesSiembra = (EditText) findViewById(R.id.et_mes_siembra);
        etSurcos = (EditText) findViewById(R.id.et_surcos);
        etDistancias = (EditText) findViewById(R.id.et_distancias);
        etLargo = (EditText) findViewById(R.id.et_largo);
        etSuperficieSembrada = (EditText) findViewById(R.id.et_superficie_sembrada);
        spSuperficieMedida = (Spinner) findViewById(R.id.sp_superficie_medida);
        spTipoProduccion = (Spinner) findViewById(R.id.sp_tipo_produccion);
        spEleccionCultivo = (Spinner) findViewById(R.id.sp_eleccion_cultivo);
        etEleccionEspecificar = (EditText) findViewById(R.id.et_eleccion_especificar);
        mDatumDbHelper = new MDatumDbHelper(this);
        opcionesEspecie = mDatumDbHelper.getAllItems("especie");
        ArrayAdapter<String> especieAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opcionesEspecie);
        especieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spEspecie.setAdapter(especieAdapter);
        opcionesTipoCultivo = mDatumDbHelper.getAllItems("tipoCultivo");
        ArrayAdapter<String> tipoCultivoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opcionesTipoCultivo);
        tipoCultivoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spTipoCultivo.setAdapter(tipoCultivoAdapter);
        opcionesSuperficieMedida = mDatumDbHelper.getAllItems("superficieMedida");
        ArrayAdapter<String> superficieMedidaAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opcionesSuperficieMedida);
        superficieMedidaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spSuperficieMedida.setAdapter(superficieMedidaAdapter);
        opcionesTipoProduccion = mDatumDbHelper.getAllItems("tipoProduccion");
        ArrayAdapter<String> tipoProduccionAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opcionesTipoProduccion);
        tipoProduccionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spTipoProduccion.setAdapter(tipoProduccionAdapter);
        opcionesEleccionCultivo = mDatumDbHelper.getAllItems("eleccionCultivo");
        ArrayAdapter<String> eleccionCultivoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opcionesEleccionCultivo);
        eleccionCultivoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spEleccionCultivo.setAdapter(eleccionCultivoAdapter);
        etEleccionEspecificar.setVisibility(View.GONE);
        spEleccionCultivo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if(parent.getItemAtPosition(position).toString().equals("Otro")) {

                    etEleccionEspecificar.setVisibility(View.VISIBLE);
                }else{
                    etEleccionEspecificar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnGuardar = (Button) findViewById(R.id.btn_guardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cultivo = new Cultivo();
                cultivo.setEspecieId((int) spEspecie.getSelectedItemId());
                cultivo.setTipoId((int) spTipoCultivo.getSelectedItemId());
                cultivo.setNroSiembra(Integer.parseInt(etNroSiembra.getText().toString()));
                cultivo.setMesSiembra(Integer.parseInt(etMesSiembra.getText().toString()));
                cultivo.setSurcos(Integer.parseInt(etSurcos.getText().toString()));
                cultivo.setDistancias(Integer.parseInt(etDistancias.getText().toString()));
                cultivo.setLargo(Integer.parseInt(etLargo.getText().toString()));
                cultivo.setSuperficieSembrada(Integer.parseInt(etSuperficieSembrada.getText().toString()));
                cultivo.setSuperficieMedidaId((int) spSuperficieMedida.getSelectedItemId());
                cultivo.setTipoProduccionId((int) spTipoProduccion.getSelectedItemId());
                cultivo.setEleccionCultivoId((int) spEleccionCultivo.getSelectedItemId());
                cultivo.setEleccionEspecificar(etEleccionEspecificar.getText().toString());

                Toast savingToast = Toast.makeText(getApplicationContext(), "Guardando los datos.", Toast.LENGTH_SHORT);

                savingToast.show();
                new AddCultivoTask().execute(cultivo);
            }
        });
        btnFinalizar = (Button) findViewById(R.id.btn_finalizar);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddEncuestaTask().execute(encuesta);
            }
        });
    }

    private class AddCultivoTask extends AsyncTask<Cultivo, Void, Boolean>{


        @Override
        protected Boolean doInBackground(Cultivo... cultivos) {
            long result = mDatumDbHelper.saveCultivo(cultivos[0]);
            cultivos[0].setId((int) result);
            encuesta.setCultivoId((int)result);
            return result > 0;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Intent intent = new Intent(getApplicationContext(),CultivoActivity.class);
            intent.putExtra("encuesta",encuesta);
            startActivity(intent);

        }
    }

    private class AddEncuestaTask extends AsyncTask<Encuesta, Void, Boolean>{


        @Override
        protected Boolean doInBackground(Encuesta... encuestas) {
            long result = mDatumDbHelper.saveEncuesta(encuestas[0]);
            encuestas[0].setId((int) result);
            return result > 0;
         }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            /*if(!encuesta.getInvernaderoId().isEmpty()){
                for(int i =0; i < encuesta.getInvernaderoId().size();i ++){
                    mDatumDbHelper.saveIntermedio("encuestaInvernaculo",encuesta.getId(),encuesta.getInvernaderoId().get(i));
                }
            }
            if(!encuesta.getCultivoId().isEmpty()){
                for(int i = 0; i < encuesta.getCultivoId().size(); i ++){
                    mDatumDbHelper.saveIntermedio("encuestaCultivo",encuesta.getId(),encuesta.getCultivoId().get(i));
                }
            }*/
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }


    }
}
