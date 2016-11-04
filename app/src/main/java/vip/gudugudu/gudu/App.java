package vip.gudugudu.gudu;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

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
        PlatformConfig.setQQZone("101362016", "6367469bd6b322533edda35c02ff81f6");
        SpUtil.init(this);
        UMShareAPI.get(this);


    }

    public static Context getAppContext() {
        return mApp;
    }

    public static Resources getAppResources() {
        return mApp.getResources();
    }

}
