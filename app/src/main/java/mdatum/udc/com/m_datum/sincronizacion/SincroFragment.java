package mdatum.udc.com.m_datum.sincronizacion;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.io.File;
import java.util.List;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.Establecimiento;
import mdatum.udc.com.m_datum.database.EstablecimientoDao;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

/**
 * A simple {@link Fragment} subclass.
 */
public class SincroFragment extends Fragment {


    DaoSession daoSession;

    WebDatumApi webDatumApi;

    public SincroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_sincro, container, false);

        daoSession = ((MDatumController)getActivity().getApplication()).getDaoSession();

        webDatumApi = ((MDatumController)getActivity().getApplication()).getApiSession();

        EstablecimientoDao establecimientoDao = daoSession.getEstablecimientoDao();

        List<Establecimiento> establecimiento = establecimientoDao.queryBuilder().where(EstablecimientoDao.Properties.Id.eq((long)1)).list();

        for(int i = 0; i < establecimiento.size(); i++){
            Establecimiento element = establecimiento.get(i);

            File foto = new File(element.getFoto());

            final SharedPreferences prefs = getActivity().getApplication().getSharedPreferences("MDATUM_PREFS", Activity.MODE_PRIVATE);

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), foto);
            MultipartBody.Part body = MultipartBody.Part.createFormData("foto",foto.getName(),reqFile);
            RequestBody nombre = RequestBody.create(MediaType.parse("text/plain"),element.getNombre());
            RequestBody numero = RequestBody.create(MediaType.parse("text/plain"),element.getNro());
            RequestBody posLatitud = RequestBody.create(MediaType.parse("text/plain"),element.getPosLatitud());
            RequestBody posLongitud = RequestBody.create(MediaType.parse("text/plain"),element.getPosLongitud());
            RequestBody regimenTenencia = RequestBody.create(MediaType.parse("text/plain"),Integer.toBinaryString(element.getRegimenTenenciaId()));
            RequestBody regimenOtros = RequestBody.create(MediaType.parse("text/plain"),element.getRegimenOtros());
            Call<Establecimiento> establecimientoCall = webDatumApi.sincroEstablecimiento("Token "+prefs.getString("PREF_USER_TOKEN",null),body,nombre,numero,posLatitud,posLongitud,regimenTenencia,regimenOtros);

            establecimientoCall.enqueue(new Callback<Establecimiento>() {
                @Override
                public void onResponse(Call<Establecimiento> call, Response<Establecimiento> response) {
                    Log.i("SUCCESS","Operacion con exito");
                }

                @Override
                public void onFailure(Call<Establecimiento> call, Throwable t) {
                    Log.i("FAILURE","Operacion fallida");
                }
            });

        }



        return rootview;
    }

}
