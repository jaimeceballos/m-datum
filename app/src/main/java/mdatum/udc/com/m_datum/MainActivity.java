package mdatum.udc.com.m_datum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mdatum.udc.com.m_datum.data.Encuesta;
import mdatum.udc.com.m_datum.encuestaAgroquimicos.EstablecimientoActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnEncuesta;
    private Encuesta encuesta = new Encuesta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btnEncuesta = (Button) findViewById(R.id.btn_encuesta);


        btnEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent establecimiento = new Intent(view.getContext(), EstablecimientoActivity.class);
                establecimiento.putExtra("encuesta",encuesta);
                startActivity(establecimiento);
            }
        });
    }
}
