package mdatum.udc.com.m_datum.sincronizacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by jaime on 16/11/17.
 */

public interface WebDatumApi {

    public static final String BASE_URL = "http://192.168.1.37:8000/";

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

}
