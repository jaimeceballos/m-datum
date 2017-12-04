package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.EncuestaDao;
import mdatum.udc.com.m_datum.database.EncuestaEstablecimiento;
import mdatum.udc.com.m_datum.database.Establecimiento;
import mdatum.udc.com.m_datum.database.EstablecimientoDao;
import mdatum.udc.com.m_datum.encuestaAgroquimicos.EncuestasListAdapter;
import mdatum.udc.com.m_datum.encuestaAgroquimicos.EstablecimientoFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class EncuestasListFragment extends Fragment {
    //Button btnEncuesta;
    Encuesta encuesta = new Encuesta();

    private RecyclerView encuestas;

    private ArrayList<EncuestaEstablecimiento> encuestaEstablecimientos;

    DaoSession daoSession;

    FloatingActionButton fabNuevaEncuesta;

    public EncuestasListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_encuestas_list, container, false);

        daoSession = ((MDatumController) getActivity().getApplication()).getDaoSession();

        //btnEncuesta = (Button) rootView.findViewById(R.id.btn_encuesta);
        encuesta.setFecha(new Date());

        encuestas = (RecyclerView) rootView.findViewById(R.id.rv_encuestas);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        encuestas.setLayoutManager(llm);
        encuestas.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        inicializarLista();

        inicializarAdaptador();

        fabNuevaEncuesta = (FloatingActionButton) rootView.findViewById(R.id.fab_nueva_encuesta);

        fabNuevaEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("encuesta",encuesta);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                EstablecimientoFragment fragment = new EstablecimientoFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.ll_body_content,fragment).addToBackStack("SPLASH_SCREEN")
                        .commit();
            }
        });



        /*btnEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("encuesta",encuesta);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                EstablecimientoFragment fragment = new EstablecimientoFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.ll_body_content,fragment)
                        .addToBackStack("SPLASH_SCREEN")
                        .commit();
            }
        });*/


        // Inflate the layout for this fragment
        return rootView;
    }

    public void inicializarAdaptador(){

        EncuestasListAdapter encuestasListAdapter = new EncuestasListAdapter(encuestaEstablecimientos);
        encuestas.setAdapter(encuestasListAdapter);
    }


    public void inicializarLista(){
        encuestaEstablecimientos = new ArrayList<EncuestaEstablecimiento>();

        EncuestaDao encuestaDao = daoSession.getEncuestaDao();
        List<Encuesta> encuestaList = new ArrayList<>();
        encuestaList = encuestaDao.loadAll();
        EstablecimientoDao establecimientoDao = daoSession.getEstablecimientoDao();
        for(int i = 0 ; i < encuestaList.size() ; i++ ){
            Establecimiento establecimiento = establecimientoDao.load(encuestaList.get(i).getEstablecimientoId());
            EncuestaEstablecimiento encuestaEstablecimiento = new EncuestaEstablecimiento(encuestaList.get(i).getId(),encuestaList.get(i).getFecha(), establecimiento.getNombre());
            encuestaEstablecimientos.add(encuestaEstablecimiento);
        }


    }

}
