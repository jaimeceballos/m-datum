package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.data.Encuesta;


public class ProduccionActivity extends AppCompatActivity {
    private RadioButton rbCubiertoSi, rbCubiertoNo;
    private Button btnProduccionSiguiente;
    private Encuesta encuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produccion);
        encuesta = (Encuesta) getIntent().getExtras().getSerializable("encuesta");
        rbCubiertoSi = (RadioButton) findViewById(R.id.rb_cubierto_si);
        rbCubiertoNo = (RadioButton) findViewById(R.id.rb_cubierto_no);
        btnProduccionSiguiente = (Button) findViewById(R.id.btn_produccion_siguiente);
        rbCubiertoSi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(rbCubiertoSi.isChecked()){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("encuesta",encuesta);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    InvernaculoFragment fragment = new InvernaculoFragment();
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.cl_view_group_invernaculo, fragment);
                    fragmentTransaction.commit();

                }
            }
        });

        btnProduccionSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cultivo = new Intent(view.getContext(), CultivoActivity.class);
                encuesta = (Encuesta) getIntent().getExtras().getSerializable("encuesta");
                cultivo.putExtra("encuesta",encuesta);
                startActivity(cultivo);
            }
        });


    }
}
