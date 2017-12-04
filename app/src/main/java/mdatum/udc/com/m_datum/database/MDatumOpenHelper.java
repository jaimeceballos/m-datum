package mdatum.udc.com.m_datum.database;

import android.content.Context;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

/**
 * Created by jaime on 15/10/17.
 */

public class MDatumOpenHelper extends DaoMaster.OpenHelper {

    public MDatumOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion){
        super.onUpgrade(db, oldVersion, newVersion);

        Log.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
        switch (oldVersion) {
            case 1:
            case 2:


        }
    }
    @Override
    public void onCreate(Database db) {
        super.onCreate(db);

    }
}
