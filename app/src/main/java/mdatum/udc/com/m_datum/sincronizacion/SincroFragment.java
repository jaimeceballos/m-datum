package mdatum.udc.com.m_datum.sincronizacion;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;
import java.util.List;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.MainActivity;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.database.AgroquimicoUsado;
import mdatum.udc.com.m_datum.database.AgroquimicoUsadoDao;
import mdatum.udc.com.m_datum.database.Agroquimicos;
import mdatum.udc.com.m_datum.database.AgroquimicosDao;
import mdatum.udc.com.m_datum.database.Cultivo;
import mdatum.udc.com.m_datum.database.CultivoDao;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.EncuestaDao;
import mdatum.udc.com.m_datum.database.Encuestado;
import mdatum.udc.com.m_datum.database.EncuestadoDao;
import mdatum.udc.com.m_datum.database.Establecimiento;
import mdatum.udc.com.m_datum.database.EstablecimientoDao;
import mdatum.udc.com.m_datum.database.Familia;
import mdatum.udc.com.m_datum.database.FamiliaDao;
import mdatum.udc.com.m_datum.database.Invernaculo;
import mdatum.udc.com.m_datum.database.InvernaculoDao;
import mdatum.udc.com.m_datum.encuestaAgroquimicos.FinalEncuestaFragment;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SincroFragment extends Fragment {

    // Sesion de acceso a la base de datos
    private DaoSession daoSession;
    // sesion de acceso a los servicios de la API web
    private WebDatumApi webDatumApi;
    // Token de autorizacion para la API web
    private String token;
    // Preferencias de la aplicacion
    private SharedPreferences prefs;
    // TextView's de la UI
    private TextView tvCargando,tvSynchronized,tvToSynchronize;
    private ImageView ivNetError;
    private FloatingActionButton fabHome;
    // ProgressBar de la UI
    private ProgressBar pbCargando,pbSincro;
    // Variable contador de proceso
    private int progreso = 0;

    public SincroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_sincro, container, false);

        // Obtengo la sesion de base de datos
        daoSession = ((MDatumController)getActivity().getApplication()).getDaoSession();
        // Obtengo la sesion de acceso a los servicios de la API Web
        webDatumApi = ((MDatumController)getActivity().getApplication()).getApiSession();
        // Obtengo las preferencias de la aplicacion
        prefs =  getActivity().getApplication().getSharedPreferences("MDATUM_PREFS", Activity.MODE_PRIVATE);
        // Obtengo desde las preferencias de la aplicacion el Token de Autorizacion de la API web
        token = prefs.getString("PREF_USER_TOKEN",null);


        // ---- Obtengo la instancia de los elementos de la UI ---- //
        tvCargando      = (TextView) rootview.findViewById(R.id.tv_cargando);

        pbCargando      = (ProgressBar) rootview.findViewById(R.id.pb_cargando);

        pbSincro        = (ProgressBar) rootview.findViewById(R.id.pb_sincro);

        tvSynchronized  = (TextView) rootview.findViewById(R.id.tv_synchronized);

        tvToSynchronize = (TextView) rootview.findViewById(R.id.tv_to_synchronize);

        ivNetError      = (ImageView) rootview.findViewById(R.id.iv_net_error);

        fabHome         = (FloatingActionButton) rootview.findViewById(R.id.fab_home);

        // -- Fin de la obtencion de las instancias de los elementos de la UI -- //


        /**
         * Verifico si es necesario hacer la sincronizacion
         * */
        if(isSynchroNeeded()){
            // Preparo la UI para la sincronizacion
            setUpUI();
            // Llamo a la tarea de sincronizacion con la accion inicio
            synchroTask("Inicio",null);
        }else{
            // si no es necesaria la sincronizacion oculto la progressbar
            pbCargando.setVisibility(View.GONE);
            // y seteo el mensaje de que la actualizacion no es necesaria
            tvCargando.setText("No hay encuestas para sincronizar.");
        }


        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        return rootview;
    }

    private void setUpUI(){
        /*
        * Obtiene la cantidad de encuestas a sincronizar
        * para poder mostrar los elementos de control
        * del avance.
        * */
        List<Encuesta> encuestaList = daoSession.getEncuestaDao().queryBuilder().where(
                EncuestaDao.Properties.Is_finished.eq(true),
                EncuestaDao.Properties.IsSincronized.eq(false)
        ).list();
        tvCargando.setText("Sincronizando Encuestas...");
        pbCargando.setVisibility(View.GONE);
        pbSincro.setVisibility(View.VISIBLE);
        pbSincro.setMax(encuestaList.size());
        pbSincro.setProgress(progreso);
        tvSynchronized.setText("0");
        tvSynchronized.setVisibility(View.VISIBLE);
        tvToSynchronize.setText(String.valueOf(encuestaList.size()));
        tvToSynchronize.setVisibility(View.VISIBLE);
    }

    private boolean isSynchroNeeded(){
        /*
        * isSynchroNeeded, verifica si existen encuestas que esten finalizadas
        * y sin haber sido sincronizadas. En caso de encontrar alguna devuelve verdadero,
        * en el caso contrario devuelve falso.
        * */

        List<Encuesta> encuestaList = daoSession.getEncuestaDao().queryBuilder().where(
                EncuestaDao.Properties.Is_finished.eq(true),
                EncuestaDao.Properties.IsSincronized.eq(false)
        ).list();

        return (encuestaList.size()>0)?true:false;
    }

    private void synchroTask(String accion, Encuesta encuesta){
        /*
        * synchroTask se encarga de la sincronizacion de las encuestas,
        * la accion es desde el dispositivo movil al servidor remoto.
        * Recibe una accion a realizar y la encuesta a sincronizar.
        * A traves de un Switch selecciona la accion a realizar
        * y realiza todas las acciones de preparacion para realizar
        * una peticion HTTP a un endpoint de la api de sincronizacion.
        * */
        final Encuesta finalEncuesta = encuesta;
        switch (accion){
            case "Inicio":
                /*Si la accion es inicio, obtiene las encuestas que estan
                 * finalizadas y no sincronizadas
                 * */

                EncuestaDao encuestaDao = daoSession.getEncuestaDao();
                List<Encuesta> encuestas = encuestaDao.queryBuilder().where(EncuestaDao.Properties.Is_finished.eq(true),EncuestaDao.Properties.IsSincronized.eq(false)).list();

                /*
                * si hay encuestas para sincronizar
                * */
                if(encuestas.size()>0){
                    /*
                    * Obtiene la primer encuesta a sincronizar
                    * */
                    encuesta = encuestas.get(0);
                    /*
                     *  Realiza una llamada recursiva con la accion establecimiento
                     *  y la encuesta que comienza a sincronizar.
                     *  */
                    synchroTask("Establecimiento",encuesta);
                }else{
                    /*
                    * Si finalizo la sincronizacion:
                    * actualiza la UI.
                    * */
                    pbSincro.setVisibility(View.GONE);
                    tvSynchronized.setVisibility(View.GONE);
                    tvToSynchronize.setVisibility(View.GONE);
                    tvCargando.setText("Sincronizacion Finalizada, se sincronizaron: " + progreso + " encuestas.");
                }
                break;
            case "Establecimiento":
                /*
                * Si la accion solicitada es establecimiento
                * obtiene el establecimiento que debe sincronizar.
                 */
                final Establecimiento establecimiento = encuesta.getEstablecimientoRelated();
                if(!establecimiento.getIsSinchronized()) {
                    /*
                    * Como el establecimiento captura una imagen del lugar
                    * el EndPoint recibe una peticion POST con un mensaje multipart
                    * ya que se debe enviar un archivo ademas de los datos del establecimiento.
                    * para esto debo preparar cada parte por separado en un cuerpo de mensaje
                    * del tipo multipart.
                     */

                    //Obtengo el archivo de la foto del establecimiento
                    File foto = new File(establecimiento.getFoto());
                    //preparo la parte del cuerpo del mensaje que lleva la imagen
                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), foto);
                    final MultipartBody.Part body = MultipartBody.Part.createFormData("foto", foto.getName(), reqFile);
                    //preparo el resto de las partes del mensaje
                    RequestBody nombre = RequestBody.create(MediaType.parse("text/plain"), establecimiento.getNombre());
                    RequestBody numero = RequestBody.create(MediaType.parse("text/plain"), establecimiento.getNro());
                    RequestBody posLatitud = RequestBody.create(MediaType.parse("text/plain"), establecimiento.getPosLatitud());
                    RequestBody posLongitud = RequestBody.create(MediaType.parse("text/plain"), establecimiento.getPosLongitud());
                    RequestBody regimenTenencia = RequestBody.create(MediaType.parse("text/plain"), Integer.toString(establecimiento.getRegimenTenenciaId()));
                    RequestBody regimenOtros = RequestBody.create(MediaType.parse("text/plain"), establecimiento.getRegimenOtros());


                    //Instancio una llamada con las partes generadas anteriormente
                    Call<Establecimiento> establecimientoCall = webDatumApi.sincroEstablecimiento(
                            "Token " + token,
                            body,
                            nombre,
                            numero,
                            posLatitud,
                            posLongitud,
                            regimenTenencia,
                            regimenOtros
                    );

                    establecimientoCall.enqueue(new Callback<Establecimiento>() {
                        @Override
                        public void onResponse(Call<Establecimiento> call, Response<Establecimiento> response) {
                            if (response.isSuccessful()) {
                                /*
                                * si la respuesta del servidor es SUCCESSFULL,
                                * en el establecimiento local guardo el id que
                                * le fue asignado por la base de datos remota
                                * y lo marco como sincronizado
                                *
                                 */
                                establecimiento.setRemoteId(Integer.parseInt(response.body().getId().toString()));
                                establecimiento.setIsSinchronized(true);
                                daoSession.update(establecimiento);
                                finalEncuesta.setEstablecimientoRelated(establecimiento);

                                /*
                                 * Hago una llamada recursiva a la tarea de sincronizacion con
                                  * la accion encuestado y la encuesta que se esta sincronizando.
                                 */
                                synchroTask("Encuestado", finalEncuesta);
                            } else {
                                responseNotSuccessfull(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<Establecimiento> call, Throwable t) {

                            errorDeRed();

                        }
                    });
                }else{
                    synchroTask("Encuestado", finalEncuesta);
                }
                break;
            case "Encuestado":
                final Encuestado encuestado = encuesta.getEncuestadoRelated();
                if(!encuestado.getIsSinchronized()) {
                    Call<Encuestado> encuestadoCall = webDatumApi.sincroEncuestado(
                            "Token " + token,
                            encuestado
                    );


                    encuestadoCall.enqueue(new Callback<Encuestado>() {
                        @Override
                        public void onResponse(Call<Encuestado> call, Response<Encuestado> response) {
                            if (response.isSuccessful()) {
                                encuestado.setIsSinchronized(true);
                                encuestado.setRemoteId(Integer.parseInt(response.body().getId().toString()));
                                daoSession.update(encuestado);
                                finalEncuesta.setEncuestadoRelated(encuestado);
                                if (finalEncuesta.getFamiliaId() != null) {
                                    synchroTask("Familia", finalEncuesta);
                                } else if (finalEncuesta.getAgroquimicoId() != null) {
                                    synchroTask("Agroquimico", finalEncuesta);
                                } else {
                                    synchroTask("Encuesta", finalEncuesta);
                                }
                            } else {
                                responseNotSuccessfull(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<Encuestado> call, Throwable t) {
                            errorDeRed();
                        }
                    });
                }else{
                    if (finalEncuesta.getFamiliaId() != null) {
                        synchroTask("Familia", finalEncuesta);
                    } else if (finalEncuesta.getAgroquimicoId() != null) {
                        synchroTask("Agroquimico", finalEncuesta);
                    } else {
                        synchroTask("Encuesta", finalEncuesta);
                    }
                }
                break;
            case "Familia":
                final Familia familia = encuesta.getFamiliaRelated();
                if(!familia.getIsSinchronized()) {
                    Call<Familia> familiaCall = webDatumApi.sincroFamilia("Token " + token, familia);
                    familiaCall.enqueue(new Callback<Familia>() {
                        @Override
                        public void onResponse(Call<Familia> call, Response<Familia> response) {
                            if (response.isSuccessful()) {
                                familia.setIsSinchronized(true);
                                familia.setIdRemote(Integer.parseInt(response.body().getId().toString()));
                                daoSession.update(familia);
                                finalEncuesta.setFamiliaRelated(familia);
                                if (finalEncuesta.getAgroquimicoId() != null) {
                                    synchroTask("Agroquimico", finalEncuesta);
                                } else {
                                    synchroTask("Encuesta", finalEncuesta);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Familia> call, Throwable t) {
                            errorDeRed();
                        }
                    });
                }else{
                    if (finalEncuesta.getAgroquimicoId() != null) {
                        synchroTask("Agroquimico", finalEncuesta);
                    } else {
                        synchroTask("Encuesta", finalEncuesta);
                    }
                }
                break;
            case "Agroquimico":
                final Agroquimicos agroquimico = encuesta.getAgroquimicoRelated();
                if(!agroquimico.getIsSincronized()) {
                    Call<Agroquimicos> agroquimicosCall = webDatumApi.sincroAgroquimico("Token " + token, agroquimico);

                    agroquimicosCall.enqueue(new Callback<Agroquimicos>() {
                        @Override
                        public void onResponse(Call<Agroquimicos> call, Response<Agroquimicos> response) {
                            if (response.isSuccessful()) {
                                agroquimico.setIsSincronized(true);
                                agroquimico.setRemoteId(Integer.parseInt(response.body().getId().toString()));
                                daoSession.update(agroquimico);
                                finalEncuesta.setAgroquimicoRelated(agroquimico);
                                synchroTask("Encuesta", finalEncuesta);
                            } else {
                                responseNotSuccessfull(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<Agroquimicos> call, Throwable t) {
                            Toast.makeText(getContext(), "Hubo un problema de comunicacion con el servidor. Vuelva a intentarlo mas tarde. Si el problema persiste comuniquese con el administrador.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    synchroTask("Encuesta", finalEncuesta);
                }
                break;
            case "Encuesta":
                if(!encuesta.getIsSincronized()) {
                    final Long id = encuesta.getId();
                    daoSession.update(encuesta);
                    encuesta.setEstablecimientoId((long) encuesta.getEstablecimientoRelated().getRemoteId());
                    encuesta.setEncuestadoId((long) encuesta.getEncuestadoRelated().getRemoteId());
                    if (encuesta.getFamiliaId() != null)
                        encuesta.setFamiliaId((long) encuesta.getFamiliaRelated().getIdRemote());
                    if (encuesta.getAgroquimicoId() != null)
                        encuesta.setAgroquimicoId((long) encuesta.getAgroquimicoRelated().getRemoteId());

                    Call<Encuesta> encuestaCall = webDatumApi.sincroEncuesta("Token " + token, encuesta);

                    encuestaCall.enqueue(new Callback<Encuesta>() {
                        @Override
                        public void onResponse(Call<Encuesta> call, Response<Encuesta> response) {
                            if (response.isSuccessful()) {
                                EncuestaDao encuestaDao1 = daoSession.getEncuestaDao();
                                Agroquimicos agroquimicos = finalEncuesta.getAgroquimicoRelated();
                                Encuesta encuesta1 = encuestaDao1.load(id);
                                encuesta1.setRemote_id(Integer.parseInt(response.body().getId().toString()));
                                encuesta1.setEstablecimientoId(getEstablecimientoByRemoteId(encuesta1.getEstablecimientoId()));
                                encuesta1.setEncuestadoId(getEncuestadoByRemoteId(encuesta1.getEncuestadoId()));
                                encuesta1.setFamiliaId((encuesta1.getFamiliaId() != null) ? getFamiliaByRemoteId(encuesta1.getFamiliaId()) : null);
                                encuesta1.setAgroquimicoId((encuesta1.getAgroquimicoId() != null) ? getAgroquimicoByRemoteId(encuesta1.getAgroquimicoId()) : null);
                                daoSession.update(encuesta1);
                                if (finalEncuesta.getInvernaculos().size() > 0) {
                                    synchroTask("Invernaculos", finalEncuesta);
                                } else if (finalEncuesta.getCultivos().size() > 0) {
                                    synchroTask("Cultivos", finalEncuesta);
                                } else if (finalEncuesta.getAgroquimicoUsados().size() > 0) {
                                    synchroTask("AgroquimicosUsados", finalEncuesta);
                                } else {
                                    synchroTask("Finaliza", finalEncuesta);
                                }
                            } else {
                                responseNotSuccessfull(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<Encuesta> call, Throwable t) {
                            errorDeRed();
                        }
                    });
                }else{
                    if (finalEncuesta.getInvernaculos().size() > 0) {
                        synchroTask("Invernaculos", finalEncuesta);
                    } else if (finalEncuesta.getCultivos().size() > 0) {
                        synchroTask("Cultivos", finalEncuesta);
                    } else if (finalEncuesta.getAgroquimicoUsados().size() > 0) {
                        synchroTask("AgroquimicosUsados", finalEncuesta);
                    } else {
                        synchroTask("Finaliza", finalEncuesta);
                    }
                }
                break;
            case "Invernaculos":

                List<Invernaculo> invernaculos = daoSession.getInvernaculoDao().queryBuilder().where(
                        InvernaculoDao.Properties.EncuestaId
                                .eq(encuesta.getId()),
                        InvernaculoDao.Properties.IsSinchronized.eq(false)
                ).list();
                if(invernaculos.size()>0) {
                    final Invernaculo invernaculo = invernaculos.get(0);

                    invernaculo.setEncuestaId((long) encuesta.getRemote_id());

                    Call<Invernaculo> invernaculoCall = webDatumApi.sincroInvernaculo("Token " + token, invernaculo);

                    invernaculoCall.enqueue(new Callback<Invernaculo>() {
                        @Override
                        public void onResponse(Call<Invernaculo> call, Response<Invernaculo> response) {
                            if (response.isSuccessful()) {
                                invernaculo.setEncuestaId(finalEncuesta.getId());
                                invernaculo.setIsSinchronized(true);
                                invernaculo.setIdRemote(Integer.parseInt(response.body().getId().toString()));
                                daoSession.update(invernaculo);
                                InvernaculoDao invernaculoDao = daoSession.getInvernaculoDao();
                                List<Invernaculo> invernaculos = invernaculoDao.queryBuilder().where(
                                        InvernaculoDao.Properties.IsSinchronized.eq(false),
                                        InvernaculoDao.Properties.EncuestaId
                                                .eq(finalEncuesta.getId()))
                                        .list();

                                if (invernaculos.size() > 0) {
                                    synchroTask("Invernaculos", finalEncuesta);
                                } else if (finalEncuesta.getCultivos().size() > 0) {
                                    synchroTask("Cultivos", finalEncuesta);
                                } else if (finalEncuesta.getAgroquimicoUsados().size() > 0) {
                                    synchroTask("AgroquimicosUsados", finalEncuesta);
                                } else {
                                    synchroTask("Finaliza", finalEncuesta);
                                }

                            }else {
                                responseNotSuccessfull(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<Invernaculo> call, Throwable t) {
                            errorDeRed();
                        }
                    });
                }else if (finalEncuesta.getCultivos().size() > 0) {
                    synchroTask("Cultivos", finalEncuesta);
                } else if (finalEncuesta.getAgroquimicoUsados().size() > 0) {
                    synchroTask("AgroquimicosUsados", finalEncuesta);
                } else {
                    synchroTask("Finaliza", finalEncuesta);
                }

                break;
            case "Cultivos":
                List<Cultivo> cultivos = daoSession.getCultivoDao().queryBuilder()
                        .where(
                                CultivoDao.Properties.EncuestaId.eq(encuesta.getId()),
                                CultivoDao.Properties.IsSinchronized.eq(false)
                        ).list();
                if(cultivos.size()>0) {
                    final Cultivo cultivo = cultivos.get(0);

                    cultivo.setEncuestaId((long) encuesta.getRemote_id());

                    Call<Cultivo> cultivoCall = webDatumApi.sincroCultivo("Token " + token, cultivo);
                    cultivoCall.enqueue(new Callback<Cultivo>() {
                        @Override
                        public void onResponse(Call<Cultivo> call, Response<Cultivo> response) {
                            if (response.isSuccessful()) {
                                cultivo.setEncuestaId(finalEncuesta.getId());
                                cultivo.setIsSinchronized(true);
                                cultivo.setRemoteId(Integer.parseInt(response.body().getId().toString()));
                                daoSession.update(cultivo);
                                CultivoDao cultivoDao = daoSession.getCultivoDao();
                                List<Cultivo> cultivos = cultivoDao.queryBuilder().where(
                                        CultivoDao.Properties.EncuestaId
                                                .eq(finalEncuesta.getId()),
                                        CultivoDao.Properties.IsSinchronized.eq(false))
                                        .list();

                                if (cultivos.size() > 0) {
                                    synchroTask("Cultivos", finalEncuesta);
                                } else if (finalEncuesta.getAgroquimicoUsados().size() > 0) {
                                    synchroTask("AgroquimicosUsados", finalEncuesta);
                                } else {
                                    synchroTask("Finaliza", finalEncuesta);
                                }
                            }else {
                                responseNotSuccessfull(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<Cultivo> call, Throwable t) {
                            errorDeRed();
                        }
                    });
                }else if (finalEncuesta.getAgroquimicoUsados().size() > 0) {
                    synchroTask("AgroquimicosUsados", finalEncuesta);
                } else {
                    synchroTask("Finaliza", finalEncuesta);
                }
                break;
            case "AgroquimicosUsados":
                List<AgroquimicoUsado> agroquimicoUsados = daoSession.getAgroquimicoUsadoDao().queryBuilder()
                        .where(
                                AgroquimicoUsadoDao.Properties.EncuestaId.eq(encuesta.getId()),
                                AgroquimicoUsadoDao.Properties.IsSinchronized.eq(false)
                        ).list();
                if(agroquimicoUsados.size()>0){
                    final AgroquimicoUsado agroquimicoUsado = agroquimicoUsados.get(0);

                    agroquimicoUsado.setEncuestaId((long) encuesta.getRemote_id());

                    Call<AgroquimicoUsado> agroquimicoUsadoCall = webDatumApi.sincroAgroquimicoUsado("Token "+token,agroquimicoUsado);

                    agroquimicoUsadoCall.enqueue(new Callback<AgroquimicoUsado>() {
                        @Override
                        public void onResponse(Call<AgroquimicoUsado> call, Response<AgroquimicoUsado> response) {
                            if(response.isSuccessful()){
                                agroquimicoUsado.setEncuestaId(finalEncuesta.getId());
                                agroquimicoUsado.setIsSinchronized(true);
                                agroquimicoUsado.setIdRemote(Integer.parseInt(response.body().getId().toString()));
                                daoSession.update(agroquimicoUsado);
                                AgroquimicoUsadoDao agroquimicoUsadoDao = daoSession.getAgroquimicoUsadoDao();
                                List<AgroquimicoUsado> agroquimicoUsados1 = agroquimicoUsadoDao.queryBuilder().where(
                                        AgroquimicoUsadoDao.Properties.EncuestaId
                                                .eq(finalEncuesta.getId()),
                                        AgroquimicoUsadoDao.Properties.IsSinchronized.eq(false))
                                        .list();

                                if(agroquimicoUsados1.size()>0){
                                    synchroTask("AgroquimicosUsados",finalEncuesta);
                                }else{
                                    synchroTask("Finaliza",finalEncuesta);
                                }
                            }else {
                                responseNotSuccessfull(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<AgroquimicoUsado> call, Throwable t) {
                            errorDeRed();
                        }
                    });
                }else{
                    synchroTask("Finaliza",finalEncuesta);
                }
                break;
            case "Finaliza":
                progreso += 1;
                pbSincro.setProgress(progreso);
                tvSynchronized.setText(String.valueOf(progreso));
                finalEncuesta.setIsSincronized(true);
                daoSession.update(finalEncuesta);
                synchroTask("Inicio",null);
                break;
        }



    }




    private long getAgroquimicoByRemoteId(long remoteId){
        AgroquimicosDao agroquimicosDao = daoSession.getAgroquimicosDao();

        Agroquimicos agroquimico = agroquimicosDao.queryBuilder().where(AgroquimicosDao.Properties.RemoteId.eq(remoteId)).list().get(0);

        return agroquimico.getId();


    }

    private long getEstablecimientoByRemoteId(long remoteId){

        EstablecimientoDao establecimientoDao = daoSession.getEstablecimientoDao();

        Establecimiento establecimiento = establecimientoDao.queryBuilder().where(EstablecimientoDao.Properties.RemoteId.eq(remoteId)).list().get(0);

        return establecimiento.getId();
    }

    private long getEncuestadoByRemoteId(long remoteId){

        EncuestadoDao encuestadoDao = daoSession.getEncuestadoDao();

        Encuestado encuestado = encuestadoDao.queryBuilder().where(EncuestadoDao.Properties.RemoteId.eq(remoteId)).list().get(0);

        return encuestado.getId();
    }

    private long getFamiliaByRemoteId(long remoteId){

        FamiliaDao familiaDao = daoSession.getFamiliaDao();

        Familia familia = familiaDao.queryBuilder().where(FamiliaDao.Properties.IdRemote.eq(remoteId)).list().get(0);

        return familia.getId();
    }

    private void errorDeRed(){

        tvCargando.setText(R.string.netError);
        pbCargando.setVisibility(View.GONE);
        pbSincro.setVisibility(View.GONE);
        tvSynchronized.setVisibility(View.GONE);
        tvToSynchronize.setVisibility(View.GONE);
        ivNetError.setVisibility(View.VISIBLE);
        fabHome.setVisibility(View.VISIBLE);
    }

    private void responseNotSuccessfull(String message) {
        tvCargando.setText(R.string.responseNotSuccess+message);
        pbCargando.setVisibility(View.GONE);
        pbSincro.setVisibility(View.GONE);
        tvSynchronized.setVisibility(View.GONE);
        tvToSynchronize.setVisibility(View.GONE);
        ivNetError.setVisibility(View.VISIBLE);
        fabHome.setVisibility(View.VISIBLE);
    }
}
