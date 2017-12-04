package mdatum.udc.com.m_datum;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mdatum.udc.com.m_datum.data.prefs.SessionPrefs;
import mdatum.udc.com.m_datum.sincronizacion.Updates;
import mdatum.udc.com.m_datum.sincronizacion.WebDatumApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private WebDatumApi webDatumApi;

    private SharedPreferences prefs;

    private final int DURACION_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        webDatumApi = ((MDatumController)getApplication()).getApiSession();

        prefs = getApplicationContext().getSharedPreferences("MDATUM_PREFS", Activity.MODE_PRIVATE);

        if(((MDatumController)getApplication()).isOnline() && SessionPrefs.get(this).isLoggedIn()){
            obtenerUltimaActualizacion();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            };
        },DURACION_SPLASH);

    }

    private void obtenerUltimaActualizacion(){
        Call<Updates> last_update = webDatumApi.last_update("Token "+prefs.getString("PREF_USER_TOKEN",null));
        last_update.enqueue(new Callback<Updates>() {
            @Override
            public void onResponse(Call<Updates> call, Response<Updates> response) {
                if(response.isSuccessful()){
                    Updates last = response.body();
                    SessionPrefs.get(getApplicationContext()).setPrefLastServerUpdate(Integer.parseInt(last.getId()));
                }
            }

            @Override
            public void onFailure(Call<Updates> call, Throwable t) {

            }
        });

    }
}
