package mdatum.udc.com.m_datum.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import mdatum.udc.com.m_datum.sincronizacion.UserToken;

/**
 * Created by jaime on 16/11/17.
 */

public class SessionPrefs {

    public static final String PREFS_NAME               = "MDATUM_PREFS";
    public static final String PREF_USER_TOKEN          = "PREF_USER_TOKEN";
    public static final String PREF_LAST_UPDATE         = "PREF_LAST_UPDATE";
    public static final String PREF_LAST_SERVER_UPDATE  = "PREF_LAST_SERVER_UPDATE";
    public static final String PREF_USER_LOGED          = "PREF_USER_LOGED";

    private final SharedPreferences mPrefs;

    private boolean mIsLoggedIn = false;
    private int lastUpdate = 0;
    private int lastServerUpdates;
    private int userLoged;

    private static SessionPrefs INSTANCE;

    public static SessionPrefs get(Context context){
        if (INSTANCE == null){
            INSTANCE = new SessionPrefs(context);
        }
        return INSTANCE;
    }

    private SessionPrefs(Context context){
        mPrefs = context.getApplicationContext().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREF_USER_TOKEN,null));

        lastUpdate = mPrefs.getInt(PREF_LAST_UPDATE,0);

        lastServerUpdates = mPrefs.getInt(PREF_LAST_SERVER_UPDATE,0);

        userLoged = mPrefs.getInt(PREF_USER_LOGED,0);

    }

    public boolean isLoggedIn(){
        return mIsLoggedIn;
    }

    public void saveToken(UserToken token){
        if(token!=null){
            SharedPreferences.Editor editor = mPrefs.edit();

            editor.putString(PREF_USER_TOKEN, token.getKey());

            editor.apply();

            mIsLoggedIn = true;
        }
    }

    public void logOut(){
        mIsLoggedIn = false;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(PREF_USER_TOKEN, null);
        editor.putInt(PREF_USER_LOGED,0);
        editor.apply();
    }

    public int getLastUpdate(){
        return this.lastUpdate;
    }

    public void saveLastUpdate(int update){
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(PREF_LAST_UPDATE,update);
        editor.apply();
        lastUpdate = update;
    }

    public void setPrefLastServerUpdate(int lastServerUpdateUpdate){
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(PREF_LAST_SERVER_UPDATE,lastServerUpdateUpdate);
        editor.apply();
        lastServerUpdates = lastServerUpdateUpdate;
    }

    public int getLastServerUpdates() { return this.lastServerUpdates; }

    public void setUserLoged(int userId){
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(PREF_USER_LOGED,userId);
        editor.apply();
        userLoged = userId;
    }

    public int getUserLoged(){
        return this.userLoged;
    }

}
