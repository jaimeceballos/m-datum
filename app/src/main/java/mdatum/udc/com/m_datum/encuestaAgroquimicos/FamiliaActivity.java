package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import mdatum.udc.com.m_datum.R;

public class FamiliaActivity extends AppCompatActivity {
    private RadioButton rbHijosSi, rbHijosNo;
    private TextView tvVarones, tvMujeres;
    private EditText etCantVarones, etCantMujeres;
    private Button btnFliaSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familia);
        rbHijosSi = (RadioButton) findViewById(R.id.rb_hijos_si);
        rbHijosNo = (RadioButton) findViewById(R.id.rb_hijos_no);
        tvVarones = (TextView) findViewById(R.id.tv_varones);
        etCantVarones = (EditText) findViewById(R.id.et_cant_varones);
        tvMujeres = (TextView) findViewById(R.id.tv_mujeres);
        etCantMujeres = (EditText) findViewById(R.id.et_cant_mujeres);

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

        btnFliaSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent produccion = new Intent(view.getContext(), ProduccionActivity.class);
                startActivity(produccion);
            }
        });
    }
}
