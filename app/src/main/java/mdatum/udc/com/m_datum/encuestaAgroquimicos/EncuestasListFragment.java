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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.EncuestaDao;


/**
 * A simple {@link Fragment} subclass.
 */
public class EncuestasListFragment extends Fragment {
    //Button btnEncuesta;
    private Encuesta encuesta = new Encuesta();

    private RecyclerView encuestas;

    private List<Encuesta> encuestaList;

    private DaoSession daoSession;

    private FloatingActionButton fabNuevaEncuesta;

    private TextView tv_nueva_encuesta;

    public EncuestasListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_encuestas_list, container, false);

        daoSession = ((MDatumController) getActivity().getApplication()).getDaoSession();

        tv_nueva_encuesta = (TextView) rootView.findViewById(R.id.tv_nueva_encuesta);

        //btnEncuesta = (Button) rootView.findViewById(R.id.btn_encuesta);
        //encuesta.setFecha(new Date());

        encuestas = (RecyclerView) rootView.findViewById(R.id.rv_encuestas);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        encuestas.setLayoutManager(llm);
        encuestas.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        inicializarLista();
        if(encuestaList.size()>0) {
            tv_nueva_encuesta.setVisibility(View.GONE);
            inicializarAdaptador();
        }else{
            tv_nueva_encuesta.setVisibility(View.VISIBLE);
        }

        fabNuevaEncuesta = (FloatingActionButton) rootView.findViewById(R.id.fab_nueva_encuesta);

        fabNuevaEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("encuesta",encuesta);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                EstablecimientoFragment fragment = new EstablecimientoFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.ll_body_content,fragment).addToBackStack("ESTABLECIMIENTO")
                        .commit();
            }
        });




        return rootView;
    }

    public void inicializarAdaptador(){

        EncuestasListAdapter encuestasListAdapter = new EncuestasListAdapter(encuestaList,getActivity());
        encuestas.setAdapter(encuestasListAdapter);
    }


    public void inicializarLista(){
        encuestaList = new ArrayList<Encuesta>();

        EncuestaDao encuestaDao = daoSession.getEncuestaDao();
        encuestaList = encuestaDao.loadAll();



    }

}
