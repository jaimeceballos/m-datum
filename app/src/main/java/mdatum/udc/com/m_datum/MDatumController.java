package mdatum.udc.com.m_datum;

import android.app.Application;

import mdatum.udc.com.m_datum.database.DaoMaster;
import mdatum.udc.com.m_datum.database.DaoSession;
import mdatum.udc.com.m_datum.database.MDatumOpenHelper;
/**
 * Created by jaime on 15/10/17.
 */

public class MDatumController extends Application {
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        mDaoSession = new DaoMaster(new MDatumOpenHelper(this, "control.db").getWritableDb()).newSession();

    }

    public DaoSession getDaoSession (){
        return mDaoSession;
    }
}
