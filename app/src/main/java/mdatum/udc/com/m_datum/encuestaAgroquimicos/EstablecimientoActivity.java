package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mdatum.udc.com.m_datum.R;


public class EstablecimientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento);

        //Arreglo que carga el spinner de Régimen de Tenencia de Tierra
        List<String> regTenenciaArray = new ArrayList<String>();
        regTenenciaArray.add("Propiedad");
        regTenenciaArray.add("Sucesión indivisa");
        regTenenciaArray.add("Arrendatario");
        regTenenciaArray.add("Otro");

    }

}



