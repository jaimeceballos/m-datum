package mdatum.udc.com.m_datum.sincronizacion.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import mdatum.udc.com.m_datum.sincronizacion.UserToken;

/**
 * Created by jaime on 16/11/17.
 */

public class SessionPrefs {

    public static final String PREFS_NAME = "MDATUM_PREFS";
    public static final String PREF_USER_TOKEN = "PREF_USER_TOKEN";

    private final SharedPreferences mPrefs;

    private boolean mIsLoggedIn = false;

    private static SessionPrefs INSTANCE;

    public static SessionPrefs get(Context context){
        if (INSTANCE == null){
            INSTANCE = new SessionPrefs(context);
        }
        return INSTANCE;
    }

    private SessionPrefs(Context context){
        mPrefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREF_USER_TOKEN,null));

    }

    public boolean isLoggedIn(){
        return mIsLoggedIn;
    }

    public void saveToken(UserToken token){
        if(token!=null){
            SharedPreferences.Editor editor = mPrefs.edit();

            editor.putString(PREF_USER_TOKEN, token.getToken());

            editor.apply();

            mIsLoggedIn = true;
        }
    }


}
