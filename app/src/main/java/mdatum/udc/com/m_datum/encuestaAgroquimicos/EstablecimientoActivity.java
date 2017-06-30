package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;

import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.util.ArrayList;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.data.Encuesta;
import mdatum.udc.com.m_datum.data.Establecimiento;
import mdatum.udc.com.m_datum.data.EstablecimientoDbHelper;
import mdatum.udc.com.m_datum.data.RegimenTenencia;
import mdatum.udc.com.m_datum.data.RegimenTenenciaDbHelper;


public class EstablecimientoActivity extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    private Encuesta encuesta = new Encuesta();
    private EstablecimientoDbHelper establecimientoDbHelper;
    private Establecimiento establecimiento = new Establecimiento();
    
    //variable donde se genera el nombre de archivo de la imagen capturada
    private String name = "";


    private static final String LOGTAG = "android-localizacion";

    //variable para la peticion de permiso de localizacion
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int PETICION_PERMISO_ALMACENAMIENTO = 102;
    //instancia de la api de google
    private GoogleApiClient apiClient;
    //textViews que muestran la posicion geografica
    private TextView tvCoordLat,tvCoordLong;

    private EditText etEspecificar;

    private Spinner spRegTenencia;

    private Button btnCapturarUbicacion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento);

//--------------------------------SPINNER-----------------------------------------------------------
        //Arreglo que carga el spinner de Régimen de Tenencia de Tierra

        new RegimenTenenciaDbHelper(this);
        
        etEspecificar = (EditText) findViewById(R.id.et_especificar);
        spRegTenencia = (Spinner) findViewById(R.id.sp_reg_tenencia);



        final String []opciones= new String[]{"Propiedad","Sucesión indivisa","Arrendatario","Med. % producto","Med. % dinero","Ocupación","Otro"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRegTenencia.setAdapter(adapter);










        //Utilizo un listener que captura los eventos realizados sobre el spinner y si elige la opción
        //"Otro" setea el EditText et_especificar como visible
        spRegTenencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString() == "Otro") {
                    etEspecificar.setVisibility(View.VISIBLE);
                }else{
                    etEspecificar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


/*-------------------------UTILIZA EL BOTON PARA ACCEDER A LA CAMARA, GUARDA LA FOTO Y LA MUESTRA
 SIEMPRE QUE EL CAMPO Nombre de Estableciemiento este completo-----------------------------------*/
        //edit text que obtiene el nombre del establecimiento
        final EditText etNombreEstablecimiento = (EditText) findViewById(R.id.et_nombre_establecimiento);
        //listener que detecta el cambio del contenido del cuadro de texto
        etNombreEstablecimiento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            //cuando el cuadro de texto para nombre cambia, setea una variable con su contenido sin espacios
            //para generar el nombre de archivo de la imagen que se captura del establecimiento.
            @Override
            public void afterTextChanged(Editable editable) {
                name = Environment.getExternalStorageDirectory()+"/"+editable.toString().replaceAll("\\s+","")+".jpg";
            }
        });

        //obtengo el boton de capturar foto
        Button btnFoto = (Button) findViewById(R.id.btn_foto);
        //le agrego un listener para detectar el click del boton
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cuando se detecta el click se genera un intent que abre la aplicacion de la camara del dispositivo
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri output = Uri.fromFile(new File(name));
                intent.putExtra(MediaStore.EXTRA_OUTPUT,output);
                //inicio la aplicacion de la camara con un activity que espera como resultado la imagen capturada.
                startActivityForResult(intent,1);

            }
        });

        //obtengo los textViews donde se van a mostrar las coordenadas geograficas
        tvCoordLat = (TextView) findViewById(R.id.tv_coord_lat);
        tvCoordLong = (TextView) findViewById(R.id.tv_coord_long);

        //obtengo el boton de captura de ubicacion
        btnCapturarUbicacion = (Button) findViewById(R.id.btn_capturar_ubicacion);

        btnCapturarUbicacion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                capturarUbicacion();
            }
        });



        //Obtengo el boton de siguiente
        Button btnSiguiente = (Button)findViewById(R.id.btn_siguiente);

        final EditText etNroEstablecimiento =  (EditText) findViewById(R.id.et_nro_establecimiento);
        final EditText etEspecificar = (EditText) findViewById(R.id.et_especificar);
        establecimientoDbHelper = new EstablecimientoDbHelper(this);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                establecimiento.setNombre(etNombreEstablecimiento.getText().toString());
                establecimiento.setNro(etNroEstablecimiento.getText().toString());
                establecimiento.setRegimenTenencia(spRegTenencia.getSelectedItemPosition());


                if(opciones[establecimiento.getRegimenTenencia()]=="Otro"){
                    establecimiento.setRegimenOtros(etEspecificar.getText().toString());
                }else{
                    establecimiento.setRegimenOtros("");
                }
                establecimiento.setFoto(name);

                if(establecimiento.validar()){
                    Toast savingToast = Toast.makeText(getApplicationContext(),"Guardando los datos.",Toast.LENGTH_SHORT);

                    savingToast.show();
                    new AddEstablecimientoTask().execute(establecimiento);
                } else {
                    Toast savingToast = Toast.makeText(getApplicationContext(),"Verifique los datos cargados.",Toast.LENGTH_SHORT);

                    savingToast.show();
                }


            }
        });

    }



    private void capturarUbicacion(){
        //Configuro la apiClient de Google
        if(apiClient != null)
            apiClient.stopAutoManage(this);

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }


    //-----------------------------FOTOGRAFIA-------------------------------------------------------
    //Metodo que se ejecuta cuando la aplicacion de la camara captura la imagen.
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        if (data != null){
            if (data.hasExtra("data")){
                ImageView ivEstablecimiento = (ImageView) findViewById(R.id.tv_establecimiento);
                ivEstablecimiento.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
            }
        }else{
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //si no los tiene se los pide al usuario
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PETICION_PERMISO_ALMACENAMIENTO);
            }else{
                ImageView ivEstablecimiento = (ImageView) findViewById(R.id.tv_establecimiento);
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
    }

    //metodo que actualiza los text views de posicion
