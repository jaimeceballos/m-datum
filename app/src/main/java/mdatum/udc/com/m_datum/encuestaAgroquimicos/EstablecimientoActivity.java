package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import mdatum.udc.com.m_datum.R;


public class EstablecimientoActivity extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks{

    private String name = "";
    private static final String LOGTAG = "android-localizacion";

    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private GoogleApiClient apiClient;
    private TextView tvCoordLat,tvCoordLong;
    private Button btnCapturarUbicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento);

        //Arreglo que carga el spinner de Régimen de Tenencia de Tierra
        Spinner spRegTenencia;

        spRegTenencia = (Spinner) findViewById(R.id.sp_reg_tenencia);
        String []opciones={"Propiedad","Sucesión indivisa","Arrendatario","Med. % producto","Med. % dinero","Ocupación","Otro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spRegTenencia.setAdapter(adapter);

        EditText etNombreEstablecimiento = (EditText) findViewById(R.id.et_nombre_establecimiento);

        etNombreEstablecimiento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                name = Environment.getExternalStorageDirectory()+"/"+editable.toString().replaceAll("\\s+","")+".jpg";
            }
        });

        Button btnFoto = (Button) findViewById(R.id.btn_foto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Log.d("FILE NAME",name);
                Uri output = Uri.fromFile(new File(name));
                intent.putExtra(MediaStore.EXTRA_OUTPUT,output);

                startActivityForResult(intent,1);

            }
        });

        tvCoordLat = (TextView) findViewById(R.id.tv_coord_lat);
        tvCoordLong = (TextView) findViewById(R.id.tv_coord_long);

        btnCapturarUbicacion = (Button) findViewById(R.id.btn_capturar_ubicacion);

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();


    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (data != null){
            if (data.hasExtra("data")){
                ImageView ivEstablecimiento = (ImageView) findViewById(R.id.iv_establecimiento);
                ivEstablecimiento.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
            }
        }else{
            ImageView ivEstablecimiento = (ImageView) findViewById(R.id.iv_establecimiento);
            ivEstablecimiento.setImageBitmap(BitmapFactory.decodeFile(name));

            new MediaScannerConnection.MediaScannerConnectionClient(){
                private MediaScannerConnection msc = null;{
                    msc = new MediaScannerConnection(getApplicationContext(),this);msc.connect();
                }
                public void onMediaScannerConnected(){
                    msc.scanFile(name,null);
                }
                public void onScanCompleted(String path,Uri uri){
                    msc.disconnect();
                }
            };
        }
    }

    private void updateUI(Location loc){
        if(loc != null){
            tvCoordLat.setText(String.valueOf(loc.getLatitude()));
            tvCoordLong.setText(String.valueOf(loc.getLongitude()));
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result){

        Log.e(LOGTAG, "Error grave al conectar con Google Play Services");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Conectado correctamente a Google Play Services

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
        }else{
            Location lastLocation =
                    LocationServices.FusedLocationApi.getLastLocation(apiClient);

            updateUI(lastLocation);
        }



        //...
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Permiso concedido

                @SuppressWarnings("MissingPermission")
                Location lastLocation =
                        LocationServices.FusedLocationApi.getLastLocation(apiClient);

                updateUI(lastLocation);

            } else {
                //Permiso denegado:
                //Deberíamos deshabilitar toda la funcionalidad relativa a la localización.

                Log.e(LOGTAG, "Permiso denegado");
            }
        }
    }



    @Override
    public void onConnectionSuspended(int i) {
        //Se ha interrumpido la conexión con Google Play Services

        Log.e(LOGTAG, "Se ha interrumpido la conexión con Google Play Services");
    }
}



