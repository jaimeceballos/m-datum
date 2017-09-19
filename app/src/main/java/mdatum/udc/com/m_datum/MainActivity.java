package mdatum.udc.com.m_datum;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        SplashScreenFragment fragment = new SplashScreenFragment();
        fragmentTransaction.replace(R.id.ll_body_content, fragment);
        fragmentTransaction.commit();
    }
}
