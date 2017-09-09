package mdatum.udc.com.m_datum;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mdatum.udc.com.m_datum.data.Encuesta;
import mdatum.udc.com.m_datum.encuestaAgroquimicos.EstablecimientoActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashScreen extends Fragment {
    Button btnEncuesta;
    Encuesta encuesta = new Encuesta();

    public SplashScreen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        btnEncuesta = (Button) rootView.findViewById(R.id.btn_encuesta);


        btnEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent establecimiento = new Intent(view.getContext(), EstablecimientoActivity.class);
                establecimiento.putExtra("encuesta",encuesta);
                startActivity(establecimiento);
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

}
