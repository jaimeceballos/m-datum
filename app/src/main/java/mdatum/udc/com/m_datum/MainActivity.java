package mdatum.udc.com.m_datum;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.api.Api;

import mdatum.udc.com.m_datum.data.prefs.SessionPrefs;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.encuestaAgroquimicos.EstablecimientoFragment;
import mdatum.udc.com.m_datum.sincronizacion.ApiError;
import mdatum.udc.com.m_datum.sincronizacion.PasswordChangeBody;
import mdatum.udc.com.m_datum.sincronizacion.PasswordChangeFragment;
import mdatum.udc.com.m_datum.sincronizacion.WebDatumApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Encuesta encuesta = new Encuesta();

    private WebDatumApi webDatumApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webDatumApi = ((MDatumController)getApplication()).getApiSession();

        //redireccion  al login
        if(!SessionPrefs.get(this).isLoggedIn()){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
            return;
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
}
