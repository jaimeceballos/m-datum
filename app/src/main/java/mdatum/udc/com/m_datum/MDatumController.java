package mdatum.udc.com.m_datum;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.URL;
import java.net.URLConnection;

import mdatum.udc.com.m_datum.database.DaoMaster;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.MDatumOpenHelper;
import mdatum.udc.com.m_datum.sincronizacion.WebDatumApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jaime on 15/10/17.
 */

public class MDatumController extends Application {
    private DaoSession mDaoSession;

    private Retrofit mRestAdapter;
    private WebDatumApi mWebDatumApi;

    @Override
    public void onCreate() {
        super.onCreate();

        mDaoSession = new DaoMaster(new MDatumOpenHelper(this, "mdatum.db").getWritableDb()).newSession();

        mRestAdapter = new Retrofit.Builder()
                .baseUrl(WebDatumApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //Crear conexion a la API de WebDatum
        mWebDatumApi = mRestAdapter.create(WebDatumApi.class);
    }

    public DaoSession getDaoSession (){
        return mDaoSession;
    }

    public WebDatumApi getApiSession(){
        return mWebDatumApi;
    }

    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnected();
    }


}
