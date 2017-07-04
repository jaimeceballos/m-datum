package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.data.Encuesta;
import mdatum.udc.com.m_datum.data.Familia;
import mdatum.udc.com.m_datum.data.MDatumDbHelper;

public class FamiliaActivity extends AppCompatActivity {
    private RadioButton rbHijosSi, rbHijosNo,rbEsposaSi,rbEsposaNo;
    private TextView tvVarones, tvMujeres;
    private EditText etCantVarones, etCantMujeres;
    private Button btnFliaSiguiente;
    private Encuesta encuesta;
    private MDatumDbHelper mDatumDbHelper;
    private Familia familia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familia);

        encuesta        =  (Encuesta) getIntent().getExtras().getSerializable("encuesta");
        rbHijosSi = (RadioButton) findViewById(R.id.rb_hijos_si);
        rbHijosNo = (RadioButton) findViewById(R.id.rb_hijos_no);
        tvVarones = (TextView) findViewById(R.id.tv_varones);
        etCantVarones = (EditText) findViewById(R.id.et_cant_varones);
        tvMujeres = (TextView) findViewById(R.id.tv_mujeres);
        etCantMujeres = (EditText) findViewById(R.id.et_cant_mujeres);
        rbEsposaSi = (RadioButton) findViewById(R.id.rb_esposa_si);
        rbEsposaNo = (RadioButton) findViewById(R.id.rb_esposa_no);

        btnFliaSiguiente = (Button) findViewById(R.id.btn_flia_siguiente);

        rbHijosSi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(rbHijosSi.isChecked()){
                    tvVarones.setVisibility(View.VISIBLE);
                    etCantVarones.setVisibility(View.VISIBLE);
                    tvMujeres.setVisibility(View.VISIBLE);
                    etCantMujeres.setVisibility(View.VISIBLE);
                }
            }
        });

        rbHijosNo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(rbHijosNo.isChecked()){
                    tvVarones.setVisibility(View.GONE);
                    etCantVarones.setVisibility(View.GONE);
                    tvMujeres.setVisibility(View.GONE);
                    etCantMujeres.setVisibility(View.GONE);
                }
            }
        });

        mDatumDbHelper = new MDatumDbHelper(this);

        btnFliaSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                familia = new Familia();
                familia.setEsCasado((rbEsposaSi.isChecked())?1:0);
                familia.setTieneHijos((rbHijosSi.isChecked())?1:0);
                familia.setCantMujeres(Integer.parseInt(etCantMujeres.getText().toString()));
                familia.setCantVarones(Integer.parseInt(etCantVarones.getText().toString()));

                new AddFamiliaTask().execute(familia);
            }
        });

    }

    private class AddFamiliaTask extends AsyncTask<Familia,Void,Boolean>{

        @Override
        protected Boolean doInBackground(Familia... familia) {
            long result = mDatumDbHelper.saveFamilia(familia[0]);
            familia[0].setId((int)result);
            encuesta.setFamiliaId((int)result);
            return result > 0;
        }

        @Override
        protected void onPostExecute(Boolean result){
            Intent produccion = new Intent(getApplicationContext(), ProduccionActivity.class);
            produccion.putExtra("encuesta",encuesta);
            startActivity(produccion);
        }


    }
}
