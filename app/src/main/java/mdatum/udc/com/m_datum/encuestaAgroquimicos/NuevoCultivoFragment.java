package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.data.prefs.SessionPrefs;
import mdatum.udc.com.m_datum.database.Cultivo;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.EleccionCultivo;
import mdatum.udc.com.m_datum.database.EleccionCultivoDao;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.Especie;
import mdatum.udc.com.m_datum.database.EspecieDao;
import mdatum.udc.com.m_datum.database.TipoCultivo;
import mdatum.udc.com.m_datum.database.TipoCultivoDao;
import mdatum.udc.com.m_datum.database.TipoProduccion;
import mdatum.udc.com.m_datum.database.TipoProduccionDao;
import mdatum.udc.com.m_datum.database.UpdatesToServer;

public class NuevoCultivoFragment extends Fragment {

    private Encuesta encuesta;
    private Spinner spEspecie;
    private Spinner spTipoCultivo;
    private EditText etNroSiembra;
    private EditText etMesSiembra;
    private EditText etSurcos;
    private EditText etDistancias;
    private EditText etLargo;
    private Spinner spTipoProduccion;
    private Spinner spEleccionCultivo;
    private Spinner spMesSiembra;
    private EditText etEleccionEspecificar;
    private DaoSession daoSession;
    private List opcionesEspecie;
    private List opcionesTipoCultivo;
    private List opcionesTipoProduccion;
    private List opcionesEleccionCultivo;
    private Button btnGuardar;
    private Cultivo cultivo;
    private List<String> especies;
    private EditText etEspecieNueva;
    private TextInputLayout tilEspecieNueva;
    private List<String> tiposCultivo;
    private List<String> tiposProduccion;
    private List<String> eleccionCultivo;
    private List<String> meses;

    private boolean haveUpdate = false;

    private View focusView;

