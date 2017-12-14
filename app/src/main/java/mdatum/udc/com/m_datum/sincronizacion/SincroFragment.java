package mdatum.udc.com.m_datum.sincronizacion;


import android.app.Activity;
import android.app.DownloadManager;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.Agroquimicos;
import mdatum.udc.com.m_datum.database.Cultivo;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.EncuestaDao;
import mdatum.udc.com.m_datum.database.Encuestado;
import mdatum.udc.com.m_datum.database.Establecimiento;
import mdatum.udc.com.m_datum.database.EstablecimientoDao;
import mdatum.udc.com.m_datum.database.Familia;
import mdatum.udc.com.m_datum.database.Transaccion;
import mdatum.udc.com.m_datum.database.TransaccionDao;
import mdatum.udc.com.m_datum.events.MensajesDeEvento;
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

    private String uid = "";

    private DaoSession daoSession;

    private WebDatumApi webDatumApi;

    private String token;

    private int usuarioId;

    private SharedPreferences prefs;

    public SincroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_sincro, container, false);

        EventBus.getDefault().register(this);

        daoSession = ((MDatumController)getActivity().getApplication()).getDaoSession();

        webDatumApi = ((MDatumController)getActivity().getApplication()).getApiSession();

        prefs =  getActivity().getApplication().getSharedPreferences("MDATUM_PREFS", Activity.MODE_PRIVATE);

        token = prefs.getString("PREF_USER_TOKEN",null);

        usuarioId = prefs.getInt("PREF_USER_LOGED",0);

        EncuestaDao encuestaDao = daoSession.getEncuestaDao();

        List<Encuesta> encuestas = encuestaDao.loadAll();//queryBuilder().where(EncuestaDao.Properties.Is_finished.eq(true),EncuestaDao.Properties.IsSincronized.eq(false)).list();

        new SincroFragment.SincroTask().execute((ArrayList<Encuesta>) encuestas);

        /*for(int i = 0; i < encuestas.size() ; i ++){
            uid = UUID.randomUUID().toString()+"-"+String.valueOf(encuestas.get(i).getId())+"-"+String.valueOf(encuestas.get(i).getUsuario());

            Encuesta encuesta = encuestas.get(i);
            encuesta.setTransaccion(uid);

            encuestaDao.update(encuesta);

            Transaccion transaccion = new Transaccion();
            transaccion.setEncuestaId(encuesta.getId());
            transaccion.setTransaccion(uid);
            try{
                daoSession.insert(transaccion);
            }catch (SQLiteConstraintException e){
                daoSession.update(transaccion);
            }

            Establecimiento establecimiento = encuestas.get(i).getEstablecimientoRelated();
            establecimiento.setTransaccion(uid);

            new SincroFragment.EstablecimientoSynchroTask().execute(establecimiento);

        }*/




        return rootview;
    }


    public class SincroTask extends AsyncTask<ArrayList<Encuesta>,Void,Boolean>{

        @Override
        protected Boolean doInBackground(ArrayList<Encuesta>... arrayLists) {

            MensajesDeEvento event =  new MensajesDeEvento();
            event.setEvento("Start");
            event.setDispatcher("SincroTask");
            event.setObject(arrayLists[0]);
            EventBus.getDefault().post(event);

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            super.onPostExecute(aBoolean);
        }
    }



    @Subscribe
    public void onEvent(MensajesDeEvento evento){

        switch (evento.getDispatcher()){

            case "SincroTask":
                List<Encuesta> encuestas = (List<Encuesta>) evento.getObject();
                Iterator<Encuesta> iterator = encuestas.iterator();
                if(evento.getEvento().equals("Start")){
                    if(iterator.hasNext()){
                        Encuesta encuesta = iterator.next();
                        Establecimiento establecimiento = encuesta.getEstablecimientoRelated();
                        uid = UUID.randomUUID().toString()+"-"+String.valueOf(encuesta.getId())+"-"+String.valueOf(encuesta.getUsuario());
                        establecimiento.setTransaccion(uid);
                        encuesta.setTransaccion(uid);
                        daoSession.update(encuesta);
                        Transaccion transaccion = new Transaccion();
                        transaccion.setEncuestaId(encuesta.getId());
                        transaccion.setTransaccion(uid);
                        try{
                            daoSession.insert(transaccion);
                        }catch (SQLiteConstraintException e){
                            daoSession.update(transaccion);
                        }
                        new SincroFragment.EstablecimientoSynchroTask().execute(establecimiento);
                    }
                }else if(evento.getEvento().equals("Continue")){

                }

            case "EstablecimientoTask":
                if(evento.getEvento().equals("Success")){
                    Encuesta encuesta = getEncuestaByTransaccion(evento.getTransaccion());
                    Encuestado  encuestado = encuesta.getEncuestadoRelated();
                    encuestado.setTransaccion(evento.getTransaccion());
                    new SincroFragment.EncuestadoSynchroTask().execute(encuestado);
                }
                break;
            case "EncuestadoTask":
                if(evento.getEvento().equals("Success")){
                    Encuesta encuesta = getEncuestaByTransaccion(evento.getTransaccion());
                    Familia familia = encuesta.getFamiliaRelated();
                    if(familia != null){
                        familia.setTransaccion(evento.getTransaccion());
                        new SincroFragment.FamiliaSynchroTask().execute(familia);
                    }else {
                        MensajesDeEvento event =  new MensajesDeEvento();
                        event.setEvento("Success");
                        event.setDispatcher("FamiliaTask");
                        event.setTransaccion(evento.getTransaccion());
                        EventBus.getDefault().post(event);
                    }
                }
                break;
            case "FamiliaTask":
                if(evento.getEvento().equals("Success")){
                    Encuesta encuesta = getEncuestaByTransaccion(evento.getTransaccion());
                    Agroquimicos agroquimico = encuesta.getAgroquimicoRelated();
                    if(agroquimico!= null){
                        agroquimico.setTransaccion(evento.getTransaccion());
                        new SincroFragment.AgroquimicoSynchroTask().execute(agroquimico);
                    }else{
                        MensajesDeEvento event =  new MensajesDeEvento();
                        event.setEvento("Success");
                        event.setDispatcher("AgroquimicoTask");
                        event.setTransaccion(evento.getTransaccion());
                        EventBus.getDefault().post(event);
                    }
                }
                break;
            case "AgroquimicoTask":
                if(evento.getEvento().equals("Success")){
                    new SincroFragment.ObtenerIdsTask().execute(evento.getTransaccion());
                }
                break;
            case "GetIdsTask":
                if(evento.getEvento().equals("Success")){
                    Encuesta temp = (Encuesta) evento.getObject();
                    if(temp.getFamiliaId()== 0)
                        temp.setFamiliaId(null);
                    if(temp.getAgroquimicoId()==0)
                        temp.setAgroquimicoId(null);
                    new SincroFragment.EncuestaSynchroTask().execute(temp);
                }
                break;

        }


    }

    private class EstablecimientoSynchroTask extends AsyncTask<Establecimiento,Void,Long>{

        @Override
        protected Long doInBackground(Establecimiento... establecimientos) {

            Long id = 0L;
            final Establecimiento establecimiento = establecimientos[0];

            //Obtengo el archivo de la foto del establecimiento
            File foto                   = new File(establecimiento.getFoto());
            //preparo la parte del cuerpo del mensaje que lleva la imagen
            RequestBody reqFile         = RequestBody.create(MediaType.parse("image/*"), foto);
            final MultipartBody.Part body     = MultipartBody.Part.createFormData("foto",foto.getName(),reqFile);
            //preparo el resto de las partes del mensaje
            RequestBody nombre          = RequestBody.create(MediaType.parse("text/plain"),establecimiento.getNombre());
            RequestBody numero          = RequestBody.create(MediaType.parse("text/plain"),establecimiento.getNro());
            RequestBody posLatitud      = RequestBody.create(MediaType.parse("text/plain"),establecimiento.getPosLatitud());
            RequestBody posLongitud     = RequestBody.create(MediaType.parse("text/plain"),establecimiento.getPosLongitud());
            RequestBody regimenTenencia = RequestBody.create(MediaType.parse("text/plain"),Integer.toString(establecimiento.getRegimenTenenciaId()));
            RequestBody regimenOtros    = RequestBody.create(MediaType.parse("text/plain"),establecimiento.getRegimenOtros());
            RequestBody transaccion     = RequestBody.create(MediaType.parse("text/plain"), establecimiento.getTransaccion());

            //Instancio una llamada con las partes generadas anteriormente
            Call<Establecimiento> establecimientoCall = webDatumApi.sincroEstablecimiento(
                    "Token " + token,
                    body,
                    nombre,
                    numero,
                    posLatitud,
                    posLongitud,
                    regimenTenencia,
                    regimenOtros,
                    transaccion
            );

            establecimientoCall.enqueue(new Callback<Establecimiento>() {
                @Override
                public void onResponse(Call<Establecimiento> call, Response<Establecimiento> response) {
                    if(response.isSuccessful()){
                        MensajesDeEvento evento =  new MensajesDeEvento();
                        evento.setEvento("Success");
                        evento.setDispatcher("EstablecimientoTask");
                        evento.setTransaccion(response.body().getTransaccion());
                        EventBus.getDefault().post(evento);
                    }
                }

                @Override
                public void onFailure(Call<Establecimiento> call, Throwable t) {

                }
            });

            return id;

        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
        }
    }


    private class EncuestadoSynchroTask extends AsyncTask<Encuestado,Void,Boolean>{

        @Override
        protected Boolean doInBackground(Encuestado... encuestados) {

            Encuestado encuestado = encuestados[0];



            Call<Encuestado> encuestadoCall = webDatumApi.sincroEncuestado(
                    "Token " + token,
                    encuestado
            );

            encuestadoCall.enqueue(new Callback<Encuestado>() {
                @Override
                public void onResponse(Call<Encuestado> call, Response<Encuestado> response) {
                    if (response.isSuccessful()) {
                        MensajesDeEvento evento =  new MensajesDeEvento();
                        evento.setEvento("Success");
                        evento.setDispatcher("EncuestadoTask");
                        evento.setTransaccion(response.body().getTransaccion());
                        EventBus.getDefault().post(evento);
                    }
                }

                @Override
                public void onFailure(Call<Encuestado> call, Throwable t) {

                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    private class AgroquimicoSynchroTask extends AsyncTask<Agroquimicos, Void,Boolean>{

        @Override
        protected Boolean doInBackground(Agroquimicos... agroquimicos) {

            Call<Agroquimicos> agroquimicosCall = webDatumApi.sincroAgroquimico(
                    "Token " + token,
                    agroquimicos[0]
            );

            agroquimicosCall.enqueue(new Callback<Agroquimicos>() {
                @Override
                public void onResponse(Call<Agroquimicos> call, Response<Agroquimicos> response) {
                    if (response.isSuccessful()) {
                        MensajesDeEvento evento =  new MensajesDeEvento();
                        evento.setEvento("Success");
                        evento.setDispatcher("AgroquimicoTask");
                        evento.setTransaccion(response.body().getTransaccion());
                        EventBus.getDefault().post(evento);
                    }
                }

                @Override
                public void onFailure(Call<Agroquimicos> call, Throwable t) {

                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    private class FamiliaSynchroTask extends  AsyncTask<Familia,Void,Boolean>{

        @Override
        protected Boolean doInBackground(Familia... familias) {

            Call<Familia> familiaCall = webDatumApi.sincroFamilia("Token " + token,familias[0]);
            familiaCall.enqueue(new Callback<Familia>() {
                @Override
                public void onResponse(Call<Familia> call, Response<Familia> response) {
                    if (response.isSuccessful()) {
                        MensajesDeEvento evento =  new MensajesDeEvento();
                        evento.setEvento("Success");
                        evento.setDispatcher("FamiliaTask");
                        evento.setTransaccion(response.body().getTransaccion());
                        EventBus.getDefault().post(evento);
                    }else{
                        Log.d("ERRORResponse",response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<Familia> call, Throwable t) {
                    Log.d("FAMILIAFAILURE",t.getMessage().toString());
                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    private class EncuestaSynchroTask extends AsyncTask<Encuesta,Void,Boolean>{

        @Override
        protected Boolean doInBackground(Encuesta... encuestas) {

            Call<Encuesta> encuestaCall = webDatumApi.sincroEncuesta(
                    "Token " + token,
                    encuestas[0]
            );

            encuestaCall.enqueue(new Callback<Encuesta>() {
                @Override
                public void onResponse(Call<Encuesta> call, Response<Encuesta> response) {
                    if (response.isSuccessful()) {
                        Encuesta encuestaResponse = response.body();
                        Encuesta encuesta = getEncuestaByTransaccion(encuestaResponse.getTransaccion());
                        encuesta.setIsSincronized(true);
                        encuesta.setRemote_id(Integer.parseInt(encuestaResponse.getId().toString()));
                        encuesta.setTransaccion(encuestaResponse.getTransaccion());
                        daoSession.update(encuesta);
                        MensajesDeEvento evento =  new MensajesDeEvento();
                        evento.setEvento("Start");
                        evento.setDispatcher("SincroTask");
                        evento.setTransaccion(response.body().getTransaccion());
                        EventBus.getDefault().post(evento);
                    }
                }

                @Override
                public void onFailure(Call<Encuesta> call, Throwable t) {

                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }



    private class ObtenerIdsTask extends AsyncTask<String,Void,Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {

            final String transaccion = strings[0];
            Call<IdsSincro> idsCall = webDatumApi.getIdsByTransaccion("Token "+token,strings[0]);
            idsCall.enqueue(new Callback<IdsSincro>() {
                @Override
                public void onResponse(Call<IdsSincro> call, Response<IdsSincro> response) {
                    if(response.isSuccessful()){
                        Encuesta encuesta = new Encuesta();
                        encuesta.setEstablecimientoId(response.body().getEstablecimiento());
                        encuesta.setEncuestadoId(response.body().getEncuestado());
                        encuesta.setFamiliaId(response.body().getFamilia());
                        encuesta.setAgroquimicoId(response.body().getAgroquimico());
                        encuesta.setFecha(getEncuestaByTransaccion(transaccion).getFecha());
                        encuesta.setUsuario(getEncuestaByTransaccion(transaccion).getUsuario());
                        encuesta.setTransaccion(transaccion);
                        MensajesDeEvento evento =  new MensajesDeEvento();
                        evento.setEvento("Success");
                        evento.setDispatcher("GetIdsTask");
                        evento.setTransaccion(transaccion);
                        evento.setObject(encuesta);
                        EventBus.getDefault().post(evento);
                    }
                }

                @Override
                public void onFailure(Call<IdsSincro> call, Throwable t) {

                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    private Encuesta getEncuestaByTransaccion(String transaction){
        TransaccionDao transaccionDao = daoSession.getTransaccionDao();
        Transaccion transaccion = transaccionDao.queryBuilder().where(TransaccionDao.Properties.Transaccion.eq(transaction)).list().get(0);
        return transaccion.getEncuesta();
    }


    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroy(){
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
