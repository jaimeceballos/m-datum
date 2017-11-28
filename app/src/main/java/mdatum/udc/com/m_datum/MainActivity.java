package mdatum.udc.com.m_datum;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mdatum.udc.com.m_datum.data.prefs.SessionPrefs;
import mdatum.udc.com.m_datum.database.AnioEstructura;
import mdatum.udc.com.m_datum.database.Asesoramiento;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.EleccionCultivo;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.Especie;
import mdatum.udc.com.m_datum.database.FactorClimatico;
import mdatum.udc.com.m_datum.database.MaterialEstructura;
import mdatum.udc.com.m_datum.database.Nacionalidad;
import mdatum.udc.com.m_datum.database.NivelInstruccion;
import mdatum.udc.com.m_datum.database.RegimenTenencia;
import mdatum.udc.com.m_datum.database.TipoCultivo;
import mdatum.udc.com.m_datum.database.TipoProduccion;
import mdatum.udc.com.m_datum.database.TripleLavado;
import mdatum.udc.com.m_datum.encuestaAgroquimicos.EstablecimientoFragment;
import mdatum.udc.com.m_datum.sincronizacion.ApiError;
import mdatum.udc.com.m_datum.sincronizacion.PasswordChangeBody;
import mdatum.udc.com.m_datum.sincronizacion.PasswordChangeFragment;
import mdatum.udc.com.m_datum.sincronizacion.Updates;
import mdatum.udc.com.m_datum.sincronizacion.WebDatumApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Encuesta encuesta = new Encuesta();

    private WebDatumApi webDatumApi;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webDatumApi = ((MDatumController)getApplication()).getApiSession();

        daoSession  = ((MDatumController)getApplication()).getDaoSession();

        //redireccion  al login
        if(!SessionPrefs.get(this).isLoggedIn()){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
            return;
        }
        int update = SessionPrefs.get(this).getLastUpdate();
        if(update == 0){
            inicializarBasedeDatos();

        }

        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        SplashScreenFragment fragment = new SplashScreenFragment();
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
            case R.id.mNueva:

                Bundle bundle = new Bundle();
                bundle.putSerializable("encuesta",encuesta);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                EstablecimientoFragment fragment = new EstablecimientoFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.ll_body_content,fragment).addToBackStack("SPLASH_SCREEN")
                        .commit();
                break;
            case R.id.mSincro:
            case R.id.change_pass_menu:
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                PasswordChangeFragment passwordChangeFragment = new PasswordChangeFragment();
                fragmentTransaction1.replace(R.id.ll_body_content,passwordChangeFragment).addToBackStack("CHANGE_PASS").commit();
                break;
            case R.id.log_out_menu:
               cerrarSesion();
                break;
        }


        return super.onOptionsItemSelected(item);
    }





    private void cerrarSesion(){
        Call<Void> logoutCall = webDatumApi.logout();
        logoutCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                SessionPrefs.get(MainActivity.this).logOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void inicializarBasedeDatos(){

        final SharedPreferences prefs = getApplicationContext().getSharedPreferences("MDATUM_PREFS", Activity.MODE_PRIVATE);

        Call<List<Updates>> updatesCall = webDatumApi.updates("Token "+prefs.getString("PREF_USER_TOKEN",null));
        updatesCall.enqueue(new Callback<List<Updates>>() {
            @Override
            public void onResponse(Call<List<Updates>> call, Response<List<Updates>> response) {
                if(response.isSuccessful()){
                    List<Updates> lista =  response.body();
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
                    Log.d("SUCCESS",response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Updates>> call, Throwable t) {
                Log.e("ERROR DETECTADO",t.toString());
            }
        });
    }
}
