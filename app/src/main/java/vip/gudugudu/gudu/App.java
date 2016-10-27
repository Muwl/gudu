package vip.gudugudu.gudu;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import vip.gudugudu.gudu.base.util.SpUtil;


/**
 * Created by baixiaokang on 16/4/23.
 */
public class App extends Application {
    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        SpUtil.init(this);


    }

    public static Context getAppContext() {
        return mApp;
    }

    public static Resources getAppResources() {
        return mApp.getResources();
    }

}
