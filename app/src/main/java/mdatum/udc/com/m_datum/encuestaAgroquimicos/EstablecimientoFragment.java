package mdatum.udc.com.m_datum.encuestaAgroquimicos;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.data.prefs.SessionPrefs;
import mdatum.udc.com.m_datum.database.*;
import mdatum.udc.com.m_datum.database.Establecimiento;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstablecimientoFragment extends Fragment implements OnConnectionFailedListener,
        ConnectionCallbacks {

    private String exceptionMsg="";
    //Instancio un nuvo objeto POJO Establecimiento
    private Establecimiento establecimiento = new Establecimiento();
    //Creo un objeto POJO del tipo Encuesta
    private Encuesta encuesta;
    //Preparo el objeto que va a manerjar la sesion de la base de datos
    private DaoSession daoSession;
    //variable donde se genera el nombre de archivo de la imagen capturada
    private String name = "";

    //Creo una lista donde guardar las opciones de regimen Tenencia
    private List opciones;

    private static final String LOGTAG = "android-localizacion";

    //variable para la peticion de permiso de localizacion
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    //variable para la peticion de permiso de almacenamiento
    private static final int PETICION_PERMISO_ALMACENAMIENTO = 102;
    //instancia de la api de google
    private GoogleApiClient apiClient;

    //Creo los elementos con los que voy a relacionar la interfaz
    private TextView tvCoordLat,tvCoordLong;
    private EditText etEspecificar, etNombreEstablecimiento, etNroEstablecimiento;
    private TextInputLayout tilEspecificar;
    private Spinner spRegTenencia;
    private Button btnCapturarUbicacion;
    //Creo un elemento donde guardar la opcion que se selecciona de regimen tenencia
    private RegimenTenencia opcionSelected;


    public EstablecimientoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_establecimiento, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        //verifica si el servicio de localizacion esta activado, sino lanza un dialogo
        verificarLocalizacion();

        // obtiene la sesion de acceso a la base de datos.
        daoSession = ((MDatumController)getActivity().getApplication()).getDaoSession();
        //Obtengo el objeto encuesta que va a pasar durante toda la sesion
        encuesta = (Encuesta) getArguments().getSerializable("encuesta");



//--------------------------------SPINNER-----------------------------------------------------------
        //Arreglo que carga el spinner de Régimen de Tenencia de Tierra
        tilEspecificar = (TextInputLayout) rootView.findViewById(R.id.til_especificar);
        etEspecificar = (EditText) rootView.findViewById(R.id.et_especificar);
        spRegTenencia = (Spinner) rootView.findViewById(R.id.sp_reg_tenencia);


        //Instancio un objeto DAO para acceder a la informacion en la base de datos
        RegimenTenenciaDao regimenTenenciaDao = daoSession.getRegimenTenenciaDao();
        //cargo la lista de opciones con los valore desde la base de datos
        opciones = regimenTenenciaDao.loadAll();



        final List<String> objects = new ArrayList<>();

        Iterator<RegimenTenencia> iterator = opciones.iterator();

        while (iterator.hasNext()){
            objects.add(iterator.next().getDescpripcion());
        }

        for(int i = 0 ; i < objects.size() ; i++){
            Log.d("{POSITION,ELEMENT}","{"+Integer.toString(i)+","+objects.get(i)+"}");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, objects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRegTenencia.setAdapter(adapter);

        //Utilizo un listener que captura los eventos realizados sobre el spinner y si elige la opción
        //"Otro" setea el EditText et_especificar como visible
        spRegTenencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).toString().equals("OTRO")) {

                    tilEspecificar.setVisibility(View.VISIBLE);
                }else{
                    tilEspecificar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


/*-------------------------UTILIZA EL BOTON PARA ACCEDER A LA CAMARA, GUARDA LA FOTO Y LA MUESTRA
 SIEMPRE QUE EL CAMPO Nombre de Estableciemiento este completo-----------------------------------*/
        //edit text que obtiene el nombre del establecimiento
        etNombreEstablecimiento = (EditText) rootView.findViewById(R.id.et_nombre_establecimiento);
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
        Button btnFoto = (Button) rootView.findViewById(R.id.btn_foto);
        //le agrego un listener para detectar el click del boton
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cuando detecta un click, valida si se ingresó el nombre del establecimiento
                if (etNombreEstablecimiento.getText().toString().trim().isEmpty()) {
                    //si el nombre está vacío, muestra un error y no accede a la cámara
                    Toast fotoToast = Toast.makeText(getContext(),"Falta introducir nombre establecimiento",Toast.LENGTH_SHORT);
                    fotoToast.show();
                }
                else
                {
                    //si encuentra el nombre genera un intent que abre la aplicacion de la camara del dispositivo
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    Uri output; //= Uri.fromFile(new File(name));
                    //intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
                    //inicio la aplicacion de la camara con un activity que espera como resultado la imagen capturada.
                    //intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        output = Uri.parse(name);
                    }else{
                        output = Uri.fromFile(new File(name));
                    }
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, output);

                    try{
                        startActivityForResult(intent, 1);
                    }catch (Exception e){
                        Toast.makeText(getContext(),"Error al abrir la camara:"+e.toString(),Toast.LENGTH_LONG).show();
                        Log.e("camara", e.toString());
                    }


                }


            }
        });

        //obtengo los textViews donde se van a mostrar las coordenadas geograficas
        tvCoordLat = (TextView) rootView.findViewById(R.id.tv_coord_lat);
        tvCoordLong = (TextView) rootView.findViewById(R.id.tv_coord_long);

        //obtengo el boton de captura de ubicacion
        btnCapturarUbicacion = (Button) rootView.findViewById(R.id.btn_capturar_ubicacion);

        btnCapturarUbicacion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                capturarUbicacion();
            }
        });



        //Obtengo el boton de siguiente
        Button btnSiguiente = (Button)rootView.findViewById(R.id.btn_establecimiento_siguiente);

        etNroEstablecimiento =  (EditText) rootView.findViewById(R.id.et_nro_establecimiento);
        etEspecificar = (EditText) rootView.findViewById(R.id.et_especificar);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                establecimiento.setNombre(etNombreEstablecimiento.getText().toString());
                establecimiento.setNro(etNroEstablecimiento.getText().toString());
                Log.d("SELECTEDPOS",Integer.toString(spRegTenencia.getSelectedItemPosition()));
                establecimiento.setRegimenTenenciaId(spRegTenencia.getSelectedItemPosition()+1);
                Log.d("STOREDPOSITION",Integer.toString(establecimiento.getRegimenTenenciaId()));
                opcionSelected = (RegimenTenencia) opciones.get(establecimiento.getRegimenTenenciaId() -1 );

                if(opcionSelected.getDescpripcion().equals("OTRO")){
                    establecimiento.setRegimenOtros(etEspecificar.getText().toString());
                }else{
                    establecimiento.setRegimenOtros("");
                }
                establecimiento.setFoto(name);


                validarDatos();




            }
        });




        return rootView;
    }

    private void validarDatos(){
        if(!validarNombre()){
            return;
        }
        if(!validarNumero()){
            return;
        }
        if(!validarRegimen()){
            return;
        }

        Toast savingToast = Toast.makeText(getContext(),"Guardando los datos.",Toast.LENGTH_SHORT);
        savingToast.show();
        new EstablecimientoFragment.AddEstablecimientoTask().execute(establecimiento);


    }

    private boolean validarNombre(){

        if(etNombreEstablecimiento.getText().toString().trim().isEmpty()){
            etNombreEstablecimiento.setError(getString(R.string.error_nombre));
            return false;
        }else {
            etNombreEstablecimiento.setError(null);


        }


        return true;
    }

    private boolean validarNumero(){

        if(etNroEstablecimiento.getText().toString().trim().isEmpty()){
            etNroEstablecimiento.setError(getString(R.string.error_numero));
            return false;
        }else {
            etNroEstablecimiento.setError(null);


        }

        return true;
    }


    private boolean validarRegimen(){



        if((opcionSelected.getDescpripcion().equals("OTRO")) && (etEspecificar.getText().toString().trim().isEmpty())){
            etEspecificar.setError(getString(R.string.error_especificar));
            return false;
        }else {
            etEspecificar.setError(null);


        }

        return true;
    }



    private void capturarUbicacion(){
        //Configuro la apiClient de Google
        if(apiClient != null)
            apiClient.stopAutoManage(getActivity());

        apiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }




    //-----------------------------FOTOGRAFIA-------------------------------------------------------
    //Metodo que se ejecuta cuando la aplicacion de la camara captura la imagen.
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){

        if (data != null){
            if (data.hasExtra("data")){
                ImageView ivEstablecimiento = (ImageView) getActivity().findViewById(R.id.tv_establecimiento);
                ivEstablecimiento.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
            }
        }else{
            if (ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //si no los tiene se los pide al usuario
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PETICION_PERMISO_ALMACENAMIENTO);
            }else{
                ImageView ivEstablecimiento = (ImageView) getActivity().findViewById(R.id.tv_establecimiento);
                ivEstablecimiento.setImageBitmap(BitmapFactory.decodeFile(name));

                new MediaScannerConnection.MediaScannerConnectionClient(){
                    private MediaScannerConnection msc = null;{
                        msc = new MediaScannerConnection(getContext(),this);msc.connect();
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
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //si no los tiene se los pide al usuario
            ActivityCompat.requestPermissions(getActivity(),
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
            ImageView ivEstablecimiento = (ImageView) getActivity().findViewById(R.id.tv_establecimiento);
            ivEstablecimiento.setImageBitmap(BitmapFactory.decodeFile(name));

            new MediaScannerConnection.MediaScannerConnectionClient(){
                private MediaScannerConnection msc = null;{
                    msc = new MediaScannerConnection(getContext(),this);msc.connect();
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

    private class AddEstablecimientoTask extends AsyncTask<mdatum.udc.com.m_datum.database.Establecimiento,Void,Boolean> {
        @Override
        protected Boolean doInBackground(Establecimiento... establecimiento){
            long result;
            try {
                if(establecimiento[0].getId()!=null) {
                    daoSession.update(establecimiento[0]);
                    result = establecimiento[0].getId();
                }else{
                    result = daoSession.insert(establecimiento[0]);
                    encuesta.setEstablecimientoRelated(establecimiento[0]);
                    Date fecha = new Date();
                    encuesta.setFecha(new SimpleDateFormat("yyyy-MM-dd").format(fecha));
                    encuesta.setUsuario(SessionPrefs.get(getContext()).getUserLoged());
                    encuesta.setIs_finished(false);
                    encuesta.setIsSincronized(false);
                    long idEncuesta = daoSession.insertOrReplace(encuesta);

                }


                return result > 0;
            }catch (SQLiteConstraintException e){
                exceptionMsg ="Ya existe un establecimiento con este nombre y numero.";
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result){
            if(result) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("encuesta", encuesta);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                EncuestadoFragment fragment = new EncuestadoFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.ll_body_content, fragment)
                        .addToBackStack("ESTABLECIMIENTO")
                        .commit();
            }else{
                etNombreEstablecimiento.setError(exceptionMsg);
                etNroEstablecimiento.setError(exceptionMsg);
                Toast.makeText(getContext(), exceptionMsg, Toast.LENGTH_SHORT).show();
            }
        }

    }

    /* Verifica que el servicio de localizacion se encuentre activado,
     * si no esta activado lanza un dialogo solicitando la activacion.
     */
    private void verificarLocalizacion(){

        // Crea unn location manager
        LocationManager locateManager=(LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);
        // obtiene el estado del proveedor del servicio
        boolean enabled = locateManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // Verifica si no esta habilitado
        if (!enabled) {


            // Notifica al usuario a traves de un dialogo
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            // setea el mensaje para el usuario
            dialog.setMessage(R.string.localizacion);
            //crea el boton de accion positiva (habilitar Localizacion)
            dialog.setPositiveButton(R.string.habilitaLocalizacion, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // abre la pantalla de ajustes de ubicacion
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    getContext().startActivity(myIntent);
                }
            });
            // crea el boton de accion por la negativa
            dialog.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {


                }
            });

            //muestra el dialogo
            dialog.show();
        }
    }


}