//-----------------------------------GEOPOSICIONAMIENTO---------------------------------------------
    //método recibe un objeto Location y actualiza los campos de tvCoordLat y tvCoordLong
    private void updateUI(Location loc){
        //si se obtiene la ubicacion
        if(loc != null){
            //actualiza el texto de las etiquetas de posicion
            tvCoordLat.setText(String.valueOf(loc.getLatitude()));
            tvCoordLong.setText(String.valueOf(loc.getLongitude()));
            establecimiento.setPosLatitud(String.valueOf(loc.getLatitude()));
            establecimiento.setPosLongitud(String.valueOf(loc.getLongitude()));
        }
    }

    //metodo que maneja el error de conexion
    @Override
    public void onConnectionFailed(ConnectionResult result){

        Log.e(LOGTAG, "Error grave al conectar con Google Play Services");
    }

    //metodo que se ejecuta cuando se detecta que se establecio la conexion
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Conectado correctamente a Google Play Services
        //verifica si la aplicacion no tiene permisos para acceder a la localizacion
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //si no los tiene se los pide al usuario
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
        }else{
            //si tiene los permisos para acceder a la localizacion
            //obtiene la ultima ubicacion registrada
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            //actualiza las etiquetas con la ultima ubicacion conocida
            updateUI(lastLocation);
        }

    }


    //metodo que gestiona el pedido de autorizacion para acceder a la ubicacion
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
        if (requestCode == PETICION_PERMISO_ALMACENAMIENTO){
            ImageView ivEstablecimiento = (ImageView) findViewById(R.id.tv_establecimiento);
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

    @Override
    public void onConnectionSuspended(int i) {
        //Se ha interrumpido la conexión con Google Play Services

        Log.e(LOGTAG, "Se ha interrumpido la conexión con Google Play Services");
    }

    private class AddEstablecimientoTask extends AsyncTask<Establecimiento,Void,Boolean>{
        @Override
        protected Boolean doInBackground(Establecimiento... establecimiento){
            long result = establecimientoDbHelper.saveEstablecimiento(establecimiento[0]);
            establecimiento[0].setId((int) result);
            encuesta.setEstablecimientoId((int) result);
            return result > 0;
        }

        @Override
        protected void onPostExecute(Boolean result){
            Intent encuestado = new Intent(getApplicationContext(),EncuestadoActivity.class);
            encuestado.putExtra("encuesta",encuesta);
            startActivity(encuestado);
        }

    }

}

//--------------------------------------------------------------------------------------------------