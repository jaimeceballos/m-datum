package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.Encuesta;


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
                Bundle argumentos = new Bundle();
                argumentos.putSerializable("encuesta",encuesta);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AgroquimicoFragment fragmento = new AgroquimicoFragment();
                fragmento.setArguments(argumentos);
                transaction.replace(R.id.ll_body_content,fragmento)
                        .addToBackStack("AGROQUIMICO")
                        .commit();

            }
        });


        return rootView;
    }





}
