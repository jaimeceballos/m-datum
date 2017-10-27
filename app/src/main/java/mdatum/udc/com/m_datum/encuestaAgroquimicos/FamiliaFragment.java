package mdatum.udc.com.m_datum.encuestaAgroquimicos;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.Familia;
import mdatum.udc.com.m_datum.database.MDatumDbHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamiliaFragment extends Fragment {

    private RadioButton rbHijosSi, rbHijosNo,rbEsposaSi,rbEsposaNo;
    private TextInputLayout tilCantVarones, tilCantMujeres;
    private EditText etCantVarones, etCantMujeres;
    private Button btnFliaSiguiente;
    private Encuesta encuesta;
    private DaoSession daoSession;
    private Familia familia;

    public FamiliaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_familia,container,false);

        encuesta        =  (Encuesta) getArguments().getSerializable("encuesta");
        rbHijosSi = (RadioButton) rootView.findViewById(R.id.rb_hijos_si);
        rbHijosNo = (RadioButton) rootView.findViewById(R.id.rb_hijos_no);
        tilCantVarones = (TextInputLayout) rootView.findViewById(R.id.til_cant_varones);
        etCantVarones = (EditText) rootView.findViewById(R.id.et_cant_varones);
        tilCantMujeres = (TextInputLayout) rootView.findViewById(R.id.til_cant_mujeres);
        etCantMujeres = (EditText) rootView.findViewById(R.id.et_cant_mujeres);
        rbEsposaSi = (RadioButton) rootView.findViewById(R.id.rb_esposa_si);
        rbEsposaNo = (RadioButton) rootView.findViewById(R.id.rb_esposa_no);

        daoSession = ((MDatumController)getActivity().getApplication()).getDaoSession();

        btnFliaSiguiente = (Button) rootView.findViewById(R.id.btn_flia_siguiente);

        rbHijosSi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(rbHijosSi.isChecked()){
                    tilCantVarones.setVisibility(View.VISIBLE);
                    etCantVarones.setVisibility(View.VISIBLE);
                    tilCantMujeres.setVisibility(View.VISIBLE);
                    etCantMujeres.setVisibility(View.VISIBLE);
                }
            }
        });

        rbHijosNo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(rbHijosNo.isChecked()){
                    tilCantVarones.setVisibility(View.GONE);
                    etCantVarones.setVisibility(View.GONE);
                    tilCantMujeres.setVisibility(View.GONE);
                    etCantMujeres.setVisibility(View.GONE);
                }
            }
        });

        btnFliaSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                familia = new Familia();
                familia.setEsCasado((rbEsposaSi.isChecked())?1:0);
                familia.setTieneHijos((rbHijosSi.isChecked())?1:0);
                familia.setCantMujeres(Integer.parseInt(etCantMujeres.getText().toString()));
                familia.setCantVarones(Integer.parseInt(etCantVarones.getText().toString()));

                new FamiliaFragment.AddFamiliaTask().execute(familia);
            }
        });



        return rootView;
    }

    private class AddFamiliaTask extends AsyncTask<Familia,Void,Boolean> {

        @Override
        protected Boolean doInBackground(Familia... familia) {
            long result = daoSession.insert(familia[0]);
            encuesta.setFamiliaId(result);
            daoSession.update(encuesta);
            return result > 0;
        }

        @Override
        protected void onPostExecute(Boolean result){
            Bundle bundle = new Bundle();
            bundle.putSerializable("encuesta",encuesta);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            ProduccionFragment fragment = new ProduccionFragment();
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.ll_body_content,fragment)
                    .commit();
        }


    }

}
