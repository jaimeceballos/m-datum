package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.SplashScreenFragment;
import mdatum.udc.com.m_datum.database.Cultivo;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.MDatumDbHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class CultivoFragment extends Fragment {

    private Encuesta encuesta;

    private Button btnFinalizar;

    public CultivoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cultivo,container,false);

        encuesta = (Encuesta) getArguments().getSerializable("encuesta");

        Bundle bundle = new Bundle();
        bundle.putSerializable("encuesta",encuesta);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        NuevoCultivoFragment fragment = new NuevoCultivoFragment();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fl_cultivo_nuevo,fragment)
                .commit();




        btnFinalizar = (Button) rootView.findViewById(R.id.btn_cultivo_siguiente);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return rootView;
    }





}
