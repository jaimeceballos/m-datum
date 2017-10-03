package mdatum.udc.com.m_datum.encuestaAgroquimicos;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.data.Encuesta;
import mdatum.udc.com.m_datum.data.Invernaculo;
import mdatum.udc.com.m_datum.data.MDatumDbHelper;


public class InvernaculoFragment extends Fragment {

    Encuesta encuesta;
    EditText etCantModulos;
    EditText etSupUnit;
    Spinner spMatEstruct;
    Spinner spAnioConstruct;
    Button btnAgregarOtro;
    Invernaculo invernaculo;
    MDatumDbHelper mDatumDbHelper;
    ArrayList<String> opcionesMaterial;
    ArrayList<String> opcionesAnio;
    public InvernaculoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            encuesta = (Encuesta) getArguments().getSerializable("encuesta");
        }
        mDatumDbHelper = new MDatumDbHelper(getContext());

        opcionesMaterial = mDatumDbHelper.getAllEstructura();
        opcionesAnio = mDatumDbHelper.getAllAnioEstructura();





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_invernaculo, container, false);

        etCantModulos = (EditText) rootView.findViewById(R.id.et_cant_modulos);
        etSupUnit = (EditText) rootView.findViewById(R.id.et_sup_unit);
        spMatEstruct = (Spinner) rootView.findViewById(R.id.sp_mat_estruct);
        spAnioConstruct = (Spinner) rootView.findViewById(R.id.sp_anio_construct);

        ArrayAdapter<String> materialAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,opcionesMaterial);
        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spMatEstruct.setAdapter(materialAdapter);

        ArrayAdapter<String> anioEstructuraAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,opcionesAnio);
        anioEstructuraAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spAnioConstruct.setAdapter(anioEstructuraAdapter);

        btnAgregarOtro = (Button) rootView.findViewById(R.id.btn_agregar_otro);
        btnAgregarOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invernaculo = new Invernaculo();

                invernaculo.setCantidadModulos((Integer.parseInt(etCantModulos.getText().toString())));
                invernaculo.setSuperficieUnitaria(Integer.parseInt(etSupUnit.getText().toString()));
                invernaculo.setMaterialEstructuraId(spMatEstruct.getSelectedItemPosition());
                invernaculo.setAnioConstruccionId(spAnioConstruct.getSelectedItemPosition());
                mDatumDbHelper = new MDatumDbHelper(getContext());

                Toast savingToast = Toast.makeText(getContext(), "Guardando los datos.", Toast.LENGTH_SHORT);

                savingToast.show();
                new AddInvernaculoTask().execute(invernaculo);





            }
        });



        return rootView;
    }


    private class AddInvernaculoTask extends AsyncTask<Invernaculo,Void,Boolean> {

        @Override
        protected Boolean doInBackground(Invernaculo... invernaculos) {
            long result = mDatumDbHelper.saveInvernaculo(invernaculos[0]);
            invernaculos[0].setId((int)result);
            encuesta.setInvernaderoId((int)result);
            return result > 0;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("encuesta",encuesta);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            InvernaculoFragment fragment = new InvernaculoFragment();
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.cl_view_group_agroquimico, fragment);
            fragmentTransaction.commit();
        }
    }
}
