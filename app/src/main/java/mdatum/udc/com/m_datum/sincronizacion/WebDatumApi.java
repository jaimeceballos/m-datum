package mdatum.udc.com.m_datum.sincronizacion;

import java.util.Date;
import java.util.List;

import mdatum.udc.com.m_datum.database.AgroquimicoUsado;
import mdatum.udc.com.m_datum.database.Agroquimicos;
import mdatum.udc.com.m_datum.database.Cultivo;
import mdatum.udc.com.m_datum.database.Encuesta;
import mdatum.udc.com.m_datum.database.Encuestado;
import mdatum.udc.com.m_datum.database.Establecimiento;
import mdatum.udc.com.m_datum.database.Familia;
import mdatum.udc.com.m_datum.database.Invernaculo;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by jaime on 16/11/17.
 */

public interface WebDatumApi {

    public static final String URL = "http://31.220.59.238";
    public static final String PORT = "/";
    public static final String BASE_URL = URL+PORT;

    @POST ("api/auth/login/")
    Call<UserToken> login(@Body LoginBody loginBody);

    @GET ("api/auth/user/")
    Call <Usuario> usuario(@Header("Authorization") String token);

    @POST ("api/auth/logout/")
    Call<Void> logout();

    @POST ("api/auth/password/change/")
    Call<Void> password_change(@Header("Authorization") String token, @Body PasswordChangeBody passwordChangeBody);

    @GET ("api/updates/")
    Call<List<Updates>> updates(@Header("Authorization") String token);

    @GET("api/last_update/")
    Call<Updates> last_update(@Header("Authorization") String token);

    @GET("api/actualizaciones_posteriores_a/{last_update}/")
    Call<List<Updates>> updates_posteriores(@Header("Authorization") String token, @Path("last_update") String last_update);

    @Multipart
    @POST("api/sincro_establecimiento/")
    Call<Establecimiento> sincroEstablecimiento(
            @Header("Authorization") String token,
            @Part MultipartBody.Part foto,
            @Part("nombre") RequestBody nombre,
            @Part("numero") RequestBody numero,
            @Part("posLatitud") RequestBody posLatitud,
            @Part("posLongitud") RequestBody posLongitud,
            @Part("regimenTenencia") RequestBody regimenTenencia,
            @Part("regimenOtros") RequestBody regimenOtros
    );

    @POST("api/sincro_encuestado/")
    Call<Encuestado> sincroEncuestado(@Header("Authorization") String token,@Body Encuestado encuestado);

    @POST("api/sincro_familia/")
    Call<Familia> sincroFamilia(@Header("Authorization") String Token, @Body Familia familia);

    @POST("api/sincro_agroquimico/")
    Call<Agroquimicos> sincroAgroquimico(@Header("Authorization") String Token, @Body Agroquimicos agroquimico);

    @POST("api/sincro_encuesta/")
    Call<Encuesta> sincroEncuesta(@Header("Authorization") String Token,@Body Encuesta encuesta);

    @GET("api/get_ids_by_transaccion/{transaccion}/")
    Call<IdsSincro> getIdsByTransaccion(@Header("Authorization") String token, @Path("transaccion") String transaccion);

    @POST("api/sincro_invernaculo/")
    Call<Invernaculo> sincroInvernaculo(@Header("Authorization") String token, @Body Invernaculo invernaculo);

    @POST("api/sincro_cultivo/")
    Call<Cultivo> sincroCultivo(@Header("Authorization") String token, @Body Cultivo cultivo);

    @POST("api/sincro_agroquimico_usado/")
    Call<AgroquimicoUsado> sincroAgroquimicoUsado(@Header("Authorization") String token, @Body AgroquimicoUsado agroquimicoUsado);

}
