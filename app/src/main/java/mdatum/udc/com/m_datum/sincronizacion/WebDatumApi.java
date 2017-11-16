package mdatum.udc.com.m_datum.sincronizacion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by jaime on 16/11/17.
 */

public interface WebDatumApi {

    public static final String BASE_URL = "http://192.168.1.41:8000/";

    @POST ("rest-auth/login/")
    Call<UserToken> login(@Body LoginBody loginBody);
}