    public NuevoCultivoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_nuevo_cultivo, container, false);

        daoSession = ((MDatumController)getActivity().getApplication()).getDaoSession();

        focusView = null;

        encuesta = (Encuesta) getArguments().getSerializable("encuesta");
        spEspecie = (Spinner) rootView.findViewById(R.id.sp_especie);
        tilEspecieNueva = (TextInputLayout) rootView.findViewById(R.id.til_nueva_especie);
        etEspecieNueva = (EditText) rootView.findViewById(R.id.et_nueva_especie);
        spTipoCultivo = (Spinner) rootView.findViewById(R.id.sp_tipo_cultivo);
        etNroSiembra = (EditText) rootView.findViewById(R.id.et_nro_siembra);
        //etMesSiembra = (EditText) rootView.findViewById(R.id.et_mes_siembra);
        etSurcos = (EditText) rootView.findViewById(R.id.et_surcos);
        etDistancias = (EditText) rootView.findViewById(R.id.et_distancias);
        etLargo = (EditText) rootView.findViewById(R.id.et_largo);
        spTipoProduccion = (Spinner) rootView.findViewById(R.id.sp_tipo_produccion);
        spEleccionCultivo = (Spinner) rootView.findViewById(R.id.sp_eleccion_cultivo);
        etEleccionEspecificar = (EditText) rootView.findViewById(R.id.et_eleccion_especificar);
        spMesSiembra = (Spinner) rootView.findViewById(R.id.sp_mes_siembra);
        meses = generarMeses();
        ArrayAdapter<String> mesesAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,meses);
        mesesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spMesSiembra.setAdapter(mesesAdapter);

        EspecieDao especieDao = daoSession.getEspecieDao();
        opcionesEspecie = especieDao.loadAll();
        especies = new ArrayList<>();
        Iterator<Especie> especieIterator = opcionesEspecie.iterator();
        while(especieIterator.hasNext()){
            especies.add(especieIterator.next().getDescripcion());
        }
        ArrayAdapter<String> especieAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,especies);
        especieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spEspecie.setAdapter(especieAdapter);

        spEspecie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("OTRO")){
                    tilEspecieNueva.setVisibility(View.VISIBLE);
                }else{
                    tilEspecieNueva.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        TipoCultivoDao tipoCultivoDao = daoSession.getTipoCultivoDao();
        opcionesTipoCultivo = tipoCultivoDao.loadAll();
        Iterator<TipoCultivo> tipoCultivoIterator = opcionesTipoCultivo.iterator();
        tiposCultivo = new ArrayList<>();
        while (tipoCultivoIterator.hasNext()){
            tiposCultivo.add(tipoCultivoIterator.next().getDescripcion());
        }
        ArrayAdapter<String> tipoCultivoAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,tiposCultivo);
        tipoCultivoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spTipoCultivo.setAdapter(tipoCultivoAdapter);

        TipoProduccionDao tipoProduccionDao = daoSession.getTipoProduccionDao();
        opcionesTipoProduccion = tipoProduccionDao.loadAll();
        Iterator<TipoProduccion> tipoProduccionIterator = opcionesTipoProduccion.iterator();
        tiposProduccion = new ArrayList<>();
        while (tipoProduccionIterator.hasNext()){
            tiposProduccion.add(tipoProduccionIterator.next().getDescripcion());
        }
        ArrayAdapter<String> tipoProduccionAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,tiposProduccion);
        tipoProduccionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spTipoProduccion.setAdapter(tipoProduccionAdapter);

        EleccionCultivoDao eleccionCultivoDao = daoSession.getEleccionCultivoDao();
        opcionesEleccionCultivo = eleccionCultivoDao.loadAll();
        Iterator<EleccionCultivo> eleccionCultivoIterator = opcionesEleccionCultivo.iterator();
        eleccionCultivo = new ArrayList<>();
        while (eleccionCultivoIterator.hasNext()){
            eleccionCultivo.add(eleccionCultivoIterator.next().getDescripcion());
        }
        ArrayAdapter<String> eleccionCultivoAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,eleccionCultivo);
        eleccionCultivoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spEleccionCultivo.setAdapter(eleccionCultivoAdapter);
        etEleccionEspecificar.setVisibility(View.GONE);
        spEleccionCultivo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if(parent.getItemAtPosition(position).toString().equals("OTRO")) {

                    etEleccionEspecificar.setVisibility(View.VISIBLE);
                }else{
                    etEleccionEspecificar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnGuardar = (Button) rootView.findViewById(R.id.btn_agregar_otro);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarDatos()) {
                    cultivo = new Cultivo();
                    cultivo.setEspecieId((int) spEspecie.getSelectedItemId() + 1);
                    cultivo.setTipoId((int) spTipoCultivo.getSelectedItemId() + 1);
                    cultivo.setNueva_especie(etEspecieNueva.getText().toString());
                    cultivo.setNroSiembra(Integer.parseInt(etNroSiembra.getText().toString()));
                    cultivo.setMesSiembra((int) spMesSiembra.getSelectedItemId() + 1);
                    cultivo.setSurcos(Integer.parseInt(etSurcos.getText().toString()));
                    cultivo.setDistancias(Integer.parseInt(etDistancias.getText().toString()));
                    cultivo.setLargo(Integer.parseInt(etLargo.getText().toString()));
                    cultivo.setTipoProduccionId((int) spTipoProduccion.getSelectedItemId() + 1);
                    cultivo.setEleccionCultivoId((int) spEleccionCultivo.getSelectedItemId() + 1);
                    cultivo.setEleccionEspecificar(etEleccionEspecificar.getText().toString());
                    cultivo.setEncuestaId(encuesta.getId());
                    if (spEspecie.getItemAtPosition(spEspecie.getSelectedItemPosition()).toString().equals("OTRO") && etEspecieNueva.getText().toString().trim().isEmpty()) {
                        etEspecieNueva.setError("Campo Requerido");
                        focusView = etEspecieNueva;
                        focusView.requestFocus();
                        return;

                    } else if (spEleccionCultivo.getItemAtPosition(spEleccionCultivo.getSelectedItemPosition()).equals("OTRO") && etEleccionEspecificar.getText().toString().trim().isEmpty()) {
                        etEleccionEspecificar.setError("Campo requerido.");
                        focusView = etEleccionEspecificar;
                        focusView.requestFocus();
                        return;
                    } else {
                        Toast savingToast = Toast.makeText(getContext(), "Guardando los datos.", Toast.LENGTH_SHORT);
                        savingToast.show();
                        if(!etEspecieNueva.getText().toString().trim().isEmpty()){
                            haveUpdate = true;
                        }
                        new NuevoCultivoFragment.AddCultivoTask().execute(cultivo);
                    }
                }
            }
        });

        return rootView;
    }


    private class AddCultivoTask extends AsyncTask<Cultivo, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Cultivo... cultivos) {
            long result = daoSession.insertOrReplace(cultivos[0]);
            if(haveUpdate){
                UpdatesToServer updatesToServer = new UpdatesToServer();
                updatesToServer.setEntidad("Cultivo");
                updatesToServer.setValor(cultivos[0].getNueva_especie());
                updatesToServer.setUsuario(SessionPrefs.get(getContext()).getUserLoged());
                daoSession.insert(updatesToServer);
            }
            return result > 0;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("encuesta",encuesta);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            NuevoCultivoFragment fragment = new NuevoCultivoFragment();
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fl_cultivo_nuevo,fragment)
                    .commit();

        }
    }

    private ArrayList<String> generarMeses(){

        ArrayList<String> meses = new ArrayList<>();
        meses.add("Enero");
        meses.add("Febrero");
        meses.add("Marzo");
        meses.add("Abril");
        meses.add("Mayo");
        meses.add("Junio");
        meses.add("Julio");
        meses.add("Agosto");
        meses.add("Septiembre");
        meses.add("Octubre");
        meses.add("Noviembre");
        meses.add("Diciembre");
        return meses;
    }


    private boolean validarDatos(){

        if(!(validarNroSiembra() && validarCantSurcos() && validarLargo() && validarDistancias())){
            focusView.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validarNroSiembra(){

        if(etNroSiembra.getText().toString().trim().isEmpty()){
            etNroSiembra.setError("Campo requerido");
            focusView = etNroSiembra;
            return false;
        }

        return true;
    }

    private boolean validarCantSurcos(){
        if(etSurcos.getText().toString().trim().isEmpty()){
            etSurcos.setError("Campo Requerido.");
            focusView = etSurcos;
            return false;
        }
        return true;
    }

    private boolean validarLargo(){
        if(etLargo.getText().toString().trim().isEmpty()){
            etLargo.setError("Campo Requerido.");
            focusView = etLargo;
            return false;
        }
        return true;
    }

    private boolean validarDistancias(){
        if(etDistancias.getText().toString().trim().isEmpty()){
            etDistancias.setError("Campo Requerido.");
            focusView = etDistancias;
            return false;
        }
        return true;
    }

}