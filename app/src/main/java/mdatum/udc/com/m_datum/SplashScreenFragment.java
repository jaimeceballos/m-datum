package mdatum.udc.com.m_datum;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mdatum.udc.com.m_datum.data.Encuesta;
import mdatum.udc.com.m_datum.encuestaAgroquimicos.EstablecimientoFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashScreenFragment extends Fragment {
    Button btnEncuesta;
    Encuesta encuesta = new Encuesta();

    public SplashScreenFragment() {
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
                Bundle bundle = new Bundle();
                bundle.putSerializable("encuesta",encuesta);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                EstablecimientoFragment fragment = new EstablecimientoFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.ll_body_content,fragment)
                        .commit();
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }

}
