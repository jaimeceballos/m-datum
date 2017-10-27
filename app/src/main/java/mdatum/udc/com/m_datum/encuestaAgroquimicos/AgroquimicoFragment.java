package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.Agroquimicos;
import mdatum.udc.com.m_datum.database.Asesoramiento;
import mdatum.udc.com.m_datum.database.AsesoramientoDao;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.FactorClimatico;
import mdatum.udc.com.m_datum.database.FactorClimaticoDao;
import mdatum.udc.com.m_datum.database.TripleLavado;
import mdatum.udc.com.m_datum.database.TripleLavadoDao;


public class AgroquimicoFragment extends Fragment {

    private Encuesta encuesta;
    private DaoSession daoSession;
    private ConstraintLayout clUsa;
    private TextInputLayout tilAsesoramientoOtro;
    private RadioButton rbAgroquimicoSi;
    private RadioButton rbAgroquimicoNo;
    private Spinner spFactorClimatico;
    private Spinner spTripleLavado;
    private Spinner spAsesoramiento;
    private List factorClimaticoList;
    private ArrayList<String> opcionesClima;
    private List tripleLavadoList;
    private ArrayList<String> opcionesTripleLavado;
    private List asesoramientoList;
    private ArrayList<String> opcionesAsesoramiento;
    private EditText etAsesoramientoOtro;
    private Button btnSiguiente;


    public AgroquimicoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_agroquimico, container, false);

        encuesta                = (Encuesta) getArguments().getSerializable("encuesta");
        daoSession              = ((MDatumController)getActivity().getApplication()).getDaoSession();

        clUsa                   = (ConstraintLayout) rootView.findViewById(R.id.cl_usa);
        tilAsesoramientoOtro    = (TextInputLayout) rootView.findViewById(R.id.til_asesoramiento_otro);

        clUsa.setVisibility(View.GONE);
        tilAsesoramientoOtro.setVisibility(View.GONE);

        rbAgroquimicoSi = (RadioButton) rootView.findViewById(R.id.rb_agroquimico_si);
        rbAgroquimicoNo = (RadioButton) rootView.findViewById(R.id.rb_agroquimico_no);

        rbAgroquimicoSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbAgroquimicoSi.isChecked()){
                    clUsa.setVisibility(View.VISIBLE);
                }
            }
        });

        rbAgroquimicoNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbAgroquimicoNo.isChecked()){
                    clUsa.setVisibility(View.GONE);
                }
            }
        });

        spFactorClimatico                                   = (Spinner) rootView.findViewById(R.id.sp_factor_climatico);
        FactorClimaticoDao factorClimaticoDao               = daoSession.getFactorClimaticoDao();
        factorClimaticoList                                 = factorClimaticoDao.loadAll();
        Iterator<FactorClimatico> factorClimaticoIterator   = factorClimaticoList.iterator();
        opcionesClima                                       = new ArrayList<>();
        while (factorClimaticoIterator.hasNext()){
            opcionesClima.add(factorClimaticoIterator.next().getDescripcion());
        }
        ArrayAdapter<String> adapterClima = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,opcionesClima);
        adapterClima.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFactorClimatico.setAdapter(adapterClima);


        spTripleLavado                                  = (Spinner) rootView.findViewById(R.id.sp_triple_lavado);
        TripleLavadoDao tripleLavadoDao                 = daoSession.getTripleLavadoDao();
        tripleLavadoList                                = tripleLavadoDao.loadAll();
        Iterator<TripleLavado> tripleLavadoIterator     = tripleLavadoList.iterator();
        opcionesTripleLavado                            = new ArrayList<>();
        while (tripleLavadoIterator.hasNext()){
            opcionesTripleLavado.add(tripleLavadoIterator.next().getDescripcion());
        }
        ArrayAdapter<String> adapterTripleLavado = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,opcionesTripleLavado);
        adapterTripleLavado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTripleLavado.setAdapter(adapterTripleLavado);


        spAsesoramiento                                 = (Spinner) rootView.findViewById(R.id.sp_asesoramiento);
        AsesoramientoDao asesoramientoDao               = daoSession.getAsesoramientoDao();
        asesoramientoList                               = asesoramientoDao.loadAll();
        Iterator<Asesoramiento> asesoramientoIterator   = asesoramientoList.iterator();
        opcionesAsesoramiento                           = new ArrayList<>();
        while (asesoramientoIterator.hasNext()){
            opcionesAsesoramiento.add(asesoramientoIterator.next().getDescripcion());
        }
        ArrayAdapter<String> adapterAsesoramiento = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,opcionesAsesoramiento);
        adapterAsesoramiento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAsesoramiento.setAdapter(adapterAsesoramiento);
        spAsesoramiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("Otro")){
                    tilAsesoramientoOtro.setVisibility(View.VISIBLE);
                }else{
                    tilAsesoramientoOtro.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etAsesoramientoOtro = (EditText) rootView.findViewById(R.id.et_asesoramiento_otro);

        btnSiguiente = (Button) rootView.findViewById(R.id.btn_agroquimico_siguiente);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle argumentos = new Bundle();
                argumentos.putSerializable("encuesta",encuesta);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                if(rbAgroquimicoNo.isChecked()){
                    FinalEncuestaFragment finalEncuestaFragment = new FinalEncuestaFragment();
                    finalEncuestaFragment.setArguments(argumentos);
                    fragmentTransaction.replace(R.id.ll_body_content,finalEncuestaFragment)
                            .commit();
                }else{
                    Agroquimicos agroquimicos = new Agroquimicos();
                    agroquimicos.setUsa(true);
                    agroquimicos.setFactor_climatico(spFactorClimatico.getSelectedItemId());
                    agroquimicos.setTriple_lavado(spTripleLavado.getSelectedItemId());
                    agroquimicos.setAsesoramiento(spAsesoramiento.getSelectedItemId());
                    agroquimicos.setAsesoramiento_otro(etAsesoramientoOtro.getText().toString());
                    new AgroquimicoFragment.AddAgroquimicoTask().execute(agroquimicos);
                }

            }
        });

        return rootView;
    }


    private class AddAgroquimicoTask extends  AsyncTask<Agroquimicos,Void,Boolean>{

        @Override
        protected Boolean doInBackground(Agroquimicos... agroquimicos) {
            Long result = daoSession.insert(agroquimicos[0]);
            encuesta.setAgroquimicoId(result);
            daoSession.update(encuesta);
            return result > 0;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("encuesta",encuesta);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            AgroquimicoUsadoBaseFragment fragment = new AgroquimicoUsadoBaseFragment();
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.ll_body_content,fragment)
                    .commit();
        }
    }


}
