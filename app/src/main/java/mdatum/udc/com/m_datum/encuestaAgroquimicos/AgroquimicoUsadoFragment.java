package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.AgroquimicoUsado;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.Encuesta;


public class AgroquimicoUsadoFragment extends Fragment {

    private Encuesta encuesta;
    private EditText etProducto, etPlaga, etMetodoAplicacion,etFrecuenciaUso;
    private DaoSession daoSession;
    private Button btnAgregarOtro;
    public AgroquimicoUsadoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_agroquimico_usado, container, false);

        daoSession = ((MDatumController) getActivity().getApplication()).getDaoSession();
        encuesta = (Encuesta) getArguments().getSerializable("encuesta");

        etProducto          = (EditText) rootView.findViewById(R.id.et_producto);
        etPlaga             = (EditText) rootView.findViewById(R.id.et_plaga);
        etMetodoAplicacion  = (EditText) rootView.findViewById(R.id.et_metodo);
        etFrecuenciaUso     = (EditText) rootView.findViewById(R.id.et_frecuencia_de_uso);
        btnAgregarOtro      = (Button) rootView.findViewById(R.id.btn_guarda_y_agregar);

        btnAgregarOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgroquimicoUsado agroquimicoUsado = new AgroquimicoUsado();
                agroquimicoUsado.setProducto(etProducto.getText().toString());
                agroquimicoUsado.setPlaga(etPlaga.getText().toString());
                agroquimicoUsado.setFrecuencia_uso(etFrecuenciaUso.getText().toString());
                agroquimicoUsado.setMetodo_aplicacion(etMetodoAplicacion.getText().toString());
                agroquimicoUsado.setEncuestaId(encuesta.getId());
                new AgroquimicoUsadoFragment.AddAgroquimicoUsadoTask().execute(agroquimicoUsado);
            }
        });


        return rootView;
    }

    private class AddAgroquimicoUsadoTask extends AsyncTask<AgroquimicoUsado,Void,Boolean>{

        @Override
        protected Boolean doInBackground(AgroquimicoUsado... agroquimicoUsados) {
            long result = daoSession.insertOrReplace(agroquimicoUsados[0]);
            return result > 0;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            Bundle arguments = new Bundle();
            arguments.putSerializable("encuesta",encuesta);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            AgroquimicoUsadoFragment fragment = new AgroquimicoUsadoFragment();
            fragment.setArguments(arguments);
            fragmentTransaction.replace(R.id.cl_usado,fragment)
                    .commit();


        }
    }
}
