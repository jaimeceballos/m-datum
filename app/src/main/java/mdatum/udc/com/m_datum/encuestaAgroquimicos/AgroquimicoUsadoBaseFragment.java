package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.Encuesta;


public class AgroquimicoUsadoBaseFragment extends Fragment {

    private Encuesta encuesta;
    private Button btnFinalizar;

    public AgroquimicoUsadoBaseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_agroquimico_usado_base, container, false);
        btnFinalizar = (Button) rootView.findViewById(R.id.btn_finalizar);
        encuesta = (Encuesta) getArguments().getSerializable("encuesta");

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction finalTransaction = getFragmentManager().beginTransaction();
                FinalEncuestaFragment finalFragment = new FinalEncuestaFragment();
                finalTransaction.replace(R.id.ll_body_content,finalFragment)
                        .commit();
            }
        });


        Bundle arguments = new Bundle();
        arguments.putSerializable("encuesta",encuesta);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        AgroquimicoUsadoFragment fragment = new AgroquimicoUsadoFragment();
        fragment.setArguments(arguments);
        fragmentTransaction.replace(R.id.cl_usado,fragment)
                .commit();

        return rootView;
    }

}
