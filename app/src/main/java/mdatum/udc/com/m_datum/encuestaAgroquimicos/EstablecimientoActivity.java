package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mdatum.udc.com.m_datum.R;

public class EstablecimientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento);

        List<String> regTenenciaArray = new ArrayList<String>();
        regTenenciaArray.add("Propiedad");
        regTenenciaArray.add("Sucesión indivisa");
        regTenenciaArray.add("Arrendatario");
        regTenenciaArray.add("Otro");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, regTenenciaArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner)findViewById(R.id.sp_reg_tenencia);
        sItems.setAdapter(adapter);
    }
}
