package mdatum.udc.com.m_datum;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mdatum.udc.com.m_datum.data.prefs.SessionPrefs;
import mdatum.udc.com.m_datum.database.AnioEstructura;
import mdatum.udc.com.m_datum.database.Asesoramiento;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.EleccionCultivo;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.EncuestaDao;
import mdatum.udc.com.m_datum.database.Especie;
import mdatum.udc.com.m_datum.database.FactorClimatico;
import mdatum.udc.com.m_datum.database.MaterialEstructura;
import mdatum.udc.com.m_datum.database.Nacionalidad;
import mdatum.udc.com.m_datum.database.NivelInstruccion;
import mdatum.udc.com.m_datum.database.RegimenTenencia;
import mdatum.udc.com.m_datum.database.TipoCultivo;
import mdatum.udc.com.m_datum.database.TipoProduccion;
import mdatum.udc.com.m_datum.database.TripleLavado;
import mdatum.udc.com.m_datum.encuestaAgroquimicos.EncuestasListFragment;
import mdatum.udc.com.m_datum.encuestaAgroquimicos.EstablecimientoFragment;
import mdatum.udc.com.m_datum.sincronizacion.PasswordChangeFragment;
import mdatum.udc.com.m_datum.sincronizacion.SincroFragment;
import mdatum.udc.com.m_datum.sincronizacion.Updates;
import mdatum.udc.com.m_datum.sincronizacion.WebDatumApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    Encuesta encuesta = new Encuesta();
    private WebDatumApi webDatumApi;
    private DaoSession daoSession;

    private ConstraintLayout progressLayout;
    private  ConstraintLayout llBodyContent;
    private TextView tvProgress;
    private int update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getApplicationContext().getSharedPreferences("MDATUM_PREFS", Activity.MODE_PRIVATE);

        webDatumApi = ((MDatumController)getApplication()).getApiSession();

        daoSession  = ((MDatumController)getApplication()).getDaoSession();

        setContentView(R.layout.activity_main);

        tvProgress = (TextView) findViewById(R.id.tv_progress_msg);

        progressLayout = (ConstraintLayout) findViewById(R.id.progress_layout);
        llBodyContent = (ConstraintLayout)findViewById(R.id.ll_body_content);



        //redireccion  al login
        if(!SessionPrefs.get(this).isLoggedIn()){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
            return;
        }



        update = SessionPrefs.get(this).getLastUpdate();
        if(update == 0){
            tvProgress.setText("CARGANDO ACTUALIZACIONES");
            showProgress(true);
            inicializarBasedeDatos();

        }else if(! (SessionPrefs.get(this).getLastServerUpdates() == -1) && (update < SessionPrefs.get(this).getLastServerUpdates()) ){
            tvProgress.setText("CARGANDO ACTUALIZACIONES");
            showProgress(true);
            obtener_ultimas_actualizaciones();

        }



        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        EncuestasListFragment fragment = new EncuestasListFragment();
        fragmentTransaction.replace(R.id.ll_body_content, fragment);
        fragmentTransaction.commit();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mSincro:
                showProgress(true);
                if(((MDatumController)getApplication()).isOnline()){
                    FragmentTransaction sincroTransaction = getSupportFragmentManager().beginTransaction();
                    SincroFragment sincroFragment = new SincroFragment();
                    showProgress(false);
                    sincroTransaction.replace(R.id.ll_body_content,sincroFragment).addToBackStack("SINCRO").commit();
                }else{
                    Toast.makeText(this, "Para sincronizar debe tener una conexion Wifi habilitada.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.change_pass_menu:
                if(((MDatumController)getApplication()).isOnline()){
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    PasswordChangeFragment passwordChangeFragment = new PasswordChangeFragment();
                    fragmentTransaction1.replace(R.id.ll_body_content,passwordChangeFragment).addToBackStack("CHANGE_PASS").commit();
                }else{
                    Toast.makeText(this, "Para realizar el cambio de contraseña debe tener una conexion Wifi habilitada.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.log_out_menu:
                if(((MDatumController)getApplication()).isOnline()){
                    showProgress(true);
                    cerrarSesion();
                }else{
                    Toast.makeText(this, "Para realizar el cierre de sesion debe tener una conexion Wifi habilitada.", Toast.LENGTH_LONG).show();
                }

                break;
        }


        return super.onOptionsItemSelected(item);
    }





    private void cerrarSesion(){
        Call<Void> logoutCall = webDatumApi.logout();
        logoutCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showProgress(false);
                SessionPrefs.get(MainActivity.this).logOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showProgress(false);
                Toast.makeText(getApplicationContext(),"No se puede conectar con el servidor.",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inicializarBasedeDatos(){



        Call<List<Updates>> updatesCall = webDatumApi.updates("Token "+prefs.getString("PREF_USER_TOKEN",null));
        updatesCall.enqueue(new Callback<List<Updates>>() {
            @Override
            public void onResponse(Call<List<Updates>> call, Response<List<Updates>> response) {
                if(response.isSuccessful()){
                    List<Updates> lista =  response.body();
                    procesar_actualizaciones(lista);
                }
            }

            @Override
            public void onFailure(Call<List<Updates>> call, Throwable t) {
                showProgress(false);
                Toast.makeText(getApplicationContext(),"Ocurrio un error al intentar conectar con el servidor.",Toast.LENGTH_LONG).show();
            }
        });
    }


    private void procesar_actualizaciones(List<Updates> lista){

        int last_update = SessionPrefs.get(MainActivity.this).getLastUpdate();
        for(int i = 0; i < lista.size(); i ++){

            Updates update = lista.get(i);

            if(i+1 == lista.size()){
                last_update = Integer.parseInt(update.getId());
            }

            switch (update.getEntidad()){
                case "Asesoramiento":
                    Asesoramiento asesoramiento = new Asesoramiento((long)Integer.parseInt(update.getId_entidad()),update.getValor());
                    daoSession.insert(asesoramiento);
                    break;
                case "TripleLavado":
                    TripleLavado tripleLavado = new TripleLavado((long) Integer.parseInt(update.getId_entidad()),update.getValor());
                    daoSession.insert(tripleLavado);
                    break;
                case "FactorClimatico":
                    FactorClimatico factorClimatico = new FactorClimatico((long) Integer.parseInt(update.getId_entidad()),update.getValor());
                    daoSession.insert(factorClimatico);
                    break;
                case "Especie":
                    Especie especie = new Especie((long) Integer.parseInt(update.getId_entidad()),update.getValor());
                    daoSession.insert(especie);
                    break;
                case "TipoCultivo":
                    TipoCultivo tipoCultivo = new TipoCultivo((long) Integer.parseInt(update.getId_entidad()),update.getValor());
                    daoSession.insert(tipoCultivo);
                    break;
                case "EleccionCultivo":
                    EleccionCultivo eleccionCultivo = new EleccionCultivo((long)Integer.parseInt(update.getId_entidad()),update.getValor());
                    daoSession.insert(eleccionCultivo);
                    break;
                case "TipoProduccion":
                    TipoProduccion tipoProduccion = new TipoProduccion((long) Integer.parseInt(update.getId_entidad()),update.getValor());
                    daoSession.insert(tipoProduccion);
                    break;
                case "MaterialEstructura":
                    MaterialEstructura materialEstructura = new MaterialEstructura((long) Integer.parseInt(update.getId_entidad()),update.getValor());
                    daoSession.insert(materialEstructura);
                    break;
                case "AnioConstruccion":
                    AnioEstructura anioEstructura = new AnioEstructura((long) Integer.parseInt(update.getId_entidad()),update.getValor());
                    daoSession.insert(anioEstructura);
                    break;
                case "RegimenTenencia":
                    RegimenTenencia regimenTenencia = new RegimenTenencia((long) Integer.parseInt(update.getId_entidad()),update.getValor());
                    daoSession.insert(regimenTenencia);
                    break;
                case "NivelInstruccion":
                    NivelInstruccion nivelInstruccion = new NivelInstruccion((long) Integer.parseInt(update.getId_entidad()),update.getValor());
                    daoSession.insert(nivelInstruccion);
                    break;
                case "Nacionalidad":
                    Nacionalidad nacionalidad = new Nacionalidad((long) Integer.parseInt(update.getId_entidad()),update.getValor());
                    daoSession.insert(nacionalidad);
                    break;
            }

        }
        SessionPrefs.get(MainActivity.this).saveLastUpdate(last_update);
        showProgress(false);



    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            llBodyContent.setVisibility(show ? View.GONE : View.VISIBLE);
            llBodyContent.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    llBodyContent.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressLayout.setVisibility(show ? View.VISIBLE : View.GONE);
            progressLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressLayout.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressLayout.setVisibility(show ? View.VISIBLE : View.GONE);
            llBodyContent.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }



    private void obtener_ultimas_actualizaciones(){
        Call<List<Updates>>  nuevasActualizaciones = webDatumApi.updates_posteriores("Token "+prefs.getString("PREF_USER_TOKEN",null),Integer.toString(update));
        nuevasActualizaciones.enqueue(new Callback<List<Updates>>() {
            @Override
            public void onResponse(Call<List<Updates>> call, Response<List<Updates>> response) {
                if(response.isSuccessful()){
                    List<Updates> lista = response.body();
                    procesar_actualizaciones(lista);
                }
            }

            @Override
            public void onFailure(Call<List<Updates>> call, Throwable t) {

            }
        });

    }

}
