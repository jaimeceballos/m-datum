package mdatum.udc.com.m_datum.encuestaAgroquimicos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.Encuesta;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProduccionFragment extends Fragment {

    private RadioButton rbCubiertoSi, rbCubiertoNo;
    private Button btnProduccionSiguiente;
    private Encuesta encuesta;

    public ProduccionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_produccion,container,false);

        encuesta = (Encuesta) getArguments().getSerializable("encuesta");
        rbCubiertoSi = (RadioButton) rootView.findViewById(R.id.rb_cubierto_si);
        rbCubiertoNo = (RadioButton) rootView.findViewById(R.id.rb_agroquimico_no);
        btnProduccionSiguiente = (Button) rootView.findViewById(R.id.btn_agroquimico_siguiente);

        rbCubiertoSi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(rbCubiertoSi.isChecked()){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("encuesta",encuesta);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    InvernaculoFragment fragment = new InvernaculoFragment();
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.cl_view_group_agroquimico, fragment).commit();

                }
            }
        });

        btnProduccionSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("encuesta",encuesta);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                CultivoFragment fragment = new CultivoFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.ll_body_content,fragment)
                        .addToBackStack("CULTIVO")
                        .commit();
            }
        });

        return rootView;
    }

}
