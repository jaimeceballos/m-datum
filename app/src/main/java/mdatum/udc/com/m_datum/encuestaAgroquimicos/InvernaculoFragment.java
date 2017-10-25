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
import java.util.Iterator;
import java.util.List;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.AnioEstructura;
import mdatum.udc.com.m_datum.database.AnioEstructuraDao;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.Invernaculo;
import mdatum.udc.com.m_datum.database.MDatumDbHelper;
import mdatum.udc.com.m_datum.database.MaterialEstructura;
import mdatum.udc.com.m_datum.database.MaterialEstructuraDao;


public class InvernaculoFragment extends Fragment {

    private Encuesta encuesta;
    private EditText etCantModulos;
    private EditText etSupUnit;
    private Spinner spMatEstruct;
    private Spinner spAnioConstruct;
    private Button btnAgregarOtro;
    private Invernaculo invernaculo;
    private List opcionesMaterial;
    private List opcionesAnio;

    private DaoSession daoSession;
    private List<String> materiales;
    private List<String> anios;


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

        daoSession = ((MDatumController)getActivity().getApplication()).getDaoSession();

        MaterialEstructuraDao materialEstructuraDao = daoSession.getMaterialEstructuraDao();
        opcionesMaterial = materialEstructuraDao.loadAll();
        materiales = new ArrayList<>();
        Iterator<MaterialEstructura> materialEstructuraIterator = opcionesMaterial.iterator();
        while (materialEstructuraIterator.hasNext()){
            materiales.add(materialEstructuraIterator.next().getDescripcion());
        }

        AnioEstructuraDao anioEstructuraDao = daoSession.getAnioEstructuraDao();
        opcionesAnio = anioEstructuraDao.loadAll();
        anios = new ArrayList<>();
        Iterator<AnioEstructura> anioEstructuraIterator = opcionesAnio.iterator();
        while (anioEstructuraIterator.hasNext()){
            anios.add(anioEstructuraIterator.next().getDescripcion());
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_invernaculo, container, false);

        etCantModulos = (EditText) rootView.findViewById(R.id.et_cant_modulos);
        etSupUnit = (EditText) rootView.findViewById(R.id.et_superficie);
        spMatEstruct = (Spinner) rootView.findViewById(R.id.sp_mat_estruct);
        spAnioConstruct = (Spinner) rootView.findViewById(R.id.sp_anio_construct);

        ArrayAdapter<String> materialAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,materiales);
        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spMatEstruct.setAdapter(materialAdapter);

        ArrayAdapter<String> anioEstructuraAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,anios);
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
            long result = daoSession.insert(invernaculos[0]);
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
