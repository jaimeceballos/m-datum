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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.data.Encuesta;
import mdatum.udc.com.m_datum.data.Encuestado;
import mdatum.udc.com.m_datum.data.MDatumDbHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class EncuestadoFragment extends Fragment {

    private Encuesta encuesta;
    private Encuestado encuestado;
    private Spinner spNivInstruccion;
    private Button btnEncuestadoSiguiente;
    private RadioButton rbHabita;
    private RadioButton rbNoHabita;
    private EditText etApellido;
    private EditText etNombre;
    private EditText etEdad;
    private Spinner spNacionalidad;
    private RadioButton rbCompleto;
    private RadioButton rbIncompleto;
    private MDatumDbHelper mDatumDbHelper;
    private Boolean isHabitaChecked = false;
    private ArrayList<String> opcionesInstruccion;
    private ArrayList<String> opcionesNacionalidad;


    public EncuestadoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_encuestado,container,false);

        encuesta = (Encuesta) getArguments().getSerializable("encuesta");
        rbHabita = (RadioButton) rootView.findViewById(R.id.rb_habita);
        rbNoHabita = (RadioButton) rootView.findViewById(R.id.rb_no_habita);
        etApellido = (EditText) rootView.findViewById(R.id.et_apellido_encuestado);
        etNombre = (EditText) rootView.findViewById(R.id.et_nombre_encuestado);
        etEdad = (EditText) rootView.findViewById(R.id.et_edad_encuestado);
        spNacionalidad = (Spinner) rootView.findViewById(R.id.sp_nacionalidad);
        rbCompleto = (RadioButton) rootView.findViewById(R.id.rb_completo);
        rbIncompleto = (RadioButton) rootView.findViewById(R.id.rb_incompleto);
        spNivInstruccion = (Spinner) rootView.findViewById(R.id.sp_niv_instruccion);
        mDatumDbHelper = new MDatumDbHelper(getContext());
        opcionesInstruccion = mDatumDbHelper.getAllNivelInstruccion();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, opcionesInstruccion);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNivInstruccion.setAdapter(adapter);

        opcionesNacionalidad = mDatumDbHelper.getAllNacionalidad();
        ArrayAdapter<String> adapterNacionalidad = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,opcionesNacionalidad);
        adapterNacionalidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNacionalidad.setAdapter(adapterNacionalidad);



        mDatumDbHelper = new MDatumDbHelper(getContext());
        btnEncuestadoSiguiente = (Button) rootView.findViewById(R.id.btn_encuestado_siguiente);

        btnEncuestadoSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  validarDatos();
                encuestado = new Encuestado();
                encuestado.setNombre(etNombre.getText().toString());
                encuestado.setApellido(etApellido.getText().toString());
                encuestado.setEdad(Integer.parseInt(etEdad.getText().toString()));
                encuestado.setNacionalidadId((int) spNacionalidad.getSelectedItemId());
                encuestado.setNivelInstruccionId((int)spNivInstruccion.getSelectedItemId());
                if (rbHabita.isChecked()) {
                    encuestado.setViveEstablecimiento(true);
                    isHabitaChecked = true;
                } else if (rbNoHabita.isChecked()) {
                    encuestado.setViveEstablecimiento(false);
                    isHabitaChecked = true;
                }
                if (rbCompleto.isChecked()) {
                    encuestado.setNivelCompleto(true);
                } else if (rbIncompleto.isChecked()) {
                    encuestado.setNivelCompleto(false);
                }
                if (isHabitaChecked) {
                    Toast savingToast = Toast.makeText(getContext(), "Guardando los datos.", Toast.LENGTH_SHORT);

                    savingToast.show();
                    new EncuestadoFragment.AddEncuestadoTask().execute(encuestado);
                } else {
                    Toast errorToast = Toast.makeText(getContext(), "Debe indicar si vive en el establecimiento o no.", Toast.LENGTH_SHORT);

                    errorToast.show();
                }

            }
        });

        return rootView;
    }

    private class AddEncuestadoTask extends AsyncTask<Encuestado, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Encuestado... encuestado) {
            long result = mDatumDbHelper.saveEncuestado(encuestado[0]);
            encuestado[0].setId((int) result);
            encuesta.setEncuestadoId((int) result);
            return result > 0;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            //Intent intent;
            Bundle bundle = new Bundle();
            bundle.putSerializable("encuesta",encuesta);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            if (encuestado.getViveEstablecimiento()) {
                //intent = new Intent(getContext(), FamiliaActivity.class);
                FamiliaFragment fragment = new FamiliaFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.ll_body_content,fragment)
                        .commit();

            } else {
                //intent = new Intent(getContext(), ProduccionActivity.class);
                ProduccionFragment fragment = new ProduccionFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.ll_body_content,fragment)
                        .commit();

            }

        }

    }
}
