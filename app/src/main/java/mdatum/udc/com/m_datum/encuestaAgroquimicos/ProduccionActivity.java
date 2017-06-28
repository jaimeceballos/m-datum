package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import mdatum.udc.com.m_datum.R;

public class ProduccionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produccion);

        RadioButton rbCubiertoSi = (RadioButton) findViewById(R.id.rb_cubierto_si);
        RadioButton rbCubiertoNo = (RadioButton) findViewById(R.id.rb_cubierto_no);
        Button btnProduccionSiguiente = (Button) findViewById(R.id.btn_produccion_siguiente);




    }
}
