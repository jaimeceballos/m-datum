package mdatum.udc.com.m_datum;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mdatum.udc.com.m_datum.data.Encuesta;
import mdatum.udc.com.m_datum.encuestaAgroquimicos.EstablecimientoActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        SplashScreen fragment = new SplashScreen();
        fragmentTransaction.replace(R.id.ll_body_content, fragment);
        fragmentTransaction.commit();
    }
}
