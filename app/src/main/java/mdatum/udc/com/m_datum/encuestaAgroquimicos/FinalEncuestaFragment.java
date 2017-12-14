package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.MainActivity;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.EncuestaDao;

public class FinalEncuestaFragment extends Fragment {

    private Button btnCerrar,btnNuevaEncuesta;

    private Encuesta encuesta;
    DaoSession mDaosession;

    public FinalEncuestaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_final_encuesta, container, false);

        mDaosession = ((MDatumController)getActivity().getApplication()).getDaoSession();
        EncuestaDao encuestaDao = mDaosession.getEncuestaDao();
        encuesta = (Encuesta) getArguments().getSerializable("encuesta");
        encuesta.setIs_finished(true);
        encuestaDao.update(encuesta);
        btnCerrar = (Button) rootView.findViewById(R.id.btn_cerrar);
        btnNuevaEncuesta = (Button) rootView.findViewById(R.id.btn_nueva_encuesta);



        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        btnNuevaEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
